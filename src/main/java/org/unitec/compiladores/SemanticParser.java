/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Cesar Bonilla
 */
public class SemanticParser {

    static String ambitoActual;
    static TablaSimbolos ts = new TablaSimbolos();
    static int offset;
    static String tempType = "";
    static ArrayList<Element> nodosHoja = new ArrayList();
    static String tipoActual = "";
    static String tipoEvaluado = "";
    static boolean print = false;
    
    public static TablaSimbolos llenarTablaSimbolos(Element nodoPadre) throws Exception {
        ambitoActual = "main";
        recorrerArbol(nodoPadre);
        ts.toString();
        return ts;
    }
    
    private static void recorrerArbol(Element nodoPadre) throws Exception{
        NodeList hijos = nodoPadre.getChildNodes();
        
        for (int i = 0; i < hijos.getLength(); i++) {
            Element nodo = (Element)hijos.item(i);
            String nodeName = nodo.getNodeName();
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
                        if(tempType.isEmpty())
                            tempType += type;
                        else
                            tempType += "X" + type;
                        String ID = ((Element)idList.item(j)).getAttribute("Value");
                        Simbolo S = new Simbolo(ID, null, type, ambitoActual, false, false, true, offset);
                        ts.Add(S);
                        offset += size;
                    }
                    break;
                }
                case "ProcedureDeclaration":{
                    String ID = nodo.getAttribute("ID");
                    Simbolo S = new Simbolo(ID, null, "void -> void", "main", false, true, false, offset);
                    int indice = ts.Add(S);
                    ambitoActual = nodo.getAttribute("ID");
                    int backupOffset = offset;
                    offset = 0;
                    recorrerArbol(nodo);
                    if(!tempType.isEmpty()){
                        tempType += " -> void";
                        S.setTipo(tempType);
                        ts.replaceNode(S, indice);
                    }
                    tempType = "";
                    ambitoActual = "main";
                    offset = backupOffset;
                    break;
                }
                case "FunctionDeclaration": {
                    String ID = nodo.getAttribute("ID");
                    String type = nodo.getAttribute("Type");
                    Simbolo S = new Simbolo(ID, null, type, "main", false, true, false, offset);
                    int indice = ts.Add(S);
                    int backupOffset = offset;
                    offset = 0;
                    ambitoActual = nodo.getAttribute("ID");
                    recorrerArbol(nodo);
                    if(!tempType.isEmpty()){
                        tempType += " -> " + type;
                        S.setTipo(tempType);
                    } else {
                        S.setTipo("void -> " + type);
                    }
                    ts.replaceNode(S, indice);
                    tempType = "";
                    ambitoActual = "main";
                    offset = backupOffset;
                    break;
                }
                case "Literal": {
                    String type = nodo.getAttribute("Type");
                    tipoEvaluado = type ;
                    if(!tipoActual.isEmpty() && !tipoActual.equals(type)){
                        throw new Exception("Tipo no compatible");
                    }
                    break;
                }
                case "Assignment": {
                    Element IdNode = (Element)nodo.getFirstChild();
                    String IdValex = IdNode.getAttribute("Value");
                    Simbolo S = ts.getVariable(IdValex);
                    tipoActual = S.getTipo();
                    try {
                        recorrerArbol(nodo);
                    }catch(Exception ex){
                        String formatString = "(%s,%s) Error: Tipos incompatibles se encontro '%s' pero se esperaba '%s'";
                        String Linea = IdNode.getAttribute("Line");
                        String Columna = IdNode.getAttribute("Column");
                        String message = String.format(formatString, Linea, Columna, tipoEvaluado, tipoActual);
                        System.out.println(message);
                        throw new Exception(message);
                    } finally {
                        tipoActual = "";
                        tipoEvaluado = "";
                    }
                    break;
                }
                case "ID": {
                    recorrerArbol(nodo);
                }
                default:{
                    recorrerArbol(nodo);
                    break;
                }
            }                
        }
        
    }
}
