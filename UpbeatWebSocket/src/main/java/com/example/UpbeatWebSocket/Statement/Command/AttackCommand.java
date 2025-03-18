package com.example.UpbeatWebSocket.Statement.Command;

import com.example.UpbeatWebSocket.Expr.Expression;
import com.example.UpbeatWebSocket.Expr.SyntaxErrorExpr;
import com.example.UpbeatWebSocket.GameState.Player;
import com.example.UpbeatWebSocket.GameState.Region;
import com.example.UpbeatWebSocket.Statement.Statement;

public class AttackCommand implements Statement {
    String Direction ;
    Expression expression ;
    public AttackCommand(String Direction, Expression expression){
        this.Direction = Direction;
        this.expression = expression ;
    }

    @Override
    public StringBuilder prettyPrint(StringBuilder s) {
        s.append("shoot ");
        s.append(this.Direction);
        s.append(" ");
        s.append(this.expression);
        return s;
    }

    @Override
    public boolean execute(Player user) throws SyntaxErrorExpr {
        double cost = this.expression.evaluate(user);

        if(user.getBudget() < user.territory().getFee()) return true;
        user.subBudget(user.territory().getFee());

        if(user.getBudget() < cost) return true;
        user.subBudget(cost);

        Region target = user.getCityCrew().moveDirection(this.Direction);
        if(target.getOwner() == null) return true;
        target.subDeposit(cost);
        if(target.getDeposit() == 0 ) target.ClearRegion(user);
        return true;
    }
}
