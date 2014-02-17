/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applets;

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
public class ReadForm extends Applet {
 String text="Enter some text for me to display!";
 Font font = new Font("TimesRoman",Font.BOLD+Font.ITALIC,24);
 JSObject win, doc, form, textField;
 public void init() {
  win = JSObject.getWindow(this);
  doc = (JSObject) win.getMember("document");
  form = (JSObject) doc.getMember("textForm");
  textField = (JSObject) form.getMember("textField");
  setLayout(new BorderLayout());
  Panel buttons = new Panel();
  Button displayTextButton = new Button("Display Text");
  displayTextButton.addActionListener(new ButtonEventHandler());
  buttons.add(displayTextButton);
  add("South",buttons);
 }
 public void paint(Graphics g) {
  g.setFont(font);
  g.drawString(text,30,30);
 }
 class ButtonEventHandler implements ActionListener {
  public void actionPerformed(ActionEvent e){
   String s = e.getActionCommand();
   if("Display Text".equals(s)) {
    text= (String) textField.getMember("value");
    win.eval("alert(\"This alert comes from Java!\")");
    repaint();
   }
  }
 }
}