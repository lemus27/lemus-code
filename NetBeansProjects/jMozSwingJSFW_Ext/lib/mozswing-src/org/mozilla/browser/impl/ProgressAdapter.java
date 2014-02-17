package org.mozilla.browser.impl;

import static org.mozilla.browser.MozillaExecutor.swingAsyncExec;
import static org.mozilla.browser.XPCOMUtils.qi;

import org.mozilla.browser.MozillaExecutor;
import org.mozilla.interfaces.nsIRequest;
import org.mozilla.interfaces.nsISupports;
import org.mozilla.interfaces.nsIURI;
import org.mozilla.interfaces.nsIWebNavigation;
import org.mozilla.interfaces.nsIWebProgress;
import org.mozilla.interfaces.nsIWebProgressListener;
import org.mozilla.xpcom.Mozilla;

/**
 * Listener for loading events in mozilla browser
 */
public class ProgressAdapter implements nsIWebProgressListener {

    private final ChromeAdapter chromeAdapter;

    ProgressAdapter(ChromeAdapter chromeAdapter) {
        this.chromeAdapter = chromeAdapter;
    }

    public void onLocationChange(nsIWebProgress aWebProgress, nsIRequest aRequest, nsIURI aLocation) {
        assert MozillaExecutor.isMozillaThread();

        final String uri = aLocation.getSpec();
        swingAsyncExec(new Runnable() { public void run() {
            chromeAdapter.window.onSetUrlbarText(uri);
        }});
    }

    public void onProgressChange(nsIWebProgress aWebProgress, nsIRequest aRequest, int aCurSelfProgress, int aMaxSelfProgress, int aCurTotalProgress, int aMaxTotalProgress) {
    }

    public void onStatusChange(nsIWebProgress aWebProgress, nsIRequest aRequest, long aStatus, final String aMessage) {
        assert MozillaExecutor.isMozillaThread();

        swingAsyncExec(new Runnable() { public void run() {
            chromeAdapter.window.onSetStatus(aMessage);
        }});
    }

    public void onSecurityChange(nsIWebProgress aWebProgress, nsIRequest aRequest, long aState) {
    }

    public void onStateChange(nsIWebProgress aWebProgress, nsIRequest aRequest, long aStateFlags, long aStatus) {
        assert MozillaExecutor.isMozillaThread();
//        if (mozCanvas.webBrowser==null) return;

        // if we've got the start flag, emit the signal
        if ((aStateFlags & nsIWebProgressListener.STATE_IS_NETWORK)!=0 &&
            (aStateFlags & nsIWebProgressListener.STATE_START)!=0)
        {
            swingAsyncExec(new Runnable() { public void run() {
                chromeAdapter.window.onEnableStopButton(true);
                chromeAdapter.window.onLoadingStarted();
            }});
        }

        if ((aStateFlags & nsIWebProgressListener.STATE_IS_NETWORK)!=0 &&
            (aStateFlags & nsIWebProgressListener.STATE_STOP)!=0) {

            nsIWebNavigation nav = qi(chromeAdapter.getWebBrowser(), nsIWebNavigation.class);
            final boolean isFwd = nav.getCanGoForward();
            final boolean isBack = nav.getCanGoBack();
            swingAsyncExec(new Runnable() { public void run() {
                chromeAdapter.window.onEnableForwardButton(isFwd);
                chromeAdapter.window.onEnableBackButton(isBack);
                chromeAdapter.window.onEnableStopButton(false);
                chromeAdapter.window.onEnableReloadButton(true);
                chromeAdapter.window.onLoadingEnded();
            }});

            chromeAdapter.contentFinishedLoading();
        }
    }

    public nsISupports queryInterface(String uuid) {
        return Mozilla.queryInterface(this, uuid);
    }

}