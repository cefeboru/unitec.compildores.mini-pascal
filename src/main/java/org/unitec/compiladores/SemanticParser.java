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
    static String tipoFuncion = "";

    public static TablaSimbolos llenarTablaSimbolos(Element nodoPadre) throws Exception {
        ambitoActual = "main";
        recorrerArbol(nodoPadre, "0", "0");
        ts.toString();
        return ts;
    }

    private static void recorrerArbol(Element nodoPadre, String Linea, String Columna) throws Exception {
        NodeList hijos = nodoPadre.getChildNodes();

        for (int i = 0; i < hijos.getLength(); i++) {
            Element nodo = (Element) hijos.item(i);
            String nodeName = nodo.getNodeName();
            switch (nodeName) {
                case "VarDeclaration": {
                    String type = ((Element) nodo.getLastChild()).getAttribute("Value");
                    int size = Integer.parseInt(
                            ((Element) nodo.getLastChild()).getAttribute("Size")
                    );
                    NodeList idList = nodo.getElementsByTagName("ID");
                    for (int j = 0; j < idList.getLength(); j++) {
                        String ID = ((Element) idList.item(j)).getAttribute("Value");
                        Simbolo S = new Simbolo(ID, null, type, ambitoActual, true, false, false, offset);
                        ts.Add(S);
                        offset += size;
                    }
                    break;
                }
                case "inlineArg": {
                    String type = ((Element) nodo.getLastChild()).getAttribute("Value");
                    String strSize = ((Element) nodo.getLastChild()).getAttribute("Size");
                    String isPointer = ((Element) nodo.getLastChild()).getAttribute("isPointer");
                    
                    int size = Integer.parseInt(strSize.isEmpty() ? "0" : strSize);
                    NodeList idList = nodo.getElementsByTagName("ID");
                    for (int j = 0; j < idList.getLength(); j++) {
                        if (tempType.isEmpty()) {
                            tempType += type;
                        } else {
                            tempType += "X" + type;
                        }
                        String ID = ((Element) idList.item(j)).getAttribute("Value");
                        Simbolo S = new Simbolo(ID, null, type, ambitoActual, false, false, true, offset);
                        if (isPointer.equals("true")) S.setByRef(true); else S.setByRef(false);
                        ts.Add(S);
                        offset += size;
                    }
                    break;
                }
                case "ProcedureDeclaration": {
                    String ID = nodo.getAttribute("ID");
                    Simbolo S = new Simbolo(ID, null, "void -> void", "main", false, true, false, offset);
                    int indice = ts.Add(S);
                    ambitoActual = nodo.getAttribute("ID");
                    int backupOffset = offset;
                    offset = 0;
                    recorrerArbol(nodo, Linea, Columna);
                    if (!tempType.isEmpty()) {
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
                    recorrerArbol(nodo, Linea, Columna);
                    if (!tempType.isEmpty()) {
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
                    tipoEvaluado = type;
                    Linea = nodo.getAttribute("Line");
                    Columna = nodo.getAttribute("Column");
                    if (!tipoActual.isEmpty() && !tipoActual.equals(type)) {
                        String formatString = "(%s,%s) Error: Tipos incompatibles se encontro '%s' pero se esperaba '%s'";
                        String message = String.format(formatString, Linea, Columna, tipoEvaluado, tipoActual);
                        throw new Exception(message);
                    }
                    break;
                }
                case "Assignment": {
                    Element IdNode = (Element) nodo.getFirstChild();
                    String IdValex = IdNode.getAttribute("Value");
                    Simbolo S = ts.getVariable(IdValex, ambitoActual);
                    String Line = IdNode.getAttribute("Line");
                    String Column = IdNode.getAttribute("Column");
                    if (S == null) {
                        String formatString = "(%s,%s) Error: Identificador no encontrado '%s'";
                        throw new Exception(String.format(formatString, Linea, Columna, IdNode.getAttribute("Value")));
                    }
                    tipoActual = S.getTipo();
                    recorrerArbol(nodo, Line, Column);
                    tipoActual = "";
                    tipoEvaluado = "";
                    break;
                }
                case "ID": {
                    String idValex = nodo.getAttribute("Value");
                    String parentName = nodo.getParentNode().getNodeName();
                    boolean programIsParent = parentName.equals("Program");
                    Linea = nodo.getAttribute("Line");
                    Columna = nodo.getAttribute("Column");
                    if (programIsParent) {
                        recorrerArbol(nodo, nodo.getAttribute("Line"), nodo.getAttribute("Column"));
                        break;
                    }
                    
                    Simbolo S = ts.getVariable(idValex, ambitoActual);
                    
                    if (S == null) {
                        
                        String formatString = "(%s,%s) Error: Identificador no encontrado '%s'";
                        throw new Exception(String.format(formatString, Linea, Columna, nodo.getAttribute("Value")));
                    }
                    boolean isSameType = S.getTipo().equals(tipoActual);
                    if (!tipoActual.isEmpty() && !isSameType) {
                        tipoEvaluado = S.getTipo();
                        String formatString = "(%s,%s) Error: Tipos incompatibles se encontro '%s' pero se esperaba '%s'";
                        String message = String.format(formatString, Linea, Columna, tipoEvaluado, tipoActual);
                        throw new Exception(message);
                    }
                    recorrerArbol(nodo, nodo.getAttribute("Line"), nodo.getAttribute("Column"));
                    break;
                }
                case "FunctionCall": {
                    String tipoBKP = tipoFuncion;
                    tipoFuncion = "";
                    Element functionId = (Element)nodo.getFirstChild();
                    String id = functionId.getAttribute("Value");
                    Linea = functionId.getAttribute("Line");
                    Columna = functionId.getAttribute("Column");
                    Simbolo S = ts.getFunction(id);
                    String tipoRetorno = "";
                    
                    if(S == null) {
                        String formatString = "(%s,%s) Error: Identificador no encontrado '%s'";
                        throw new Exception(String.format(formatString, Linea, Columna, nodo.getAttribute("Value")));
                    }
                    tipoRetorno = S.getTipo().split(" -> ")[1];
                    
                    if(nodo.getChildNodes().getLength() > 1){
                        comprobarFuncion((Element)nodo.getLastChild());
                        tipoFuncion = tipoFuncion + " -> " + tipoRetorno;
                    } else {
                        tipoFuncion = "void -> " + tipoRetorno;
                    }
                    if(!tipoActual.equals(tipoRetorno)){
                        tipoEvaluado = tipoRetorno;
                        String formatString = "(%s,%s) Error: Tipos incompatibles se encontro '%s' pero se esperaba '%s'";
                        String message = String.format(formatString, Linea, Columna, tipoEvaluado, tipoActual);
                        throw new Exception(message);
                    }
                    if(!tipoFuncion.equals(S.getTipo())){
                        //EXPLOTAR
                    }
                    System.out.println(tipoFuncion);
                    tipoFuncion = tipoBKP;
                    
                    break;
                }
                case "GreaterThan":
                case "LessThan":
                case "Equals":
                case "LessOrEqual":
                case "GreaterOrEqual":
                case "Different": {
                    String tipoActualBKP = tipoActual;
                    tipoActual = "";
                    comprobarTipos(nodo);
                    tipoActual = tipoActualBKP;
                    break;
                }
                case "AND":
                case "OR":
                case "NOT":
                {
                    String tipoActualBKP = tipoActual;
                    tipoActual = "boolean";
                    recorrerArbol(nodo, Linea, Columna);
                    tipoActual = tipoActualBKP;
                }   
                default: {
                    recorrerArbol(nodo, Linea, Columna);
                    break;
                }
            }
        }

    }

    private static void comprobarTipos(Element nodoPadre) throws Exception {
        NodeList hijos = nodoPadre.getChildNodes();

        for (int i = 0; i < hijos.getLength(); i++) {
            Element nodo = (Element) hijos.item(i);
            String nodeName = nodo.getNodeName();
            switch (nodeName) {
                case "ID": {
                    Simbolo S = ts.getVariable(nodo.getAttribute("Value"), ambitoActual);
                    if (S == null) {
                        String message = "(%s,%s) Error: Identificador no encontrado '%s'";
                        String Line = nodo.getAttribute("Line");
                        String Column = nodo.getAttribute("Column");
                        throw new Exception(String.format(message, Line, Column, nodo.getAttribute("Value")));
                    }
                    if (tipoActual.isEmpty()) {
                        tipoActual = S.getTipo();
                    }
                    if (!tipoActual.equals(S.getTipo())) {
                        String errorMessage = "(%s,%s) Error: Tipos incompatibles, se esperaba '%s' pero se encontro '%s'";
                        String Line = nodo.getAttribute("Line");
                        String Column = nodo.getAttribute("Column");
                        errorMessage = String.format(errorMessage, Line, Column, tipoActual, S.getTipo());
                        throw new Exception(errorMessage);
                    }
                    break;
                }
                case "Literal": {
                    String type = nodo.getAttribute("Type");
                    if (tipoActual.isEmpty()) {
                        tipoActual = type;
                    }
                    if (!tipoActual.equals(type)) {
                        String errorMessage = "(%s,%s) Error: Tipos incompatibles, se esperaba '%s' pero se encontro '%s'";
                        String Line = nodo.getAttribute("Line");
                        String Column = nodo.getAttribute("Column");
                        errorMessage = String.format(errorMessage, Line, Column, tipoActual, type);
                        throw new Exception(errorMessage);
                    }
                    break;
                }
                default: {
                    comprobarTipos(nodo);
                }
            }

        }
    }

    private static void comprobarFuncion(Element nodoPadre) throws Exception {
         NodeList hijos = nodoPadre.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Element nodo = (Element) hijos.item(i);
            String nodeName = nodo.getNodeName();
            switch (nodeName){
                case "ID":{
                    Simbolo S = ts.getVariable(nodo.getAttribute("Value"), ambitoActual);
                    if (S == null) {
                        String message = "(%s,%s) Error: Identificador no encontrado '%s'";
                        String Line = nodo.getAttribute("Line");
                        String Column = nodo.getAttribute("Column");
                        throw new Exception(String.format(message, Line, Column, nodo.getAttribute("Value")));
                    }
                    if(tipoFuncion.isEmpty())
                        tipoFuncion += S.getTipo();
                    else
                        tipoFuncion += "X"+S.getTipo();
                    break;
                }
                case "Literal":{
                    String tipo = nodo.getAttribute("Type");
                    if(tipoFuncion.isEmpty())
                        tipoFuncion += tipo;
                    else
                        tipoFuncion += "X"+tipo;
                    break;
                }
                case "Minus":
                case "Times":
                case "Div":{
                    String tipoBKP = tipoActual;
                    tipoActual = "integer";
                    comprobarTipos(nodo);
                    tipoActual = tipoBKP;
                    if(tipoFuncion.isEmpty())
                        tipoFuncion += "integer";
                    else
                        tipoFuncion += "Xinteger";
                    break;
                }
                case "Plus":{
                    String tipoBKP = tipoActual;
                    tipoActual = "";
                    comprobarTipos(nodo);
                    
                    if(tipoFuncion.isEmpty())
                        tipoFuncion += tipoActual;
                    else
                        tipoFuncion += "X"+tipoActual;
                    tipoActual = tipoBKP;
                    break;
                }
                case "GreaterThan":
                case "LessThan":
                case "Equals":
                case "LessOrEqual":
                case "GreaterOrEqual":
                case "Different": {
                    String tipoActualBKP = tipoActual;
                    tipoActual = "";
                    comprobarTipos(nodo);
                    tipoActual = tipoActualBKP;
                    if(tipoFuncion.isEmpty())
                        tipoFuncion += "boolean";
                    else
                        tipoFuncion += "Xboolean";
                    break;
                }
                case "AND":
                case "OR":
                case "NOT":
                {
                    String tipoActualBKP = tipoActual;
                    tipoActual = "boolean";
                    recorrerArbol(nodo,"0","0");
                    tipoActual = tipoActualBKP;
                    if(tipoFuncion.isEmpty())
                        tipoFuncion += "boolean";
                    else
                        tipoFuncion += "Xboolean";
                    break;
                }
                case "FunctionCall":{
                    recorrerArbol(nodo,"0","0");
                }
                default:{
                    //NADA TODAVIA
                }
            }
        }
        
    }
}
