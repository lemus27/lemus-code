package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.blockingLoad;
import static org.mozilla.browser.MozillaAutomation.sleep;
import static org.mozilla.browser.XPCOMUtils.qi;

import org.mozilla.browser.MozillaConfig;
import org.mozilla.browser.MozillaExecutor;
import org.mozilla.interfaces.nsIDOMDocument;
import org.mozilla.interfaces.nsIDOMElement;
import org.mozilla.interfaces.nsIDOMXULTextBoxElement;
import org.mozilla.interfaces.nsIWebBrowser;

public class ProxyTest extends MozillaTest {

    public void openAboutConfig() {
        //enlarge to see all settings
        win.setSize(win.getWidth(), win.getHeight()+100);

        //load config page
        assertFalse(blockingLoad(win, "about:config")); //$NON-NLS-1$
        win.load("javascript:ShowPrefs();"); //$NON-NLS-1$

        MozillaExecutor.mozSyncExec(new Runnable() { public void run() {
            nsIWebBrowser brow = win.getChromeAdapter().getWebBrowser();
            nsIDOMDocument doc = brow.getContentDOMWindow().getDocument();
            nsIDOMElement elem = doc.getElementById("textbox"); //$NON-NLS-1$
            nsIDOMXULTextBoxElement tb = qi(elem, nsIDOMXULTextBoxElement.class);
            tb.focus();
            tb.setValue("network.proxy."); //$NON-NLS-1$
        }});
        win.load("javascript:FilterPrefs();"); //$NON-NLS-1$
    }

    public void testProxy() {
        openAboutConfig();

        //set manual proxy
        MozillaConfig.
        setManualProxy("localhost1", 8081, //$NON-NLS-1$
                       "localhost2", 8082, //$NON-NLS-1$
                       "localhost3", 8083, //$NON-NLS-1$
                       "localhost4", 8084, //$NON-NLS-1$
                       "some,noproxy,hosts"); //$NON-NLS-1$
        sleep(200);

        //disable manual proxy
        MozillaConfig.
        setManualProxy(null, -1,
                       null, -1,
                       null, -1,
                       null, -1,
                       null);
        sleep(200);

        //set automatic proxy
        MozillaConfig.setAutomaticProxy("http://bla/config1.pac"); //$NON-NLS-1$
        sleep(200);

        //disable automatic proxy
        MozillaConfig.setAutomaticProxy(null);
        sleep(200);

        //set automatic proxy
        MozillaConfig.setAutomaticProxy("http://bla/config2.pac"); //$NON-NLS-1$
        sleep(200);

        //set no proxy
        MozillaConfig.disableProxy();
        sleep(200);
    }

}
