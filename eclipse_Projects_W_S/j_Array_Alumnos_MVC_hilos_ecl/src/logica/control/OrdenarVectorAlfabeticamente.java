package logica.control;



import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 * @file OrdenarVector.java
 * @version 1.0
 * @author Linea de Codigo (http://lineadecodigo.com)
 * @date   22/marzo/2009
 * @url  http://lineadecodigo.com/2009/03/25/ordenar-un-vector-de-forma-alfabetica-con-java/
 * @description Ordenar un Vector mediante la clase Collections y su m�todo .sort. Apoyandonos en un Comparador creado por nosotros.
 */

class Comparador implements Comparator<String> {
    public int compare(String s1, String s2)
    {           
                return s1.toLowerCase().compareTo(s2.toLowerCase());
    }
}

public class OrdenarVectorAlfabeticamente {
        

        public static void main(String[] args) {

                
                Vector<String> miVector = new Vector<String>();
                miVector.add("Victor");
                miVector.add("julio");
                miVector.add("amaya");                  
                                
                Collections.sort(miVector,new Comparador());            
                
                for(String sElemento: miVector){
                        System.out.println(sElemento);  
                }
                                
        }

}
