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
    String formatHeader = "%-20s %-20s %-60s %-15s %-15s %-15s %-15s %-18s";
    String formatBody = "%-20s %-20s %-60s %-15s %-15s %-15s %-15s %-18s";

    public int Add(Simbolo S) throws Exception{
        int itemIndex = this.getSymbolIndex(S);
        if(itemIndex > 0) {
            throw new Exception("Ya existe un elemento " + S.getId() + " en el ambito " + S.getAmbito());
        } else {
            Simbolos.add(S);
        }
        return Simbolos.size() -1;
    }
    
    public int Add(Simbolo S, boolean isParameter) throws Exception{
        int itemIndex = this.getSymbolIndex(S);
        if(itemIndex > 0) {
            throw new Exception("Ya existe un elemento " + S.getId() + " en el ambito " + S.getAmbito());
        } else {
            Simbolos.add(S);
        }
        return Simbolos.size() -1;
    }
    
    
    public Simbolo getSimbolo(int index) throws Exception {
        if(index >= 0 && index < Simbolos.size()){
            return Simbolos.get(index);
        } else {
            throw new Exception("Symbol not found");
        }
    }
    
     public Simbolo getVariable(String Id) throws Exception {
        for(Simbolo S : Simbolos){
            if(S.getId().equals(Id) && S.isVariable()){
                return S;
            }
        }  
        return null;
    }
    
    public int getSymbolIndex(Simbolo S){
        for (int i = 0; i < Simbolos.size(); i++) {
            Simbolo St = Simbolos.get(i);
            boolean hasSameName = S.getId().equals(St.getId());
            boolean hasSameScope = S.getAmbito().equals(St.getAmbito());
            boolean hasSameType = S.getTipo().equals(St.getTipo());
            if( hasSameName && hasSameScope && hasSameType){
                return i;
            }
        }
        return -1;
    } 
    
    public void replaceNode(Simbolo S, int index) {
        Simbolos.set(index, S);
    }
    
    private boolean hasSameParameters(Simbolo S1, Simbolo S2) {
        return false;
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
