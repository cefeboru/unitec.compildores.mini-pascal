/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Cesar Bonilla
 */
public class SemanticParser {

    static String ambitoActual;
    static TablaSimbolos ts = new TablaSimbolos();
    static int offset;

    public static TablaSimbolos llenarTablaSimbolos(Element nodoPadre) {
        ambitoActual = "main";
        recorrerArbol(nodoPadre);
        ts.toString();
        return ts;
    }
    
    private static void recorrerArbol(Element nodoPadre){
        NodeList hijos = nodoPadre.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Element nodo = (Element)hijos.item(i);            
            String nodeName = nodo.getNodeName();
            System.out.println(nodeName);
            switch(nodeName){
                case "VarDeclaration":{
                    String type = ((Element)nodo.getLastChild()).getAttribute("Value");
                    int size = Integer.parseInt(
                            ((Element)nodo.getLastChild()).getAttribute("Size")
                    );
                    NodeList idList = nodo.getElementsByTagName("ID");
                    for (int j = 0; j < idList.getLength(); j++) {
                        String ID = ((Element)idList.item(j)).getAttribute("Value");
                        Simbolo S = new Simbolo(ID, null, type, ambitoActual, true, false, false, offset);
                        ts.Add(S);
                        offset += size;
                    }
                    break;
                }
                case "inlineArg":{
                    String type = ((Element)nodo.getLastChild()).getAttribute("Value");
                    String strSize = ((Element)nodo.getLastChild()).getAttribute("Size");
                    int size = Integer.parseInt(strSize.isEmpty() ? "0" : strSize);
                    NodeList idList = nodo.getElementsByTagName("ID");
                    for (int j = 0; j < idList.getLength(); j++) {
                        String ID = ((Element)idList.item(j)).getAttribute("Value");
                        Simbolo S = new Simbolo(ID, null, type, ambitoActual, false, false, true, offset);
                        ts.Add(S);
                        offset += size;
                    }
                    break;
                }
                case "ProcedureDeclaration":{
                    String ID = nodo.getAttribute("ID");
                    String type = nodo.getAttribute("Type");
                    Simbolo S = new Simbolo(ID, null, "void", "main", false, true, false, offset);
                    ts.Add(S);
                    ambitoActual = nodo.getAttribute("ID");
                    int backupOffset = offset;
                    offset = 0;
                    recorrerArbol(nodo);
                    ambitoActual = "main";
                    offset = backupOffset;
                    break;
                }
                case "FunctionDeclaration": {
                    String ID = nodo.getAttribute("ID");
                    String type = nodo.getAttribute("Type");
                    Simbolo S = new Simbolo(ID, null, type, "main", false, true, false, offset);
                    ts.Add(S);
                    ambitoActual = nodo.getAttribute("ID");
                    recorrerArbol(nodo);
                    ambitoActual = "main";
                    break;
                }
                default:
                    recorrerArbol(nodo);
                
            }                
        }
        
    }

    private static TablaSimbolos llenarVarDeclaration(Element nodoPadre) {
        NodeList nodos = nodoPadre.getElementsByTagName("VarDeclaration");

        for (int j = 0; j < nodos.getLength(); j++) {
            Node padre = nodos.item(j).getParentNode();
            if (padre.getNodeName().equals("Declarations")) {
                SemanticParser.addChildSymbols(nodos.item(j), ts);
            } else if (padre.getNodeName().equals("Declarations")){
            
            } else {
                Node parentParent = padre.getParentNode();
                String ID = getAttribute(parentParent, "ID");
                String type = getAttribute(parentParent, "Type");
                Node firstChild = parentParent.getFirstChild();
                NodeList arguments = firstChild.getChildNodes();

                String argumentsType = "";
                for (int i = 0; i < arguments.getLength(); i++) {
                    Node argument = arguments.item(i);
                    Node argumentsTypeNode = argument.getLastChild();
                    for (int k = 0; k < argument.getChildNodes().getLength() - 1; k++) {
                        if (i == 0 && k == 0) {
                            argumentsType += getAttribute(argumentsTypeNode, "Value");
                        } else {
                            argumentsType += "X" + getAttribute(argumentsTypeNode, "Value");
                        }
                    }
                }
                type = argumentsType + " -> " + type;
                Simbolo S = new Simbolo(ID, null, type, "main", false, true, false, 0);
                ts.Add(S);

                NodeList inlineArgs = ((Element) padre.getParentNode()).getElementsByTagName("inlineArg");
                ts = addArgumentSymbols(inlineArgs, ts, ID);
                ts = addChildSymbols(nodos.item(j), ts, ID);
            }

        }

        return ts;
    }

    public static void addChildSymbols(Node element, TablaSimbolos ts) {
        NodeList hijos = element.getChildNodes();
        if (hijos.getLength() > 0) {
            String tipo = getAttribute(hijos.item(hijos.getLength() - 1), "Value");
            for (int i = 0; i < hijos.getLength() - 1; i++) {
                Node child = hijos.item(i);
                String id = getAttribute(child, "Value");
                Simbolo S = new Simbolo(id, null, tipo, "main", false, false, false, 0);
                ts.Add(S);
            }
        }
    }

    public static TablaSimbolos addChildSymbols(Node element, TablaSimbolos ts, String ambito) {
        NodeList hijos = element.getChildNodes();
        if (hijos.getLength() > 0) {
            String tipo = getAttribute(hijos.item(hijos.getLength() - 1), "Value");
            for (int i = 0; i < hijos.getLength() - 1; i++) {
                Node child = hijos.item(i);
                String id = getAttribute(child, "Value");
                Simbolo S = new Simbolo(id, null, tipo, ambito);
                ts.Add(S);
            }
        }
        return ts;
    }

    public static TablaSimbolos addArgumentSymbols(NodeList arguments, TablaSimbolos ts, String ambito) {
        for (int j = 0; j < arguments.getLength(); j++) {
            Node element = arguments.item(j);
            NodeList hijos = element.getChildNodes();
            if (hijos.getLength() > 0) {
                String tipo = getAttribute(hijos.item(hijos.getLength() - 1), "Value");
                for (int i = 0; i < hijos.getLength() - 1; i++) {
                    Node child = hijos.item(i);
                    String id = getAttribute(child, "Value");
                    Simbolo S = new Simbolo(id, null, tipo, ambito, false, false, true, 0);
                    ts.Add(S);
                }
            }
        }

        return ts;
    }

    public static String getAttribute(Node element, String atttribute) {
        Node attElement = element.getAttributes().getNamedItem(atttribute);
        if (attElement != null) {
            return attElement.getNodeValue();
        } else {
            return null;
        }

    }
}
