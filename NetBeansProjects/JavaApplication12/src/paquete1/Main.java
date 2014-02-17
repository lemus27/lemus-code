package paquete1;

import java.util.ArrayList; 
import javax.swing.JOptionPane;
/**
 *  @author mike
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.23C40C10-F53D-702E-E167-C98BD3B6758E]
// </editor-fold> 
public class Main
{
 public static void main(String[] args)
    {
        presentacion muestra= new presentacion();
        int des=0;
        do
        {
        System.out.println("MENU");
        System.out.println("1.-Capturar");
        System.out.println("2.-Mostrar Calificaciones");
        System.out.println("3.-Promedio por alumno");
        System.out.println("4.-total de aprobados");
        System.out.println("5.-total de reprobados");
        System.out.println("6.- Promedio mas alto");
        int opc= Integer.parseInt(JOptionPane.showInputDialog(null,"Elija la opcion"));
        switch (opc){
            case 1: muestra.Capturar();
            break;
            case 2: muestra.Mostrar ();
            break;
            case 3: muestra.Promedio();
            break;
            case 4: muestra.aprobados();
            break;
            case 5: muestra.reprobados();
            break;
            case 6: muestra. alto();
            break;

        }
        des= Integer.parseInt(JOptionPane.showInputDialog(null,"DESEA REGRESAR A EL MENU?\n"+"1.-si\n2.-no"));
       }while(des==1);


      }


   

  
}


