package com.example.UpbeatWebSocket.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenizerConfig{
    //Chat GPT
    List<String> line  ;
    public int pos;
    public TokenizerConfig(List<String> text){
        pos = 0 ;
        List<String> temp = new ArrayList<>();
        for (String inputString : text) {
            List<String> tokens1 =tokenize(inputString);
            temp.addAll(tokens1);
        }
        this.line = temp;
    }
    public TokenizerConfig(){
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
        if (pos == line.size()) return false;
        return line.get(pos) != null; }
    public String peek() throws SyntaxErrorException {
        checkNextToken();
        return line.get(pos);
    }
    public void checkNextToken() throws SyntaxErrorException {
        if (!hasNextToken()) throw new SyntaxErrorException("no more tokens");
    }
    public String consume() throws SyntaxErrorException {
        checkNextToken();
        String result = line.get(pos);
//        if (pos+1 <= line.size()){
//            pos++;
//        }
        pos++;
        return result;
    }
    public boolean peek(String s) throws SyntaxErrorException {
        if (!hasNextToken()) return false;
        return peek().equals(s);
    }
    public void consume(String s) throws SyntaxErrorException {
        if (peek(s))
            consume();
        else
            throw new SyntaxErrorException(" expected");
    }


}
