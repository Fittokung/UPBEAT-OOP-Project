package com.example.UpbeatWebSocket.Expr;

import com.example.UpbeatWebSocket.GameState.Player;
import com.example.UpbeatWebSocket.GameState.Region;

public class InfoOpponent implements  Expression{
    String command ;
    public  InfoOpponent(String command){this.command = command ;}
    @Override
    public double evaluate(Player user) throws SyntaxErrorExpr {
        String[] Direction = {"up","upright","downright" , "down" , "downleft","upleft"};
        Region current = user.getCityCrew();
        int minDistance = Integer.MAX_VALUE ;
        int Maxbound = user.territory().getTerritory_col() * user.territory().getTerritory_row();
        for (int i = 0; i < 6; i++) {
            for (int distance = 1; distance <= Maxbound ; distance++) {
                current = current.moveDirection(Direction[i]);
                if (current == null) break;
                if (current.getOwner() != null && current.getOwner() != user){
                    int temp = 10 * distance + i + 1;
                    if(temp < minDistance) {
                        minDistance = temp ;
                        Maxbound = distance ;
                    }
                }
            }
            current = user.getCityCrew();
        }
        if (minDistance == Integer.MAX_VALUE){
            return 0 ;
        } else{
            return minDistance;
        }
    }

    @Override
    public StringBuilder prettyPrint(StringBuilder s) {
        s.append("opponent");
        return s ;
    }
}
