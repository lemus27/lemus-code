/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package consultaantibioticosxnombre;

import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class InputNombreAntibiotico {

    public  static String pedirNombreAntibiotico()
    {
          String inputString="";
       boolean inputIsValid=false;
       Integer exampleInteger=0;

       //A while loop to check input.
       while(inputIsValid == false) {
           /*The actual dialog box. Data entered is returned as a string and assigned to inputString.*/
           inputString = JOptionPane.showInputDialog("Example Prompt:\nPlease enter an integer");

          
           /*Check that the input is what we want*/
           if((inputString instanceof String) == true) {
               inputIsValid = true; //If it is, then quit the while loop.
           }
       }

       //Now we can use our data! Example:
       JOptionPane.showMessageDialog(null, inputString, "Example Output", JOptionPane.INFORMATION_MESSAGE);

       return inputString;

    }
    }
 