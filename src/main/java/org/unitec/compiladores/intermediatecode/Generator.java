/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores.intermediatecode;

import java.util.ArrayList;
import org.unitec.compiladores.Simbolo;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Cesar Bonilla
 */
public class Generator {

    TablaCuadruplos TC = new TablaCuadruplos();
    ArrayList<String> pila = new ArrayList<>();
    String arg1 = "";
    String arg2 = "";
    String last = "";
    int tempCounter = 0;

    public void generateCodeFromAST(Element rootNode) {
        NodeList hijos = rootNode.getChildNodes();

        for (int i = 0; i < hijos.getLength(); i++) {
            Element nodo = (Element) hijos.item(i);
            String nodeName = nodo.getNodeName();
            switch (nodeName) {
                case "Plus": {
                    String t = newtemp();
                    last = "OP";
                    generateCodeFromAST(nodo);
                    TC.GEN(TablaCuadruplos.PLUS, arg1, arg2, t);
                    break;
                }
                case "Literal": {
                    
                    break;
                }
                case "ID": {
                    if (last.equals("ID")) {
                        arg2 = nodo.getAttribute("Value");
                        arg1 = pila.remove(pila.size() - 1);
                    } else {
                        pila.add(nodo.getAttribute("Value"));
                        last = "ID";
                    }
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
                    break;
                }
            }
        }

    }
    
    public void print(){
        TC.print();
    }
    
    public String newtemp(){
        return "t"+tempCounter++;
    }
}
