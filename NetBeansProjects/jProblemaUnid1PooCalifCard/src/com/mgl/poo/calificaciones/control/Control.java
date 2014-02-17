/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.poo.calificaciones.control;

import com.mgl.poo.calificaciones.modelo.Arreglo;
import com.mgl.poo.calificaciones.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 * PROGRAMA PARA AUTOMATIZAR EL INGRESO DE CALIFICACIONES,
 * PROMEDIO DE CADA ALUMNO Y PROMEDIO DE APROBADOS Y REPROBADOS DE UN GRUPO
 * La clase Control comprende todas las operaciones y metodos del programa en cuestion
 * @author Monica Lopez
 * @since 25 al 29 de marzo del 2011
 * @version 1.0
 */

public class Control
{
    private Arreglo array=null;
    private Integer promXalumno[]=null;
    private Integer numAprobados=0;
    private Integer numReprobados=0;
    private Integer calMinAprob=0;
    private String mejorProm=null;

 public Control(){
     
 }
 public int consultarAtPosicion (int alumno,int unidad) {
        return  this.array.consultarAtPosicion(alumno, unidad);
    }
    public void insertarAtPosicion (int alumno,int unidad, int valor) {
          this.array.insertarAtPosicion(alumno, unidad, valor);
    }
    public void modificarAtPosicion (int alumno,int unidad, int valor) {
         this.array.modificarAtPosicion(alumno, unidad, valor);
    }
    public void eliminarAtPosicion (int alumno,int unidad) {
        this.array.eliminarAtPosicion(alumno, unidad);
    }

 public void calcularPromedioXAlumno() /** Metodo  reprobados*/
 {
      int cont=0;

     int numAlum=this.getArray().getNumAlum();
      int numUni=this.getArray().getNumUni();
      this.promXalumno=new Integer[numAlum];
      for (int a=0;a<numAlum;a++)/**For que asigna al alumno */
     {
          cont=0;
          for (int u=0;u<numUni;u++)/**For que asigna al alumno */
         {
            cont=cont+this.array.consultarAtPosicion(a,u);

         }
          this.promXalumno[a]=cont/numUni;
     }

  }
      
  public void calcularReprobados() /** Metodo  reprobados*/
 {
      this.calcularPromedioXAlumno();
      int contReprobados=0;
    
     int numAlum=this.getArray().getNumAlum();
      for (int a=0;a<numAlum;a++)/**For que asigna al alumno */
     {
          if(this.promXalumno[a]<=this.calMinAprob)
          {
              contReprobados++;
          }

     }
        this.setNumReprobados((Integer) contReprobados);
  }

  public void calcularAprobados() /** Metodo  calcularAprobados*/
  {this.calcularReprobados();
        this.setNumAprobados((Integer) this.getArray().getNumAlum() - this.getNumReprobados());
      
  }

  public void calcularMejorPromedio()/** Metodo  Mejor Promedio*/
  { int numAlum=this.getArray().getNumAlum();
      HashSet  hs = new HashSet();
      this.calcularPromedioXAlumno();
      for (int a=0;a<numAlum;a++)/**For que asigna al alumno */
     {
          hs.add(this.promXalumno[a]);

     }
     
           
          
       this.mejorProm=(String)Collections.max(hs).toString();

  
   
      
  }

    /**
     * @return the promXalumno
     */
    public Integer[] getPromXalumno() {
        return promXalumno;
    }

    /**
     * @param promXalumno the promXalumno to set
     */
    public void setPromXalumno(Integer[] promXalumno) {
        this.setPromXalumno(promXalumno);
    }

    /**
     * @return the array
     */
    public Arreglo getArray() {
        return array;
    }

    /**
     * @param array the array to set
     */
    public void setArray(Arreglo array) {
        this.array = array;
    }


    /**
     * @return the calMinAprob
     */
    public Integer getCalMinAprob() {
        return calMinAprob;
    }

    /**
     * @param calMinAprob the calMinAprob to set
     */
    public void setCalMinAprob(Integer calMinAprob) {
        this.calMinAprob = calMinAprob;
    }

    /**
     * @return the mejorProm
     */
    public String getMejorProm() {
        this.calcularMejorPromedio();
        return mejorProm;
    }

    /**
     * @param mejorProm the mejorProm to set
     */
    public void setMejorProm(String mejorProm) {
        this.mejorProm = mejorProm;
    }

    /**
     * @return the numAprobados
     */
    public Integer getNumAprobados() {
        return numAprobados;
    }

    /**
     * @param numAprobados the numAprobados to set
     */
    public void setNumAprobados(Integer numAprobados) {
        this.numAprobados = numAprobados;
    }

    /**
     * @return the numReprobados
     */
    public Integer getNumReprobados() {
        return numReprobados;
    }

    /**
     * @param numReprobados the numReprobados to set
     */
    public void setNumReprobados(Integer numReprobados) {
        this.numReprobados = numReprobados;
    }
}