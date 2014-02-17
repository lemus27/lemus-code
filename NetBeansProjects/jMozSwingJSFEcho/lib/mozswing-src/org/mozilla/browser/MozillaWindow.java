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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

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
 * Browser window.
 *
 * <p> MozillaWindow is a ready to use JFrame subclass suited
 * for smaller swing applications or server-side applications.
 * It implements GUI of standard browser window with
 * toolbar and statusbar and handles HTML popups.
 *
 * <p>Therefore, creating a browser in your code is as simple as:
 * <code>
 * <pre>
 *   MozillaWindow w = new MozillaWindow();
 *   w.setBounds(200, 200, 1024, 768);
 *   w.load("about:");
 *   w.setVisible(true);
 * </pre>
 * </code>
 *
 * <p>The result is displayed on the picture bellow:</p>
 * <img src="http://sourceforge.net/dbimage.php?id=150841"></img>
 * <p>
 */
public class MozillaWindow
    extends JFrame //Dialog
    implements IMozillaWindow
{
    private static final long serialVersionUID = 1L;

    static Log log = LogFactory.getLog(MozillaWindow.class);

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

    private Dimension initialPreferredlSize;

    private static int DEFAULT_WIDTH = 300;
    private static int DEFAULT_HEIGHT = 300;

    /**
     * Creates a new MozillaWindow with default
     * toolbar and statusbar visibility mode.
     */
    public MozillaWindow()
    {
        this(true, null, null);
    }

    /**
     * Creates a new MozillaWindow with the given
     * toolbar and statusbar visibility mode.
     *
     * @param toolbarVisMode toolbar visibility mode
     * @param statusbarVisMode status visibility mode
     */
    public MozillaWindow(VisibilityMode toolbarVisMode,
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
    public MozillaWindow(boolean attachNewBrowserOnCreation,
                         VisibilityMode toolbarVisMode,
                         VisibilityMode statusbarVisMode)
    {
        this.attachNewBrowserOnCreation = attachNewBrowserOnCreation;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        MozillaInitialization.initialize();

        this.toolbarVisMode = toolbarVisMode!=null ? toolbarVisMode : VisibilityMode.DEFAULT;
        this.statusbarVisMode = statusbarVisMode!=null ? statusbarVisMode : VisibilityMode.DEFAULT;

        this.initialPreferredlSize = new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        createChrome();
    }

    /**
     * Internal method.
     * Related to pre-creating of swing windows for {@link WindowCreator}.
     */
    public void attachNewBrowser() {
        assert isMozillaThread();
        WindowCreator.attachBrowser(MozillaWindow.this, CHROME_DEFAULT, null);
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
                setPreferredSize(initialPreferredlSize);
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

    protected void createChrome()
    {

//        if ((chromeMask & CHROME_MENUBAR)!=0) {
//            createMenu();
//        }

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
        backButton = new JChromeButton("back", mt.t("MozillaWindow.Tooltip_Back")); //$NON-NLS-1$ //$NON-NLS-2$
        backButton.setEnabled(false);
        backButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            goBack();
        }});
        toolbar.add(backButton);
        forwardButton = new JChromeButton("forward", mt.t("MozillaWindow.Tooltip_Forward")); //$NON-NLS-1$ //$NON-NLS-2$
        forwardButton.setEnabled(false);
        forwardButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            goForward();
        }});
        toolbar.add(forwardButton);
        reloadButton = new JChromeButton("reload", mt.t("MozillaWindow.Tooltip_Reload")); //$NON-NLS-1$ //$NON-NLS-2$
        reloadButton.setEnabled(false);
        reloadButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            reload();
        }});
        toolbar.add(reloadButton);
        stopButton = new JChromeButton("stop", mt.t("MozillaWindow.Tooltip_Stop")); //$NON-NLS-1$ //$NON-NLS-2$
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

        goButton = new JChromeButton("go", mt.t("MozillaWindow.Tooltip_Go")); //$NON-NLS-1$ //$NON-NLS-2$
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

        if (parentWin==null || !parentWin.isShowing()) return;

        Point pos = parentWin.getLocationOnScreen();
        if ((chromeMask & (nsIWebBrowserChrome.CHROME_CENTER_SCREEN))!=0) {
            Point p=new Point((int)parentWin.getSize().getWidth()/2, (int)parentWin.getSize().getHeight()/2);
            p.translate((int)-getSize().getWidth()/2, (int)-getSize().getHeight()/2);
            log.trace(String.format("adjustLocation: %d, %d", p.x, p.y)); //$NON-NLS-1$
            pos.translate(p.x, p.y);
            if (pos.x<0) pos.x=0;
            if (pos.y<0) pos.y=0;
        } else {
            //shift right,down by the height of title bar
            Insets insets = getInsets();
            pos.translate(insets.top, insets.top);
        }
        setLocation(pos);
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
        loadHTML(null, content);
    }

    public void loadHTML(final String content, final String asUrl) {
        if (content==null) return;
        if (chromeAdapter==null) {
            pendingContentToLoad = content;
            pendingContentUriToLoad = asUrl;
            return;
        }

        mozAsyncExec(new Runnable() { public void run() {
            MozillaAutomation.triggerLoadHTML(MozillaWindow.this, content, asUrl);
        }});
    }

    public String getUrl() {
        if (chromeAdapter==null) {
            return null;
        }
        return
            mozSyncExecQuiet(new Callable<String>() { public String call() {
                return MozillaAutomation.getCurrentURI(MozillaWindow.this);
            }});
    }

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
        setSize(w, h);
    }
    public void onSetTitle(String title) {
        setTitle(title);
    }
    public void onSetVisible(boolean visibility) {
        setVisible(visibility);
    }
    public void onLoadingStarted() {
    }
    public void onLoadingEnded() {
    }
    public void onCloseWindow() {
        onSetVisible(false);
        dispose();
    }
    public void onDispatchEvent(AWTEvent e) {
        super.processEvent(e);
    }

    public Document getDocument() {
        if (chromeAdapter==null)
            return null;
        else {
            return
                mozSyncExecQuiet(new Callable<Document>() { public Document call() {
                    nsIDOMDocument nsdoc = chromeAdapter.getWebBrowser().getContentDOMWindow().getDocument();
                    return (Document) NodeFactory.getNodeInstance(nsdoc);
                }});
        }
    }

    /**
     * Sets the initial preferred size of the Mozilla panel
     * used when the browser is attached
     *
     * @param preferredSize intial preferred size
     */
    public void setInitialPreferredlSize(Dimension preferredSize){
        this.initialPreferredlSize = preferredSize;
    }

    public VisibilityMode getToolbarVisibilityMode() { return toolbarVisMode; }
    public VisibilityMode getStatusbarVisibilityMode() { return statusbarVisMode; }
    public JToolBar getToolbar() { return toolbar; }
    public JTextField getStatusbar() { return statusField; }

    @SuppressWarnings("all") //javadoc only - how? //$NON-NLS-1$
    public static void main(String[] args) throws Exception {
        String url = "about:"; //$NON-NLS-1$
        if (args.length>=2 &&
            (args[0].equals("-url") || //$NON-NLS-1$
             args[0].equals("--url"))) //$NON-NLS-1$
        {
            url = args[1];
        }

        MozillaWindow frame = new MozillaWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 1024, 768);
        //frame.load(MozillaTest.resolveURL(""));
        frame.load(url);
        frame.setVisible(true);
    }

}
