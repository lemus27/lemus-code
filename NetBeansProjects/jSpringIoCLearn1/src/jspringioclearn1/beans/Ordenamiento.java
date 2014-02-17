/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jspringioclearn1.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author mike
 */
@Service
public class Ordenamiento implements Proceso
{
    public Object ejecuta()
    {
        List< Integer> listaEnteros = new ArrayList<Integer>();

        listaEnteros.add(9);
        listaEnteros.add(3);
        listaEnteros.add(1);
        listaEnteros.add(6);
        listaEnteros.add(5);
        listaEnteros.add(10);

        Collections.sort(listaEnteros);

        return listaEnteros;
    }
}