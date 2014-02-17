/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbeansheltest1;

/**
 *
 * @author mike
 */

//CallScript2.java - Invoke a script from Java, handling errors
import bsh.*;

public class Main {
    public static void main( String [] args ) throws Exception {
        try {
            Object obj = new bsh.Interpreter().source("\\src\\jbeansheltest1\\myscript.java");//la ruta del paquete

        } catch ( TargetError e ) {
            System.out.println(
                "The script or code called by the script threw an exception: "
                + e.getTarget() );
        } catch ( EvalError e2 )    {
            System.out.println(
                "There was an error in evaluating the script:" + e2 );
        }
    }
}
