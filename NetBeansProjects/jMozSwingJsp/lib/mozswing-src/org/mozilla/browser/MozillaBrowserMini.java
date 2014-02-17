package org.mozilla.browser;

import static org.mozilla.browser.MozillaExecutor.mozAsyncExec;
import static org.mozilla.browser.MozillaExecutor.mozInit;
import static org.mozilla.browser.MozillaExecutor.mozSyncExec;
import static org.mozilla.browser.XPCOMUtils.create;
import static org.mozilla.browser.XPCOMUtils.qi;
import static org.mozilla.browser.impl.jna.Gtk.gtk;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;

import javax.swing.JFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.browser.common.Platform;
import org.mozilla.browser.impl.LocationProvider;
import org.mozilla.browser.impl.XREAppData;
import org.mozilla.browser.impl.jna.Gtk;
import org.mozilla.browser.impl.jna.X11;
import org.mozilla.browser.impl.jna.Gdk.GdkDisplay;
import org.mozilla.browser.impl.jna.Gtk.GtkWindow;
import org.mozilla.interfaces.nsIAppShell;
import org.mozilla.interfaces.nsIBaseWindow;
import org.mozilla.interfaces.nsISupports;
import org.mozilla.interfaces.nsIWebBrowser;
import org.mozilla.interfaces.nsIWebBrowserChrome;
import org.mozilla.interfaces.nsIWebBrowserFocus;
import org.mozilla.interfaces.nsIWebNavigation;
import org.mozilla.xpcom.Mozilla;

public class MozillaBrowserMini extends Canvas
    implements nsIWebBrowserChrome
{

    private static final long serialVersionUID = 1L;

    static Log log = LogFactory.getLog(MozillaBrowserMini.class);

    public static nsIAppShell appShell;
    public static boolean initialized;
    public nsIWebBrowser webBrowser;

    public MozillaBrowserMini() {

        if (!initialized) {
            try {
                //File xulrunnerDir = new File("P:\\mozswing\\xulrunner-build\\build\\compile\\mozilla\\dist\\bin");
                //File xulrunnerDir = new File("/Library/Frameworks/XUL.framework/Versions/1.9");
                final File xulrunnerDir = new File(System.getProperty("user.home")+"/mozswing/xulrunner-build/build/compile/mozilla/dist/bin"); //$NON-NLS-1$ //$NON-NLS-2$

                //create temp file, and delete it
                File f = File.createTempFile("swing-mozilla", ""); //$NON-NLS-1$ //$NON-NLS-2$
                f.delete();
                //use the temp file as a name for the temporary profile dir
                final File profDir = f;
                profDir.mkdirs();


                mozInit(xulrunnerDir, null, new RunnableEx() { public void run() throws Exception {
                    Mozilla moz = Mozilla.getInstance();
                    moz.initialize(xulrunnerDir);
                    LocationProvider locProvider = new LocationProvider(xulrunnerDir, profDir);
                    moz.initEmbedding(xulrunnerDir, xulrunnerDir, locProvider, new XREAppData());
                    initialized = true;
                }});
            } catch (Exception e) {
                log.error("failed to initialize mozilla", e); //$NON-NLS-1$
            }
        }
    }

    private GtkWindow gtkPtr = null;

    private long getHandle() {
        Mozilla moz = Mozilla.getInstance();
        if (Platform.usingGTK2Toolkit())
        {
            //Mozilla assumes a top-level GTK window exists.
            //So, we create one, but without border. Then we
            //reparent the toplevel window under this AWT widget
            //using the reparent function from X11
            X11.Window awtID = new X11.Window((int) moz.getNativeHandleFromAWT(this));
            assert !awtID.isNull();
            gtkPtr = gtk.gtk_window_new(Gtk.GTK_WINDOW_POPUP);
            assert gtkPtr!=null;
            X11.Window gtkID = gtk.gdk_x11_drawable_get_xid(gtkPtr.window);
            GdkDisplay display = gtk.gdk_display_get_default();
            X11.Display xdisplay = gtk.gdk_x11_display_get_xdisplay(display);
            X11.INSTANCE.XReparentWindow(xdisplay, gtkID, awtID, 0, 0);
            return gtkPtr.getPeer();
        } else {
            long h = moz.getNativeHandleFromAWT(this);
            assert h!=0;
            return h;
        }
    }

    public void addNotify() {
        super.addNotify();
        try {
            mozSyncExec(new RunnableEx() { public void run() throws Exception {
                webBrowser = create("@mozilla.org/embedding/browser/nsWebBrowser;1", nsIWebBrowser.class); //$NON-NLS-1$
                webBrowser.setContainerWindow(MozillaBrowserMini.this);

                nsIBaseWindow baseWindow = (nsIBaseWindow) webBrowser.queryInterface(nsIBaseWindow.NS_IBASEWINDOW_IID);

                //get dimension of the mozilla area. Usually, the widget does not
                //have the correct bounds at this stage yet, so use defaults
                Rectangle dim = getBounds(); //FIXME getClientArea();
                if (dim.isEmpty()) dim.setSize(300, 300);

                //create the native mozilla area
                baseWindow.initWindow(getHandle(), 0, 0, 0, dim.width, dim.height);
                baseWindow.create();
                baseWindow.setVisibility(true);

                String url = new File(System.getProperty("user.home")).toURI().toString(); //$NON-NLS-1$
                loadURL(url);

                nsIWebBrowserFocus webBrowserFocus = (nsIWebBrowserFocus) webBrowser.queryInterface(nsIWebBrowserFocus.NS_IWEBBROWSERFOCUS_IID);
                webBrowserFocus.activate();
            }});
        } catch (MozillaException e) {
            log.error("failed to initialize mozilla", e); //$NON-NLS-1$
        }

        addComponentListener(new ComponentListener() {
            public void componentHidden(ComponentEvent e) {
            }
            public void componentShown(ComponentEvent e) {
            }
            public void componentResized(ComponentEvent e) {
                onResize();
            }
            public void componentMoved(ComponentEvent e) {}
        });

    }

    private void onResize() {
        //sync exec deadlocks on OSX when manually resizing window
        mozAsyncExec(new Runnable() { public void run() {
            Rectangle rect = getBounds();
            nsIBaseWindow baseWindow = qi(webBrowser, nsIBaseWindow.class);
            baseWindow.setPositionAndSize(rect.x, rect.y, rect.width, rect.height, true);
            if (Platform.usingGTK2Toolkit() && gtkPtr!=null) {
                gtk.gtk_widget_set_usize(gtkPtr, rect.width, rect.height);
            }
        }});
    }

    public void loadURL(final String url) {
        mozSyncExec(new Runnable() { public void run() {
            nsIWebNavigation webNavigation = qi(webBrowser, nsIWebNavigation.class);
            webNavigation.loadURI(url, nsIWebNavigation.LOAD_FLAGS_NONE, null, null, null);
        }});
    }

    // nsISupports

    public nsISupports queryInterface(String aIID) {
        return Mozilla.queryInterface(this, aIID);
    }

    // nsIWebBrowserChrome

    public nsIWebBrowser getWebBrowser() {
        return webBrowser;
    }
    public void setWebBrowser(nsIWebBrowser aWebBrowser) {
        webBrowser = aWebBrowser;
    }
    public long getChromeFlags() { return nsIWebBrowserChrome.CHROME_DEFAULT; }
    public void setStatus(long statusType, String status) {}
    public void setChromeFlags(long aChromeFlags) {}
    public void destroyBrowserWindow() {}
    public void sizeBrowserTo(int aCX, int aCY) {}
    public void showAsModal() {}
    public boolean isWindowModal() { return false; }
    public void exitModalEventLoop(long aStatus) {}


    public static void main(String[] args) {
        final JFrame frame = new JFrame(mt.t("MozillaBrowserMini.8")); //$NON-NLS-1$
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 640, 480);
        frame.setLayout(new BorderLayout());
           final MozillaBrowserMini b = new MozillaBrowserMini();
        frame.add(b, BorderLayout.CENTER);

        frame.setVisible(true);

//        while (true) {
//            nsIAppShell as = MozillaBrowserMini.appShell;
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e1) {
//                log.error("wait interrupted", e1);
//            }
//            if (as!=null) as.run();
//        }
//        mozExecQuiet(new MozillaRunnable() { @Override public void run() {
//                nsIAppShell as = MozillaBrowserMini.appShell;
//                as.run();
//        }});

    }

}
