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
public class Concatenacion implements Proceso
{
    public Object ejecuta()
    {
        return new StringBuilder().append("Hola ").append(" mundo");
    }
}
