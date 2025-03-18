import { Client } from "@stomp/stompjs";

let client = null;

const WebSocketService = {
  initializeWebSocket: async () => {
    if (!client) {
      client = new Client({
        brokerURL: "http://localhost:8083/ws",

        onConnect: async () => {
          console.log("Connected");

          await client.subscribe("/topic/ws", (message) => {
            const body = JSON.parse(message.body);
            console.log(message);
          });

          await client.subscribe("/Play/player", (message) => {
            console.log(message);
          });
        },
      });
    }
  },

  getClient: () => client,
};

export default WebSocketService;