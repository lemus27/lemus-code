package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.sleep;
import static org.mozilla.browser.MozillaAutomation.blockingLoad;

public class DisabledAppletTest extends MozillaTest {

    private static final String TEST_URL = resolveURL("applet.html"); //$NON-NLS-1$

    public void testApplet() {
        //load page with a java applet
        //
        //(usually jvm hangs or crashes on win32
        //if trying to start an applet)
        assertFalse(blockingLoad(win, TEST_URL));
        sleep(500);
    }

}
