/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.poo.calificaciones;

import com.mgl.poo.calificaciones.control.Control;
import com.mgl.poo.calificaciones.modelo.Arreglo;
import com.mgl.poo.calificaciones.vista.Vista;
import com.mgl.poo.calificaciones.vista.VistaMenu;




import java.awt.Color;
import enigma.console.*;
import enigma.core.Enigma;

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
     //new eric.Console();//crea una  consola  interactiva  basada en el eric.jar
     //Consola();
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

      System.out.println ((char)27 + "[2J");//limpiar pantalla


 }

 public static void Consola()
    {
        TextAttributes attrs = new TextAttributes(Color.BLUE, Color.WHITE);
     //   s_console.setTextAttributes(attrs);
        System.out.println("Hello World!");
    }
    //private static final Console s_console;
    static
    {
        //s_console = Enigma.getConsole("consola!");
    }
}