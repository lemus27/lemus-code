/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jspringioclearn1.beans;

import org.springframework.stereotype.Service;

/**
 *
 * @author mike
 */
@Service
public class Calculo implements Proceso
{
    public Object ejecuta()
    {
        return (int)(Math.random()*100.0);
    }
}
