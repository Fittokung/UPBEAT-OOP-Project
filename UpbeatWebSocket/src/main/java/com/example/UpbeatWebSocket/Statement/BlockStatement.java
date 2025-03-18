package com.example.UpbeatWebSocket.Statement;

import com.example.UpbeatWebSocket.Expr.SyntaxErrorExpr;
import com.example.UpbeatWebSocket.GameState.Player;
import java.util.List;
import java.util.ArrayList;

public class BlockStatement implements Statement{
    private List<Statement> statementList;
    public BlockStatement(List<Statement> list){
        this.statementList = new ArrayList<>(list);
    }

    public StringBuilder prettyPrint(StringBuilder s) {
        s.append("{\n");
        for (Statement statement : this.statementList){
            statement.prettyPrint(s);
            s.append("\n");
        }
        s.append("}");
        return s;
    }

    @Override
    public boolean execute(Player user) throws SyntaxErrorExpr {
        for (Statement statement : this.statementList){
            if(!statement.execute(user)) {
                return false;
            }
        }
        return true;
    }
}
