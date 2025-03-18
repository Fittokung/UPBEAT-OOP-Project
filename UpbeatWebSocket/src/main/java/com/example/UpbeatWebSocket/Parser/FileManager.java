package com.example.UpbeatWebSocket.Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileManager {
    public ArrayList<String> FileReader(String path){
        Path file = Paths.get(path);  // path string
        ArrayList<String> arrayList = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                if(line.isEmpty()){
                    continue;
                }
                arrayList.add(line);
            }
            return arrayList;
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
            return null ;
        }
    }
    public  void FileWriter(String path , ArrayList<String> answer){
        Path file = Paths.get(path);  // path string
        Charset charset = Charset.forName("UTF-8");
        boolean del = true ;
        for (String ans : answer) {
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset, StandardOpenOption.APPEND)) {
                if(del){
                    try(BufferedWriter writer1 = Files.newBufferedWriter(file, charset)) {
                        writer1.write("");
                        del = false ;
                    }
                }
                writer.write(ans + "\n");
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        } ;
    }
}
