package org.mozilla.browser;

import static org.mozilla.browser.MozillaExecutor.mozSyncExec;
import static org.mozilla.browser.MozillaExecutor.mozPostponableSyncExec;
import static org.mozilla.browser.XPCOMUtils.getService;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.browser.common.Platform;
import org.mozilla.browser.impl.ChromeAdapter;
import org.mozilla.interfaces.nsICache;
import org.mozilla.interfaces.nsICacheService;
import org.mozilla.interfaces.nsICookieManager;
import org.mozilla.interfaces.nsIDOMWindowInternal;
import org.mozilla.interfaces.nsIDocShell;
import org.mozilla.interfaces.nsIInterfaceRequestor;
import org.mozilla.interfaces.nsIPrefBranch;
import org.mozilla.interfaces.nsISimpleEnumerator;
import org.mozilla.interfaces.nsIWebBrowser;
import org.mozilla.interfaces.nsIWebBrowserChrome;
import org.mozilla.interfaces.nsIWindowWatcher;

/**
 * Functions for configuring the mozilla browser.
 */
public class MozillaConfig {

    static Log log = LogFactory.getLog(MozillaConfig.class);

    /**
     * directory with XULRunner binaries
     */
    private static File xulRunnerHome; // = new File("..\\..\\xulrunner19-build\\build\\compile\\mozilla\\dist\\bin").getAbsoluteFile();

    /**
     * Set directory where xulrunner binaries are located.
     *
     * If set to null, mozswing will try to locate
     * the xulrunner binaries automatically.
     *
     * @param xulDir directory with XULRunner binaries
     */
    public static void setXULRunnerHome(File xulDir) {
        MozillaConfig.xulRunnerHome = xulDir;
    }
    /**
     * Returns directory, where xulrunner binaries are located.
     *
     * @return directory with XULRunner binaries
     */
    public static File getXULRunnerHome() {
        return xulRunnerHome;
    }

    /**
     * directory with xulrunner binaries
     */
    private static boolean initializeWithProfile = true;
    /**
     * Returns true if profile is/was enabled on
     * mozilla initialization.
     *
     * @return true if enable profile on mozilla
     * initialization
     */
    public static boolean isInitializeWithProfile() {
        return initializeWithProfile;
    }
    /**
     * Configure whether enable profile during mozilla
     * initialization.
     *
     * <p>When profile is disabled, some advanced functionality
     * in mozilla might not work as expected.
     * (for example save-as chrome dialog will not work)
     *
     * @param profileOn true if enable profiles on
     * mozilla initialization
     */
    public static void setInitializeWithProfile(boolean profileOn) {
        MozillaConfig.initializeWithProfile = profileOn;
    }

    /**
     * Directory, where profile is created
     */
    private static File profileDir;

    /**
     * Set directory where profile should be created.
     *
     * @param profileDir profile directory
     */
    public static void setProfileDir(File profileDir) {
        MozillaConfig.profileDir = profileDir;
    }
    /**
     * Returns directory, where profile is created.
     *
     * @return profile directory
     */
    public static File getProfileDir() {
        return profileDir;
    }


    /**
     * true, if enabled loading of images in webpages
     */
    private static boolean isEnabledImages = true;

    /**
     * Enable loading of images (in all windows).
     */
    public static void enableImages() {
        mozPostponableSyncExec(new Runnable() { public void run() {
            /*
            normally should be sufficient to set a preference,
            but content-blocker extension is not part of xulrunner build

            //http://kb.mozillazine.org/Permissions.default.image
            // 1-Accept, 2-Deny, 3-dontAcceptForeign
            nsIPrefBranch pref = getService("@mozilla.org/preferences-service;1", nsIPrefBranch.class);
            pref.setIntPref("permissions.default.image", 1);
            */

            isEnabledImages = true;
            //enable images in existing windows
            applyForAllWindows(new DocShellApplyTask() { public void apply(nsIDocShell ds) {
                ds.setAllowImages(true);
            }});
        }});
    }

    /**
     * Disable loading of images (in all windows).
     */
    public static void disableImages() {
        mozPostponableSyncExec(new Runnable() { public void run() {
            /*
            normally should be sufficient to set a preference,
            but content-blocker extension is not part of xulrunner build

            //http://kb.mozillazine.org/Permissions.default.image
            // 1-Accept, 2-Deny, 3-dontAcceptForeign
            nsIPrefBranch pref = getService("@mozilla.org/preferences-service;1", nsIPrefBranch.class);
            pref.setIntPref("permissions.default.image", 2);
            */

            isEnabledImages = false;

            //disable images in existing windows
            applyForAllWindows(new DocShellApplyTask() { public void apply(nsIDocShell ds) {
                ds.setAllowImages(false);
            }});
        }});
    }

    /**
     * Enable loading of images in the given window.
     *
     * @param win browser window where to enable loading of images
     */
    public static void enableImages(final IMozillaWindow win) {
        mozSyncExec(new Runnable() { public void run() {
            ChromeAdapter chromeAdapter = win.getChromeAdapter();
            if (chromeAdapter==null) return;

            nsIWebBrowser webBrowser = chromeAdapter.getWebBrowser();
            nsIInterfaceRequestor ir = XPCOMUtils.qi(webBrowser, nsIInterfaceRequestor.class);
            nsIDocShell docShell = (nsIDocShell) ir.getInterface(nsIDocShell.NS_IDOCSHELL_IID);
            docShell.setAllowImages(false);
        }});
    }

    /**
     * Disable loading of images in the given window.
     *
     * @param win browser window where to disable loading of images
     */
    public static void disableImages(final IMozillaWindow win) {
        mozSyncExec(new Runnable() { public void run() {
            ChromeAdapter chromeAdapter = win.getChromeAdapter();
            if (chromeAdapter==null) return;

            nsIWebBrowser webBrowser = chromeAdapter.getWebBrowser();
            nsIInterfaceRequestor ir = XPCOMUtils.qi(webBrowser, nsIInterfaceRequestor.class);
            nsIDocShell docShell = (nsIDocShell) ir.getInterface(nsIDocShell.NS_IDOCSHELL_IID);
            docShell.setAllowImages(false);
        }});
    }

    /**
     * Returns true, if loading of images in webpages is enabled.
     *
     * @return true if enabled loading of images in webpages
     */
    public static boolean isEnabledImages() {
        return isEnabledImages;
    }

    /**
     * true, if enabled javascript execution in webpages
     */
    private static boolean isEnabledJavascript = true;

    /**
     * Enable Javascript execution (in all windows).
     */
    public static void enableJavascript() {
        mozPostponableSyncExec(new Runnable() { public void run() {
            isEnabledJavascript = true;

            //enable images in existing windows
            applyForAllWindows(new DocShellApplyTask() { public void apply(nsIDocShell ds) {
                ds.setAllowJavascript(true);
            }});
        }});
    }

    /**
     * Disable Javascript execution (in all windows).
     */
    public static void disableJavascript() {
        mozPostponableSyncExec(new Runnable() { public void run() {
            isEnabledJavascript = false;

            //disable images in existing windows
            applyForAllWindows(new DocShellApplyTask() { public void apply(nsIDocShell ds) {
                ds.setAllowJavascript(false);
            }});
        }});
    }

    /**
     * Enable Javascript execution in the given window.
     *
     * @param win browser window where to enable javascript
     */
    public static void enableJavascript(final IMozillaWindow win) {
        mozSyncExec(new Runnable() { public void run() {
            ChromeAdapter chromeAdapter = win.getChromeAdapter();
            if (chromeAdapter==null) return;

            nsIWebBrowser webBrowser = chromeAdapter.getWebBrowser();
            nsIInterfaceRequestor ir = XPCOMUtils.qi(webBrowser, nsIInterfaceRequestor.class);
            nsIDocShell docShell = (nsIDocShell) ir.getInterface(nsIDocShell.NS_IDOCSHELL_IID);
            docShell.setAllowJavascript(false);
        }});
    }

    /**
     * Disable Javascript execution in the given window.
     *
     * @param win browser window where to disable javascript
     */
    public static void disableJavascript(final IMozillaWindow win) {
        mozSyncExec(new Runnable() { public void run() {
            ChromeAdapter chromeAdapter = win.getChromeAdapter();
            if (chromeAdapter==null) return;

            nsIWebBrowser webBrowser = chromeAdapter.getWebBrowser();
            nsIInterfaceRequestor ir = XPCOMUtils.qi(webBrowser, nsIInterfaceRequestor.class);
            nsIDocShell docShell = (nsIDocShell) ir.getInterface(nsIDocShell.NS_IDOCSHELL_IID);
            docShell.setAllowJavascript(false);
        }});
    }

    /**
     * Returns true, if Javascript execution in webpages
     * is enabled.
     *
     * @return true if Javascript execution in webpages
     * is enabled
     */
    public static boolean isEnabledJavascript() {
        return isEnabledJavascript;
    }

    private interface DocShellApplyTask {
        /**
         * Function to call on a window
         *
         * @param ds docshell (window)
         */
        public void apply(nsIDocShell ds);
    }

    private static void applyForAllWindows(DocShellApplyTask t) {
        assert MozillaExecutor.isMozillaThread();
        nsIWindowWatcher ww = XPCOMUtils.getService("@mozilla.org/embedcomp/window-watcher;1", nsIWindowWatcher.class); //$NON-NLS-1$

        nsISimpleEnumerator winEn = ww.getWindowEnumerator();
        while (winEn.hasMoreElements()) {
          nsIDOMWindowInternal domWin = XPCOMUtils.qi(winEn.getNext(), nsIDOMWindowInternal.class);
          nsIWebBrowserChrome chrome = ww.getChromeForWindow(domWin);
          nsIWebBrowser webBrowser = chrome.getWebBrowser();
          nsIInterfaceRequestor ir = XPCOMUtils.qi(webBrowser, nsIInterfaceRequestor.class);
          nsIDocShell docShell = (nsIDocShell) ir.getInterface(nsIDocShell.NS_IDOCSHELL_IID);
          t.apply(docShell);
        }
    }

    /**
     * Configures browser to use proxy setting by defining parameters
     * for all proxy types. Use null, -1 if a particular proxy type
     * should not be set.
     *
     * @param httpHost host (ip address) for HTTP proxy
     * @param httpPort port for HTTP proxy
     * @param sslHost host (ip address) for HTTPS proxy
     * @param sslPort port for HTTPS proxy
     * @param ftpHost host (ip address) for FTP proxy
     * @param ftpPort port for FTP proxy
     * @param socksHost host (ip address) for SOCKS proxy
     * @param socksPort port for SOCKS proxy
     * @param noProxyFor list of host to bypass with proxies.
     *   The same format as in Firefox configuration dialog
     *   (comma separated)
     */
    public static void setManualProxy(final String httpHost, final int httpPort,
                                      final String sslHost, final int sslPort,
                                      final String ftpHost, final int ftpPort,
                                      final String socksHost, final int socksPort,
                                      final String noProxyFor)
    {
        mozPostponableSyncExec(new Runnable() { public void run() {
            nsIPrefBranch pref = getService("@mozilla.org/preferences-service;1", nsIPrefBranch.class); //$NON-NLS-1$

            //switch to manual configuration
            pref.setIntPref("network.proxy.type", 1); //$NON-NLS-1$

            boolean validConfig = false;
            //http proxy
            if (httpHost!=null && httpHost.length()>0 && httpPort>0) {
                pref.setCharPref("network.proxy.http", httpHost); //$NON-NLS-1$
                pref.setIntPref("network.proxy.http_port", httpPort); //$NON-NLS-1$
                validConfig = true;
            } else {
                pref.setCharPref("network.proxy.http", ""); //$NON-NLS-1$ //$NON-NLS-2$
                pref.setIntPref("network.proxy.http_port", 0); //$NON-NLS-1$
            }

            //ssl proxy
            if (sslHost!=null && sslHost.length()>0 && sslPort>0) {
                pref.setCharPref("network.proxy.ssl", sslHost); //$NON-NLS-1$
                pref.setIntPref("network.proxy.ssl_port", sslPort); //$NON-NLS-1$
                validConfig = true;
            } else {
                pref.setCharPref("network.proxy.ssl", ""); //$NON-NLS-1$ //$NON-NLS-2$
                pref.setIntPref("network.proxy.ssl_port", 0); //$NON-NLS-1$
            }

            //ftp proxy
            if (ftpHost!=null && ftpHost.length()>0 && ftpPort>0) {
                pref.setCharPref("network.proxy.ftp", ftpHost); //$NON-NLS-1$
                pref.setIntPref("network.proxy.ftp_port", ftpPort); //$NON-NLS-1$
                validConfig = true;
            } else {
                pref.setCharPref("network.proxy.ftp", ""); //$NON-NLS-1$ //$NON-NLS-2$
                pref.setIntPref("network.proxy.ftp_port", 0); //$NON-NLS-1$
            }

            //socks proxy
            if (socksHost!=null && socksHost.length()>0 && socksPort>0) {
                pref.setCharPref("network.proxy.socks", socksHost); //$NON-NLS-1$
                pref.setIntPref("network.proxy.socks_port", socksPort); //$NON-NLS-1$
                validConfig = true;
            } else {
                pref.setCharPref("network.proxy.socks", ""); //$NON-NLS-1$ //$NON-NLS-2$
                pref.setIntPref("network.proxy.socks_port", 0); //$NON-NLS-1$
            }

            //no proxy for
            if (noProxyFor!=null && noProxyFor.length()>0) {
                pref.setCharPref("network.proxy.no_proxies_on", noProxyFor); //$NON-NLS-1$
            } else {
                pref.setCharPref("network.proxy.no_proxies_on", "localhost, 127.0.0.1"); //$NON-NLS-1$ //$NON-NLS-2$
            }

            if (!validConfig) {
                //reset proxy type
                pref.setIntPref("network.proxy.type", 0); //$NON-NLS-1$
            }
        }});
    }

    /**
     * Configures browser to use automatic proxy settings.
     * (usually located in a file called proxy.pac)
     *
     * @param configURL url of a proxy.pac file
     */
    public static void setAutomaticProxy(final String configURL)
    {
        mozPostponableSyncExec(new Runnable() { public void run() {
            nsIPrefBranch pref = getService("@mozilla.org/preferences-service;1", nsIPrefBranch.class); //$NON-NLS-1$

            //switch to automatic configuration
            pref.setIntPref("network.proxy.type", 2); //$NON-NLS-1$

            boolean validConfig = false;
            if (configURL!=null && configURL.length()>0) {
                pref.setCharPref("network.proxy.autoconfig_url", configURL); //$NON-NLS-1$
                validConfig = true;
            } else {
                pref.setCharPref("network.proxy.autoconfig_url", ""); //$NON-NLS-1$ //$NON-NLS-2$
            }

            if (!validConfig) {
                //reset proxy type
                pref.setIntPref("network.proxy.type", 0); //$NON-NLS-1$
            }
        }});
    }

    /**
     * Disables using of all proxy types (HTTP, HTTPS, SOCKS, FTP).
     */
    public static void disableProxy() {
        mozPostponableSyncExec(new Runnable() { public void run() {
            nsIPrefBranch pref = getService("@mozilla.org/preferences-service;1", nsIPrefBranch.class); //$NON-NLS-1$

            //switch to direct connection
            pref.setIntPref("network.proxy.type", 0); //$NON-NLS-1$
        }});
    }

    /**
     * Cleans browser cache.
     */
    public static void cleanCache() {
        mozPostponableSyncExec(new Runnable() { public void run() {
            nsICacheService cache = getService("@mozilla.org/network/cache-service;1", nsICacheService.class); //$NON-NLS-1$
            cache.evictEntries(nsICache.STORE_ANYWHERE);
        }});
    }

    /**
     * Cleans browser cookies.
     */
    public static void cleanCookies() {
        mozPostponableSyncExec(new Runnable() { public void run() {
            nsICookieManager cookieManager = getService("@mozilla.org/cookiemanager;1", nsICookieManager.class); //$NON-NLS-1$
            cookieManager.removeAll();
        }});
    }

    /**
     * Returns a short XULRunner, java, platform configuration summary.
     * Used e.g. in error reports.
     *
     * @return short summary of XULRunner, Java, platform configuration
     */
    public static String getConfigSummary() {
        String s = ""; //$NON-NLS-1$

        File xulDir = MozillaConfig.getXULRunnerHome();
        s += String.format(mt.t("MozillaConfig.XULRunnerHome"), xulDir!=null ? xulDir.getAbsolutePath() : mt.t("MozillaConfig.not_resolved")); //$NON-NLS-1$ //$NON-NLS-2$

        File profDir = MozillaConfig.getProfileDir();
        s += String.format(mt.t("MozillaConfig.Profile_directory"), profDir!=null ? profDir.getAbsolutePath() : mt.t("MozillaConfig.not_used")); //$NON-NLS-1$ //$NON-NLS-2$

        s += String.format(mt.t("MozillaConfig.Platform"), Platform.platform); //$NON-NLS-1$

        s += String.format(mt.t("MozillaConfig.Java"), //$NON-NLS-1$
                           System.getProperty("java.vm.version"), //$NON-NLS-1$
                           System.getProperty("java.vm.vendor")); //$NON-NLS-1$

        return s;
    }


    static {
        //read proxy setting from java properties
        String httpHost = System.getProperty("proxy.http.host", ""); //$NON-NLS-1$ //$NON-NLS-2$
        int httpPort = parseInt(System.getProperty("proxy.http.port", "")); //$NON-NLS-1$ //$NON-NLS-2$
        String sslHost = System.getProperty("proxy.https.host", ""); //$NON-NLS-1$ //$NON-NLS-2$
        int sslPort = parseInt(System.getProperty("proxy.https.port", "")); //$NON-NLS-1$ //$NON-NLS-2$
        String ftpHost = System.getProperty("proxy.https.host", ""); //$NON-NLS-1$ //$NON-NLS-2$
        int ftpPort = parseInt(System.getProperty("proxy.https.port", "")); //$NON-NLS-1$ //$NON-NLS-2$
        String socksHost = System.getProperty("proxy.https.host", ""); //$NON-NLS-1$ //$NON-NLS-2$
        int socksPort = parseInt(System.getProperty("proxy.https.port", "")); //$NON-NLS-1$ //$NON-NLS-2$
        String noProxyFor = System.getProperty("proxy.bypass.list", ""); //$NON-NLS-1$ //$NON-NLS-2$
        setManualProxy(httpHost, httpPort,
                       sslHost, sslPort,
                       ftpHost, ftpPort,
                       socksHost, socksPort,
                       noProxyFor);
    }
    private static int parseInt(String s) {
        if (s.length()==0) return -1;
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

}
