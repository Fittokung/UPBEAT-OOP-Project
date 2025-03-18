package com.example.UpbeatWebSocket.Expr;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenizerOneLine {
    //Chat GPT
    List<String> line  ;


    public int pos;
    public TokenizerOneLine(String text){
        pos = 0 ;
        List<String> temp = tokenize(text);
//        for (String inputString : text) {
//            List<String> tokens1 =tokenize(inputString);
//            temp.addAll(tokens1);
//        }
        this.line = temp;
    }
    public TokenizerOneLine(){

        pos = 0 ;
    }
    private static final Pattern pattern = Pattern.compile(
            "#[^\\n]*"+
                    "|\\b(?:if|then|else|while|end|collect|invest|move|shoot|random|budget|deposit|opponent|nearby)\\b" + // Keywords
                    "|[-+*/%^]" + // Arithmetic operators
                    "|[-+]?\\d*\\.\\d+|\\d+" + // Numbers
                    "|[\\(\\)\\{\\}\\[\\];,]" + // Punctuation
                    "|=" + // Assignment operator
                    "|\\b(?:upleft|downleft|downright|upright|up|down|left|right|done)\\b" + // Directions
                    "|\\b(?:[a-zA-Z][a-zA-Z0-9]*|deposit|budget|opponentLoc|cost|dir)\\b" + // Variables
                    "|\\b(?:nearby)\\b" + // Nearby keyword
                    "|\\/\\/.*\\n" + // C++-style comments
                    "|\\/\\/.*$" // C++-style comments
    );
    public static List<String> tokenize(String inputString) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = pattern.matcher(inputString);
        while (matcher.find()) {
            if (Pattern.matches("#[^\\n]*", matcher.group().trim())){return tokens;}
            else {
                tokens.add(matcher.group().trim());
            }
        }
        return tokens;
    }
    public boolean hasNextToken() {
        return line.get(pos) != null; }
    public String peek() throws SyntaxErrorExpr {
        checkNextToken();
        return line.get(pos);
    }
    public void checkNextToken() throws SyntaxErrorExpr {
        if (!hasNextToken()) throw new SyntaxErrorExpr("no more tokens");
    }
    public String consume() throws SyntaxErrorExpr {
        checkNextToken();
        String result = line.get(pos);
        if (pos+1 <= line.size()){
            pos++;
        }
        return result;
    }
    public boolean peek(String s) throws SyntaxErrorExpr {
        if (!hasNextToken()) return false;
        return peek().equals(s);
    }
    public void consume(String s) throws SyntaxErrorExpr {
        if (peek(s))
            consume();
        else
            throw new SyntaxErrorExpr(" expected");
    }


}
