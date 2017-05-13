/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores;

/**
 *
 * @author Cesar Bonilla
 */
public class Simbolo {
    private String id;
    private String valor;
    private String tipo;
    private boolean variable;
    private boolean funcion;
    private boolean parametro;
    private int posicionMemoria;

    
    public Simbolo() {
        this.id = "";
        this.valor = "";
        this.tipo = "";
        this.variable = false;
        this.funcion = false;
        this.parametro = false;
        this.posicionMemoria = 0;
    }

    public Simbolo(String id, String valor, String tipo) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
    }
    
    
    
    public Simbolo(String id, String valor, String tipo, boolean variable, boolean funcion, boolean parametro, int posicionMemoria) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.variable = variable;
        this.funcion = funcion;
        this.parametro = parametro;
        this.posicionMemoria = posicionMemoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isVariable() {
        return variable;
    }

    public void setVariable(boolean variable) {
        this.variable = variable;
    }

    public boolean isFuncion() {
        return funcion;
    }

    public void setFuncion(boolean funcion) {
        this.funcion = funcion;
    }

    public boolean isParametro() {
        return parametro;
    }

    public void setParametro(boolean parametro) {
        this.parametro = parametro;
    }

    public int getPosicionMemoria() {
        return posicionMemoria;
    }

    public void setPosicionMemoria(int posicionMemoria) {
        this.posicionMemoria = posicionMemoria;
    }
}