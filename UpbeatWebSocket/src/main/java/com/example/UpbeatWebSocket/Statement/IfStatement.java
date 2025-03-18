package com.example.UpbeatWebSocket.Statement;

import com.example.UpbeatWebSocket.Expr.Expression;
import com.example.UpbeatWebSocket.Expr.SyntaxErrorExpr;
import com.example.UpbeatWebSocket.GameState.Player;

public class IfStatement implements Statement{
    private final Expression expression;
    private final Statement thenStatement;
    private final Statement elseStatement;
    public IfStatement(Expression expression , Statement thenStatement , Statement elseStatement){
        this.expression = expression;
        this.thenStatement = thenStatement ;
        this.elseStatement = elseStatement ;
    }
    @Override
    public boolean execute(Player user) throws SyntaxErrorExpr {
        if(this.expression.evaluate(user) > 0){
            return this.thenStatement.execute(user);
        }else{
            return this.elseStatement.execute(user);
        }
    }

    @Override
    public StringBuilder prettyPrint(StringBuilder s) {
        s.append("if (");
        this.expression.prettyPrint(s);
        s.append(") then ");
        this.thenStatement.prettyPrint(s);
        s.append(" else ");
        this.elseStatement.prettyPrint(s);
        return s;
    }
}
