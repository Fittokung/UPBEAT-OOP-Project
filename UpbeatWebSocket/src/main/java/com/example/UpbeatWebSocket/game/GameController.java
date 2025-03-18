package com.example.UpbeatWebSocket.game;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    // Player Connect
    @MessageMapping("/room.addUser")
    @SendTo("/topic/public")
    public PlayerMessage addUser(PlayerMessage message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        PlayerMessage.addPeople();
        var chatMessage1 = PlayerMessage.builder()
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .sender(message.getSender())
                .type(MessageType.JOIN)
                .cound(PlayerMessage.getPeople())
                .build();
        System.out.println(PlayerMessage.getPeople());
        return chatMessage1;
    }
    //Send Map
    @MessageMapping("/room.sendMap")
    @SendTo("/topic/public")
    public PlayerMessage sendMessage(PlayerMessage message) {
        return message;
    }

}
