/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calificaciones;

import javax.swing.JOptionPane;

/**
 *MODELO
 * @author Oscar
 */
public class Arreglo
{
    int [][]calf=new int [10][10];/**Declaracion de el arreglo*/

public void agregar(int alumno,int unidad, int calificacion )
{
    calf[alumno][unidad]=calificacion;

     
     }
public int obtenercalif(int alumno, int unidad)
{
    return calf[alumno][unidad];
}
   
}