package org.mozilla.browser.impl;

import static org.mozilla.browser.MozillaExecutor.isMozillaThread;
import static org.mozilla.browser.MozillaExecutor.mozAsyncExec;
import static org.mozilla.browser.MozillaExecutor.swingAsyncExec;
import static org.mozilla.browser.XPCOMUtils.qi;
import static org.mozilla.browser.impl.jna.Gtk.gtk;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.browser.MozillaConfig;
import org.mozilla.browser.common.Platform;
import org.mozilla.browser.impl.jna.Gtk;
import org.mozilla.browser.impl.jna.X11;
import org.mozilla.browser.impl.jna.Gdk.GdkDisplay;
import org.mozilla.browser.impl.jna.Gtk.GtkWindow;
import org.mozilla.interfaces.nsIBaseWindow;
import org.mozilla.interfaces.nsIDOMElement;
import org.mozilla.interfaces.nsIDOMEvent;
import org.mozilla.interfaces.nsIDOMEventListener;
import org.mozilla.interfaces.nsIDOMEventTarget;
import org.mozilla.interfaces.nsIDOMHTMLAnchorElement;
import org.mozilla.interfaces.nsIDOMWindow2;
import org.mozilla.interfaces.nsISupports;
import org.mozilla.interfaces.nsIWebBrowser;
import org.mozilla.interfaces.nsIWebBrowserFocus;
import org.mozilla.xpcom.Mozilla;
import org.mozilla.xpcom.XPCOMException;

public class MozillaCanvas extends Canvas
{

    private static final long serialVersionUID = 2946773219993586110L;

    static Log log = LogFactory.getLog(MozillaCanvas.class);

    private ChromeAdapter chromeAdapter;

    public MozillaCanvas() {
    }

    private long mozHandle = 0;
    private GtkWindow gtkPtr = null;

    private CanvasListener canvasListener;
    private WindowListener windowListener;
    
    private EventBuffer eventBuffer = new EventBuffer();

    public long createHandle(Rectangle dim) {
        assert isMozillaThread();
        Mozilla moz = Mozilla.getInstance();
        if (Platform.usingGTK2Toolkit()) {
            Toolkit.getDefaultToolkit().sync();
            //Mozilla assumes a top-level GTK window exists.
            //
            //So, we create one with GtkPlug widget. This
            //widget is hooked as a child of the AWT canvas
            //using the XEMBED protocol.
            //
            //The implementation of XEMBED/Server mode
            //for the AWT canvas is not fully implemented (in java6),
            //so we have to take care of handling resize/show/hide
            //events
            X11.Window awtID = new X11.Window((int) moz.getNativeHandleFromAWT(this));
            assert !awtID.isNull();
            if (Platform.platform==Platform.Solaris) {
                //XEmbed implementation on solaris seems to be broken
                gtkPtr = gtk.gtk_window_new(Gtk.GTK_WINDOW_POPUP);
                gtk.gtk_window_set_default_size(gtkPtr, 300, 300);
                gtk.gtk_window_set_title(gtkPtr, "Mozilla Wrapper Window"); //$NON-NLS-1$
            } else {
                //on Linux use XEmbed, it handles focus
                //and keyevent propagation
                gtkPtr = gtk.gtk_plug_new(awtID);
            }
            assert gtkPtr!=null;
            Toolkit.getDefaultToolkit().sync();
            gtk.gtk_widget_set_usize(gtkPtr, dim.width, dim.height);
            gtk.gtk_widget_show(gtkPtr);
            Toolkit.getDefaultToolkit().sync();
            if (Platform.platform==Platform.Solaris) {
                X11.Window gtkID = gtk.gdk_x11_drawable_get_xid(gtkPtr.window);
                GdkDisplay display = gtk.gdk_display_get_default();
                X11.Display xdisplay = gtk.gdk_x11_display_get_xdisplay(display);
                X11.INSTANCE.XReparentWindow(xdisplay, gtkID, awtID, 0, 0);
            }
            mozHandle = gtkPtr.getPeer();
        } else {
            long h = moz.getNativeHandleFromAWT(this);
            assert h!=0;
            mozHandle = h;
        }

        return mozHandle;
    }

    public void destroyHandle() {
        assert isMozillaThread();
        if (Platform.usingGTK2Toolkit()) {
            gtk.gtk_widget_destroy(gtkPtr);
            gtkPtr = null;
        }
        mozHandle = 0;
    }

    public long getHandle() {
        return mozHandle;
    }

    public void addNotify() {
        log.trace("addNotify"); //$NON-NLS-1$
        super.addNotify();
        canvasListener = new CanvasListener();
        addComponentListener(canvasListener);

        Window win = SwingUtilities.getWindowAncestor(this);
        assert win!=null;
        windowListener = new WindowListener(win);
        win.addComponentListener(windowListener);
        win.addWindowFocusListener(windowListener);
        win.addWindowListener(windowListener);

        setFocusable(true);
    }

    private class CanvasListener implements ComponentListener
    {
        public void componentHidden(ComponentEvent e) {
            //never called, so we have to register
            //on window ancestor
        }
        public void componentShown(ComponentEvent e) {
            //never called, so we have to register
            //on window ancestor
        }
        public void componentResized(ComponentEvent e) {
            onResize();
        }
        public void componentMoved(ComponentEvent e) {}
    }

    private class WindowListener
        extends WindowAdapter
        implements
            ComponentListener,
            WindowFocusListener
    {
        private final Window win;

        public WindowListener(Window win) {
            this.win = win;
        }

        public void componentHidden(ComponentEvent e) {
            onHide();
        }
        public void componentShown(ComponentEvent e) {
            onShow();
        }
        public void componentResized(ComponentEvent e) {}
        public void componentMoved(ComponentEvent e) {}

        public void windowGainedFocus(WindowEvent e) {}
        public void windowLostFocus(WindowEvent e) {}

        Component lastFocusedCmpOnDeactivate = null;
        public void windowActivated(WindowEvent e) {
            log.debug("window activated, lastWas: "+lastFocusedCmpOnDeactivate); //$NON-NLS-1$
            onFocusMovedTo(lastFocusedCmpOnDeactivate);
        }


        public void windowDeactivated(WindowEvent e) {
            log.debug("window deactivated"); //$NON-NLS-1$
            lastFocusedCmpOnDeactivate = lastFocusedCmp;
            onFocusMovedTo(null);
        }
    }

    @Override
    public void removeNotify() {
        System.err.println("removeNotify"); //$NON-NLS-1$
        log.trace("removeNotify"); //$NON-NLS-1$
        if (canvasListener!=null) {
            removeComponentListener(canvasListener);
            canvasListener = null;
        }
        if (windowListener!=null) {
            windowListener.win.removeComponentListener(windowListener);
            windowListener.win.removeWindowFocusListener(windowListener);
            windowListener.win.removeWindowListener(windowListener);
            windowListener = null;
        }

        Runnable r = new Runnable() { public void run() {
            if (chromeAdapter==null) return;
            chromeAdapter.destroyBrowserWindow();
        }};
        mozAsyncExec(r);

        super.removeNotify();
    }

//    public void doDestroy() {
//        if (webBrowser!=null) {
//            if (MozillaExecutor.isMozillaThread()) {
//                //unregister progress listener
//                webBrowser.removeWebBrowserListener(win.mpa, nsIWebProgressListener.NS_IWEBPROGRESSLISTENER_IID);
////                if (win.ma.inModalLoop) {
////                    win.ma.exitModalEventLoop(NS_OK);
////                }
//            } else {
//                final nsIWebBrowser[] cached = new nsIWebBrowser[] { webBrowser };
//                mozAsyncExec(new Runnable() { public void run() {
////                    if (win.ma.inModalLoop) {
////                        win.ma.exitModalEventLoop(NS_OK);
////                    }
//                    cached[0].removeWebBrowserListener(win.mpa, nsIWebProgressListener.NS_IWEBPROGRESSLISTENER_IID);
//                    cached[0].getContainerWindow().destroyBrowserWindow();
//                    cached[0] = null;
//                    forceGC();
//                }});
//            }
//            //release reference to the webbrowser instance
//            //so that the webshell/docshell can be destroyed
//            webBrowser = null;
//            //force GC to release components of the window on
//            //the native side. This will also unregister the
//            //window from window watcher
//            forceGC();
//            //GtkUtils.destroyWindow(gtkPtr);
//        }
//    }

//    public void forceGC() {
//        //we have to try long enough for
//        //the gc to really execute
//        for (int i=0; i<3; i++) {
//            //trigger gc
//            System.gc();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                log.error("wait interrupted", e);
//            }
//        }
//    }

    private void onShow() {
        log.trace("onShow"); //$NON-NLS-1$
        // set the visibility on the thing
        if (chromeAdapter==null) {
        	eventBuffer.record("onShow"); //$NON-NLS-1$
        	return;
        }
        //Toolkit.getDefaultToolkit().sync();
        Runnable r = new Runnable() { public void run() {
            //Toolkit.getDefaultToolkit().sync();
        	if (chromeAdapter==null) {
            	eventBuffer.record("onShow"); //$NON-NLS-1$
            	return;
            }
            nsIBaseWindow baseWindow = qi(chromeAdapter.getWebBrowser(), nsIBaseWindow.class);
            baseWindow.setVisibility(true);
        }};
        mozAsyncExec(r);
    }

    private void onHide() {
        log.trace("onHide"); //$NON-NLS-1$
        if (chromeAdapter==null) {
        	eventBuffer.record("onHide"); //$NON-NLS-1$
        	return;
        }

        //Toolkit.getDefaultToolkit().sync();
        // set the visibility on the thing
        Runnable r = new Runnable() { public void run() {
            //Toolkit.getDefaultToolkit().sync();
	        if (chromeAdapter==null) {
	        	eventBuffer.record("onHide"); //$NON-NLS-1$
	        	return;
	        }
            nsIBaseWindow baseWindow = qi(chromeAdapter.getWebBrowser(), nsIBaseWindow.class);
            baseWindow.setVisibility(false);
        }};
        mozAsyncExec(r);
    }

    private void onResize() {
        log.trace("onResize"); //$NON-NLS-1$
        if (chromeAdapter==null) {
        	eventBuffer.record("onResize"); //$NON-NLS-1$
        	return;
        }
        //Toolkit.getDefaultToolkit().sync();
        //sync exec deadlocks on OSX when manually resizing window
        Runnable r = new Runnable() { public void run() {
        	//if (Platform.platform == Platform.Win32) Toolkit.getDefaultToolkit().sync();
        	Rectangle rect = getBounds();
            //sometimes (e.g. when opening javascript:
            //the height is be negative
            if (rect.isEmpty()) return;
	        if (chromeAdapter==null) {
	        	eventBuffer.record("onResize"); //$NON-NLS-1$
	        	return;
	        }
            nsIBaseWindow baseWindow = qi(chromeAdapter.getWebBrowser(), nsIBaseWindow.class);
            baseWindow.setPositionAndSize(rect.x, rect.y, rect.width, rect.height, true);
            if (Platform.usingGTK2Toolkit() && gtkPtr!=null) {
                gtk.gdk_window_resize(gtkPtr.window, rect.width, rect.height);
            }
        }};
        mozAsyncExec(r);
    }
    
    public void onAttachBrowser(ChromeAdapter chromeAdapter) {
        assert chromeAdapter!=null;
        assert this.chromeAdapter==null;

        this.chromeAdapter = chromeAdapter;

        //On osx, when user clicks on a textfield,
        // in the mozarea, mozilla paints focus
        //in that textfield.
        //
        //We are supposed to call activate(), but
        //I do not know a good way of listening
        //for that event.
        //
        //For example, GtkEmbed does this with hooking
        //on focus_in_event signal but this does not work
        //in our case because of using the GtkPlug or
        //XReparentWindow
        //
        //Also, there are events DOMFocusIn, DOMFocusOut, DOMActivate
        //but mozilla (xul1.8.1) does not send them correctly
        //
        //So, we listen for all mousedown events, and activate
        //mozarea on such event.
        //Activation on traversal keys (e.g. <Tab>) are
        //handled via onFocusMovedTo.
        if (hasFocus()) {
            //sync state
            onFocusMovedTo(this);
        }
        nsIWebBrowser brow = chromeAdapter.getWebBrowser();
        nsIDOMWindow2 win = qi(brow.getContentDOMWindow(), nsIDOMWindow2.class);
        nsIDOMEventTarget et = win.getWindowRoot();
        et.addEventListener("mousedown", ml, false); //$NON-NLS-1$
        //listen when focus moves to another swing component
        FocusWatcher.register(this);

        //sync with the enabled images flag
        if (!MozillaConfig.isEnabledImages()) {
            MozillaConfig.disableImages(chromeAdapter.getWindow());
        }

        //synchronize with current visibility state
        boolean vis = isVisible();
        nsIBaseWindow baseWindow = qi(chromeAdapter.getWebBrowser(), nsIBaseWindow.class);
        baseWindow.setVisibility(vis);
        
        eventBuffer.replayOn( this );
    }

    public void onDetachBrowser() {
        nsIWebBrowser brow = chromeAdapter.getWebBrowser();
        nsIDOMWindow2 win = qi(brow.getContentDOMWindow(), nsIDOMWindow2.class);
        nsIDOMEventTarget et = win.getWindowRoot();
        et.removeEventListener("mousedown", ml, false); //$NON-NLS-1$
        FocusWatcher.unregister(this);

        this.chromeAdapter = null;
    }

    private class MozMouseListener implements nsIDOMEventListener {
        public void handleEvent(nsIDOMEvent ev) {
            log.debug("dom event "+ev.getType()); //$NON-NLS-1$
            swingAsyncExec(new Runnable() { public void run() {
                if (lastFocusedCmp!=MozillaCanvas.this) {
                    MozillaCanvas.this.requestFocus();
                }
            }});
        }
        public nsISupports queryInterface(String uuid) {
            return Mozilla.queryInterface(this, uuid);
        }
    }
    private final MozMouseListener ml = new MozMouseListener();

    Component lastFocusedCmp = null;
    public void onFocusMovedTo(Component cmp) {
        if (cmp==lastFocusedCmp) return;
        log.debug("focus moved to: "+ //$NON-NLS-1$
                 (lastFocusedCmp==null?null:lastFocusedCmp.getClass().getSimpleName())+
                 " -> "+ //$NON-NLS-1$
                 (cmp==null?null:cmp.getClass().getSimpleName()));

        if (cmp==this) {
            if (lastFocusedCmp!=this && chromeAdapter!=null) {
                Runnable r = new Runnable() { public void run() {
                    if (chromeAdapter==null) return;
                    nsIWebBrowserFocus webBrowserFocus = qi(chromeAdapter.getWebBrowser(), nsIWebBrowserFocus.class);
                    webBrowserFocus.activate();
                    log.debug("-------mozilla activated"); //$NON-NLS-1$
                }};
                mozAsyncExec(r);
            }
        } else {
            if (lastFocusedCmp==this && chromeAdapter!=null) {
                Runnable r = new Runnable() { public void run() {
                    if (chromeAdapter==null) return;
                    nsIWebBrowserFocus webBrowserFocus = qi(chromeAdapter.getWebBrowser(), nsIWebBrowserFocus.class);

                    if (Platform.platform==Platform.OSX) {
                        //this code does not work on win32
                        //
                        //if clicking away from a focuse <a> element, into
                        //a JTextfield, mozilla does not un-paint the the
                        //dotted rectangle, so do that manually
                        try {
                            log.debug("blurring"); //$NON-NLS-1$
                            nsIDOMElement el = webBrowserFocus.getFocusedElement();
                            log.debug("el="+el); //$NON-NLS-1$
                            nsIDOMHTMLAnchorElement ael = qi(el, nsIDOMHTMLAnchorElement.class);
                            if (ael!=null) {
                                ael.blur();
                                log.debug("-------link blurred"); //$NON-NLS-1$
                            }
                        } catch (XPCOMException e) {
                            //ignore
                        }
                    }

                    webBrowserFocus.deactivate();
                    log.debug("-------mozilla deactivated"); //$NON-NLS-1$

                }};
                mozAsyncExec(r);
            }
        }

        lastFocusedCmp = cmp;
    }

}
