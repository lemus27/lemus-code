/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jappletxhtmlgenerator;

/**
 *
 * @author mike
 */
// import the JDOM and io stuff we need1
import org.jdom.*;
import org.jdom.output.XMLOutputter;
import java.io.*;
//import com.sun.java.browser.plugin2.DOM;
//import org.w3c.dom.*;
//import org.w3c.dom.html.
// generates an xhtml 'profile' document based on user input
public class xhtmlgen {
   private static BufferedReader reader;
   public static void main(String[] args) {
      // setup reader for getting user input
      reader = new BufferedReader(
         new InputStreamReader(System.in)
      );

      // create new XML document, set DocType and set root element
      Document profile = new Document();
      DocType type     = new DocType("html", "-//W3C//DTD XHTML 1.0 Transitional//EN",
                                     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd");
      profile.setDocType(type);
      Element html     = new Element("html");
      profile.setRootElement(html);

      // get the persons name
      System.out.println("Hi there, welcome to ProfileGen 1.0");
      String name = getUserInput("What is your name?");

      // create head element, title (set by name) and meta (predefined) and styles (predefined)
      Element head     = new Element("head");
      head.addContent  ( new Element("title").setText(name+"'s Profile"));
      Element meta     = new Element("meta");
      meta.setAttribute( new Attribute("http-equiv", "Content-Type"));
      meta.setAttribute( new Attribute("content", "text/html; charset=utf-8"));
      head.addContent(meta);
      Element style    = new Element("style");
      style.setAttribute( new Attribute("type", "text/css"));
      style.setText("h1 { text-align: center; } " +
                    "div.centered {text-align: center;} " +
                    "div.centered table {margin: 0 auto; text-align: left;}");

      head.addContent(style);
      html.addContent(head);

      // create body
      Element body     = new Element("body");
      Element h1       = new Element("h1");
      h1.setAttribute(   new Attribute("align", "center"));
      h1.setText(name+"'s Profile");
      body.addContent(h1);
      body.addContent(   new Element("hr"));

      // create table and add profile items to it
      Element table       = new Element("table");
      table.addContent(createTableEntry("Name", name));
      table.addContent(createDefinedTableEntry("Age","How old are you?"));
      table.addContent(createDefinedTableEntry("Sex","What is your sex? (M/F)"));
      table.addContent(createDefinedTableEntry("Country Of Origin","What is your country of origin?"));
      table.addContent(createDefinedTableEntry("Occupation","What is your occupation?"));
      table.addContent(createDefinedTableEntry("Email","What is your email address?"));

      // let the user add arbitrary elements to the list
      String identifier=null;
      String description=getUserInput("Would you like to add extra items? (y/n)");
      while(!description.equals("n")) {
         table.addContent(createCustomTableEntry());
         description=getUserInput("Add another? (y/n)");
      }

      // add the table element (centered by div) to the body element and add final horizontal line
      Element div = new Element("div");
      div.setAttribute("class", "centered"); // css mess
      div.addContent(table);
      body.addContent(div);
      body.addContent(new Element("hr"));

      // add the body element to the html (root) element
      html.addContent(body);

      // initialize FileWriter, filename created from persons name
      FileWriter writer = null;
      try {
      writer = new FileWriter(name+"_Profile.html");
      } catch(Exception e) {}

      // initialize JDOM XMLOutputter with 2 spaces indent and newlines on
      XMLOutputter prettyOutput = new XMLOutputter();

      // output the document to the file (via writer) and close writer
      try {
         prettyOutput.output(profile, writer);
         writer.close();
      } catch(Exception e) {}
   }

   // creates a html table entry element which embodies identifier and description
   private static Element createTableEntry(String identifier, String description) {
     Element tr     = new Element("tr");
     Element td1    = new Element("td");
     Element td2    = new Element("td");
     Element strong = new Element("strong");
     strong.setText(identifier+": ");
     td1.addContent(strong);
     td2.setText(description);
     tr.addContent(td1);
     tr.addContent(td2);
     return tr;
   }

   // creates a html table entry element based on identifier and user input prompted by prompt
   private static Element createDefinedTableEntry(String identifier, String prompt) {
     return createTableEntry(identifier, getUserInput(prompt));
   }

   // creates a custom html table entry element defined by the user
   private static Element createCustomTableEntry() {
      String identifier = getUserInput("Please enter the profile identifier e.g Name");
      String description= getUserInput("Please enter the answer to the identifier, e.g Peter");
      return createTableEntry(identifier, description);
   }

   // gets user input response to supplied prompt
   private static String getUserInput(String prompt) {
      String userinput = "";
      while(userinput.equals("")) {
         System.out.println(prompt);
         try {
            userinput = reader.readLine();
         } catch(IOException e) { System.err.println(e); }
      }
      return userinput;
   }
}

