package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.sleep;
import static org.mozilla.browser.MozillaAutomation.blockingLoad;

public class BadURLTest extends MozillaTest {

    public void testNonExistingLoad() {
        //try loading non-existing url
        //(an error page should appear)
        assertTrue(blockingLoad(win, "http://127.0.0.1/some-non-existing-page")); //$NON-NLS-1$
        sleep(500);
    }

}
