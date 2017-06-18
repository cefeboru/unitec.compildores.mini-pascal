/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores.targetcode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.unitec.compiladores.TablaSimbolos;
import org.unitec.compiladores.intermediatecode.TablaCuadruplos;

/**
 *
 * @author Cesar Bonilla
 */
public class CodigoFinalTabla {
    List<String> data = new ArrayList();
    List<String> text = new ArrayList();
    ArrayList<Integer> pila = new ArrayList();
    
    public CodigoFinalTabla(){
        data.add(".data");
        text.add(".text");
        text.add(".globl main");
    }
    
    int messageCount = 0;
    
    public String addDataMessage(String message){
        String varName = "_msg" + ++messageCount;
        String varType = "asciiz";
        String varValue = message.replaceAll("'", "\"");
        String row = " %s:\t.%s %s";
        data.add(String.format(row, varName, varType, varValue));
        return varName;
    }
    
    public void addIntegerDataVariable(String varName){
        varName = "_" + varName;
        String varType = "word";
        String varValue = "0";
        String row = " %s:\t.%s %s";
        data.add(String.format(row, varName, varType, varValue));
    }
    
    public void addCharDataVariable(String varName){
        varName = "_" + varName;
        String varType = "byte";
        String varValue = "' '";
        String row = " %s:\t.%s %s";
        data.add(String.format(row, varName, varType, varValue));
    }
    
    public void addWriteStringStatement(String variableName){
        String row = "%s %s, %s";
        text.add(String.format(row, "li", "$v0", "4"));
        text.add(String.format(row, "lw", "$a0", "_"+variableName));
        text.add("syscall");
    }
    
    public void addLabel(String arg){
        text.add(arg + ":");
    }
            
    public void print(){
        for (String dataRow : data) {
            System.out.println(dataRow);
        }
    }

    public void generateFile(String FileName) throws IOException{
        Path file = Paths.get(FileName);
        data.addAll(data.size(), text);
        Files.write(file, data, Charset.forName("UTF-8"));
    }
    
    
    void addWriteStatementLiteral(String messageName) {
        String row = "%s %s, %s";
        text.add(String.format(row, "li", "$v0", "4"));
        text.add(String.format(row, "la", "$a0", messageName));
        text.add("syscall");
    }

    void addWriteStatement(String variableName) {
        String row = "%s %s, %s";
        text.add(String.format(row, "li", "$v0", "1"));
        text.add(String.format(row, "lw", "$a0", "_"+variableName));
        text.add("syscall");
    }

    void generateEndOfProgram() {
        text.add("li $v0, 10");
        text.add("syscall");
    }
}
