/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package consultaantibioticosxnombre;

import javax.swing.JFrame;

/**
 *
 * @author Administrador
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        String nomAntibiotico="";
        
       nomAntibiotico= InputNombreAntibiotico.pedirNombreAntibiotico();
        JFrame frame = new JFrame();
                frame.setContentPane(new buscarAntibioticos(nomAntibiotico));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
     //System.exit(0);

    }
}
