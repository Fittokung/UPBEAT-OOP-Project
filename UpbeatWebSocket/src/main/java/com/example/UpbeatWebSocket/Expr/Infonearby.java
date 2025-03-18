package com.example.UpbeatWebSocket.Expr;

import com.example.UpbeatWebSocket.GameState.Player;
import com.example.UpbeatWebSocket.GameState.Region;

public class Infonearby implements  Expression{
    String Direction ;
    String command ;
    public  Infonearby(String command , String Direction)   {
        this.command = command ;
        this.Direction = Direction ;
    }
    @Override
    public double evaluate(Player user) throws SyntaxErrorExpr {
        Region currrent = user.getCityCrew();
        double distance = 0 ;
        while(currrent.moveDirection(this.Direction) != null ){
            currrent = currrent.moveDirection(this.Direction);
            if (currrent == null) return 0;
            distance++ ;
            if (currrent.getOwner() != user && currrent.getOwner() != null){
                return 100 * distance + currrent.getDeposit();
            }
        }
        return  0 ;
    }

    @Override
    public StringBuilder prettyPrint(StringBuilder s) {
        s.append("nearby");
        s.append(this.Direction);
        return  s ;
    }
}
