/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jmozswingventana;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.mozilla.browser.MozillaWindow;
import org.mozilla.browser.MozillaAutomation;
import org.mozilla.browser.MozillaWindow;
import org.mozilla.browser.impl.DOMUtils;
import org.w3c.dom.Document;
import org.mozilla.browser.MozillaPanel;
import org.mozilla.browser.MozillaAutomation;
import org.mozilla.browser.MozillaWindow;
import org.mozilla.browser.common.Platform;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.TransferHandler;

import net.sourceforge.iharder.Base64;

import org.mozilla.browser.MozillaExecutor;
import org.mozilla.browser.MozillaPanel;
import org.mozilla.browser.XPCOMUtils;
import org.mozilla.dom.NodeFactory;
import org.mozilla.interfaces.nsIDOMEvent;
import org.mozilla.interfaces.nsIDOMEventListener;
import org.mozilla.interfaces.nsIDOMEventTarget;
import org.mozilla.interfaces.nsIDOMWindow2;
import org.mozilla.interfaces.nsIDragService;
import org.mozilla.interfaces.nsIDragSession;
import org.mozilla.interfaces.nsISupports;
import org.mozilla.interfaces.nsISupportsString;
import org.mozilla.interfaces.nsITransferable;
import org.mozilla.interfaces.nsIWebBrowser;
import org.mozilla.xpcom.Mozilla;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import org.mozilla.browser.IMozillaWindow;
import org.mozilla.browser.MozillaAutomation;
import org.mozilla.browser.MozillaExecutor;
import org.mozilla.browser.MozillaWindow;
import org.mozilla.browser.XPCOMUtils;
import org.mozilla.interfaces.nsICommandManager;
import org.mozilla.interfaces.nsIDOMElement;
import org.mozilla.interfaces.nsIDOMEvent;
import org.mozilla.interfaces.nsIDOMEventListener;
import org.mozilla.interfaces.nsIDOMEventTarget;
import org.mozilla.interfaces.nsIDOMWindow;
import org.mozilla.interfaces.nsIDOMWindow2;
import org.mozilla.interfaces.nsIEditingSession;
import org.mozilla.interfaces.nsIEditor;
import org.mozilla.interfaces.nsIEditorStyleSheets;
import org.mozilla.interfaces.nsIHTMLEditor;
import org.mozilla.interfaces.nsIHTMLInlineTableEditor;
import org.mozilla.interfaces.nsIHTMLObjectResizer;
import org.mozilla.interfaces.nsIInterfaceRequestor;
import org.mozilla.interfaces.nsISelection;
import org.mozilla.interfaces.nsISupports;
import org.mozilla.interfaces.nsIWebBrowser;
import org.mozilla.xpcom.Mozilla;

import static org.mozilla.browser.XPCOMUtils.qi;
import static org.mozilla.browser.examples.Example13_Editor.initializeHTMLEditor;

import java.util.Arrays;

import org.mozilla.browser.IMozillaWindow;
import org.mozilla.browser.MozillaAutomation;
import org.mozilla.browser.MozillaExecutor;
import org.mozilla.browser.MozillaWindow;
import org.mozilla.browser.XPCOMUtils;
import org.mozilla.interfaces.nsIClipboardDragDropHooks;
import org.mozilla.interfaces.nsICommandManager;
import org.mozilla.interfaces.nsICommandParams;
import org.mozilla.interfaces.nsIDOMDocument;
import org.mozilla.interfaces.nsIDOMElement;
import org.mozilla.interfaces.nsIDOMEvent;
import org.mozilla.interfaces.nsIDOMNode;
import org.mozilla.interfaces.nsIDOMText;
import org.mozilla.interfaces.nsIDOMWindow;
import org.mozilla.interfaces.nsIDragSession;
import org.mozilla.interfaces.nsIEditingSession;
import org.mozilla.interfaces.nsIEditor;
import org.mozilla.interfaces.nsIFormatConverter;
import org.mozilla.interfaces.nsIInterfaceRequestor;
import org.mozilla.interfaces.nsISupports;
import org.mozilla.interfaces.nsISupportsArray;
import org.mozilla.interfaces.nsISupportsCString;
import org.mozilla.interfaces.nsISupportsString;
import org.mozilla.interfaces.nsITransferable;
import org.mozilla.interfaces.nsIWebBrowser;
import org.mozilla.xpcom.Mozilla;



/**
 *para  que  funcione  debes   agregar a las  lib todos los paquete  que tiene
 * y debes  agregar las  carpetas de  xulruner a la carpeta del proyecto
 *-- native
 *-- extensions
 *-- update
 * el jar  no  funciona por si solo necesita  toda la carpeta dist pero detener tambien las  carpetas
 * -- native
 *-- extensions
 *-- update
 * @author mike
 */

public class Main {
 //static MozillaWindow win;
    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws Exception {
        MozillaWindow win = new MozillaWindow();
        win.setSize(500, 600);
        //win.load("C:\\Documents and Settings\\mike\\Mis documentos\\ExeProjects\\examenTeoPooArreglosJava\\pooarreglosjavateoria.htm"); //$NON-NLS-1$
                   win.load("http://localhost:8084/jMozSwingJSPWeb/");
         win.setVisible(true);
    }
     static void cargarHtml()
     {
         String s1 =
            "<html>" + //$NON-NLS-1$
            "<h3>HTML content</h3>" + //$NON-NLS-1$
            "<li><a href=\"about:\">about:</a>" + //$NON-NLS-1$
            "<li><a href=\"about:config\">about:config</a>" + //$NON-NLS-1$
            "</html>"; //$NON-NLS-1$
      //  win.loadHTML(s1);
     }

}
