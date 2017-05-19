/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Cesar Bonilla
 */
public class TablaSimbolos {

    ArrayList<Simbolo> Simbolos = new ArrayList();
    String formatHeader = "%-20s %-20s %-20s %-15s %-15s %-15s %-15s %-18s";
    String formatBody = "%-20s %-20s %-20s %-15s %-15s %-15s %-15s %-18s";

    public void Add(Simbolo S){
        Simbolos.add(S);
    }
    
    public Simbolo getSimbolo(int index) throws Exception{
        if(index >= 0 && index < Simbolos.size()){
            return Simbolos.get(index);
        } else {
            throw new Exception("Symbol not found");
        }
    }
    
    public int getSymbolIndex(String id){
        for (int i = 0; i < Simbolos.size(); i++) {
            Simbolo S = Simbolos.get(i);
            if(S.getId().equals(id)){
                return i;
            }
        }
        return -1;
    } 
    
    public void clear(){
        Simbolos.clear();
    }
    
    @Override
    public String toString() {
        String headers = String.format(
                formatHeader,
                "ID",
                "VALOR",
                "TIPO",
                "AMBITO",
                "ES VARIABLE",
                "ES FUNCION",
                "ES PARAMETRO",
                "POSICION MEMORIA"
        );
        System.out.println(headers);
        for (Simbolo S: Simbolos) {
            String output = String.format(
                    formatBody,
                    S.getId(),
                    S.getValor(),
                    S.getTipo(),
                    S.getAmbito(),
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
