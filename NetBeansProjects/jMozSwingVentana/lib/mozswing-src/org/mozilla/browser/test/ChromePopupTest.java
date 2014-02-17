package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.waitForWindowWithTitle;
import static org.mozilla.browser.MozillaExecutor.mozSyncExec;

import org.mozilla.browser.MozillaAutomation;
import org.mozilla.browser.MozillaWindow;

public class ChromePopupTest extends MozillaTest {

    private static final String TEST_URL = "chrome://global/content/console.xul"; //$NON-NLS-1$

    public void testOpen() throws Exception {
        mozSyncExec(new Runnable() { public void run() {
            win.load(TEST_URL);
        }});

        //test if the chrome popup is openned
        MozillaWindow popup = (MozillaWindow) waitForWindowWithTitle("Error Console", 3000); //$NON-NLS-1$
        assertNotNull(popup);

        //wait to visually recognize the dialog was there
        MozillaAutomation.sleep(1000);

        //close the popup
        popup.setVisible(false);
        popup.dispose();
    }

}
