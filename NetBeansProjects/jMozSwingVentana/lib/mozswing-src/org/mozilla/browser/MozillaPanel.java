package org.mozilla.browser;

import static org.mozilla.browser.MozillaExecutor.isMozillaThread;
import static org.mozilla.browser.MozillaExecutor.mozAsyncExec;
import static org.mozilla.browser.MozillaExecutor.mozSyncExecQuiet;
import static org.mozilla.browser.MozillaExecutor.swingAsyncExec;
import static org.mozilla.browser.XPCOMUtils.qi;
import static org.mozilla.interfaces.nsIWebBrowserChrome.CHROME_DEFAULT;
import static org.mozilla.interfaces.nsIWebBrowserChrome.CHROME_LOCATIONBAR;
import static org.mozilla.interfaces.nsIWebBrowserChrome.CHROME_STATUSBAR;
import static org.mozilla.interfaces.nsIWebBrowserChrome.CHROME_TOOLBAR;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.browser.impl.ChromeAdapter;
import org.mozilla.browser.impl.MozillaContainer;
import org.mozilla.browser.impl.WindowCreator;
import org.mozilla.browser.impl.components.JChromeButton;
import org.mozilla.dom.NodeFactory;
import org.mozilla.interfaces.nsIDOMDocument;
import org.mozilla.interfaces.nsIWebBrowserChrome;
import org.mozilla.interfaces.nsIWebNavigation;
import org.w3c.dom.Document;

/**
 * Browser component for embedding into more complex Swing GUI.
 *
 * <p> MozillaWindow is a {@link JPanel} subclass suitable for
 * embedding into larger swing application. However, you might
 * need to adapt toolbar, statusbar or popup handling to fulfill
 * requirements of your application.
 *
 * <p>We use sane defaults, therefore creating a browser
 * in your code is as simple as:
 * <code>
 * <pre>
 *   JFrame f = new JFrame();
 *   f.setSize(500, 600);
 *   MozillaPanel p = new MozillaPanel();
 *   p.load("about:");
 *   f.getContentPane().add(p);
 *   f.setVisible(true);
 * </pre>
 * </code>
 *
 * <!--FIXME
 * <p>The result is displayed on the picture bellow:</p>
 * <img src="http://sourceforge.net/dbimage.php?id=150841"></img>
 * <p>
 * -->
 */
public class MozillaPanel
    extends JPanel
    implements IMozillaWindow
{
    private static final long serialVersionUID = 1L;

    static Log log = LogFactory.getLog(MozillaPanel.class);

    protected final VisibilityMode toolbarVisMode;
    protected final VisibilityMode statusbarVisMode;

    protected JToolBar toolbar;
    protected JButton backButton, forwardButton, reloadButton, stopButton, goButton;
    protected JTextField urlBar;
    protected MozillaContainer mozContainer;
    protected JTextField statusField;

    protected IMozillaWindow parentWin;

    private ChromeAdapter chromeAdapter;

    private boolean attachNewBrowserOnCreation;

    private String pendingUriToLoad, pendingContentToLoad, pendingContentUriToLoad;

    private Dimension initialPreferredSize;

    private static int DEFAULT_WIDTH = 300;
    private static int DEFAULT_HEIGHT = 300;

    /**
     * true, if window/tab title should be
     * updated on mozilla callback
     */
    protected boolean updateTitle = true;

    /**
     * Creates a new MozillaPanel with default
     * toolbar and statusbar visibility mode.
     */
    public MozillaPanel()
    {
        this(true, null, null);
    }

    /**
     * Creates a new MozillaPanel with the given
     * toolbar and statusbar visibility mode.
     *
     * @param toolbarVisMode toolbar visibility mode
     * @param statusbarVisMode status visibility mode
     */
    public MozillaPanel(VisibilityMode toolbarVisMode,
                        VisibilityMode statusbarVisMode)
    {
        this(true, toolbarVisMode, statusbarVisMode);
    }

    /**
     * Internal constructor.
     *
     * @param attachNewBrowserOnCreation true if a new mozilla browser
     *   should be immediately attached. Used with false when pre-creating
     *   popups in {@link WindowCreator}
     * @param toolbarVisMode toolbar visibility mode
     * @param statusbarVisMode status visibility mode
     */
    public MozillaPanel(boolean attachNewBrowserOnCreation,
                        VisibilityMode toolbarVisMode,
                        VisibilityMode statusbarVisMode)
    {
        this.attachNewBrowserOnCreation = attachNewBrowserOnCreation;
        //setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        MozillaInitialization.initialize();

        this.toolbarVisMode = toolbarVisMode!=null ? toolbarVisMode : VisibilityMode.DEFAULT;
        this.statusbarVisMode = statusbarVisMode!=null ? statusbarVisMode : VisibilityMode.DEFAULT;

        this.initialPreferredSize = new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        createChrome();
    }

    /**
     * Internal method.
     * Related to pre-creating of swing windows for {@link WindowCreator}.
     */
    public void attachNewBrowser() {
        assert isMozillaThread();
        WindowCreator.attachBrowser(MozillaPanel.this, CHROME_DEFAULT, null);
    }

    public void onAttachBrowser(final ChromeAdapter chromeAdapter, final ChromeAdapter parentChromeAdapter) {
        assert isMozillaThread();
        assert chromeAdapter!=null;
        assert this.chromeAdapter==null;

        this.chromeAdapter = chromeAdapter;
        //notify swing canvas component, that the native area is ready
        mozContainer.onAttachBrowser(chromeAdapter);

        swingAsyncExec(new Runnable() { public void run() {
            final boolean showToolbar;
            {
                VisibilityMode vm = toolbarVisMode;
                if (vm==VisibilityMode.DEFAULT && parentChromeAdapter!=null)
                    vm = parentChromeAdapter.getWindow().getToolbarVisibilityMode();
                switch (vm) {
                    case FORCED_VISIBLE:
                        showToolbar = true;
                        break;
                    case FORCED_HIDDEN:
                        showToolbar = false;
                        break;
                    case DEFAULT:
                    default:
                        showToolbar = (chromeAdapter.getChromeFlags() & (CHROME_TOOLBAR|CHROME_LOCATIONBAR))!=0;
                }
            }
            toolbar.setVisible(showToolbar);

            final boolean showStatusbar;
            {
                VisibilityMode vm = statusbarVisMode;
                if (vm==VisibilityMode.DEFAULT && parentChromeAdapter!=null)
                    vm = parentChromeAdapter.getWindow().getStatusbarVisibilityMode();
                switch (vm) {
                case FORCED_VISIBLE:
                    showStatusbar = true;
                    break;
                case FORCED_HIDDEN:
                    showStatusbar = false;
                    break;
                case DEFAULT:
                default:
                    showStatusbar =
                        (chromeAdapter.getChromeFlags() & CHROME_STATUSBAR)!=0 &&
                        (chromeAdapter.getChromeFlags() & nsIWebBrowserChrome.CHROME_OPENAS_DIALOG)==0;
                }
            }
            statusField.setVisible(showStatusbar);

            adjustLocation(chromeAdapter.getChromeFlags());
            if (parentWin!=null) {
                setSize(parentWin.getSize());
            } else {
                setPreferredSize(initialPreferredSize);
            }
            if (pendingUriToLoad!=null)
                load(pendingUriToLoad);
            else if (pendingContentToLoad!=null)
                loadHTML(pendingContentToLoad, pendingContentUriToLoad);
            pendingUriToLoad = pendingContentToLoad = pendingContentUriToLoad = null;
        }});
    }

    public void onDetachBrowser() {
        assert isMozillaThread();
        this.chromeAdapter = null;
        mozContainer.onDetachBrowser();
    }

    protected void createChrome() {

//        if ((chromeMask & CHROME_MENUBAR)!=0) {
//            createMenu();
//        }

        setLayout(new BorderLayout());
        createToolbar();
        toolbar.setVisible(false);
        createMozillaPanel();
        createStatusbar();
        statusField.setVisible(false);
    }

    protected void createToolbar()
    {
        toolbar = new JToolBar();
        toolbar.setFloatable(false); //bug #18229
        add(toolbar, BorderLayout.NORTH);
        backButton = new JChromeButton("back", mt.t("MozillaPanel.Tooltip_Back")); //$NON-NLS-1$ //$NON-NLS-2$
        backButton.setEnabled(false);
        backButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            goBack();
        }});
        toolbar.add(backButton);
        forwardButton = new JChromeButton("forward", mt.t("MozillaPanel.Tooltip_Forward")); //$NON-NLS-1$ //$NON-NLS-2$
        forwardButton.setEnabled(false);
        forwardButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            goForward();
        }});
        toolbar.add(forwardButton);
        reloadButton = new JChromeButton("reload", mt.t("MozillaPanel.Tooltip_Reload")); //$NON-NLS-1$ //$NON-NLS-2$
        reloadButton.setEnabled(false);
        reloadButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            reload();
        }});
        toolbar.add(reloadButton);
        stopButton = new JChromeButton("stop", mt.t("MozillaPanel.Tooltip_Stop")); //$NON-NLS-1$ //$NON-NLS-2$
        stopButton.setEnabled(false);
        stopButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            stop();
        }});
        toolbar.add(stopButton);

        //enclose the textfield into a panel,
        //to keep the default height
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        urlBar = new JTextField();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0;
        c.weightx = 1.0; c.weighty = 0.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        p.add(urlBar, c);
        urlBar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            String uri = urlBar.getText();
            load(uri);
        }});
        toolbar.add(p);

        goButton = new JChromeButton("go", mt.t("MozillaPanel.Tooltip_Go")); //$NON-NLS-1$ //$NON-NLS-2$
        goButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            String uri = urlBar.getText();
            load(uri);
        }});
        toolbar.add(goButton);
    }

    protected void createMozillaPanel()
    {
        //enclose the awt canvas component with mozilla
        //(MozCanvas) into a jpanel (MozContainer).
        //otherwise relative positioning in a layout of the
        //parent component and focus traversal do not work
        //properly
        mozContainer = new MozillaContainer();
        add(mozContainer, BorderLayout.CENTER);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if (MozillaInitialization.isInitialized()) {
            MozillaInitialization.winCreator.ensurePrecreatedWindows();
            if (attachNewBrowserOnCreation) {
                Runnable r = new Runnable() { public void run() {
                    attachNewBrowser();
                }};
                mozAsyncExec(r);
            }
        }
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
    }

    protected void createStatusbar()
    {
        statusField = new JTextField(""); //$NON-NLS-1$
        statusField.setEditable(false);
        statusField.setFocusable(false);
        add(statusField, BorderLayout.SOUTH);
    }

    protected void adjustLocation(long chromeMask){
        //usually can't move, so ignore by default
    }

    public void goBack() {
        mozAsyncExec(new Runnable() { public void run() {
            nsIWebNavigation nav = qi(chromeAdapter.getWebBrowser(), nsIWebNavigation.class);
            nav.goBack();
        }});
    }

    public void goForward() {
        if (chromeAdapter==null) return;
        mozAsyncExec(new Runnable() { public void run() {
            nsIWebNavigation nav = qi(chromeAdapter.getWebBrowser(), nsIWebNavigation.class);
            nav.goForward();
        }});
    }

    public void reload() {
        if (chromeAdapter==null) return;
        mozAsyncExec(new Runnable() { public void run() {
            nsIWebNavigation nav = qi(chromeAdapter.getWebBrowser(), nsIWebNavigation.class);
            nav.reload(nsIWebNavigation.LOAD_FLAGS_NONE);
        }});
    }

    public void stop() {
        if (chromeAdapter==null) return;
        mozAsyncExec(new Runnable() { public void run() {
            nsIWebNavigation nav = qi(chromeAdapter.getWebBrowser(), nsIWebNavigation.class);
            nav.stop(nsIWebNavigation.STOP_ALL);
        }});
    }

    public void load(final String uri) {
        if (uri==null || uri.length()==0) return;
        if (chromeAdapter==null) {
            pendingUriToLoad = uri;
            return;
        }

        mozAsyncExec(new Runnable() { public void run() {
            nsIWebNavigation nav = qi(chromeAdapter.getWebBrowser(), nsIWebNavigation.class);
            nav.loadURI(uri, nsIWebNavigation.LOAD_FLAGS_NONE, null, null, null);
        }});
    }

    public void loadHTML(final String content) {
        loadHTML(content, null);
    }

    public void loadHTML(final String content, final String asUrl) {
        if (content==null) return;
        if (chromeAdapter==null) {
            pendingContentToLoad = content;
            pendingContentUriToLoad = asUrl;
            return;
        }

        mozAsyncExec(new Runnable() { public void run() {
            MozillaAutomation.triggerLoadHTML(MozillaPanel.this, content, asUrl);
        }});
    }

    public String getUrl() {
        if (chromeAdapter==null) {
            return null;
        }
        return
            mozSyncExecQuiet(new Callable<String>() { public String call() {
                return MozillaAutomation.getCurrentURI(MozillaPanel.this);
            }});
    }

    /**
     * execute javascript code inside the current web page
     */
    public Object jsexec(String script) {
        if (chromeAdapter==null) return null;
        return MozillaAutomation.executeJavascript(this, script);
    }

    public MozillaContainer getMozillaContainer() {
        return mozContainer;
    }

    public ChromeAdapter getChromeAdapter() {
        return chromeAdapter;
    }

    public void setParentWindow(IMozillaWindow parentWin) {
        this.parentWin = parentWin;
    }
    public IMozillaWindow getParentWindow() {
        return parentWin;
    }

    public void onSetStatus(String text) {
        statusField.setText(text);
    }
    public void onEnableBackButton(boolean enabled) {
        backButton.setEnabled(enabled);
    }
    public void onEnableForwardButton(boolean enabled) {
        forwardButton.setEnabled(enabled);
    }
    public void onEnableReloadButton(boolean enabled) {
        reloadButton.setEnabled(true);
    }
    public void onEnableStopButton(boolean enabled) {
        stopButton.setEnabled(enabled);
    }
    public void onSetUrlbarText(String url) {
        urlBar.setText(url);
    }
    public void onSetSize(int w, int h) {
        //usually can't resize, so ignore by default
        log.debug("ignored onSetSize in mozpanel"); //$NON-NLS-1$
    }

    /**
     * By default, MozillaPanel updates title of the
     * window or tab where it is embedded.
     * Using this method you can enable/disable
     * that behaviour.
     *
     * @param updateTitle flag enabling/disabling
     *  updating of the window or tab title
     */
    public void setUpdateTitle(boolean updateTitle){
        this.updateTitle = updateTitle;
    }
    /**
     * Callback when mozilla is requesting us to set
     * the window title
     */
    public void onSetTitle(String title) {
        if (updateTitle){
            setTitle(title);
        }
    }
    public void setTitle(String title) {
        //check if we are contained in a JTabbedPane
        Component tab = this;
        Container tabPane = this.getParent();
        while(tabPane != null && !(tabPane instanceof JTabbedPane)) {
            tab = tabPane;
            tabPane = tabPane.getParent();
        }

        if (tabPane!=null) {
            JTabbedPane tabPane2 = (JTabbedPane) tabPane;
            int idx = tabPane2.indexOfComponent(tab);
            if (idx>=0) {
                tabPane2.setTitleAt(idx, title);
            }
        } else {
            Window win = SwingUtilities.getWindowAncestor(this);
            if (win instanceof Frame) {
                Frame f = (Frame) win;
                f.setTitle(title);
            } else if (win instanceof Dialog) {
                Dialog d = (Dialog) win;
                d.setTitle(title);
            }
        }
    }
    public String getTitle() {
        //check if we are contained in a JTabbedPane
        Component tab = this;
        Container tabPane = this.getParent();
        while(tabPane != null && !(tabPane instanceof JTabbedPane)) {
            tab = tabPane;
            tabPane = tabPane.getParent();
        }

        if (tabPane!=null) {
            JTabbedPane tabPane2 = (JTabbedPane) tabPane;
            int idx = tabPane2.indexOfComponent(tab);
            if (idx>=0) {
                return tabPane2.getTitleAt(idx);
            } else {
                log.error("Unknown tab index"); //$NON-NLS-1$
                return ""; //$NON-NLS-1$
            }
        } else {
            Window win = SwingUtilities.getWindowAncestor(this);
            if (win instanceof Frame) {
                Frame f = (Frame) win;
                return f.getTitle();
            } else if (win instanceof Dialog) {
                Dialog d = (Dialog) win;
                return d.getTitle();
            } else {
                log.error("Unknown window type"); //$NON-NLS-1$
                return ""; //$NON-NLS-1$
            }
        }
    }
    public void onSetVisible(boolean visibility) {
        setVisible(visibility);
    }
    public void onLoadingStarted() {}
    public void onLoadingEnded() {}
    public void onCloseWindow() {
        //subclass should override this method
        log.debug("Ignoring mozilla request to close the panel"); //$NON-NLS-1$
    }

    public void onDispatchEvent(AWTEvent e) {
        super.processEvent(e);
    }

    public Document getDocument() {
        if (chromeAdapter==null)
            return null;
        else {
            nsIDOMDocument nsdoc = chromeAdapter.getWebBrowser().getContentDOMWindow().getDocument();
            return (Document) NodeFactory.getNodeInstance(nsdoc);
        }
    }

    /**
     * Sets the initial preferred size of the Mozilla panel
     * used when the browser is attached
     *
     * @param preferredSize intial preferred size
     */
    public void setInitialPreferredSize(Dimension preferredSize){
        this.initialPreferredSize = preferredSize;
    }

    public VisibilityMode getToolbarVisibilityMode() { return toolbarVisMode; }
    public VisibilityMode getStatusbarVisibilityMode() { return statusbarVisMode; }
    public JToolBar getToolbar() { return toolbar; }
    public JTextField getStatusbar() { return statusField; }

    @SuppressWarnings("all") //javadoc only - how? //$NON-NLS-1$
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        MozillaPanel moz = new MozillaPanel();
        //moz.load(MozillaTest.resolveURL(""));
        moz.load("about:"); //$NON-NLS-1$

        frame.getContentPane().add(moz);
        frame.setVisible(true);
    }

}
