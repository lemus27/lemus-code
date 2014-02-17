/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package problemaunid1;

import javax.swing.JOptionPane;

/**
 * PROGRAMA PARA AUTOMATIZAR EL INGRESO DE CALIFICACIONES, 
 * PROMEDIO DE CADA ALUMNO Y PROMEDIO DE APROBADOS Y REPROBADOS DE UN GRUPO
 * LA CLASE Presentacion, comprende ell menu del programa asi como la bienvenida y despedida
 * @author Monica Lopez
 * @since 25 al 29 de marzo del 2011
 * @version 1.0
 */
public class Presentacion {
 public static void main(String[] args)
    {
        Main menu= new Main();
        int z=0;
        JOptionPane.showMessageDialog(null, "BIENVENIDO AL SISTEMA");/*da la bienvenida al sistema*/
        do/*menu del sistema*/
        {
        System.out.println("1.-Ingresar Calificaciones");
        System.out.println("2.-Mostrar Calificaciones");
        System.out.println("3.-Promedio por alumno");
        System.out.println("4.-Numero de aprobados");
        System.out.println("5.-Numero de reprobados");
        System.out.println("6.-Mejor Promedio");
        int opc= Integer.parseInt(JOptionPane.showInputDialog(null,"Elija la opcion"));
        switch (opc)/*switch permite mandar llamar los metodos atravez del menu*/
        {
            case 1: menu.Capturar();
            break;
            case 2: menu.Mostrar();
            break;
            case 3: menu.Promedio();
            break;
            case 4: menu.Aprobados();
            break;
            case 5: menu.Reprobados();
            break;
            case 6: menu.Mejorpromedio();
            break;
        }
        /*la siguiente linea permite al usuario salir o continuar despues de una operacion*/
        z= Integer.parseInt(JOptionPane.showInputDialog(null,"Desea continuar?\n"+"1.-si\n2.-no"));
        }
        while(z==1);
         for (int clear=0; clear<20;clear++){System.out.println();}/**limpia la pantalla*/
        JOptionPane.showMessageDialog(null, "Adios!");
 }
}