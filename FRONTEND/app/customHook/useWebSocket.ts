import Stomp from "stompjs";
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-expect-error
import SockJS from "sockjs-client/dist/sockjs";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import {setIsConnected, appendMessage,setStompClient} from "../store/Slices/webSocketSlice";
import { selectWebSocket } from "../store/Slices/webSocketSlice";

function useWebSocket(){
    const dispatch = useAppDispatch()
    const webSocket = useAppSelector(selectWebSocket)

    function connect(username : string){
        try {
            const socket: WebSocket = new SockJS(`http://localhost:8083/ws`);
            const stompClient: Stomp.Client = Stomp.over(socket);
            // eslint-disable-next-line @typescript-eslint/ban-ts-comment
            // @ts-expect-error
            stompClient.connect({}, () =>onConnected(stompClient,username), onError);
        } catch (e) {
            console.log(e);
        }
    }

    function sendMessage(message : string, username : string){
        if (webSocket.stompClient && webSocket.stompClient.connected) {
            const chatMessage = {
                sender: username,
                timestamp: new Date().toLocaleTimeString(),
            };
            webSocket.stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        }
    }

    const onConnected = (stompClient : Stomp.Client, username : string) => {
        stompClient.subscribe('/topic/public', onMessageReceived);
        stompClient.send("/app/chat.addUser", {}, JSON.stringify({ sender: username, type: 'JOIN', timestamp: new Date().toLocaleTimeString() }));
        dispatch(setIsConnected(true))
        dispatch(setStompClient(stompClient))
    }
    const onMessageReceived = (payload : Stomp.Message) => {
        dispatch(appendMessage(JSON.parse(payload.body)))
    }
    return {connect,sendMessage}
}

export default useWebSocket;

const onError = (err: Stomp.Message) => {
    console.log(err);
}