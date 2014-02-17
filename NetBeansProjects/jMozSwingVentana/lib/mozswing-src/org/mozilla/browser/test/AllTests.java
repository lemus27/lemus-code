package org.mozilla.browser.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for org.mozilla.browser"); //$NON-NLS-1$
        for (int i=0; i<1; i++) {
        //$JUnit-BEGIN$
        suite.addTestSuite(BackForwardTest.class);
        suite.addTestSuite(PopupTest.class);
        suite.addTestSuite(CloseTest.class);
        suite.addTestSuite(DialogTest.class);
        suite.addTestSuite(ChromePopupTest.class);
        suite.addTestSuite(TooltipTest.class);
        suite.addTestSuite(ImagesTest.class);
        suite.addTestSuite(ProxyTest.class);
        suite.addTestSuite(JsExecTest.class);
        suite.addTestSuite(DisabledAppletTest.class);
        suite.addTestSuite(BadURLTest.class);
        suite.addTestSuite(GetUrlTest.class);
        suite.addTestSuite(DOMTest.class);
        suite.addTestSuite(CoordinatesTest.class);
        suite.addTestSuite(RenderToImageTest.class);
        suite.addTestSuite(ChromeVisibilityTest.class);
        //$JUnit-END$
        }

        return suite;
    }

}
