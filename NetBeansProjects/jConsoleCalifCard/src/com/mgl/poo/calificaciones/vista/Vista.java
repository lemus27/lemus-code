/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.poo.calificaciones.vista;

import com.mgl.poo.calificaciones.control.Control;
import javax.swing.JOptionPane;

/**
 *
 * @author mike
 */
public class Vista {

    private Control objCtrl=null;
    private int numAlum = 0;
    private int numUni = 0;

    public Vista()
    {
      
    
    }


     public void Capturar()/**Metodo captura*/
 {
         
     this.numAlum=this.objCtrl.getArray().getNumAlum();
     this.numUni=this.objCtrl.getArray().getNumAlum();
   for (int a=0;a< this.numAlum;a++)/**For que asiga numero de alumno*/
   {
       for (int u=0;u<this.numUni;u++)/**For que asigna unidad a un alumno*/
       {

           System.out.println("Introduzca la Calificacion["+a+"]["+u+"]: ");
           int calif=Integer.parseInt(Consola.GetConsoleString());
               this.objCtrl.insertarAtPosicion(a,u,  calif);
       }
   }
 }

 public void Mostrar() /** Metodo Muestra*/
 {
     for (int a=0;a< this.numAlum;a++)/**For que asiga numero de alumno*/
   {
       for (int u=0;u<this.numUni;u++)/**For que asigna unidad a un alumno*/
       {
          
               System.out.println( "la  calificaiones son : ["+a+"]["+u+"]= "+ this.objCtrl.consultarAtPosicion(a,u));
       }
   }
 }



    /**
     * @return the objCtrl
     */
    public Control getObjCtrl() {
        return objCtrl;
    }

    /**
     * @param objCtrl the objCtrl to set
     */
    public void setObjCtrl(Control objCtrl) {
        this.objCtrl = objCtrl;
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
    public void setNumUni(int numUni) {
        this.numUni = numUni;
    }

    public void mostrarPromedioXAlumno(){

        this.objCtrl.calcularPromedioXAlumno();
       for (int a=0;a< this.numAlum;a++)/**For que asiga numero de alumno*/
       {
         System.out.println( "elpromedio del alumno["+a+"]= "+this.objCtrl.getPromXalumno()[a]);
       }

    }

    public void mostrarNumAprobados() {
        this.objCtrl.calcularAprobados();
        System.out.println( "el numerode alumnos Aprobados es = " + this.objCtrl.getNumAprobados());
    }

    public void mostrarNumReprobados() {
        this.objCtrl.calcularReprobados();
         System.out.println( "el numerode alumnos reprobados es = " + this.objCtrl.getNumReprobados());
    }

    public void mostrarMejorPromedio() {
          System.out.println( "el MejorPromedio es = " + this.objCtrl.getMejorProm());
    }

}
