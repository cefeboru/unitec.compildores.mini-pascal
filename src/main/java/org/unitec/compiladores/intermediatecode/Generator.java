/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores.intermediatecode;

import org.unitec.compiladores.Simbolo;
import org.unitec.compiladores.TablaSimbolos;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Cesar Bonilla
 */
public class Generator {

    final String SUM = "+";
    final String MINUS = "-";
    final String TIMES = "*";
    final String DIV = "/";

    TablaCuadruplos Cuadruplos = new TablaCuadruplos();
    TablaSimbolos TS = new TablaSimbolos();
    String opracionActual = "";
    String ambitoActual = "main";
    int tempCounter = 0;
    boolean debug = true;

    public Generator(TablaSimbolos TS) {
        this.TS = TS;
    }

    public void recorrer(Element rootNode) throws Exception {
        NodeList hijos = rootNode.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Element nodo = (Element) hijos.item(i);
            String nodeName = nodo.getNodeName();
            if (debug) {
                System.out.println("RecorrerArbol - " + nodeName);
            }
            switch (nodeName) {
                case "Assignment": {
                    cuadruploAssignment(nodo);
                    break;
                }
                case "Div":
                case "Minus":
                case "Times":
                case "Plus": {
                    cuadruploAritmetico(nodo);
                    break;
                }
                case "Literal": {

                    break;
                }
                case "ID": {

                    break;
                }
                case "ARRAY": {

                    break;
                }
                case "FunctionCall": {
                    break;
                }
                case "GreaterThan":
                case "LessThan":
                case "Equals":
                case "LessOrEqual":
                case "GreaterOrEqual":
                case "Different": {
                    this.cuadruplosExpresion(nodo);
                    Element parent = (Element)nodo.getParentNode();
                    String listaV = nodo.getAttribute("listaV");
                    String listaF = nodo.getAttribute("listaF");
                    parent.setAttribute("listaV", listaV);
                    parent.setAttribute("listaF", listaF);
                    break;
                }
                case "AND":
                case "OR":
                case "NOT": {
                    cuadruploRelacional(nodo);
                    break;
                }
                default: {
                    recorrer(nodo);
                    break;
                }
            }
        }

    }

    public void cuadruploAritmetico(Element nodo) throws Exception {
        String nodeName = nodo.getNodeName();
        if (debug) {
            System.out.println("cuadruploArit: " + nodeName);
        }
        switch (nodeName) {
            case "Div":
            case "Minus":
            case "Times":
            case "Plus": {
                Element arg1 = (Element) nodo.getFirstChild();
                Element arg2 = (Element) nodo.getLastChild();
                String arg1Name = arg1.getNodeName();
                String arg2Name = arg2.getNodeName();
                boolean arg1IsArray = arg1Name.equals("ARRAY");
                boolean arg2IsArray = arg2Name.equals("ARRAY");
                boolean arg1IsFinal = arg1Name.equals("Literal") || arg1Name.equals("ID") || arg1IsArray;
                boolean arg2IsFinal = arg2Name.equals("Literal") || arg2Name.equals("ID") || arg2IsArray;

                if (arg1IsFinal && arg2IsFinal) {
                    if (arg1IsArray && arg2IsArray) {
                        cuadruploAritmetico(arg1);
                        String tempArg1 = this.getTemp();
                        cuadruploAritmetico(arg2);
                        String tempArg2 = this.getTemp();

                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, tempArg1, tempArg2, temp);
                    } else if (arg1IsArray) {
                        cuadruploAritmetico(arg1);
                        String tempArg = this.getTemp();
                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, tempArg, arg2.getAttribute("Value"), temp);
                    } else if (arg2IsArray) {
                        cuadruploAritmetico(arg2);
                        String tempArg = this.getTemp();
                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, arg1.getAttribute("Value"), tempArg, temp);
                    } else {
                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, arg1.getAttribute("Value"), arg2.getAttribute("Value"), temp);
                    }
                } else if (arg1IsFinal) {
                    if (arg1IsArray) {
                        cuadruploAritmetico(arg1);
                        String tempArg1 = this.getTemp();
                        cuadruploAritmetico(arg2);
                        String tempArg2 = this.getTemp();

                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, tempArg1, tempArg2, temp);
                    } else {
                        cuadruploAritmetico(arg2);
                        String lastTemp = this.getTemp();
                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, arg1.getAttribute("Value"), lastTemp, temp);
                    }
                } else if (arg2IsFinal) {
                    if (arg2IsArray) {
                        cuadruploAritmetico(arg1);
                        String tempArg1 = this.getTemp();
                        cuadruploAritmetico(arg2);
                        String tempArg2 = this.getTemp();

                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, tempArg1, tempArg2, temp);
                    } else {
                        cuadruploAritmetico(arg1);
                        String lastTemp = this.getTemp();
                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, lastTemp, arg2.getAttribute("Value"), temp);
                    }
                } else { // Both are OPs
                    cuadruploAritmetico(arg1);
                    String tempArg1 = this.getTemp();
                    cuadruploAritmetico(arg2);
                    String tempArg2 = this.getTemp();

                    String temp = this.newTemp();
                    String operacion = nodo.getAttribute("Value");
                    if (debug) {
                        System.out.println("Operacion: " + operacion);
                    }
                    Cuadruplos.GEN(operacion, tempArg1, tempArg2, temp);
                }
                break;
            }
            case "ARRAY": {
                this.cuadruploArray(nodo);
                break;
            }
            default: {
                break;
            }
        }
    }

    public void cuadruploAssignment(Element nodo) throws Exception {
        Element arg1 = (Element) nodo.getFirstChild();
        Element arg2 = (Element) nodo.getLastChild();

        String temp1 = "";
        String temp2 = "";
        String operacion = "";
        if (arg2.getNodeName().equals("ID") || arg2.getNodeName().equals("Literal")) {
            temp2 = arg2.getAttribute("Value");
        } else {// relational and arithmetic operation, arrays
            cuadruploAritmetico(arg2);
            temp2 = this.getTemp();
        }

        if (arg1.getNodeName().equals("ID")) {
            temp1 = arg1.getAttribute("Value");
            operacion = ":=";
            Cuadruplos.GEN(operacion, temp2, temp1);
        } else if (arg1.getNodeName().equals("ARRAY")) {
            Element arg = (Element) arg1.getFirstChild();
            String argName = arg.getNodeName();
            String Valex = arg1.getAttribute("Value");
            operacion = "[]=";
            Simbolo S = TS.getVariable(Valex, ambitoActual);
            String tipo = S.getTipo();
            String indiceInicial = tipo.split("\\.")[2];
            if (argName.equals("ID") || argName.equals("Literal")) {
                String Valex2 = arg.getAttribute("Value");
                String newTemp = this.newTemp();
                Cuadruplos.GEN(MINUS, Valex2, indiceInicial, newTemp);
                String temp = this.getTemp();
                newTemp = this.newTemp();
                Cuadruplos.GEN(TIMES, temp, this.getTypeSize(tipo), newTemp);
                temp = this.getTemp();
                Cuadruplos.GEN(operacion, temp, temp2, Valex);
            } else {
                cuadruploAritmetico(arg);
                String temp = this.getTemp();
                String newTemp = this.newTemp();
                Cuadruplos.GEN(MINUS, temp, indiceInicial, newTemp);
                temp = this.getTemp();
                newTemp = this.newTemp();
                Cuadruplos.GEN(TIMES, temp, this.getTypeSize(tipo), newTemp);
                temp = this.getTemp();
                Cuadruplos.GEN(operacion, temp, temp2, Valex);
            }

        }

    }

    public void cuadruplosExpresion(Element nodo) throws Exception {
        if(debug){
            System.out.println("cuadruplosExpresion: " + nodo.getNodeName());
        }
        Element arg1 = (Element) nodo.getFirstChild();
        Element arg2 = (Element) nodo.getLastChild();
        Element parent = (Element)nodo.getParentNode();
        
        String arg1Name = arg1.getNodeName();
        String arg2Name = arg2.getNodeName();

        String t1 = "";
        String t2 = "";
        String tResultado = "";
        String op = nodo.getAttribute("Value");
        
        String parentName = parent.getNodeName();

        if (arg1Name.equals("ID") || arg1Name.equals("Literal")) {
            t1 = this.newTemp();
            String arg1Value = arg1.getAttribute("Value");
            Cuadruplos.GEN(":=", arg1Value, t1);
        } else if (arg1Name.equals("ARRAY")) {
            cuadruploArray(arg1);
            t1 = this.getTemp();
        } else {
            cuadruploAritmetico(arg1);
            t1 = this.getTemp();
        }
        
        if (arg2Name.equals("ID") || arg2Name.equals("Literal")) {
            t2 = this.newTemp();
            String arg2Value = arg2.getAttribute("Value");
            Cuadruplos.GEN(":=", arg2Value, t2);
        } else if (arg2Name.equals("ARRAY")) {
            cuadruploArray(arg2);
            t2 = this.getTemp();
        } else {
            cuadruploAritmetico(arg2);
            t2 = this.getTemp();
        }
        tResultado = this.newTemp();
        Cuadruplos.GEN(op, t1, t2,tResultado);
        int quadFIndex = -1;
        int quadVIndex = -1;
        
        if(parentName.equals("AND")){
            quadFIndex = Cuadruplos.GEN_JUMP_FALSE("@", tResultado);
        } else if(parentName.equals("OR")){
            quadVIndex = Cuadruplos.GEN_JUMP_TRUE("@", tResultado);
        } else {
            quadFIndex = Cuadruplos.GEN_JUMP_FALSE("@", tResultado);
        }
        String listaF = nodo.getAttribute("listaF");
        String listaV = nodo.getAttribute("listaV");
        
        if(listaV == null || listaV.isEmpty()){
            if(quadVIndex != -1){
                listaV = String.valueOf(quadVIndex);
            }
        } else {
            if( quadVIndex != 1){
                listaV += "," + quadVIndex;
            }
        }
        
        if(listaF == null || listaF.isEmpty()){
            if(quadFIndex != -1){
                listaF = String.valueOf(quadFIndex);
            }
        } else {
            if( quadFIndex != 1){
                listaF += "," + quadFIndex;
            }
        }
        parent.setAttribute("listaF", listaF);        
        parent.setAttribute("listaV", listaV);   
    }

    public void cuadruploArray(Element nodo) throws Exception {
        Element arg = (Element) nodo.getFirstChild();
        String argName = arg.getNodeName();
        String operacion = "=[]";
        String IDArray = nodo.getAttribute("Value");
        Simbolo S = TS.getVariable(IDArray, ambitoActual);
        String tipo = S.getTipo();
        String indiceInicial = tipo.split("\\.")[2];
        System.out.println("------" + IDArray);
        if (argName.equals("ID") || argName.equals("Literal")) {
            String newTemp = this.newTemp();
            Cuadruplos.GEN(MINUS, arg.getAttribute("Value"), indiceInicial, newTemp);
            String temp = this.getTemp();
            newTemp = this.newTemp();
            Cuadruplos.GEN(TIMES, temp, getTypeSize(tipo.split("\\.")[1]), newTemp);
            temp = this.getTemp();
            newTemp = this.newTemp();
            Cuadruplos.GEN(operacion, IDArray, temp, newTemp);
        } else {
            cuadruploAritmetico(arg);
            String temp = this.getTemp();
            String newTemp = this.newTemp();
            Cuadruplos.GEN(MINUS, temp, indiceInicial, newTemp);
            temp = this.getTemp();
            newTemp = this.newTemp();
            Cuadruplos.GEN(TIMES, temp, getTypeSize(tipo.split("\\.")[1]), newTemp);
            temp = this.getTemp();
            newTemp = this.newTemp();
            Cuadruplos.GEN(operacion, IDArray, temp, newTemp);
        }
    }

    public void print() {
        Cuadruplos.print();
    }

    private String getTypeSize(String tipo) {
        if (tipo.equals("char") || tipo.equals("boolean")) {
            return "1";
        } else {
            return "4";
        }
    }

    private String getTemp() {
        return "t" + this.tempCounter;
    }

    private String newTemp() {
        return "t" + ++this.tempCounter;
    }

    private void cuadruploAND(Element nodo) throws Exception {
        if(debug){
            System.out.println("cuadruploAND: " + nodo.getNodeName());
        }
        Element arg1 = (Element) nodo.getFirstChild();
        Element arg2 = (Element) nodo.getLastChild();
        Element parent = (Element)nodo.getParentNode();

        String arg1Name = arg1.getNodeName();
        String arg2Name = arg2.getNodeName();
        
        String t1 = "";
                
        switch (arg1Name) {
            case "ID":{
                String arg1Value = arg1.getAttribute("Value");
                Cuadruplos.GEN_JUMP_FALSE("@", arg1Value);
                break;
            }
            case "Literal":{
                String arg1Value = arg1.getAttribute("Value");
                arg1Value = arg1Value.toLowerCase();
                String temp = this.newTemp();
                Cuadruplos.GEN(":=", arg1Value, temp);
                Cuadruplos.GEN_JUMP_FALSE("@", temp);
                break;
            }
            case "ARRAY":{
                cuadruploArray(arg1);
                String temp = this.getTemp();
                Cuadruplos.GEN_JUMP_FALSE("@", temp);
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different":{
                cuadruplosExpresion(arg1);
                String listaV = nodo.getAttribute("listaV");
                String listaF = nodo.getAttribute("listaF");
                parent.setAttribute("listaV", listaV);
                parent.setAttribute("listaF", listaF);
                break;
            }
            default:{
                cuadruploRelacional(arg1);
                break;
            }
                
        }
        
        switch (arg2Name) {
            case "ID":{
                String arg2Value = arg2.getAttribute("Value");
                Cuadruplos.GEN_JUMP_FALSE("@", arg2Value);
                break;
            }
            case "Literal":{
                String arg2Value = arg2.getAttribute("Value");
                arg2Value = arg2Value.toLowerCase();
                String temp = this.newTemp();
                Cuadruplos.GEN(":=", arg2Value, temp);
                Cuadruplos.GEN_JUMP_FALSE("@", temp);
                break;
            }
            case "ARRAY":{
                cuadruploArray(arg2);
                String temp = this.getTemp();
                Cuadruplos.GEN_JUMP_FALSE("@", temp);
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different":{
                cuadruplosExpresion(arg2);
                String listaV = nodo.getAttribute("listaV");
                String listaF = nodo.getAttribute("listaF");
                parent.setAttribute("listaV", listaV);
                parent.setAttribute("listaF", listaF);
                break;
            }
            default:{
                cuadruploRelacional(arg2);
                String listaV = nodo.getAttribute("listaV");
                String listaF = nodo.getAttribute("listaF");
                parent.setAttribute("listaV", listaV);
                parent.setAttribute("listaF", listaF);
                break;
            }
                
            
        }
    }

    private void cuadruploRelacional(Element node) throws Exception {
        String nodeName = node.getNodeName();
        
        switch(nodeName){
            case "AND":{
                cuadruploAND(node);
                break;
            }
            case "OR":{
                break;                
            }
            case "NOT":{
                break;
            }
        }
    }
    
    
}
