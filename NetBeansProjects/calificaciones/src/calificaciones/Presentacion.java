/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calificaciones;

import javax.swing.JOptionPane;

/**
 *VISTA
 * @author Oscar
 */
public class Presentacion /**Vista*/
{
       public static void main(String[] args)
    {
        Main control= new Main();
        int des=0;
        do
        {
        System.out.println("1.-Capturar");
        System.out.println("2.-Mostrar Calificaciones");
        System.out.println("3.-Promedio por alumno");
        System.out.println("4.-Numero de aprobados");
        System.out.println("5.-Numero de reprobados");
        System.out.println("6.-Mejor Promedio");
        int opc= Integer.parseInt(JOptionPane.showInputDialog(null,"Elija la opcion"));
        switch (opc){
            case 1: control.Capturar();
            break;
            case 2: control.Mostrar();
            break;
            case 3: control.Promedio();
            break;
            case 4: control.Aprobados();
            break;
            case 5: control.Reprobados();
            break;
            case 6: control.Mejorpromedio();
            break;

        }
       des= Integer.parseInt(JOptionPane.showInputDialog(null,"Desea continuar?\n"+"1.-si\n2.-no"));
       }while(des==1);
        
        
      }

 
}
