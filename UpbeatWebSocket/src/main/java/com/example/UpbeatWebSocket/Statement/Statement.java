package com.example.UpbeatWebSocket.Statement;

import com.example.UpbeatWebSocket.Expr.Node;
import com.example.UpbeatWebSocket.Expr.SyntaxErrorExpr;
import com.example.UpbeatWebSocket.GameState.Player;


public interface Statement extends Node {
    boolean execute(Player user) throws SyntaxErrorExpr;
}
