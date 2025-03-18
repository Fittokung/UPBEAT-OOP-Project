package com.example.UpbeatWebSocket;

import com.example.UpbeatWebSocket.GameState.Territory;
import com.example.UpbeatWebSocket.game.MessageType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayerAPI {
    private String name;
    private String plan;
    private int money;
    Territory territory;

}
