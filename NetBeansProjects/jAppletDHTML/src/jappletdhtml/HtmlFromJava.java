/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jappletdhtml;

/**
 *
 * @author mike
 */
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import netscape.javascript.JSException;//agregar a la librerias  el jara. pluguin.jar  del jre
import netscape.javascript.JSObject;

public class HtmlFromJava extends Applet implements ActionListener {
  Button aButton;
  JSObject win, doc, form, textField;

  public void init(){
       
    setLayout(new FlowLayout());
    aButton = new Button("create HTML");
    add(aButton);
    aButton.addActionListener(this);
    }
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == aButton)
    {
         win = JSObject.getWindow(this);
  doc = (JSObject) win.getMember("document");
       String HTML =  "<HTML><HEAD></HEAD><BODY>";
       HTML += "<TABLE BORDER=1><TR><TD>Hello world</TD></TR></TABLE>";
       HTML += "</BODY></HTML>";
        System.out.println(HTML);
   
        System.out.println("Handler  de la  ventana obtenida");
      win.eval("alert(\"This alert comes from Java!\")");
       win.eval("createHTML(\"" + HTML +"\");");
       }
    }
}