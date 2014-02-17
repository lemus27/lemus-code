package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.sleep;

import org.mozilla.browser.MozillaAutomation;
import org.mozilla.browser.MozillaWindow;

public class ChromeVisibilityTest extends MozillaTest {

    @Override
    protected void setUp() throws Exception {}
    @Override
    protected void tearDown() throws Exception {}

    public void testForcedVisible()
    {
        win = new MozillaWindow(MozillaWindow.VisibilityMode.FORCED_VISIBLE, MozillaWindow.VisibilityMode.FORCED_VISIBLE);
        win.setBounds(200, 200, 300, 300);
        win.setVisible(true);

        MozillaAutomation.blockingLoadHTML(win, "<html><body><h3>Forced showing of toolbar and statusbar</h3></body></html>", null); //$NON-NLS-1$
        sleep(2000);

        win.setVisible(false);
        win.dispose();
    }

    public void testForcedHidden()
    {
        win = new MozillaWindow(MozillaWindow.VisibilityMode.FORCED_HIDDEN, MozillaWindow.VisibilityMode.FORCED_HIDDEN);
        win.setBounds(200, 200, 300, 300);
        win.setVisible(true);

        MozillaAutomation.blockingLoadHTML(win, "<html><body><h3>Forced hiding of toolbar and statusbar</h3></body></html>", null); //$NON-NLS-1$
        sleep(2000);

        win.setVisible(false);
        win.dispose();
    }

    public void testHideShow()
    {
        win = new MozillaWindow(MozillaWindow.VisibilityMode.FORCED_VISIBLE, MozillaWindow.VisibilityMode.FORCED_VISIBLE);
        win.setBounds(200, 200, 300, 300);
        win.setVisible(true);

        MozillaAutomation.blockingLoadHTML(win, "<html><body><h3>Hidding and showing toolbar and statusbar</h3></body></html>", null); //$NON-NLS-1$
        sleep(2000);

        win.getToolbar().setVisible(false);
        win.getStatusbar().setVisible(false);
        sleep(2000);

        win.getToolbar().setVisible(true);
        win.getStatusbar().setVisible(true);
        sleep(2000);

        win.setVisible(false);
        win.dispose();
    }


//    public void testForcedHiddenPopup()
//    {
//        win = new MozillaWindow(MozillaWindow.VisibilityMode.FORCED_VISIBLE, MozillaWindow.VisibilityMode.FORCED_VISIBLE);
//        win.setBounds(200, 200, 300, 300);
//        win.setVisible(true);
//
////        MozillaAutomation.blockingLoadHTML(win, "<html><body><h3>Forced hiding of toolbar and statusbar</h3></body></html>");
////        sleep(2000);
//        assertFalse(blockingLoad(win, resolveURL("popup.html")));
//
//        //open popup
//        mozAsyncExec(new Runnable() { public void run() {
//            assertFalse(click(win, "Open1"));
//        }});
//        //we had to leave the previous mozilla code block so that
//        //the mozilla thread can complete the window opening
//
//        //test if the popup is openned
//        assertNotNull(waitForWindowWithTitle("Popup1", 3000));
//
//        //close popup from main window
//        mozSyncExec(new Runnable() { public void run() {
//            assertFalse(click(win, "Close1"));
//        }});
//        //test if the popup is closed
//        assertNull(waitForNoWindowWithTitle("Popup1", 3000));
//
//
//        win.setVisible(false);
//        win.dispose();
//    }

}
