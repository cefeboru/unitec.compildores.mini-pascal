/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores.targetcode;
import org.unitec.compiladores.Simbolo;
import org.unitec.compiladores.TablaSimbolos;
import org.unitec.compiladores.intermediatecode.Cuadruplo;
import org.unitec.compiladores.intermediatecode.TablaCuadruplos;

/**
 *
 * @author Cesar Bonilla
 */
public class TargetGenerator {
    
    TablaSimbolos ts = new TablaSimbolos();
    TablaCuadruplos tc = new TablaCuadruplos();
    CodigoFinalTabla cft = new CodigoFinalTabla();
    
    public TargetGenerator(TablaSimbolos TS, TablaCuadruplos TC) throws Exception{
        ts = TS;
        tc = TC;
        generateVariables();
        recorrerCuadruplos();
        cft.generateFile("MIPSFinalCode.s");
        
    }
    
    public void generateVariables(){
        for(Simbolo S : ts.Simbolos){
            if(S.getAmbito().equals("main") && S.isVariable()){
                String tipo = S.getTipo();
                switch(tipo){
                    case "integer":{
                        cft.addIntegerDataVariable(S.getId());
                        break;
                    }
                    case "char":{
                        cft.addCharDataVariable(S.getId());
                        break;
                    }
                    case "string":{
                        // T_T Esta malo
                        cft.addIntegerDataVariable(S.getId());
                        break;
                    }
                }
            }
        }
    }
    
    public void recorrerCuadruplos() throws Exception{
        String ambitoActual = "";
        for (int i = 0; i < tc.getSize(); i++) {
            Cuadruplo C = tc.item(i);
            String op = C.getOperacion();
            switch(op){
                case "WRITE":{
                    generateWrite(C, ambitoActual);
                    break;
                }
                case "LABEL":{
                    ambitoActual = C.getArg1();
                    cft.addLabel(C.getArg1());
                    break;
                }
            }
        }
        cft.generateEndOfProgram();
    }
    
    public void generateWrite(Cuadruplo C, String ambitoActual) throws Exception{
        String param = C.getArg1();
        if(param.contains("'")){
            String messageName = cft.addDataMessage(param);
            cft.addWriteStatementLiteral(messageName);
        } else {
            Simbolo S = ts.getVariable(param, ambitoActual);
            if(S == null && !ambitoActual.equals("main")){
                S = ts.getVariable(param, "main");
            }
            String tipo = S.getTipo();
            if(tipo.equals("string") || tipo.equals("char")){
                cft.addWriteStringStatement(param);
            } else {
                cft.addWriteStatement(param);
            }
        }
    }
    
    public void printTargetCode(){
        cft.print();
    }
}
