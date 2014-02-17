package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.blockingBack;
import static org.mozilla.browser.MozillaAutomation.blockingForward;
import static org.mozilla.browser.MozillaAutomation.blockingLoad;

public class BackForwardTest extends MozillaTest {

    public void testHistory() {
        assertFalse(blockingLoad(win, "about:")); //$NON-NLS-1$
        assertFalse(blockingLoad(win, "about:cache")); //$NON-NLS-1$
        //single move
        assertFalse(blockingBack(win));
        assertFalse(blockingForward(win));

        assertFalse(blockingLoad(win, "about:config")); //$NON-NLS-1$
        //double move
        assertFalse(blockingBack(win));
        assertFalse(blockingBack(win));
        assertFalse(blockingForward(win));
        assertFalse(blockingForward(win));
    }

}
