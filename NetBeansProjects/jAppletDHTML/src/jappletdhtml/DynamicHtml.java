/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jappletdhtml;

/**
 *
 * @author mike
 */

/**
 *
 * @author mike
 */
//Reading a JavaScript Form (ReadForm.java)


import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import netscape.javascript.JSObject;
import netscape.javascript.JSException;
//import com.sun.java.browser.plugin2.DOM;
//import org.w3c.dom.*;
//import org.w3c.dom.html.*;
public class DynamicHtml extends Applet {
 String text="Enter some text for me to display!";
 Font font = new Font("TimesRoman",Font.BOLD+Font.ITALIC,24);
 JSObject win, doc, form, textField;
    @Override
 public void init() {
 
  setLayout(new BorderLayout());
  Panel buttons = new Panel();
  Button displayTextButton = new Button("Display Text");
  displayTextButton.addActionListener(new ButtonEventHandler());
  buttons.add(displayTextButton);
  add("South",buttons);
  

   win = JSObject.getWindow(this);
  doc = (JSObject) win.getMember("document");
  
 }
    @Override
 public void paint(Graphics g) {
  g.setFont(font);
  g.drawString(text,30,30);
 }
 class ButtonEventHandler implements ActionListener {
  public void actionPerformed(ActionEvent e){
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
