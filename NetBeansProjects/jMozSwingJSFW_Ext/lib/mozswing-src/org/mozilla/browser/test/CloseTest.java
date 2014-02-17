package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaExecutor.mozSyncExec;

import org.mozilla.browser.MozillaAutomation;
import org.mozilla.browser.MozillaWindow;
import org.mozilla.browser.XPCOMUtils;
import org.mozilla.interfaces.nsIPrefBranch;
import org.mozilla.interfaces.nsIPrefService;

public class CloseTest extends MozillaTest {

    @Override
    protected void setUp() throws Exception {}
    @Override
    protected void tearDown() throws Exception {}

    public void testClose1()
    {
        win = new MozillaWindow();
        win.setBounds(200, 200, 300, 300);
        win.setVisible(true);

        win.setVisible(false);
        win.dispose();

        int numAfter = countWindows();
        assertEquals(0, numAfter);

        //additionally, in debug build if this
        //is the only executed test method should
        //after exit on the console appear that both
        //domwindow and docshell have been destroyed:
        //
        //++WEBSHELL == 1
        //++DOMWINDOW == 1
        //--WEBSHELL == 0
        //--DOMWINDOW == 0
        //forceGC();
    }

    public void testClose2()
    {
        win = new MozillaWindow();
        win.setBounds(200, 200, 300, 300);
        win.setVisible(true);

        int numBefore = countWindows();
        assertEquals(1, numBefore);

        win.load("about:blank"); //$NON-NLS-1$

        win.setVisible(false);
        win.dispose();

        int numAfter = countWindows();
        assertEquals(0, numAfter);

        //forceGC();
    }

    public void testClose3()
    {
        win = new MozillaWindow();
        win.setBounds(200, 200, 300, 300);
        win.setVisible(true);

        int numBefore = countWindows();
        assertEquals(1, numBefore);

        win.reload();

        win.setVisible(false);
        win.dispose();

        int numAfter = countWindows();
        assertEquals(0, numAfter);

        //forceGC();
    }

    void disableCache() {
        mozSyncExec(new Runnable() { public void run() {
            nsIPrefService ps = XPCOMUtils.getService("@mozilla.org/preferences-service;1", nsIPrefService.class); //$NON-NLS-1$
            nsIPrefBranch pref = ps.getBranch(null);
            pref.setIntPref("browser.sessionhistory.max_total_viewers", 0); //$NON-NLS-1$
            pref.setBoolPref("browser.cache.memory.enable", 0); //$NON-NLS-1$
            pref.setBoolPref("browser.cache.disk.enable", 0); //$NON-NLS-1$
        }});
    }

    int countWindows() {
        final int[] num = { 0 };
        mozSyncExec(new Runnable() { public void run() {
            num[0] = MozillaAutomation.getOpennedWindows().size();
        }});

        return num[0];
    }

    public static void forceGC() {
        log.debug("forcing GC..."); //$NON-NLS-1$
        //we have to try long enough for
        //the gc to really execute
        for (int i=0; i<10; i++) {
            //trigger gc
            System.gc();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.error("wait interrupted", e); //$NON-NLS-1$
            }
        }
        log.debug("done"); //$NON-NLS-1$
    }

}
