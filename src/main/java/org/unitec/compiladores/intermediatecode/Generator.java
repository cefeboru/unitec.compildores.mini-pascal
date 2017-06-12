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
    
    public Generator(TablaSimbolos TS){
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
                    break;
                }
                case "AND":
                case "OR":
                case "NOT": {
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
                        String tempArg1 = Cuadruplos.getTemp();
                        cuadruploAritmetico(arg2);
                        String tempArg2 = Cuadruplos.getTemp();

                        String temp = Cuadruplos.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, tempArg1, tempArg2, temp);
                    } else if (arg1IsArray) {
                        cuadruploAritmetico(arg1);
                        String tempArg = Cuadruplos.getTemp();
                        String temp = Cuadruplos.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, tempArg, arg2.getAttribute("Value"), temp);
                    } else if (arg2IsArray) {
                        cuadruploAritmetico(arg2);
                        String tempArg = Cuadruplos.getTemp();
                        String temp = Cuadruplos.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, arg1.getAttribute("Value"), tempArg, temp);
                    } else {
                        String temp = Cuadruplos.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, arg1.getAttribute("Value"), arg2.getAttribute("Value"), temp);
                    }
                } else if (arg1IsFinal) {
                    if (arg1IsArray) {
                        cuadruploAritmetico(arg1);
                        String tempArg1 = Cuadruplos.getTemp();
                        cuadruploAritmetico(arg2);
                        String tempArg2 = Cuadruplos.getTemp();

                        String temp = Cuadruplos.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, tempArg1, tempArg2, temp);
                    } else {
                        cuadruploAritmetico(arg2);
                        String lastTemp = Cuadruplos.getTemp();
                        String temp = Cuadruplos.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, arg1.getAttribute("Value"), lastTemp, temp);
                    }
                } else if (arg2IsFinal) {
                    if (arg2IsArray) {
                        cuadruploAritmetico(arg1);
                        String tempArg1 = Cuadruplos.getTemp();
                        cuadruploAritmetico(arg2);
                        String tempArg2 = Cuadruplos.getTemp();

                        String temp = Cuadruplos.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, tempArg1, tempArg2, temp);
                    } else {
                        cuadruploAritmetico(arg1);
                        String lastTemp = Cuadruplos.getTemp();
                        String temp = Cuadruplos.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, lastTemp, arg2.getAttribute("Value"), temp);
                    }
                } else { // Both are OPs
                    cuadruploAritmetico(arg1);
                    String tempArg1 = Cuadruplos.getTemp();
                    cuadruploAritmetico(arg2);
                    String tempArg2 = Cuadruplos.getTemp();

                    String temp = Cuadruplos.newTemp();
                    String operacion = nodo.getAttribute("Value");
                    if (debug) {
                        System.out.println("Operacion: " + operacion);
                    }
                    Cuadruplos.GEN(operacion, tempArg1, tempArg2, temp);
                }
                break;
            }
            case "ARRAY": {
                Element arg = (Element) nodo.getFirstChild();
                String argName = arg.getNodeName();
                String operacion = "=[]";
                String IDArray = nodo.getAttribute("Value");
                Simbolo S = TS.getVariable(IDArray, ambitoActual);
                String tipo = S.getTipo();
                String indiceInicial = tipo.split("\\.")[2];
                System.out.println("------" + IDArray);
                if (argName.equals("ID") || argName.equals("Literal")) {
                    String newTemp = Cuadruplos.newTemp();
                    Cuadruplos.GEN(MINUS, arg.getAttribute("Value"), indiceInicial, newTemp);
                    String temp = Cuadruplos.getTemp();
                    newTemp = Cuadruplos.newTemp();
                    Cuadruplos.GEN(TIMES, temp, getTypeSize(tipo.split("\\.")[1]), newTemp);
                    temp = Cuadruplos.getTemp();
                    newTemp = Cuadruplos.newTemp();
                    Cuadruplos.GEN(operacion, IDArray, temp, newTemp);
                } else {
                    cuadruploAritmetico(arg);
                    String temp = Cuadruplos.getTemp();
                    String newTemp = Cuadruplos.newTemp();
                    Cuadruplos.GEN(MINUS, temp, indiceInicial, newTemp);
                    temp = Cuadruplos.getTemp();
                    newTemp = Cuadruplos.newTemp();
                    Cuadruplos.GEN(TIMES, temp, getTypeSize(tipo.split("\\.")[1]), newTemp);
                    temp = Cuadruplos.getTemp();
                    newTemp = Cuadruplos.newTemp();
                    Cuadruplos.GEN(operacion, IDArray, temp, newTemp);
                }
                break;
            }
            default: {
                break;
            }
        }
    }

    public void cuadruploAssignment(Element nodo) throws Exception {
        /*String id = ((Element) nodo.getFirstChild()).getAttribute("Value");
         String assigmentElement = nodo.getFirstChild().getNodeName();
         String operacion = "";
         Element lastChild = (Element) nodo.getLastChild();
         String lastChildName = lastChild.getNodeName();

         if (assigmentElement.equals("ARRAY")) {
         operacion = "[]=";
         } else {
         operacion = ":=";
         }

         if (lastChildName.equals("ID") || lastChildName.equals("Literal")) {
         Cuadruplos.GEN(operacion, lastChild.getAttribute("Value"), id);
         } else {
         if (assigmentElement.equals("ARRAY")) {
                
         }else{
         recorrer(nodo);
         String lastTemp = Cuadruplos.getTemp();
         Cuadruplos.GEN(operacion, lastTemp, id);
         }
         }*/

        Element arg1 = (Element) nodo.getFirstChild();
        Element arg2 = (Element) nodo.getLastChild();

        String temp1 = "";
        String temp2 = "";
        String operacion = "";
        if (arg2.getNodeName().equals("ID") || arg2.getNodeName().equals("Literal")) {
            temp2 = arg2.getAttribute("Value");
        } else {// relational and arithmetic operation, arrays
            cuadruploAritmetico(arg2);
            temp2 = Cuadruplos.getTemp();
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
                String newTemp = Cuadruplos.newTemp();
                Cuadruplos.GEN(MINUS, Valex2, indiceInicial, newTemp);
                String temp = Cuadruplos.getTemp();
                newTemp = Cuadruplos.newTemp();
                Cuadruplos.GEN(TIMES, temp, this.getTypeSize(tipo), newTemp);
                temp = Cuadruplos.getTemp();
                Cuadruplos.GEN(operacion, temp, temp2, Valex);
            } else {
                cuadruploAritmetico(arg);
                String temp = Cuadruplos.getTemp();
                String newTemp = Cuadruplos.newTemp();
                Cuadruplos.GEN(MINUS, temp, indiceInicial , newTemp);
                temp = Cuadruplos.getTemp();
                newTemp = Cuadruplos.newTemp();
                Cuadruplos.GEN(TIMES, temp, this.getTypeSize(tipo) , newTemp);
                temp = Cuadruplos.getTemp();
                Cuadruplos.GEN(operacion, temp, temp2 , Valex);
            }

        }

    }

    public void print() {
        Cuadruplos.print();
    }

    private String getTypeSize(String tipo) {
        if(tipo.equals("char") || tipo.equals("boolean")){
            return "1";
        } else {
            return "4";
        } 
            
        
    }
}
