package com.example.UpbeatWebSocket.Statement;

import com.example.UpbeatWebSocket.Expr.Expression;
import com.example.UpbeatWebSocket.Expr.SyntaxErrorExpr;
import com.example.UpbeatWebSocket.GameState.Player;

public class WhileStatement implements Statement{
    private final Expression expression ;
    private final Statement statement ;
    public  WhileStatement(Expression expression , Statement statement){
        this.expression = expression ;
        this.statement = statement ;
    }
    @Override
    public StringBuilder prettyPrint(StringBuilder s) {
        s.append("while ( ");
        expression.prettyPrint(s);
        s.append(" ) ");
        statement.prettyPrint(s);
        return s ;
    }

    @Override
    public boolean execute(Player user) throws SyntaxErrorExpr {
        double e = this.expression.evaluate(user);
        for (int counter = 0; counter < 10000 && e > 0; counter++){
            if(!this.statement.execute(user)){
                return false;
            }
            e = this.expression.evaluate(user);
        }
        return true;
    }
}
