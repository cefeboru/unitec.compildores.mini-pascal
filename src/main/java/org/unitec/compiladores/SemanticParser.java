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
                ts = addChildNodes(nodos.item(j), ts);
            } else {
                //TODO NODE BLOCK
            }

        }
        return ts;
    }

    public static TablaSimbolos addChildNodes(Node element, TablaSimbolos ts) {
        NodeList hijos = element.getChildNodes();
        if (hijos.getLength() > 0) {
            String tipo = getAttribute(hijos.item(hijos.getLength() - 1), "Value");
            for (int i = 0; i < hijos.getLength() - 1; i++) {
                Node child = hijos.item(i);
                String id = getAttribute(child, "Value");
                Simbolo S = new Simbolo(id, "", tipo);
                ts.Add(S);
            }
        }
        return ts;
    }

    public static String getAttribute(Node element, String atttribute) {
        return element.getAttributes().getNamedItem(atttribute).getNodeValue();
    }
}
