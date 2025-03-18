package com.example.UpbeatWebSocket;

import com.example.UpbeatWebSocket.GameState.Player;
import com.example.UpbeatWebSocket.GameState.Region;
import com.example.UpbeatWebSocket.GameState.Territory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Getter
public class PlayerRepository {
    @Getter
    private List<PlayerAPI> players = new ArrayList<>() ;

    @Getter
    private List<ConfigFile> configFile = new ArrayList<>();

    @Getter
    @Setter
    private Territory territory = new Territory();

    public boolean createPlayer(PlayerAPI player) {
        if (players.contains(player)){
            return false;
        }
        players.add(player);
        return true;
    }


    public void setConfigFile(ConfigFile config){
        configFile.clear();
        configFile.add(config);
    }
    public List<ConfigFile> getConfigFile(){
        return configFile;
    }
    public void addTerritory(Territory territory){
        for (PlayerAPI player : players){
            player.setTerritory(territory);
        }
    }



}
