/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package paquete1;

/**
 *
 * @author mike
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int tam=3,val=0;
        arreglo objArreglo=new arreglo();
        objArreglo.setTamano(tam);
        objArreglo.crear();
        presentacion objPresentacion= new presentacion();
        objPresentacion.setObjArr(objArreglo);
        for (int i=0; i<tam;i++)
        { val= i*2;
            objArreglo.insertarAtPosicion(i, val);
        }
        objPresentacion.calcularSuma();
        objPresentacion.MostrarArreglo();
        objPresentacion.MostrarSuma();
    }

}
