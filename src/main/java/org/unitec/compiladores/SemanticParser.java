/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Cesar Bonilla
 */
public class SemanticParser {

    public static TablaSimbolos llenarTablaSimbolos(Element nodoPadre) {
        TablaSimbolos ts = new TablaSimbolos();
        ts = llenarVarDeclaration(ts, nodoPadre);

        ts.toString();

        return ts;
    }

    private static TablaSimbolos llenarVarDeclaration(TablaSimbolos ts, Element nodoPadre) {
        NodeList nodos = nodoPadre.getElementsByTagName("VarDeclaration");
        for (int j = 0; j < nodos.getLength(); j++) {
            Node padre = nodos.item(j).getParentNode();
            if (padre.getNodeName().equals("Declarations")) {
                ts = SemanticParser.addChildSymbols(nodos.item(j), ts);
            } else {
                Node parentParent = padre.getParentNode();
                String ID = getAttribute(parentParent, "ID");
                if (parentParent.getNodeName().equals("FunctionDeclaration")) {
                    String type = "function(" + getAttribute(parentParent,"Type") + ")";
                    Simbolo S = new Simbolo(ID,null,type,"main",false,true,false,0);
                    ts.Add(S);
                }else{
                    Simbolo S = new Simbolo(ID,null,"procedure","main",false,true,false,0);
                    ts.Add(S);
                }
                NodeList inlineArgs = ((Element) padre.getParentNode()).getElementsByTagName("inlineArg");
                ts = addArgumentSymbols(inlineArgs, ts, ID);
                ts = addChildSymbols(nodos.item(j), ts, ID);
            }

        }
        return ts;
    }

    public static TablaSimbolos addChildSymbols(Node element, TablaSimbolos ts) {
        NodeList hijos = element.getChildNodes();
        if (hijos.getLength() > 0) {
            String tipo = getAttribute(hijos.item(hijos.getLength() - 1), "Value");
            for (int i = 0; i < hijos.getLength() - 1; i++) {
                Node child = hijos.item(i);
                String id = getAttribute(child, "Value");
                Simbolo S = new Simbolo(id,null,tipo,"main",false,false,false,0);
                ts.Add(S);
            }
        }
        return ts;
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
                    Simbolo S = new Simbolo(id, null, tipo, ambito,false, false, true, 0);
                    ts.Add(S);
                }
            }
        }

        return ts;
    }

    public static String getAttribute(Node element, String atttribute) {
        return element.getAttributes().getNamedItem(atttribute).getNodeValue();
    }
}
