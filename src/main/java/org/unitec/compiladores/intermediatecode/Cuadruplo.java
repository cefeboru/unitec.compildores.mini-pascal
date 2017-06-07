/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores.intermediatecode;

/**
 *
 * @author Cesar Bonilla
 */
public class Cuadruplo {
    String operacion;
    String arg1;
    String arg2;
    String resultado;

    public Cuadruplo(String operacion, String arg1, String arg2, String resultado) {
        this.operacion = operacion;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.resultado = resultado;
    }

    public Cuadruplo(String operacion, String arg1, String resultado) {
        this.operacion = operacion;
        this.arg1 = arg1;
        this.resultado = resultado;
    }

    public Cuadruplo(String operacion, String resultado) {
        this.operacion = operacion;
        this.resultado = resultado;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    @Override
    public String toString(){
        String message = "%s %s %s %s";
        return String.format(message, operacion, arg1, arg2, resultado);
    }
    
    
}
