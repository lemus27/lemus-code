package org.mozilla.browser.impl;

import static org.mozilla.browser.MozillaExecutor.mozEnterModalEventLoop;
import static org.mozilla.browser.MozillaExecutor.swingAsyncExec;
import static org.mozilla.browser.XPCOMUtils.qi;
import static org.mozilla.xpcom.IXPCOMError.NS_ERROR_INVALID_ARG;
import static org.mozilla.xpcom.IXPCOMError.NS_OK;

import java.awt.Insets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.browser.IMozillaWindow;
import org.mozilla.browser.MozillaExecutor;
import org.mozilla.browser.XPCOMUtils;
import org.mozilla.browser.impl.components.JFakeTooltip;
import org.mozilla.interfaces.nsIBaseWindow;
import org.mozilla.interfaces.nsIDOMWindow;
import org.mozilla.interfaces.nsIEmbeddingSiteWindow;
import org.mozilla.interfaces.nsIInterfaceRequestor;
import org.mozilla.interfaces.nsIJSContextStack;
import org.mozilla.interfaces.nsISupports;
import org.mozilla.interfaces.nsITooltipListener;
import org.mozilla.interfaces.nsIWebBrowser;
import org.mozilla.interfaces.nsIWebBrowserChrome;
import org.mozilla.interfaces.nsIWebBrowserChromeFocus;
import org.mozilla.xpcom.Mozilla;
import org.mozilla.xpcom.XPCOMException;

/**
 * The core component in implementation of the
 * mozilla embedding layer.
 *
 * nsIEmbeddingSiteWindow must be implemented by
 * the same object that implmenets nsIWebBrowserChrome
 * otherwise nsDocShellTreeOwner::GetOwnerWin() fails
 * and the SetVisible method will not be called.
 */
public class ChromeAdapter
    implements
        nsIWebBrowserChrome,
        nsIWebBrowserChromeFocus,
        nsIEmbeddingSiteWindow,
        nsIInterfaceRequestor,
        nsITooltipListener
{

    static Log log = LogFactory.getLog(ChromeAdapter.class);

    /**
     *
     */
    protected final IMozillaWindow window;

    /**
     * true if this is a chrome window, i.e.
     * displays some XUL content
     */
    private boolean isChromeWindow;

    /**
     * Visibility of chrome widgets, i.e.
     * menu, toolbar, statusbar
     */
    private long chromeFlags;

    private final nsIWebBrowser webBrowser;

    /**
     * listener for content events in mozilla browser
     */
    private final ContentAdapter contentAdapter;
    /**
     * listener for loading events in mozilla browser
     */
    private final ProgressAdapter progressAdapter;

    /**
     * listener for dom events in mozilla browser
     */
    private final DOMAdapter domAdapter;

    public ChromeAdapter(IMozillaWindow window,
                         nsIWebBrowser webBrowser,
                         long chromeFlags)
    {
        this.window = window;
        this.webBrowser = webBrowser;
        this.contentAdapter = new ContentAdapter(this);
        this.progressAdapter = new ProgressAdapter(this);
        this.domAdapter = new DOMAdapter(this);

        // set flag on the new window if it's a chrome open
        this.isChromeWindow = (chromeFlags & nsIWebBrowserChrome.CHROME_OPENAS_CHROME)!=0;
        this.chromeFlags = chromeFlags;

    }

    //title that mozilla should think we have
    private String mozTitle;

    public String getTitle() {
        assert MozillaExecutor.isMozillaThread();
        return mozTitle;
    }

    public void setTitle(final String title) {
        assert MozillaExecutor.isMozillaThread();
        log.trace("setTitle="+title); //$NON-NLS-1$
        this.mozTitle = title;
        swingAsyncExec(new Runnable() { public void run() {
            window.onSetTitle(title);
        }});
    }

    public long getSiteWindow() {
        assert MozillaExecutor.isMozillaThread();
        return window.getMozillaContainer().getMozillaCanvas().getHandle();
    }

    public void setFocus() {
        assert MozillaExecutor.isMozillaThread();
        nsIBaseWindow baseWindow = qi(webBrowser, nsIBaseWindow.class);
        baseWindow.setFocus();
    }

    //visibility status that mozilla should think we have
    private boolean mozVisibility = false;

    public void setVisibility(boolean aVisibility) {
        assert MozillaExecutor.isMozillaThread();
        log.trace("setVisibility="+aVisibility); //$NON-NLS-1$

        // We always set the visibility so that if it's chrome and we finish
        // the load we know that we have to show the window.
        mozVisibility = aVisibility;

        // if this is a chrome window and the chrome hasn't finished loading
        // yet then don't show the window yet.
        if (isChromeWindow && !chromeLoaded)
          return;

        swingAsyncExec(new Runnable() { public void run() {
            window.onSetVisible(mozVisibility);
        }});
    }

    public boolean getVisibility() {
        assert MozillaExecutor.isMozillaThread();
        log.trace("getVisibility="+mozVisibility); //$NON-NLS-1$

        // Work around the problem that sometimes the window
        // is already visible even though mVisibility isn't true
        // yet.
        return mozVisibility ||
               (!isChromeWindow &&
                 window.getMozillaContainer()!=null &&
                 window.getMozillaContainer().isDisplayable());//is this needed?
    }

    public void getDimensions(long flags, int[] x, int[] y, int[] cx, int[] cy) {
        assert MozillaExecutor.isMozillaThread();
        log.trace("getDimension"); //$NON-NLS-1$

        nsIBaseWindow baseWindow = qi(webBrowser, nsIBaseWindow.class);
        if ((flags & DIM_FLAGS_POSITION)!=0 &&
            (flags & (DIM_FLAGS_SIZE_INNER|DIM_FLAGS_SIZE_OUTER))!=0)
        {
            baseWindow.getPositionAndSize(x, y, cx, cy);
        } else if ((flags & DIM_FLAGS_POSITION)!=0) {
            baseWindow.getPosition(x, y);
        } else if ((flags & (DIM_FLAGS_SIZE_INNER|DIM_FLAGS_SIZE_OUTER))!=0) {
            baseWindow.getSize(cx, cy);
        } else {
            throw new XPCOMException(NS_ERROR_INVALID_ARG);
        }
    }

    public void setDimensions(long flags, int x, int y, int cx, int cy) {
        assert MozillaExecutor.isMozillaThread();
        log.trace(String.format("setDimension flag=%d %d,%d %dx%d\n", flags, x, y, cx, cy)); //$NON-NLS-1$

        nsIBaseWindow baseWindow = qi(webBrowser, nsIBaseWindow.class);
        if ((flags & DIM_FLAGS_POSITION)!=0 &&
            ((flags & (DIM_FLAGS_SIZE_INNER|DIM_FLAGS_SIZE_OUTER)))!=0)
        {
            baseWindow.setPositionAndSize(x, y, cx, cy, true);
        } else if ((flags & DIM_FLAGS_POSITION)!=0) {
            baseWindow.setPosition(x, y);
        } else if ((flags & (DIM_FLAGS_SIZE_INNER|DIM_FLAGS_SIZE_OUTER))!=0) {
            baseWindow.setSize(cx, cy, true);
        } else {
            throw new XPCOMException(NS_ERROR_INVALID_ARG);
        }
    }

    // nsIWebBrowserChrome methods

    public nsIWebBrowser getWebBrowser() {
        assert MozillaExecutor.isMozillaThread();
        return webBrowser;
    }
    public void setWebBrowser(nsIWebBrowser aWebBrowser) {
        assert MozillaExecutor.isMozillaThread();
        //we already have it
        assert webBrowser!=aWebBrowser;
    }
    public long getChromeFlags() { return chromeFlags; }
    public void setStatus(long statusType, final String status) {
        assert MozillaExecutor.isMozillaThread();
        log.trace("set status="+status); //$NON-NLS-1$

        swingAsyncExec(new Runnable() { public void run() {
            window.onSetStatus(status);
        }});
    }
    public void setChromeFlags(long aChromeFlags) {
        assert MozillaExecutor.isMozillaThread();
        log.trace(String.format("setChromeFlags %d", aChromeFlags)); //$NON-NLS-1$
    }
    public void destroyBrowserWindow() {
        assert MozillaExecutor.isMozillaThread();
        log.trace("destroyBrowserWindow"); //$NON-NLS-1$
        if (inModalLoop) exitModalEventLoop(NS_OK);
        //setVisibility(false);
        WindowCreator.detachBrowser(window);
        swingAsyncExec(new Runnable() { public void run() {
            window.onCloseWindow();
        }});
    }
    public void sizeBrowserTo(final int aCX, final int aCY) {
        assert MozillaExecutor.isMozillaThread();
        log.trace(String.format("sizeBrowserTo %d %d", aCX, aCY)); //$NON-NLS-1$
        swingAsyncExec(new Runnable() { public void run() {
            Insets insets = window.getInsets();
            int width = aCX + insets.left + insets.right;
            int height = aCY + insets.top + insets.bottom;
            window.onSetSize(width, height);
            //FIXME window.adjustLocation();
        }});
    }

    boolean inModalLoop = false;

    public void showAsModal() {
        assert MozillaExecutor.isMozillaThread();
        log.trace("showAsModal"); //$NON-NLS-1$
        //create JS context, that will hold the 'arguments'
        //passed via JS code in some XUL files, e.g.
        //content/global/commonDialog.xul
        nsIJSContextStack stack = XPCOMUtils.getService("@mozilla.org/js/xpc/ContextStack;1", nsIJSContextStack.class); //$NON-NLS-1$
        stack.push(0);
        inModalLoop = true;
        mozEnterModalEventLoop(this);
        long cx = stack.pop();
        assert cx==0;
    }

    public boolean isWindowModal() {
        log.trace("isWindowModal"); //$NON-NLS-1$
        return inModalLoop;
    }

    public void exitModalEventLoop(long aStatus) {
        log.trace("exitModalEventLoop"); //$NON-NLS-1$
        MozillaExecutor.mozExitModalEventLoop();
        inModalLoop = false;
    }

    // nsIWebBrowserChromeFocus

    public void focusNextElement() {
        //called when focus is leaving the gecko area and
        //we should set focus to next swing component
        log.trace("focusNextElement"); //$NON-NLS-1$
        swingAsyncExec(new Runnable() { public void run() {
            window.getMozillaContainer().getMozillaCanvas().transferFocus();
        }});
    }

    public void focusPrevElement() {
        //called when focus is leaving the gecko area and
        //we should set focus to previous swing component
        log.trace("focusPrevElement"); //$NON-NLS-1$
        swingAsyncExec(new Runnable() { public void run() {
            window.getMozillaContainer().getMozillaCanvas().transferFocusBackward();
        }});
    }

    // nsIInterfaceRequestor methods

    public nsISupports getInterface(String riid) {
        if (riid.equals(nsIDOMWindow.NS_IDOMWINDOW_IID)) {
            //otherwise when creating a new window the code
            //in nsWindowWatcher::OpenWindowJSInternal after calling
            //  mWindowCreator->CreateChromeWindow(...)
            //fails on the test
            //  nsCOMPtr<nsIDOMWindow> newWindow(do_GetInterface(newChrome));
            //  if (newWindow)...
            nsIDOMWindow contentDOMWindow = webBrowser.getContentDOMWindow();
            return contentDOMWindow;
        }
        return queryInterface(riid);
    }

    // nsISupports methods

    public nsISupports queryInterface(String uuid) {
        return Mozilla.queryInterface(this, uuid);
    }

    // has the chrome finished loading?
    private boolean chromeLoaded;

    public void contentFinishedLoading() {
        if (isChromeWindow) {
            // We're done loading.
            chromeLoaded = true;

            // get the content DOM window for that web browser
            nsIDOMWindow domWindow = webBrowser.getContentDOMWindow();
            if (domWindow==null) {
                //NS_WARNING("no dom window in content finished loading\n");
                return;
            }

            // resize the content
            domWindow.sizeToContent();

            // and since we're done loading show the window, assuming that the
            // visibility flag has been set.
            boolean visibility = getVisibility();
            if (visibility)
                setVisibility(true);
        }
    }

    public IMozillaWindow getWindow() {
        return window;
    }

    private JFakeTooltip tooltip;

    public void onShowTooltip(final int XCoords , final int YCoords , final String tipText  ) {
        assert MozillaExecutor.isMozillaThread();
        log.trace(String.format("shot tooltip: %d,%d '%s'", XCoords, YCoords, tipText)); //$NON-NLS-1$
        swingAsyncExec(new Runnable() { public void run() {
            if (tooltip==null) tooltip = JFakeTooltip.create(window);
            tooltip.setup(XCoords, YCoords, tipText);
            tooltip.setVisible(true);
        }});
    }

    public void onHideTooltip() {
        assert MozillaExecutor.isMozillaThread();
        log.trace("hide tip"); //$NON-NLS-1$
        swingAsyncExec(new Runnable() { public void run() {
            if (tooltip==null) return;
            tooltip.setVisible(false);
            tooltip.dispose();
            tooltip = null;
        }});
    }

    public ContentAdapter getContentAdapter() {
        return contentAdapter;
    }

    public ProgressAdapter getProgressAdapter() {
        return progressAdapter;
    }

    public DOMAdapter getDOMAdapter() {
        return domAdapter;
    }

    public boolean isChromeWindow() {
        return isChromeWindow;
    }

}