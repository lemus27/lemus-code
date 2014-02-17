package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.blockingLoad;

public class GetUrlTest extends MozillaTest {

    public void testGetUrl() {
        assertFalse(blockingLoad(win, "about:")); //$NON-NLS-1$
        String url = win.getUrl();
        assertEquals(url, "about:"); //$NON-NLS-1$

        assertFalse(blockingLoad(win, "about:config")); //$NON-NLS-1$
        url = win.getUrl();
        assertEquals(url, "about:config"); //$NON-NLS-1$
    }
}
