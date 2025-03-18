package com.example.UpbeatWebSocket.Expr;

import com.example.UpbeatWebSocket.GameState.Player;

public class DoubleLit implements Expression {
    double val ;
    public DoubleLit(double val){
        this.val = val ;
    }

    @Override
    public StringBuilder prettyPrint(StringBuilder s) {
        s.append(val);
        return s ;
    }
    @Override
    public double evaluate(Player user) throws SyntaxErrorExpr {
        return val ;
    }
}
