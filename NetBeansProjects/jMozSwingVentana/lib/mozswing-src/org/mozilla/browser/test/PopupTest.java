package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.blockingLoad;
import static org.mozilla.browser.MozillaAutomation.click;
import static org.mozilla.browser.MozillaAutomation.getOpennedWindows;
import static org.mozilla.browser.MozillaAutomation.waitForNoWindowWithTitle;
import static org.mozilla.browser.MozillaAutomation.waitForNumWindowsWithTitle;
import static org.mozilla.browser.MozillaAutomation.waitForWindowWithTitle;
import static org.mozilla.browser.MozillaExecutor.mozAsyncExec;
import static org.mozilla.browser.MozillaExecutor.mozSyncExec;

import java.util.List;
import java.util.concurrent.Callable;

import junit.framework.Assert;

import org.mozilla.browser.IMozillaWindow;
import org.mozilla.browser.MozillaExecutor;
import org.mozilla.browser.MozillaInitialization;
import org.mozilla.browser.MozillaWindow;

public class PopupTest extends MozillaTest {

    private static final String TEST_URL = resolveURL("popup.html"); //$NON-NLS-1$

    public void testOpen() {
        assertFalse(blockingLoad(win, TEST_URL));

        //open popup
        mozAsyncExec(new Runnable() { public void run() {
            assertFalse(click(win, "Open1")); //$NON-NLS-1$
        }});
        //we had to leave the previous mozilla code block so that
        //the mozilla thread can complete the window opening

        //test if the popup is openned
        assertNotNull(waitForWindowWithTitle("Popup1", 3000)); //$NON-NLS-1$

        //close popup from main window
        mozSyncExec(new Runnable() { public void run() {
            assertFalse(click(win, "Close1")); //$NON-NLS-1$
        }});
        //test if the popup is closed
        assertNull(waitForNoWindowWithTitle("Popup1", 3000)); //$NON-NLS-1$

        //open popup
        mozSyncExec(new Runnable() { public void run() {
            assertFalse(click(win, "Open1")); //$NON-NLS-1$
        }});
        //close popup from the popup itself
        final MozillaWindow childWin = (MozillaWindow) waitForWindowWithTitle("Popup1", 3000); //$NON-NLS-1$
        Assert.assertNotNull(childWin);
        mozSyncExec(new Runnable() { public void run() {
            assertFalse(click(childWin, "Close")); //$NON-NLS-1$
        }});
        //test if the popup is closed
        assertNull(waitForNoWindowWithTitle("Popup1", 3000)); //$NON-NLS-1$
    }

    public void testDouble() {
        testOpen();
        flushSwingJobs();
        testOpen();
    }

    public void testMixedOrder() {
        assertFalse(blockingLoad(win, TEST_URL));

        //open popup
        mozSyncExec(new Runnable() { public void run() {
            assertFalse(click(win, "Open1")); //$NON-NLS-1$
            assertFalse(click(win, "Open2")); //$NON-NLS-1$
        }});
        //test if the popups are openned
        assertNotNull(waitForWindowWithTitle("Popup1", 3000)); //$NON-NLS-1$
        assertNotNull(waitForWindowWithTitle("Popup2", 3000)); //$NON-NLS-1$
        //close popup2
        mozSyncExec(new Runnable() { public void run() {
            assertFalse(click(win, "Close2")); //$NON-NLS-1$
        }});
        //test if the popups are openned/closed
        assertNotNull(waitForWindowWithTitle("Popup1", 3000)); //$NON-NLS-1$
        assertNull(waitForNoWindowWithTitle("Popup2", 3000)); //$NON-NLS-1$
        //close popup1
        mozSyncExec(new Runnable() { public void run() {
            assertFalse(click(win, "Close1")); //$NON-NLS-1$
        }});
        //test if the popups are closed
        assertNull(waitForNoWindowWithTitle("Popup1", 3000)); //$NON-NLS-1$
        assertNull(waitForNoWindowWithTitle("Popup2", 3000)); //$NON-NLS-1$
    }

    //needs higher default value in
    //WindowCreator.ensurePrecreatedWindows()
    public void dis_testManyOpens()
        throws Exception
    {
        assertFalse(blockingLoad(win, TEST_URL));

        log.debug("opening..."); //$NON-NLS-1$
        MozillaExecutor.swingSyncExec(new Runnable() { public void run() {
            MozillaInitialization.getWinCreator().ensurePrecreatedWindows(20);
        }});
        for (int i=0; i<20; i++) {
            //open popup
            mozSyncExec(new Runnable() { public void run() {
                assertFalse(click(win, "Open3")); //$NON-NLS-1$
            }});
            flushMozillaJobs();
            flushSwingJobs();
        }
        log.debug("openned"); //$NON-NLS-1$

        log.debug("closing..."); //$NON-NLS-1$
        List<IMozillaWindow> wins =
            waitForNumWindowsWithTitle("Popup3", 20, 10000); //$NON-NLS-1$
        assertEquals(20, wins.size());
        for (IMozillaWindow iwin : wins) {
            MozillaWindow win = (MozillaWindow) iwin;
            win.setVisible(false);
            win.dispose();
        }
        assertNull(waitForNoWindowWithTitle("Popup3", 10000)); //$NON-NLS-1$

        wins = mozSyncExec(new Callable<List<IMozillaWindow>>() { public java.util.List<IMozillaWindow> call() throws Exception {
            return getOpennedWindows();
        }});
        assertEquals(1, wins.size());
        log.debug("closed"); //$NON-NLS-1$
    }

    public void testZZZ() {
        //to ensure nothing crashed before
    }
}
