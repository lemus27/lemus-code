package jAppletDomHtml1;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author mike
 */

//---------File: SGCookie.java --------------
import java.applet.Applet;
import netscape.javascript.JSException;//agregar a la librerias  el jara. pluguin.jar  del jre
import netscape.javascript.JSObject;
/**
* Demonstration of setting cookies from within an applet.
* <p>
* To be able to use this applet, the attribute "MAYSCRIPT" is required
* in the applet tag, e.g.
* <pre>
* &lt;applet
* code="SGCookie.class"
* codebase="."
* name="cookieApp"
* MAYSCRIPT
* &gt;
* &lt;/applet&gt;
* </pre>
*/
public class SGCookie extends Applet
{
private JSObject window;
private JSObject document;

public void init()
{
// Get a reference to the DOM window and document objects
try
{
window = JSObject.getWindow(this);
window.setMember(null, this);
System.out.println("init()\twindow=" + window);

document = (JSObject) window.getMember("document");

System.out.println("init()\tdocument=" + document);
}
catch (JSException ex)
{
// We couldn't get reference to either object
ex.printStackTrace();
}
}

public void setCookie(String name, String value)
{
if (document == null)
{
// init() failed to obtain a reference to document object.
System.out.println("Can't set cookie: no document reference");
return;
}

try
{
/*
* should do here checks to ensure name and value don't contain
* invalid characters and name is not an empty string
* ...
*/

String cookieText = name + "=" + value;

System.out.println("Setting document.cookie = \""
+ cookieText + '"');

// Set document member "cookie" to cookieText
// Similar to Javascript document.cookie = "name=value";
document.setMember("cookie", cookieText);

// Read document cookies; This might return multiple
// name=value pairs separated by ';' (a document can
// have more than one cookie)
// Similar to Javascript cookies = document.cookie;
Object cookies = document.getMember("cookie");

System.out.println("Got document.cookie = \"" + cookies + '"');
}
catch (JSException ex)
{
// Something went wrong
ex.printStackTrace();
}
}
}

