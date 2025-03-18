package com.example.UpbeatWebSocket.Expr;

import com.example.UpbeatWebSocket.GameState.Player;

import java.util.Random;

public class SpecialVaribles implements Expression {
    String variables;
    public SpecialVaribles(String varibles){
        this.variables = varibles ;
    }
    @Override
    public double evaluate(Player user) throws SyntaxErrorExpr {
        if (variables.equals("rows")){return user.territory().getTerritory_row() ;}
        if (variables.equals("cols")){return user.territory().getTerritory_col() ;}
        if (variables.equals("currow")){return user.getCityCrew().getRow();}
        if (variables.equals("curcol")){return  user.getCityCrew().getCol();}
        if (variables.equals("budget")){return  user.getBudget();}
        if (variables.equals("deposit")){return user.getCityCrew().getDeposit() ;}
        if (variables.equals("int")){return  user.getCityCrew().getInterestRate() ;}
        if (variables.equals("maxdeposit")){return user.territory().getMax_dep() ;}
        if (variables.equals("random")){
            Random rand = new Random();
            return rand.nextInt(1000);
        }
        return 0;
    }

    @Override
    public StringBuilder prettyPrint(StringBuilder s) {
        return  s.append(variables) ;
    }
}
