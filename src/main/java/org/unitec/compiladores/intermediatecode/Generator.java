/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores.intermediatecode;

import org.unitec.compiladores.Simbolo;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Cesar Bonilla
 */
public class Generator {

    TablaCuadruplos Cuadruplos = new TablaCuadruplos();
    String opracionActual = "";
    int tempCounter = 0;
    boolean debug = true;

    public void recorrer(Element rootNode) {
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

    public void cuadruploAritmetico(Element rootNode) {
        String nodeName = rootNode.getNodeName();
        if (debug) {
            System.out.println("cuadruploArit: " + nodeName);
        }
        switch (nodeName) {
            case "Div":
            case "Minus":
            case "Times":
            case "Plus": {
                Element arg1 = (Element) rootNode.getFirstChild();
                Element arg2 = (Element) rootNode.getLastChild();
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
                        String operacion = rootNode.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, tempArg1, tempArg2, temp);
                    } else if (arg1IsArray) {
                        cuadruploAritmetico(arg1);
                        String tempArg = Cuadruplos.getTemp();
                        String temp = Cuadruplos.newTemp();
                        String operacion = rootNode.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, tempArg, arg2.getAttribute("Value"), temp);
                    } else if (arg2IsArray) {
                        cuadruploAritmetico(arg2);
                        String tempArg = Cuadruplos.getTemp();
                        String temp = Cuadruplos.newTemp();
                        String operacion = rootNode.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, arg1.getAttribute("Value"), tempArg, temp);
                    } else {
                        String temp = Cuadruplos.newTemp();
                        String operacion = rootNode.getAttribute("Value");
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
                        String operacion = rootNode.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, tempArg1, tempArg2, temp);
                    } else {
                        cuadruploAritmetico(arg2);
                        String lastTemp = Cuadruplos.getTemp();
                        String temp = Cuadruplos.newTemp();
                        String operacion = rootNode.getAttribute("Value");
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
                        String operacion = rootNode.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Cuadruplos.GEN(operacion, tempArg1, tempArg2, temp);
                    } else {
                        cuadruploAritmetico(arg1);
                        String lastTemp = Cuadruplos.getTemp();
                        String temp = Cuadruplos.newTemp();
                        String operacion = rootNode.getAttribute("Value");
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
                    String operacion = rootNode.getAttribute("Value");
                    if (debug) {
                        System.out.println("Operacion: " + operacion);
                    }
                    Cuadruplos.GEN(operacion, tempArg1, tempArg2, temp);
                }
                break;
            }
            case "ARRAY": {
                Element arg = (Element) rootNode.getFirstChild();
                String argName = arg.getNodeName();
                String operacion = "=[]";
                String IDArray = rootNode.getAttribute("Value");
                System.out.println("------"+IDArray);
                if (argName.equals("ID") || argName.equals("Literal")) {
                    String temp = Cuadruplos.newTemp();
                    //temp = rootNodeID [arg.Value]
                    Cuadruplos.GEN(operacion, IDArray, arg.getAttribute("Value"), temp);
                } else {
                    cuadruploAritmetico(arg);
                    String lastTemp = Cuadruplos.getTemp();
                    String temp = Cuadruplos.newTemp();
                    Cuadruplos.GEN(operacion, IDArray, lastTemp, temp);
                }
                break;
            }
            default: {
                break;
            }
        }
    }

    public void cuadruploAssignment(Element nodo) {
        String id = ((Element) nodo.getFirstChild()).getAttribute("Value");
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
            recorrer(nodo);
            String lastTemp = Cuadruplos.getTemp();
            Cuadruplos.GEN(operacion, lastTemp, id);
        }
    }

    public void print() {
        Cuadruplos.print();
    }
}
