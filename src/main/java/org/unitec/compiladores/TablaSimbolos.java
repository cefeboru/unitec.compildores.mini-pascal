/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Cesar Bonilla
 */
public class TablaSimbolos {

    HashMap<String, Simbolo> Simbolos = new HashMap();
    String formatString = "%s \t %s \t %s \t %s \t %s \t %s \t %s";

    public void Add(Simbolo S){
        Simbolos.put(S.getId(), S);
    }
    
    public Simbolo getSimbolo(String id){
        return Simbolos.get(id);
    }
    
    public boolean existeId(String id){
        Simbolo S = this.getSimbolo(id);
        if(S != null){
            return true;
        }
        return false;
    } 
    
    public void clear(){
        Simbolos.clear();
    }
    
    @Override
    public String toString() {
        Set set = Simbolos.entrySet();
        Iterator iterator = set.iterator();

        String headers = String.format(
                formatString,
                "ID",
                "VALOR",
                "TIPO",
                "ES VARIABLE",
                "ES FUNCION",
                "ES PARAMETRO",
                "POSICION MEMORIA"
        );
        System.out.println(headers);
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Simbolo S = (Simbolo) entry.getValue();
            String output = String.format(
                    formatString,
                    S.getId(),
                    S.getValor(),
                    S.getTipo(),
                    String.valueOf(S.isVariable()),
                    String.valueOf(S.isFuncion()),
                    String.valueOf(S.isParametro()),
                    String.valueOf(S.getPosicionMemoria())
            );
            System.out.println(output);
        }

        return "";
    }
}
