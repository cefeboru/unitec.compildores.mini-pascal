/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores.targetcode;

import java.util.HashMap;
import java.util.Map;
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
    HashMap<Integer, String> labelControl = new HashMap();
    HashMap<String, Boolean> tempVivos = new HashMap();
    HashMap<String, String> tempEquivalente = new HashMap();
    int labelCount = 0;

    public TargetGenerator(TablaSimbolos TS, TablaCuadruplos TC) throws Exception {
        ts = TS;
        tc = TC;
        for (int i = 0; i <= 9; i++) {
            String temp = "$t" + i;
            tempVivos.put(temp, false);
        }
    }

    public void generateFinalCode() throws Exception {
        generateVariables();
        lookForLabels();
        recorrerCuadruplos();
        cft.generateFile("MIPSFinalCode.s");
    }

    public void generateVariables() {
        for (Simbolo S : ts.Simbolos) {
            if (S.getAmbito().equals("main") && S.isVariable()) {
                String tipo = S.getTipo();
                switch (tipo) {
                    case "integer": {
                        cft.addIntegerDataVariable(S.getId());
                        break;
                    }
                    case "char": {
                        cft.addCharDataVariable(S.getId());
                        break;
                    }
                    case "string": {
                        // T_T Esta malo
                        cft.addIntegerDataVariable(S.getId());
                        break;
                    }
                }
            }
        }
    }

    public void recorrerCuadruplos() throws Exception {
        String ambitoActual = "";
        for (int i = 0; i < tc.getSize(); i++) {
            if (labelControl.containsKey(i)) {
                cft.addLabel(labelControl.get(i));
            }
            Cuadruplo C = tc.item(i);
            String op = C.getOperacion();
            String arg1 = C.getArg1();
            String arg2 = C.getArg2();
            String resultado = C.getResultado();
            switch (op) {
                case "WRITE": {
                    generateWrite(C, ambitoActual);
                    break;
                }
                case "LABEL": {
                    ambitoActual = C.getArg1();
                    cft.addLabel(C.getArg1());
                    break;
                }
                case "READ": {
                    this.generateRead(C, ambitoActual);
                    break;
                }
                case ":=": {
                    if (arg1.contains("$t")) {
//                        String temp = tempEquivalente.get(arg1);
//                        cft.generateAssignTemp(resultado, temp);
//                        this.tempVivos.put(temp, false);
//                        tempEquivalente.remove(arg1);
                    } else if (arg1.contains("'")) {
                        //
                    } else if(arg1.matches("[0-9]+")){
                        String temp = this.getTempDisponible(resultado);
                        cft.generateAssignNum(arg1, temp);
                    } else if(arg1.equals("true") || arg1.equals("false")){
                        //
                    } else {
                        String temp = this.getTempDisponible(resultado);
                        cft.generateAssignVar(arg1, temp);
                    }
                    break;
                }
                case "+":{
                    if(arg2.matches("[0-9]+")){
                        String resultTemp = this.getTempDisponible(resultado);
                        String newArg1 = this.getTemp(arg1);
                        cft.generateAddi(newArg1, arg2, resultTemp);
                    } else if(arg2.contains("$t")){
                        
                    } else {
                    
                    }
                    break;
                }
            }
        }
        cft.generateEndOfProgram();

    }

    public void lookForLabels() throws Exception {
        for (int i = 0; i < tc.getSize(); i++) {
            Cuadruplo C = tc.item(i);
            String op = C.getOperacion();
            if (op.equals("GOTO")) {
                String jumpTo = C.getArg1();
                int jump = Integer.parseInt(jumpTo);
                if (!labelControl.containsKey(jump)) {
                    labelControl.put(jump, newLabel());
                }
            } else if (op.startsWith("if")) {
                String jumpTo = C.getResultado();
                int jump = Integer.parseInt(jumpTo);
                if (!labelControl.containsKey(jump)) {
                    labelControl.put(jump, newLabel());
                }
            }
        }
    }

    public void generateWrite(Cuadruplo C, String ambitoActual) throws Exception {
        String param = C.getArg1();
        if (param.contains("'")) {
            String messageName = cft.addDataMessage(param);
            cft.addWriteStatementLiteral(messageName);
        } else {
            Simbolo S = ts.getVariable(param, ambitoActual);
            if (S == null && !ambitoActual.equals("main")) {
                S = ts.getVariable(param, "main");
            }
            String tipo = S.getTipo();
            if (tipo.equals("string") || tipo.equals("char")) {
                cft.addWriteStringStatement(param);
            } else {
                cft.addWriteStatement(param);
            }
        }
    }

    public void generateRead(Cuadruplo C, String ambitoActual) throws Exception {
        String variable = C.getResultado();

        Simbolo S = ts.getVariable(variable, ambitoActual);
        if (S == null && !ambitoActual.equals("main")) {
            S = ts.getVariable(variable, "main");
        }
        String tipo = S.getTipo();
        if (tipo.equals("string") || tipo.equals("char")) {
            //cft.addWriteStringStatement(variable);
        } else {
            cft.addReadStatement(variable);
        }

    }

    public void printTargetCode() {
        cft.print();
    }

    public String newLabel() {
        return "L" + ++labelCount;
    }

    private String getTempDisponible(String previousTemp) {
        for (Map.Entry<String, Boolean> entry : tempVivos.entrySet()) {
            String key = entry.getKey();
            boolean estaVivo = entry.getValue();
            if(!estaVivo){
                tempVivos.put(key, true);
                this.tempEquivalente.put(previousTemp, key);
                return key;
            }
        }
        return "";
    }

    private String getTemp(String arg1) {
        String temp = tempEquivalente.get(arg1);
        tempEquivalente.remove(arg1);
        this.tempVivos.put(temp, false);
        return temp;
    }
}
