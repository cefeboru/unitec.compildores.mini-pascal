/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores.intermediatecode;

import java.util.ArrayList;

/**
 *
 * @author Cesar Bonilla
 */
public class TablaCuadruplos {
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String TIMES = "*";
    public static final String DIV = "/";
    public static final String GOTO = "GOTO";
    public static final String LABEL = "LABEL";
    public static final String IF = "IF";
    
    ArrayList<Cuadruplo> cuadruplos = new ArrayList<>();
    
    /**
    * Used for generating full operations like Arithmetic Operations
    *
    * @param op Operation that will generate
    * @param arg1 Cuadruple first argument 
    * @param arg2 Cuadruple second argument
    * @param resultado Where the result will be stored
    */
    public void GEN(String op, String arg1, String arg2, String resultado){
        cuadruplos.add(new Cuadruplo(op, arg1, arg2, resultado));
        
    }
    
    /**
    * Used for generating Assignments and one argument operations.
    *
    * @param op Operation that will generate
    * @param arg1 Cuadruple first argument 
    * @param resultado Where the result will be stored
    */
    public void GEN(String op, String arg1, String resultado){
        cuadruplos.add(new Cuadruplo(op, arg1, resultado));
    }
    
    /**
    * Generates simple instructions as labels.
    *
    * @param op Operation that will be generated
    * @param resultado Where the result will be stored
    */
    public void GEN(String op, String resultado){
        cuadruplos.add(new Cuadruplo(op, resultado));
    }
    
    public void print(){
        for (int i = 0; i < cuadruplos.size(); i++) {
            Cuadruplo C = cuadruplos.get(i);
            System.out.println(C);
        }
    }
}
