/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calificaciones;

import javax.swing.JOptionPane;



/**
 *CONTROLADOR
 * @author Oscar
 */
public class Main
{
    Arreglo arr;
 public Main(){
     arr= new Arreglo();
 }

    
 public void Capturar()/**Metodo captura*/
 {
   for (int alumno=0;alumno<10;alumno++)/**For que asiga numero de alumno*/
   {
       for (int unidad=0;unidad<10;unidad++)/**For que asigna unidad a un alumno*/
       {
        int calificacion=Integer.parseInt(JOptionPane.showInputDialog(null,"Calificacion del alumno "+(alumno+1)+" Unidad "+(unidad+1)));
           arr.agregar(alumno, unidad, calificacion);
       }
   }
 }
 public void Mostrar() /** Metodo Muestra*/
 {

        for (int alumno=0;alumno<10;alumno++)/**For que muestra posicion de el alumno*/
        {
            System.out.println("Alumno "+(alumno+1));
            for(int unidad=0;unidad<10;unidad++)

            {
            System.out.println(arr.obtenercalif(alumno,unidad));/**Impresion alumno-unidad*/
            }
       }

            }
 public void Promedio() /** Metodo  Promedio*/
 {
     int suma=0;
     int prom=0;
     for (int alumno=0;alumno<10;alumno++)/**For que asigna al alumno */
     {
       for (int unidad=0;unidad<10;unidad++)/**For que asigna unidad*/
       {
           suma=suma+arr.obtenercalif(alumno, unidad);/**Calcula Promedios*/
        prom=0;
       }
       
       prom=suma/10;/**Calculo de el promedio*/
       suma=0;
       System.out.println("Promedio del alumno "+(alumno+1)+" = "+prom);

   }

      }
  public void Reprobados() /** Metodo  reprobados*/
 {

     int suma=0;
     int prom=0;

     for (int alumno=0;alumno<10;alumno++)/**For que asigna al alumno */
     {
       for (int unidad=0;unidad<10;unidad++)/**For que asigna unidad*/
       {
           suma=suma+arr.obtenercalif(alumno, unidad);/**Calcula Promedios*/
        prom=0;
       }

       prom=suma/3;/**Calculo de el promedio*/
       suma=0;

       if (prom <= 6)
       {
        System.out.println("El alumno "+(alumno+1)+" Reprobado");
       }

   }
  
 }
  public void Aprobados() /** Metodo  Aprobados*/
 {
     int suma=0;
     int prom=0;

     for (int alumno=0;alumno<10;alumno++)/**For que asigna al alumno */
     {
       for (int unidad=0;unidad<10;unidad++)/**For que asigna unidad*/
       {
           suma=suma+arr.obtenercalif(alumno, unidad);/**Calcula Promedios*/
        prom=0;
       }

       prom=suma/10;/**Calculo de el promedio*/
       suma=0;

       if (prom >= 7)
       {
        System.out.println("El alumno "+(alumno+1)+" Aprobo");
       }

   }
 }
  public void Mejorpromedio() /** Metodo  Mejor Promedio*/
 {
     int suma=0;
     int prom=0;
     int mejorprom=0;
     for (int alumno=0;alumno<10;alumno++)/**For que asigna al alumno */
     {
       for (int unidad=0;unidad<10;unidad++)/**For que asigna unidad*/
       {
           suma=suma+arr.obtenercalif(alumno, unidad);/**Calcula Promedios*/
        prom=0;
       }

       prom=suma/10;/**Calculo de el mejor promedio*/
       suma=0;

       if (prom>mejorprom){
           mejorprom=prom;

       }

       
   }
    System.out.println("El mejor promedio es "+mejorprom);
      
 }
}



