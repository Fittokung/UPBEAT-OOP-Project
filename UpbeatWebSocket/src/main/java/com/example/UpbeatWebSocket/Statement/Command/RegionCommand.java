package com.example.UpbeatWebSocket.Statement.Command;

import com.example.UpbeatWebSocket.Expr.Expression;
import com.example.UpbeatWebSocket.Expr.SyntaxErrorExpr;
import com.example.UpbeatWebSocket.GameState.Player;
import com.example.UpbeatWebSocket.GameState.Region;
import com.example.UpbeatWebSocket.Statement.Statement;

public class RegionCommand implements Statement {
    private Expression expression ;
    private  String action;
    public RegionCommand(String action, Expression expression){
        this.action = action ;
        this.expression = expression ;
    }
    @Override
    public StringBuilder prettyPrint(StringBuilder s) {
        return s;
    }

    @Override
    public boolean execute(Player user) throws SyntaxErrorExpr {
        Region region = user.getCityCrew();
        double fee = user.territory().getFee();
        if (this.action.equals("invest")){
            double amount = expression.evaluate(user);
            user.subBudget(fee);
            if(user.getBudget() < amount) {
                return true;
            } else {
                region.setOwner(user);
                region.addDeposit(amount);
                user.subBudget(amount);
                user.addRegion(region);
                return true;
            }
            // collect
        }else {
            double collect = expression.evaluate(user);
            if (user.getBudget() < fee){
                return  false ;
            }
            user.subBudget(fee);
            if(region.getDeposit() < collect) {
                return true;
            } else {
                region.subDeposit(collect);
                user.addBudget(collect);
                if (region.getDeposit() == 0 ) region.ClearRegion(user) ;
            }
        }
        return true;
    }
}