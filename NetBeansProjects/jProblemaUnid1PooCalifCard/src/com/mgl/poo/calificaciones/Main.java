/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.poo.calificaciones;

import com.mgl.poo.calificaciones.control.Control;
import com.mgl.poo.calificaciones.modelo.Arreglo;
import com.mgl.poo.calificaciones.vista.Vista;
import com.mgl.poo.calificaciones.vista.VistaMenu;




/**
 * PROGRAMA PARA AUTOMATIZAR EL INGRESO DE CALIFICACIONES, 
 * PROMEDIO DE CADA ALUMNO Y PROMEDIO DE APROBADOS Y REPROBADOS DE UN GRUPO
 * 
 * @author mike
 * @since 30 de marzo del 2011
 * @version 1.0
 */
public class Main {
 public static void main(String[] args)
    {
   
     Integer numAlum=2,numUni=2,calMinAprob=7;
     Arreglo objModelo= new Arreglo();
        objModelo.setNumAlum(numAlum);
        objModelo.setNumUni(numUni);
        objModelo.crear();
        Control objControl= new Control();

        Vista objVista=new Vista();
        objVista.setObjCtrl(objControl);
        objControl.setArray(objModelo);
        objControl.setCalMinAprob(calMinAprob);
        

        VistaMenu  objMenu= new VistaMenu(objControl,objVista);

        objMenu.menu();



 }


}