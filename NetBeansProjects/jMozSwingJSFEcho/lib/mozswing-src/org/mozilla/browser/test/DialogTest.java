package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.blockingLoad;
import static org.mozilla.browser.MozillaAutomation.click;
import static org.mozilla.browser.MozillaAutomation.sleep;
import static org.mozilla.browser.MozillaAutomation.waitForNoWindowWithNodeText;
import static org.mozilla.browser.MozillaAutomation.waitForNoWindowWithTitle;
import static org.mozilla.browser.MozillaAutomation.waitForWindowWithNodeText;
import static org.mozilla.browser.MozillaAutomation.waitForWindowWithTitle;
import static org.mozilla.browser.MozillaExecutor.mozAsyncExec;
import junit.framework.Assert;

import org.mozilla.browser.MozillaAutomation;
import org.mozilla.browser.MozillaExecutor;
import org.mozilla.browser.MozillaWindow;
import org.mozilla.browser.common.Platform;

public class DialogTest extends MozillaTest {

    public void testAlertDirect()
        throws Exception
    {
        //next page load will open a modal dialog and wait
        //until the dialog is closed.
        //therefore we use a new thread for closing
        //that dialog
        CloseModalDialogThread t = new CloseModalDialogThread();
        t.start();

        //load a webpage that immediately opens a dialog
        assertFalse(blockingLoad(win, resolveURL("alert.html"))); //$NON-NLS-1$

        t.join2();
    }

    public void testConfirmDirect()
        throws Exception
    {
        //next page load will open a modal dialog and wait
        //until the dialog is closed.
        //therefore we use a new thread for closing
        //that dialog
        CloseModalDialogThread t = new CloseModalDialogThread();
        t.start();

        //load a webpage that immediately opens a dialog
        assertFalse(blockingLoad(win, resolveURL("confirm.html"))); //$NON-NLS-1$

        t.join2();
    }

    public void testPromptDirect()
        throws Exception
    {
        //next page load will open a modal dialog and wait
        //until the dialog is closed.
        //therefore we use a new thread for closing
        //that dialog
        CloseModalDialogThread t = new CloseModalDialogThread();
        t.start();

        //load a webpage that immediately opens a dialog
        assertFalse(blockingLoad(win, resolveURL("prompt.html"))); //$NON-NLS-1$

        t.join2();
    }

    /*
    public void testMultiple() {
        MozillaWindow win1 = new MozillaWindow();
        win1.setBounds(100, 200, 200, 300);
        win1.setVisible(true);

        MozillaWindow win2 = new MozillaWindow();
        win2.setBounds(400, 200, 200, 300);
        win2.setVisible(true);

        MozillaWindow win3 = new MozillaWindow();
        win3.setBounds(700, 200, 200, 300);
        win3.setVisible(true);

        win1.go(resolveURL("alert.html"));
        List<MozillaWindow> dlgs1 = MozillaAutomation.waitForNumWindowsWithTitle("[JavaScript Application]", 1, 3000);
        win2.go(resolveURL("confirm.html"));
        List<MozillaWindow> dlgs2 = MozillaAutomation.waitForNumWindowsWithTitle("[JavaScript Application]", 2, 3000);
        win3.go(resolveURL("prompt.html"));
        List<MozillaWindow> dlgs3 = MozillaAutomation.waitForNumWindowsWithTitle("[JavaScript Application]", 3, 3000);
        //assertTrue(dlgs.size()==3);

        sleep(1000);

        //close dialogs and windows
        for (MozillaWindow dlg : dlgs3) {
            dlg.setVisible(false);
            dlg.dispose();
        }
        win1.setVisible(false);
        win1.dispose();
        win2.setVisible(false);
        win2.dispose();
        win3.setVisible(false);
        win3.dispose();

        //force gc to delete the dialog on the native side
        //CloseTest.forceGC();
        //test if the dialogs are destroyed
        assertNull(waitForNoWindowWithTitle("[JavaScript Application]", 10000));
    }

    public void XXtestMultipleReorder() {
        MozillaWindow win1 = new MozillaWindow();
        win1.setBounds(200, 200, 300, 300);
        win1.setVisible(true);

        MozillaWindow win2 = new MozillaWindow();
        win2.setBounds(200, 200, 300, 300);
        win2.setVisible(true);

        MozillaWindow win3 = new MozillaWindow();
        win3.setBounds(200, 200, 300, 300);
        win3.setVisible(true);

        assertFalse(syncLoad(win1, resolveURL("alert.html")));
        assertFalse(syncLoad(win2, resolveURL("confirm.html")));
        assertFalse(syncLoad(win3, resolveURL("prompt.html")));

        sleep(10000);
        win3.setVisible(false);
        win3.dispose();
        win2.setVisible(false);
        win2.dispose();
        win1.setVisible(false);
        win1.dispose();
    }
    */

    public void testAlertIndirect()
        throws Exception
    {
        //next click will open a modal dialog and wait
        //until the dialog is closed.
        //therefore we use a new thread for closing
        //that dialog
        CloseModalDialogThread t = new CloseModalDialogThread();
        t.start();

        //load a webpage and after mouse click open a dialog from JS
        assertFalse(blockingLoad(win, resolveURL("alert2.html"))); //$NON-NLS-1$
        mozAsyncExec(new Runnable() { public void run() {
            assertFalse(click(win, "btnID")); //$NON-NLS-1$
        }});

        t.join2();
    }

    public void testConfirmIndirect()
        throws Exception
    {
        //next click will open a modal dialog and wait
        //until the dialog is closed.
        //therefore we use a new thread for closing
        //that dialog
        CloseModalDialogThread t = new CloseModalDialogThread();
        t.start();

        //load a webpage and after mouse click open a dialog from JS
        assertFalse(blockingLoad(win, resolveURL("confirm2.html"))); //$NON-NLS-1$
        mozAsyncExec(new Runnable() { public void run() {
            assertFalse(click(win, "btnID")); //$NON-NLS-1$
        }});

        t.join2();
    }

    public void testPromptIndirect()
        throws Exception
    {
        //next click will open a modal dialog and wait
        //until the dialog is closed.
        //therefore we use a new thread for closing
        //that dialog
        CloseModalDialogThread t = new CloseModalDialogThread();
        t.start();

        //load a webpage and after mouse click open a dialog from JS
        assertFalse(blockingLoad(win, resolveURL("prompt2.html"))); //$NON-NLS-1$
        mozAsyncExec(new Runnable() { public void run() {
            assertFalse(click(win, "btnID")); //$NON-NLS-1$
        }});

        t.join2();
    }


    private class CloseModalDialogThread extends Thread {

        private boolean closeFailed = false;

        public CloseModalDialogThread() {
            setName("Close dialog"); //$NON-NLS-1$
        }

        @Override
        public void run() {
            //log.debug("entering dialog close thread");
            try {
                //wait until dialog enters modal loop
                MozillaAutomation.sleep(1000);
                //find the new dialog
                 final MozillaWindow dlg;
                 if (Platform.platform!=Platform.OSX) {
                     dlg =
                         (MozillaWindow)
                        waitForWindowWithTitle("[JavaScript Application]", 3000); //$NON-NLS-1$
                } else {
                    //on osx, the modal dialog does not have
                    //a title so, we have to search differently
                     dlg =
                         (MozillaWindow)
                         waitForWindowWithNodeText("[JavaScript Application]", 3000); //$NON-NLS-1$
                }
                 Assert.assertNotNull(dlg);

                //wait to visually recognize the dialog was there
                MozillaAutomation.sleep(1500);
                MozillaExecutor.swingSyncExec(new Runnable() { public void run() {
                    //close dialog
                    dlg.setVisible(false);
                }});
                flushMozillaJobs();
                MozillaExecutor.swingSyncExec(new Runnable() { public void run() {
                    //close dialog
                    dlg.dispose();
                }});

                //force gc to delete the dialog on the native side
                //CloseTest.forceGC();
                //test if the dialog is destroyed
                if (Platform.platform!=Platform.OSX) {
                    assertNull(waitForNoWindowWithTitle("[JavaScript Application]", 3000)); //$NON-NLS-1$
                } else {
                    //on osx, the modal dialog does not have
                    //a title so, we have to search differently
                    assertNull(waitForNoWindowWithNodeText("[JavaScript Application]", 3000)); //$NON-NLS-1$
                }
            } catch (Throwable e) {
                closeFailed = true;
            }
            //log.debug("leave dialog close thread");
        }

        public void join2()
            throws Exception
        {
            super.join();
            assertFalse(closeFailed);
        }
    }

    public void testRun()
        throws Exception
    {
        assertFalse(blockingLoad(win, resolveURL("download.html"))); //$NON-NLS-1$
        //click a link that opens the run-only dialog
        mozAsyncExec(new Runnable() { public void run() {
            assertFalse(click(win, "linkEXE")); //$NON-NLS-1$
        }});
        //find the new dialog
        final MozillaWindow dlg =
            (MozillaWindow)
            waitForWindowWithTitle("Opening regxpcom.exe", 3000); //$NON-NLS-1$
        assertNotNull(dlg);

        //wait to visually recognize the dialog was there
        sleep(1000);

        //close dialog
        dlg.setVisible(false);
        dlg.dispose();
        //force gc to delete the dialog on the native side
        //CloseTest.forceGC();
        //test if the dialog is destroyed
        assertNull(waitForNoWindowWithTitle("Opening xpcom.zip", 10000)); //$NON-NLS-1$
    }

    public void testRunSave()
        throws Exception
    {
        assertFalse(blockingLoad(win, resolveURL("download.html"))); //$NON-NLS-1$
        //click a link that opens the run/save dialog
        mozAsyncExec(new Runnable() { public void run() {
            assertFalse(click(win, "linkZIP")); //$NON-NLS-1$
        }});
        //find the new dialog
        final MozillaWindow dlg =
            (MozillaWindow)
            waitForWindowWithTitle("Opening xpcom.zip", 3000); //$NON-NLS-1$
        assertNotNull(dlg);

        //wait to visually recognize the dialog was there
        sleep(1000);

        //close dialog
        dlg.setVisible(false);
        dlg.dispose();
        //force gc to delete the dialog on the native side
        //CloseTest.forceGC();
        //test if the dialog is destroyed
        assertNull(waitForNoWindowWithTitle("Opening xpcom.zip", 10000)); //$NON-NLS-1$
    }

}
