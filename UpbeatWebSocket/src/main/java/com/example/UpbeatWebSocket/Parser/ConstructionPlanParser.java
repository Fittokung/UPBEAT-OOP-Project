package com.example.UpbeatWebSocket.Parser;

import java.util.*;
import java.util.regex.Pattern;
import com.example.UpbeatWebSocket.Expr.*;
import com.example.UpbeatWebSocket.Statement.*;
import com.example.UpbeatWebSocket.Statement.Command.*;
//import com.example.UpbeatWebSocket.Statement.Command.AttackCommand;
//import com.example.UpbeatWebSocket.Statement.Command.MoveCommand;
//import com.example.UpbeatWebSocket.Statement.Command.RegionCommand;

import javax.swing.plaf.nimbus.State;

public class ConstructionPlanParser {
    private final Tokenizer line ;
    public ConstructionPlanParser(List<String> file){
        line = new Tokenizer(file);
    }
    public List<Statement> parse() throws SyntaxErrorException {
        List<Statement> plan = new ArrayList<>();
        while (this.line.line.size() != line.pos){
            plan.add(Statement());
        }
        return  plan ;
    }



    public Statement Statement() throws SyntaxErrorException {
        if (line.peek("if")) {
            line.consume("if");
            return  IfStatement();
        } else if (line.peek("while")) {
            line.consume("while");
             return  WhileStatement();
        } else if (line.peek("{")) {
            return BlockStatement();
        } else {
            return Command();
        }
    }
    public Statement Command() throws SyntaxErrorException {
        if (line.peek().matches("\\b(?:done|relocate|move|invest|collect|shoot)\\b")){
            return ActionCommand();
        }else if (matchIdentifier()) {
            return AssignmentStatement();
        }
        throw new SyntaxErrorException("Next token should be Command word ex done , relocate , invest , collect , or Idenrifier");
    }
    public Statement AssignmentStatement() throws SyntaxErrorException {
        Identifier v = new Identifier(line.peek());
        line.consume();
        line.consume("=");
        Expression expression = Expression();
        return new AssignmentStatement( v ,expression ) ;
    }
    public Statement ActionCommand() throws SyntaxErrorException {
        Statement action ;
        if(line.peek("done")) {
            action =  new ActionCommand(line.peek());
            line.consume("done");
            return action ;
        }
        if(line.peek("relocate")){
            action =  new ActionCommand(line.peek());
            line.consume("relocate");
            return action ;
        }
        if (line.peek("move")){
            action = MoveCommand();
            return action ;
        }
        if(line.peek("invest")){
            action = RegionCommand();
            return action ;
        }
        if(line.peek("collect")){
            action = RegionCommand();
            return action ;
        }
        if(line.peek("shoot")){
            action = AttackCommand();
            return action ;
        }else {
            throw new SyntaxErrorException("Invalid command");
        }

    }
    public Statement MoveCommand() throws SyntaxErrorException {
        String command = line.peek();
        line.consume("move");
        String Direction = Direction();
        return  new MoveCommand(command,Direction);
    }
    public Statement RegionCommand() throws SyntaxErrorException {
        if(line.peek("invest")){
            String command = line.peek();
            line.consume("invest");
            Expression term = Expression();
            return new RegionCommand(command , term);
        }else if(line.peek("collect"))
        {
            String command = line.peek();
            line.consume("collect");
            Expression term = Expression();
            return new RegionCommand(command, term);
        }
        throw new SyntaxErrorException("Invalid command");
    }
    public Statement AttackCommand() throws SyntaxErrorException {
        if(line.peek("shoot")){
            line.consume("shoot");
            String direction = Direction();
            Expression expresstion = Expression();
            return new AttackCommand(direction , expresstion);
        }else {
            throw new SyntaxErrorException("Invalid command");
        }
    }
    public String Direction() throws SyntaxErrorException {
        String[] validDirections = {"up", "down", "upleft", "upright", "downleft", "downright" };
        String token = line.peek();
        if (Arrays.asList(validDirections).contains(token)) {
            line.consume();
            return token;
        } else {
            throw new RuntimeException("Invalid direction: " + token);
        }
    }
    public BlockStatement BlockStatement() throws SyntaxErrorException {
        List<Statement> state = new ArrayList<>();
        line.consume("{");
        while (!line.peek().equals("}")) {
            state.add(Statement()) ;
        }
        if (line.peek("}")){
            line.consume("}");
        }
        return new BlockStatement(state) ;
    }
    public IfStatement IfStatement() throws SyntaxErrorException {
        line.consume("(");
        Expression condition = Expression();
        line.consume(")");
        line.consume("then");
        Statement stateAfterThen = Statement();
        line.consume("else");
        Statement stateAfterElse = Statement();
        return new IfStatement(condition,stateAfterThen , stateAfterElse) ;
    }
    public WhileStatement WhileStatement() throws SyntaxErrorException {
        line.consume("(");
        Expression condition = Expression();
        line.consume(")");
        Statement state = Statement();
        return new WhileStatement(condition , state) ;
    }
    public Expression Expression() throws SyntaxErrorException {
        Expression x = Term();
        if(line.peek("+")) {
            line.consume("+");
            return new BinaryArithExpr(x,"+",Expression());
        }
        if(line.peek("-")){
            line.consume("-");
            return new BinaryArithExpr(x,"-",Expression());
        }
        return  x ;
    }
    public Expression Term () throws SyntaxErrorException {
        Expression x = Factor();
        if(line.peek("*")){
            line.consume("*");
            x = new BinaryArithExpr(x, "*" , Term());
        }
        if(line.peek("/")){
            line.consume("/");
            x = new BinaryArithExpr(x, "/" , Term());
        }
        if(line.peek("%")){
            line.consume("%");
            x = new BinaryArithExpr(x, "%" , Term());
        }
        return  x  ;
    }
    public Expression Factor () throws SyntaxErrorException {
        Expression x = Power();
        if (line.peek("^")) {
            line.consume("^");
            return new BinaryArithExpr(x,"^",Factor());
        }
        return x ;
    }
    public Expression Power() throws SyntaxErrorException {
        Expression expr = null;
        if (isSpecialVariables(line.peek())){
            expr = new SpecialVaribles(line.peek());
            line.consume();
            return expr ;
        }else if (line.peek("nearby") || line.peek("opponent")){
            return InfoExpression();
        }else if (line.peek("(")) {
            line.consume("(");
            expr = Expression();
            line.consume(")");
        }else if (matchIdentifier()){
            expr = new Identifier(line.peek());
            line.consume();
            return expr;
        }else if (matchNumber()) {
            expr = new DoubleLit(Double.parseDouble(line.peek()));
            line.consume();
            return expr;
        }
        return expr ;

    }
    public Expression InfoExpression() throws SyntaxErrorException {
        if (line.peek("nearby")) {
            String command = line.peek();
            line.consume("nearby");
            String direction = Direction();
            return new Infonearby(command , direction);
        } else if(line.peek("opponent")){
            String command = line.peek();
            line.consume("opponent");
            return  new InfoOpponent(command);
        }else {
            throw new RuntimeException("Invalid InfoExpression");
        }
    }


    private boolean matchIdentifier() throws SyntaxErrorException {
        if (!isReservedWord(line.peek())){
            return Pattern.matches("[a-zA-Z][a-zA-Z0-9]*", line.peek());
        }else {
            return false ;
        }

    }

    private boolean matchNumber() {
        try {
            long number = Long.parseLong(line.peek());
            return number >= 0;
        } catch (NumberFormatException | SyntaxErrorException e) {
            return false;
        }

    }
    private boolean isReservedWord(String word) {
        List<String> reservedWords = Arrays.asList(
                "collect", "done", "down", "downleft", "downright", "else", "if", "invest",
                "move", "nearby", "opponent", "relocate", "shoot", "then", "up", "upleft",
                "upright", "while"
        );
        return  reservedWords.contains(word);
    }
    private boolean isSpecialVariables(String word){
        List<String> reservedWords = Arrays.asList(
                "rows", "cols","currow", "curcol",
                "budget", "deposit", "int" , "maxdeposit" ,"random"
        );
        return reservedWords.contains(word);
    }
    private boolean isInformationWord(String word){
        List<String> reservedWords = Arrays.asList(
                "opponent","nearby"
        );
        return reservedWords.contains(word);
    }




}
