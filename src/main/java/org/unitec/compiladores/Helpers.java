/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Cesar Bonilla
 */
public class Helpers {
    public static TablaSimbolos llenarTablaSimbolos(Element nodoPadre){
        TablaSimbolos ts = new TablaSimbolos();
        String[] declarationsNodes = new String[]{"VarDeclaration","ProcedureDeclaration","FunctionDeclaration"};
        ArrayList<NodeList> declarations = new ArrayList();
        for(String nodo: declarationsNodes){
            NodeList lista = nodoPadre.getElementsByTagName(nodo);
            if(lista.getLength() > 0){
                declarations.add(lista);
            }
        }
        
        for(NodeList nodos: declarations){
            NodeList children = nodos.item(0).getChildNodes();
            String tipo = getAttribute(children.item(children.getLength() - 1),"Value");
            for (int i = 0; i < children.getLength() - 1; i++) {
                Node child = children.item(i);
                String id = getAttribute(child,"Value");
                Simbolo S = new Simbolo(id,"",tipo);
                if(!ts.existeId(id)){
                    ts.Add(S);
                }
            }
        }
        
        ts.toString();
        
        return ts;
    }
    
    public static String getAttribute(Node element, String atttribute){
        return element.getAttributes().getNamedItem(atttribute).getNodeValue();
    }
}
