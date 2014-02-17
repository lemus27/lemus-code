package org.mozilla.browser.test;

import java.io.File;
import java.net.URL;

import javax.swing.SwingUtilities;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.browser.MozillaExecutor;
import org.mozilla.browser.MozillaWindow;

public abstract class MozillaTest extends TestCase {

    static Log log = LogFactory.getLog(MozillaTest.class);

    MozillaWindow win;

    @Override
    protected void setUp() throws Exception {
        win = new MozillaWindow();
        win.setBounds(200, 200, 600, 450);
        win.setVisible(true);
    }

    @Override
    protected void tearDown() throws Exception {
        win.setVisible(false);
        win.dispose();
        //ensure all swing and mozilla tasks generated
        //in this test are completed before moving
        //to another test
        flushMozillaJobs();
        flushSwingJobs();
    }

    protected static void flushMozillaJobs() {
        try {
            MozillaExecutor.mozSyncExec(new Runnable() { public void run() {}});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void flushSwingJobs() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() { public void run() {}});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String resolveURL(String relPath) {
        File base;
        try {
            AllTests t = new AllTests();
            URL u = t.getClass().getResource(""); //$NON-NLS-1$
            base = new File(u.toURI());
            while (base!=null) {
                File rf = new File(base, "test/res"); //$NON-NLS-1$
                if (rf.exists()) break;
                base = base.getParentFile();
            }
        } catch (Exception e) {
            log.error("failed to resolve relative path", e); //$NON-NLS-1$
            base = null;
        }

        if (base!=null) {
            File f = new File(base, "test/res"); //$NON-NLS-1$
            f = new File(f, relPath);
            return f.toURI().toString();
        } else {
            return relPath;
        }
    }

}
