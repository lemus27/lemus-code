/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.poo.calificaciones.vista;
import com.mgl.poo.calificaciones.control.Control;
//import java.io.Console; es para  capturar datos  desde  consola, pero no funcina desde  netbeans


/**
 *
 * @author mike
 */
public class VistaMenu {
        
    private Control  objCtrl= null;
    private Vista objVista = null;
    private Consola miConsola = new Consola();
   
    public VistaMenu(Control objCtrl, Vista objVista)

    {this.objCtrl=objCtrl;
     this.objVista=objVista;
    }
    public  void menu()
    {
 
        int z=0;
        System.out.println("BIENVENIDO AL SISTEMA");/*da la bienvenida al sistema*/
        do/*objCtrl del sistema*/
        {
        System.out.println("1.-Ingresar Calificaciones");
        System.out.println("2.-Mostrar Calificaciones");
        System.out.println("3.-Promedio por alumno");
        System.out.println("4.-Numero de aprobados");
        System.out.println("5.-Numero de reprobados");
        System.out.println("6.-Mejor Promedio");
       // int opc= Integer.parseInt(JOptionPane.showInputDialog(null,"Elija la opcion"));
        System.out.println("Elige una Opcion");
        //String Opc=miConsola.readLine("Usuario?");// solo funcion en la  ventana CMD
        String Opc =miConsola.GetConsoleString();
        int opc= Integer.parseInt(Opc);
        switch (opc)/*switch permite mandar llamar los metodos atravez del objCtrl*/
        {
            case 1: this.objVista.Capturar();
            break;
            case 2:  this.objVista.Mostrar();
            break;
            case 3:  this.objVista.mostrarPromedioXAlumno();
            break;
            case 4:  this.objVista.mostrarNumAprobados();
            break;
            case 5: this.objVista.mostrarNumReprobados();
            break;
            case 6:  this.objVista.mostrarMejorPromedio();
            break;
        }
        /*la siguiente linea permite al usuario salir o continuar despues de una operacion*/
         System.out.println("Desea continuar?\n"+"1.-si\n2.-no");
      //z= Integer.parseInt(System.console().readLine());// solo funcion en la  ventana CMD
      z=Integer.parseInt(miConsola.GetConsoleString());
        for (int clear=0; clear<1000;clear++){System.out.println();}/**limpia la pantalla*/
        }
        while(z==1);
         
        System.out.println("ADIOS");
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
     * @return the objVista
     */
    public Vista getObjVista() {
        return objVista;
    }

    /**
     * @param objVista the objVista to set
     */
    public void setObjVista(Vista objVista) {
        this.objVista = objVista;
    }
}
