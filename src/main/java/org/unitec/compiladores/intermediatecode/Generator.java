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
                boolean arg1IsFinal = arg1Name.equals("Literal") || arg1Name.equals("ID");
                boolean arg2IsFinal = arg2Name.equals("Literal") || arg2Name.equals("ID");

                if (arg1IsFinal && arg2IsFinal) {
                    String temp = Cuadruplos.newTemp();
                    String operacion = rootNode.getAttribute("Value");
                    if (debug) {
                        System.out.println("Operacion: " + operacion);
                    }
                    Cuadruplos.GEN(operacion, arg1.getAttribute("Value"), arg2.getAttribute("Value"), temp);
                } else if (arg1IsFinal) {
                    cuadruploAritmetico(arg2);
                    String lastTemp = Cuadruplos.getTemp();
                    String temp = Cuadruplos.newTemp();
                    String operacion = rootNode.getAttribute("Value");
                    if (debug) {
                        System.out.println("Operacion: " + operacion);
                    }
                    Cuadruplos.GEN(operacion, arg1.getAttribute("Value"), lastTemp, temp);
                } else if(arg2IsFinal){
                    cuadruploAritmetico(arg1);
                    String lastTemp = Cuadruplos.getTemp();
                    String temp = Cuadruplos.newTemp();
                    String operacion = rootNode.getAttribute("Value");
                    if (debug) {
                        System.out.println("Operacion: " + operacion);
                    }
                    Cuadruplos.GEN(operacion, lastTemp, arg2.getAttribute("Value"), temp);
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
            case "Literal": {
                System.out.println("Literal");
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
