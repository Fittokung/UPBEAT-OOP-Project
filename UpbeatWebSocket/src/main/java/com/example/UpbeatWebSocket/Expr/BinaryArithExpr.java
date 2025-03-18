package com.example.UpbeatWebSocket.Expr;

import com.example.UpbeatWebSocket.GameState.*;

public class  BinaryArithExpr implements Expression {
    Expression left ;
    Expression right ;
    String op ;
    public BinaryArithExpr(Expression left , String op , Expression right){
        this.left = left ;
        this.op = op;
        this.right = right ;
    }


    @Override
    public double evaluate(Player user) throws SyntaxErrorExpr {
        double lv = left.evaluate(user);
        double rv = right.evaluate(user);
        double result = 0;

        switch (op) {
            case "+":
                result = lv + rv;
                break;
            case "-":
                result = lv - rv;
                break;
            case "*":
                result = lv * rv;
                break;
            case "/":
                if (rv == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = lv / rv;
                break;
            case "%":
                result = lv % rv;
                break;
            case "^":
                result = Math.pow(lv, rv);
                break;
            default:
                throw new SyntaxErrorExpr("Operator not found: " + op);
        }

        // Absolute value if result is zero
        if (result == 0) {
            result = Math.abs(result);
        }

        return result;
    }


    @Override
    public StringBuilder prettyPrint(StringBuilder s) {
        s.append("(");
        left.prettyPrint(s);
        s.append(op);
        right.prettyPrint(s);
        s.append(")");
        return s ;
    }
}
