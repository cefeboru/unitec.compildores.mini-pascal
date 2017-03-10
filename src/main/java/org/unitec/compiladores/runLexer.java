/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import java_cup.runtime.XMLElement;

/**
 *
 * @author cbonilla
 */


public class runLexer {
    
    static Logger logger = Logger.getLogger(runLexer.class.getName());
   
    public static void main(String[] vargs) throws Exception{
        PascalFlexer scanner;
        
        ComplexSymbolFactory csf = new ComplexSymbolFactory();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/input.txt"));
            scanner = new PascalFlexer(br);
            Symbol sb = scanner.next_token();
            
            while(sb.sym != sym.EOF) {
                System.out.println(scanner.yytext());
                sb = scanner.next_token();
            }
            
            /*Parser parser = new Parser(scanner,csf);
            
            XMLElement e = (XMLElement)parser.parse().value;
            System.out.println(e.toString());*/
            
        } catch (FileNotFoundException  ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
    }
}

