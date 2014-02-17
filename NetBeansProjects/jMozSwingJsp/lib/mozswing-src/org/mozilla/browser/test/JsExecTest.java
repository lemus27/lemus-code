package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.sleep;
import static org.mozilla.browser.MozillaAutomation.blockingLoad;

public class JsExecTest extends MozillaTest {

    private static final String TEST_URL = resolveURL("jsexec.html"); //$NON-NLS-1$

    @SuppressWarnings("unused") //$NON-NLS-1$
    public void testJsExec() {
        //load with image
        assertFalse(blockingLoad(win, TEST_URL));
        sleep(200);

        Object o1 = win.jsexec("func1()"); //$NON-NLS-1$
        //assertEquals((long) o1, 123);
        sleep(200);

        Object o2 = win.jsexec("func2()"); //$NON-NLS-1$
        //assertEquals((long) o2, "abc");
        sleep(200);

        Object o3 = win.jsexec("func3()"); //$NON-NLS-1$
        assertEquals(o3, null);
        sleep(200);
    }

}
