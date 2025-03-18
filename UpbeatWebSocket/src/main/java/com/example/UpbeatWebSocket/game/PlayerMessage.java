package com.example.UpbeatWebSocket.game;

import lombok.Builder;
import lombok.Getter;

import java.awt.*;
@Getter
@Builder
public class PlayerMessage {
    private String content;
    private String timestamp;
    private String sender;
    private MessageType type;
    private static int people ;
    private int cound  ;

    public static void addPeople() {
        people++ ;
        System.out.println(people);

    }
    public static void minusPeople() {
        people-- ;
    }
    public static  Integer getPeople() {
        return people;
    }
}
