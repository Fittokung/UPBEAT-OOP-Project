package com.example.UpbeatWebSocket.Expr;

import com.example.UpbeatWebSocket.GameState.Player;

public interface Expression extends Node {
    double evaluate(Player user) throws SyntaxErrorExpr;
}
