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
                case "IfStatement":{
                    cuadruploIf(nodo);
                    break;
                }
                case "WhileStatement":{
                    cuadruploWhile(nodo);
                    break;
                }
                case "RepeatStatement":{
                    cuadruploRepeat(nodo);
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
        
        
        if (arg1Name.equals("ID") || arg1Name.equals("Literal")) {
            t1 = arg1.getAttribute("Value");
        } else if (arg1Name.equals("ARRAY")) {
            cuadruploArray(arg1);
            t1 = this.getTemp();
        } else {
            cuadruploAritmetico(arg1);
            t1 = this.getTemp();
        }
        
        if (arg2Name.equals("ID") || arg2Name.equals("Literal")) {
            t2 = arg2.getAttribute("Value");
        } else if (arg2Name.equals("ARRAY")) {
            cuadruploArray(arg2);
            t2 = this.getTemp();
        } else {
            cuadruploAritmetico(arg2);
            t2 = this.getTemp();
        }
        
        nodo.setAttribute("listaV", this.crearLista(Cuadruplos.getSize()));
        nodo.setAttribute("listaF", this.crearLista(Cuadruplos.getSize() + 1));
        
        Cuadruplos.GEN("if" + op, t1, t2, "@");
        Cuadruplos.GEN_GOTO("@");
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
    
    public String crearLista(int quadIndex){
        return ""+quadIndex;
    }
    
    public String fusiona(String list1, String list2){
        return list1 + "," + list2;
    }
    
    public void completa(int quadIndex, String lista){
        String[] splitList = lista.split(",");
        for (String indexS : splitList) {
            int index = Integer.valueOf(indexS);
            Cuadruplo quad = Cuadruplos.cuadruplos.get(index);
            if(quad.operacion.equals("GOTO")){
                quad.arg1 = quadIndex + "";
            } else {
                quad.resultado = quadIndex + "";
            }
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

        String arg1Name = arg1.getNodeName();
        String arg2Name = arg2.getNodeName();
        
        switch (arg1Name) {
            case "ID":{
                String arg1Value = arg1.getAttribute("Value");
                arg1.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg1.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg1Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "Literal":{
                String arg1Value = arg1.getAttribute("Value");
                arg1.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg1.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg1Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "ARRAY":{
                cuadruploArray(arg1);
                String temp = this.getTemp();
                arg1.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg1.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", temp,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different":{
                cuadruplosExpresion(arg1);
                break;
            }
            default:{
                cuadruploRelacional(arg1);
                break;
            }
                
        }
        
        int M1 = Cuadruplos.getSize();
        
        switch (arg2Name) {
            case "ID":{
                String arg2Value = arg2.getAttribute("Value");
                arg2.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg2.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg2Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "Literal":{
                String arg2Value = arg2.getAttribute("Value");
                arg2.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg2.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg2Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "ARRAY":{
                cuadruploArray(arg2);
                String temp = this.getTemp();
                arg2.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg2.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", temp,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different":{
                cuadruplosExpresion(arg2);
                break;
            }
            default:{
                cuadruploRelacional(arg2);
                break;
            }
                
            
        }
        
        this.completa(M1, arg1.getAttribute("listaV"));
        String listaFusionada = this.fusiona(arg1.getAttribute("listaF"), arg2.getAttribute("listaF"));
        nodo.setAttribute("listaF", listaFusionada);
        nodo.setAttribute("listaV", arg2.getAttribute("listaV"));
    }
    
    private void cuadruploOR(Element nodo) throws Exception {
        if(debug){
            System.out.println("cuadruploOR: " + nodo.getNodeName());
        }
        Element arg1 = (Element) nodo.getFirstChild();
        Element arg2 = (Element) nodo.getLastChild();

        String arg1Name = arg1.getNodeName();
        String arg2Name = arg2.getNodeName();
        
        switch (arg1Name) {
            case "ID":{
                String arg1Value = arg1.getAttribute("Value");
                arg1.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg1.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg1Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "Literal":{
                String arg1Value = arg1.getAttribute("Value");
                arg1.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg1.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg1Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "ARRAY":{
                cuadruploArray(arg1);
                String temp = this.getTemp();
                arg1.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg1.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", temp,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different":{
                cuadruplosExpresion(arg1);
                break;
            }
            default:{
                cuadruploRelacional(arg1);
                break;
            }
                
        }
        int M1 = Cuadruplos.getSize();
        
        switch (arg2Name) {
            case "ID":{
                String arg2Value = arg2.getAttribute("Value");
                arg2.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg2.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg2Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "Literal":{
                String arg2Value = arg2.getAttribute("Value");
                arg2.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg2.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg2Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "ARRAY":{
                cuadruploArray(arg2);
                String temp = this.getTemp();
                arg2.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg2.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", temp,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different":{
                cuadruplosExpresion(arg2);
                break;
            }
            default:{
                cuadruploRelacional(arg2);
                break;
            }   
        }
        
        this.completa(M1, arg1.getAttribute("listaF"));
        String listaFusionada = this.fusiona(arg1.getAttribute("listaV"), arg2.getAttribute("listaV"));
        nodo.setAttribute("listaV", listaFusionada);
        nodo.setAttribute("listaF", arg2.getAttribute("listaF"));
    }
    
    private void cuadruploNOT(Element nodo) throws Exception {
        if(debug){
            System.out.println("cuadruploNOT: " + nodo.getNodeName());
        }
        Element arg1Node = (Element) nodo.getFirstChild();
        String arg1Name = arg1Node.getNodeName();
        
        switch (arg1Name) {
            case "ID":{
                String arg1Value = arg1Node.getAttribute("Value");
                arg1Node.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg1Node.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg1Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "Literal":{
                String arg1Value = arg1Node.getAttribute("Value");
                arg1Node.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg1Node.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg1Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "ARRAY":{
                cuadruploArray(arg1Node);
                String temp = this.getTemp();
                arg1Node.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                arg1Node.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", temp,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different":{
                cuadruplosExpresion(arg1Node);
                break;
            }
            default:{
                cuadruploRelacional(arg1Node);
                break;
            }
                
        }
        nodo.setAttribute("listaV", arg1Node.getAttribute("listaF"));
        nodo.setAttribute("listaF", arg1Node.getAttribute("listaV"));
    }

    private void cuadruploRelacional(Element node) throws Exception {
        String nodeName = node.getNodeName();
         if(debug){
            System.out.println("cuadruploRelacional: " + node.getNodeName());
        }
        switch(nodeName){
            case "AND":{
                cuadruploAND(node);
                break;
            }
            case "OR":{
                cuadruploOR(node);
                break;                
            }
            case "NOT":{
                cuadruploNOT(node);
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different":{
                cuadruplosExpresion(node);
                break;
            }
            
        }
    }

    private void cuadruploIf(Element nodo) {
        
    }

    private void cuadruploWhile(Element nodo) throws Exception {
         if(debug){
            System.out.println("cuadruploWhile: " + nodo.getNodeName());
        }
        Element expression = (Element)nodo.getFirstChild();
        Element body = (Element)nodo.getLastChild();
        
        String nodeName = expression.getNodeName();
        
        int M1 = Cuadruplos.getSize();
        switch(nodeName){
            case "ID":{
                String arg1Value = expression.getAttribute("Value");
                expression.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                expression.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg1Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "Literal":{
                String arg1Value = expression.getAttribute("Value");
                expression.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                expression.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg1Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "ARRAY":{
                cuadruploArray(expression);
                String temp = this.getTemp();
                expression.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                expression.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", temp,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            default:{
                this.cuadruploRelacional(expression);
                break;
            }
        }
        
        int M2 = Cuadruplos.getSize();
        recorrer(body);
        
        this.completa(M2, expression.getAttribute("listaV"));
        Cuadruplos.GEN_GOTO(M1 + "");
        int M3 = Cuadruplos.getSize();
        this.completa(M3, expression.getAttribute("listaF"));
        
    }

    private void cuadruploRepeat(Element nodo) throws Exception {
         if(debug){
            System.out.println("cuadruploRepeat: " + nodo.getNodeName());
        }
        Element body = (Element)nodo.getFirstChild();
        Element expression = (Element)nodo.getLastChild();
        String nodeName = expression.getNodeName();
        
        
        int M1 = Cuadruplos.getSize();
        recorrer(body);
        
        switch(nodeName){
            case "ID":{
                String arg1Value = expression.getAttribute("Value");
                expression.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                expression.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg1Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "Literal":{
                String arg1Value = expression.getAttribute("Value");
                expression.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                expression.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", arg1Value,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            case "ARRAY":{
                cuadruploArray(expression);
                String temp = this.getTemp();
                expression.setAttribute("listaV", crearLista(Cuadruplos.getSize()));
                expression.setAttribute("listaF", crearLista(Cuadruplos.getSize() + 1));
                Cuadruplos.GEN("if=", temp,"1", "@");
                Cuadruplos.GEN_GOTO("@");
                break;
            }
            default:{
                this.cuadruploRelacional(expression);
                break;
            }
        }                
        
        this.completa(M1, expression.getAttribute("listaF"));
        int M2 = Cuadruplos.getSize();
        this.completa(M2, expression.getAttribute("listaV"));
    }
    
    
}
