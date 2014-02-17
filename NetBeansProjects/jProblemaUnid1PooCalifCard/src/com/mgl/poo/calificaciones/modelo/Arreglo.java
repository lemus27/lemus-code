/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.poo.calificaciones.modelo;

/**
 * PROGRAMA PARA AUTOMATIZAR EL INGRESO DE CALIFICACIONES,
 * PROMEDIO DE CADA ALUMNO Y PROMEDIO DE APROBADOS Y REPROBADOS DE UN GRUPO
 * LA CLASE ARREGLO COMPRENDE LAS OPERACIONES BASICAS!
 * @author mike
 * @since  29 de marzo del 2011
 * @version 1.0
 */
public class Arreglo
{
    private Integer[][] arrayCal;
    private int numAlum = 10;
    private int numUni = 10;
   
    

    public Arreglo () {
         
    }

   
    public void crear () {
               this.arrayCal=new Integer[this.numAlum][this.numUni];

    }
    public void inicializar (int valor) {
        for (int a=0;a<=numAlum;a++)/**For que asigna al alumno */
     {
         for (int u=0;u<=numUni;u++)/**For que asigna unidad*/
         {
             this.arrayCal[a][u]=valor;
         }
        
     }
    }
    public int consultarAtPosicion (int alumno,int unidad) {
        return  this.arrayCal[alumno][unidad];
    }
    public void insertarAtPosicion (int alumno,int unidad, int valor) {
          this.arrayCal[alumno][unidad]=valor;
    }
    public void modificarAtPosicion (int alumno,int unidad, int valor) {
         this.arrayCal[alumno][unidad]=valor;
    }
    public void eliminarAtPosicion (int alumno,int unidad) {
        this.arrayCal[alumno][unidad]=null;
    }

    /**
     * @return the arrayCal
     */
    public Integer[][] getArrayCal() {
        return arrayCal;
    }

    /**
     * @param arrayCal the arrayCal to set
     */
    public void setArrayCal(Integer[][] arrayCal) {
        this.arrayCal = arrayCal;
    }

    /**
     * @return the numAlum
     */
    public int getNumAlum() {
        return numAlum;
    }

    /**
     * @param numAlum the numAlum to set
     */
    public void setNumAlum(int numAlum) {
        this.numAlum = numAlum;
    }

    /**
     * @return the numUni
     */
    public int getNumUni() {
        return numUni;
    }

    /**
     * @param numUni the numUni to set
     */
    public void setNumUni(int NumUni) {
        this.numUni = NumUni;
    }
}