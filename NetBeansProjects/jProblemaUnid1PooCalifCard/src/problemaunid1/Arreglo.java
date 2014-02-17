/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package problemaunid1;

/**
 * PROGRAMA PARA AUTOMATIZAR EL INGRESO DE CALIFICACIONES,
 * PROMEDIO DE CADA ALUMNO Y PROMEDIO DE APROBADOS Y REPROBADOS DE UN GRUPO
 * LA CLASE ARREGLO COMPRENDE LAS OPERACIONES BASICAS!
 * @author Monica Lopez
 * @since 25 al 29 de marzo del 2011
 * @version 1.0
 */
public class Arreglo
{
    int [][]cal=new int [10][10];/**Declaracion de el arreglo*/
    public void agregar(int alumno,int unidad, int calif )
    {
        cal[alumno][unidad]=calif;
    }
    public int obtenercalif(int alumno, int unidad)
    {
        return cal[alumno][unidad];
    }
    
    private Integer[] array;
    private int Tamano=2;
    public Arreglo () {
           this.array=new Integer[this.Tamano];
    }
    public int getTamano () {
        return Tamano;
    }
    public void setTamano (int val) {
        this.Tamano = val;
    }
    public Integer[] getArray () {
        return array;
    }
    public void setArray (Integer[] val) {
        this.array = val;
    }
    public void crear () {
              this.array=new Integer[this.Tamano];
    }
    public void inicializar (int valores) {
        for( int i=0; i<=Tamano; i++)
        {
              this.array[i]=1;
        }
    }
    public int consultarAtPosicion (int pos) {
        return this.array[pos];
    }
    public void insertarAtPosicion (int pos, int valor) {
         this.array[pos]=valor;
    }
    public void modificarAtPosicion (int pos, int valor) {
        this.array[pos]=valor;
    }
    public void eliminarAtPosicion (int pos) {
        this.array[pos]=null;
    }
}