/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores.jflex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cbonilla
 */


public class runLexer {
    
    static Logger logger = Logger.getLogger(runLexer.class.getName());
   
    public static void main(String[] vargs){
        PascalFlexer scanner;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/input.txt"));
            scanner = new PascalFlexer(br);
            scanner.yylex();
        } catch (FileNotFoundException  ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
    }
}
