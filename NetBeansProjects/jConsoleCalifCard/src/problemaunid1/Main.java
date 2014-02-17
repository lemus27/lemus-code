/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package problemaunid1;

import javax.swing.JOptionPane;

/**
 * PROGRAMA PARA AUTOMATIZAR EL INGRESO DE CALIFICACIONES,
 * PROMEDIO DE CADA ALUMNO Y PROMEDIO DE APROBADOS Y REPROBADOS DE UN GRUPO
 * La clase Main comprende todas las operaciones y metodos del programa en cuestion
 * @author Monica Lopez
 * @since 25 al 29 de marzo del 2011
 * @version 1.0
 */

public class Main
{
    Arreglo array;

 public Main(){
     array= new Arreglo();
 }

 public void Capturar()/**Metodo captura*/
 {
     for (int clear=0; clear<20;clear++){System.out.println();} /**limpia la pantalla*/
   for (int a=0;a<=10;a++)/**For que asiga numero de alumno*/
   {
       for (int u=0;u<=10;u++)/**For que asigna unidad a un alumno*/
       {
           int calif=Integer.parseInt(JOptionPane.showInputDialog(null,"Calificacion "));
         array.agregar(a, u, calif);
       }
   }
 }

 public void Mostrar() /** Metodo Muestra*/
 {
     for (int clear=0; clear<20;clear++){System.out.println();}/**limpia la pantalla*/
     for (int a=0;a<=10;a++)/**For que muestra posicion de el alumno*/
     {
         System.out.println("Alumno "+(a+1));
         for(int u=0;u<=10;u++)
         {
             System.out.println(array.obtenercalif(a,u));/**Impresion alumno-unidad*/
         }
     }
 }

 public void Promedio() /** Metodo  Promedio*/
 {
     int suma=0;
     int prom=0;
     for (int clear=0; clear<20;clear++){System.out.println();}/**limpia la pantalla*/
     for (int a=0;a<=10;a++)/**For que asigna al alumno */
     {
         for (int u=0;u<=10;u++)/**For que asigna unidad*/
         {
             suma=suma+array.obtenercalif(a, u);/**Calcula Promedios*/
             prom=0;
         }
         prom=suma/10;/**Calculo de el promedio*/
         suma=0;
         System.out.println("Promedio del alumno "+(a++)+" = "+prom);
     }
 }
      
  public void Reprobados() /** Metodo  reprobados*/
 {
      int suma=0;
      int prom=0;
      for (int clear=0; clear<20;clear++){System.out.println();}/**limpia la pantalla*/
      for (int a=0;a<=10;a++)/**For que asigna al alumno */
      {
          for (int u=0;u<=10;u++)/**For que asigna unidad*/
          {
              suma=suma+array.obtenercalif(a, u);/**Calcula Promedios*/
              prom=0;
          }
          prom=suma/10;/**Calculo de el promedio*/
          suma=0;
          if (prom <= 7)
          {
              System.out.println("El alumno "+(a+1)+" Reprobado");
          }
      }
  }

  public void Aprobados() /** Metodo  Aprobados*/
  {
      int suma=0;
      int prom=0;
      for (int clear=0; clear<20;clear++){System.out.println();}/**limpia la pantalla*/
      for (int a=0;a<=10;a++)/**For que asigna al alumno */
     {
       for (int u=0;u<=10;u++)/**For que asigna unidad*/
       {
           suma=suma+array.obtenercalif(a, u);/**Calcula Promedios*/
           prom=0;
       }
       prom=suma/10;/**Calculo de el promedio*/
       suma=0;
       if (prom > 7)
       {
           System.out.println("El alumno "+(a+1)+" Aprobo");
       }
      }
  }

  public void Mejorpromedio() /** Metodo  Mejor Promedio*/
  {
      int suma=0;
      int prom=0;
      int mejorprom=0;
      for (int clear=0; clear<20;clear++){System.out.println();}/**limpia la pantalla*/
      for (int a=0;a<=10;a++)/**For que asigna al alumno */
      {
          for (int u=0;u<=10;u++)/**For que asigna unidad*/
          {
              suma=suma+array.obtenercalif(a, u);/**Calcula Promedios*/
              prom=0;
          }
          prom=suma/10;/**Calculo de el mejor promedio*/
          if (prom>mejorprom)
          {
              mejorprom=prom;
          }
      }
      System.out.println("El mejor promedio es "+mejorprom);
  }
}