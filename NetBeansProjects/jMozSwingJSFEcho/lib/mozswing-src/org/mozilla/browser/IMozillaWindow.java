package org.mozilla.browser;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import org.mozilla.browser.impl.ChromeAdapter;
import org.mozilla.browser.impl.MozillaContainer;
import org.w3c.dom.Document;

/**
 * A browser window.
 *
 * <p>Contains methods for loading HTML documents,
 * querying content and manipulating state of
 * a browser window.
 *
 * <p>A set of callbacks named on*() is available
 * that can be overriden to be notified about various
 * mozilla callbacks and/or modify the default MozSwing's
 * handled of these callbacks.
 *
 * <p>
 * Two implementations of IMozillaWindow are available:
 * <ul>
 * <li> {@link MozillaWindow} is a ready to use JFrame subclass suited
 *   for smaller swing applications or server-side applications
 * <li> {@link MozillaPanel} subclasses JPanel and is more suitable
 *   for embedding into larger swing application. However, you might
 *   need to adapt toolbar, statusbar or popup handling to fulfill
 *   requirements of your application.
 * </ul>
 */
public interface IMozillaWindow {

    /**
     * Behaviour for toolbar and statusbar visibility.
     */
    public static enum VisibilityMode {
        /**
         * Mozilla (or javascript in webpage) decides
         * if toolbar and statusbar are visible or hidden.
         */
        DEFAULT,
        /**
         * Be always visible, ignoring mozilla when it says
         * to hide toolbar or statusbar
         */
        FORCED_VISIBLE,
        /**
         * Be always hidden, ignoring mozilla when it says
         * to show toolbar or statusbar.
         */
        FORCED_HIDDEN
    }

    /**
     * Returns title of this browser window
     *
     * @return window title
     */
    public String getTitle();
    /**
     * Sets title for this browser window
     *
     * @param title window title
     */
    public void setTitle(String title);

    /**
     * Request to set title for the browser window.
     * Callback from mozilla embedding code.
     *
     * <p>See {@link JFrame#setTitle(String)}
     *
     * @param title window title
     */
    public void onSetTitle(String title);
    /**
     * Request to show/hide the browser window
     * Callback from mozilla embedding code.
     *
     * <p>See {@link JFrame#setVisible(boolean)}
     *
     * @param visibility window visibility
     */
    public void onSetVisible(boolean visibility);
    /**
     * Request to resize the browser window.
     * Callback from mozilla embedding code.
     *
     * <p>See {@link JFrame#setSize(int, int)}
     *
     * @param w requested window width
     * @param h requested window height
     */
    public void onSetSize(int w, int h);
    /**
     * Request to show text in statusbar.
     * Callback from mozilla embedding code.
     *
     * @param text text for statusbar
     */
    public void onSetStatus(String text);
    /**
     * Request to set text in urlbar.
     * Callback from mozilla embedding code.
     *
     * @param url text to show in urlbar
     */
    public void onSetUrlbarText(String url);
    /**
     * Request to enable/disable the forward button.
     * Callback from mozilla embedding code.
     *
     * @param enabled true to enable the button,
     *   otherwise false
     */
    public void onEnableForwardButton(boolean enabled);
    /**
     * Enable/disable the back button.
     * Callback from mozilla embedding code.
     *
     * @param enabled true to enable the button,
     *   otherwise false
     */
    public void onEnableBackButton(boolean enabled);
    /**
     * Enable/disable the stop button.
     * Callback from mozilla embedding code.
     *
     * @param enabled true to enable the button,
     *   otherwise false
     */
    public void onEnableStopButton(boolean enabled);
    /**
     * Enable/disable the reload button.
     * Callback from mozilla embedding code.
     *
     * @param enabled true to enable the button,
     *   otherwise false
     */
    public void onEnableReloadButton(boolean enabled);

    /**
     * Notification when document loading starts
     * Callback from mozilla embedding code.
     */
    public void onLoadingStarted();
    /**
     * Notification when document loading end.
     * Callback from mozilla embedding code.
     */
    public void onLoadingEnded();
    /**
     * Request to close the browser window.
     * Callback from mozilla embedding code.
     */
    public void onCloseWindow();

    /**
     * Returns insets of this container
     *
     * <p>See {@link JFrame#getInsets()}
     *
     * @return insets of this container
     */
    public Insets getInsets();
    /**
     * Returns position on the screen
     *
     * <p>See {@link JFrame#getLocationOnScreen()}
     * or {@link JPanel#getLocationOnScreen()}
     *
     * @return position on the screen
     */
    public Point getLocationOnScreen();
    /**
     * Returns size of the browser window. It includes size
     * of toolbar, status and for {@link MozillaWindow}
     * also window decoration.
     *
     * <p>See {@link JFrame#getSize()} or {@link JPanel#getSize()}
     *
     * @return size of the window
     */
    public Dimension getSize();
    /**
     * See {@link Component#isShowing()}
     *
     * @return true if the component is showing
     */
    public boolean isShowing();

    /**
     * Notification, when mozilla browser is being added
     * into the given swing window.
     *
     * @param chromeAdapter mozilla browser being added
     *   into swing window/panel
     * @param parentChromeAdapter mozilla browser of parent
     * window, see {@link IMozillaWindow#getParentWindow()}
     */
    public void onAttachBrowser(ChromeAdapter chromeAdapter, ChromeAdapter parentChromeAdapter);
    /**
     * Notification when mozilla browser is being removed from swing window.
     */
    public void onDetachBrowser();
    /**
     * Returns mozilla browser in this swing window
     *
     * @return embedding adapter of mozilla browser in this swing window
     */
    public ChromeAdapter getChromeAdapter();
    /**
     * Return swing component with the mozilla
     * rendering area
     *
     * @return (MozSwing internal) swing component
     * with mozilla rendering area
     */
    public MozillaContainer getMozillaContainer();

    /**
     * Sets parent window of this window
     *
     * <p>From a  webpage, child window can be created
     * by opening an HTML popup or chrome window
     * using the openWindow() javascript function.
     *
     * @param parentWin parent browser window
     */
    public void setParentWindow(IMozillaWindow parentWin);
    /**
     * Returns parent window of this window.
     *
     * <p>From a  webpage, child window can be created
     * by opening an HTML popup or chrome window
     * using the openWindow() javascript function.
     *
     * @return parent browser window
     */
    public IMozillaWindow getParentWindow();

    /**
     * Triggers loading of web page with the given url
     * For blocking version, see {@link MozillaAutomation#blockingLoad(IMozillaWindow, String)}
     *
     * @param uri uri to be loaded
     */
    public void load(String uri);
    /**
     * Triggers loading of web page with the give content.
     * For blocking version, see {@link MozillaAutomation#triggerLoadHTML(IMozillaWindow, String, String)}
     *
     * @param content HTML content to be loaded
     */
    public void loadHTML(String content);
    /**
     * Triggers loading of web page with the give content.
     * Sets the given url as origin of the content.
     * For blocking version, see {@link MozillaAutomation#triggerLoadHTML(IMozillaWindow, String, String)}
     *
     * @param content HTML content to be loaded
     * @param asUrl alias URL
     */
    public void loadHTML(String content, String asUrl);
    /**
     * Triggers loading of previous page in the session history.
     * For blocking version, see {@link MozillaAutomation#blockingBack(IMozillaWindow)}
     */
    public void goBack();
    /**
     * Triggers loading of next page in the session history.
     * For blocking version, see {@link MozillaAutomation#blockingForward(IMozillaWindow)}
     */
    public void goForward();
    /**
     * Triggers re-loading of current page.
     * For blocking version, see {@link MozillaAutomation#blockingReload(IMozillaWindow)}
     */
    public void reload();
    /**
     * Stops loading of the page, if running.
     */
    public void stop();
    /**
     * Returns the current URL.
     *
     * @return url of current webpage
     */
    public String getUrl();

    /**
     * execute javascript code inside the current web page
     *
     * @param script javascript code to execute
     *
     * @return value returned from evaluated javascript code
     *   (not implemented ATM)
     */
    public Object jsexec(String script);

    /**
     * Request to dispatch an synthetized AWT event.
     *
     * <p>Used to emulate AWT mouse/key events using
     * DOM mouse/key events.
     *
     * @param e AWT event to be dispatched
     */
    public void onDispatchEvent(AWTEvent e);

    /**
     * Returns current toolbar visibility mode.
     * See {@link VisibilityMode}
     *
     * @return toolbar visibility mode
     */
    public VisibilityMode getToolbarVisibilityMode();
    /**
     * Returns current statusbar visibility mode.
     * See {@link VisibilityMode}
     *
     * @return statusbar visibility mode
     */
    public VisibilityMode getStatusbarVisibilityMode();

    /**
     * Returns chrome toolbar containing back, forward,
     * reload, stop buttons, urlbar and go botton.
     *
     * @return chrome toolbar
     */
    public JToolBar getToolbar();
    /**
     * Returns chrome statusbar.
     *
     * @return chrome statusbar
     */
    public JTextField getStatusbar();

    /**
     * Returns DOM document of the currently
     * loaded webpage.
     *
     * <p>This is a live DOM tree, so modifying
     * the tree is directly changing the webpage.
     *
     * @return DOM document of current webpage
     */
    public Document getDocument();
}
