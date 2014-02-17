package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.blockingLoad;
import static org.mozilla.browser.MozillaAutomation.sleep;

import org.mozilla.browser.MozillaConfig;
import org.mozilla.browser.MozillaWindow;

public class ImagesTest extends MozillaTest {

    private static final String TEST_URL = resolveURL("image.html"); //$NON-NLS-1$

    public void testImagesExisting() {
        //load with image
        assertFalse(blockingLoad(win, TEST_URL));
        sleep(400);

        //load without image
        MozillaConfig.disableImages();
        assertFalse(blockingLoad(win, TEST_URL));
        sleep(400);

        //load with image again
        MozillaConfig.enableImages();
        assertFalse(blockingLoad(win, TEST_URL));
        sleep(400);
    }

    public void testImagesNew() {
        MozillaConfig.disableImages();

        MozillaWindow win2 = new MozillaWindow();
        win2.setBounds(300, 300, 600, 450);
        win2.setVisible(true);

        //should load with images disabled
        assertFalse(blockingLoad(win2, TEST_URL));
        sleep(400);

        //load with image again
        MozillaConfig.enableImages();
        assertFalse(blockingLoad(win2, TEST_URL));
        sleep(400);

        win2.setVisible(false);
        win2.dispose();
    }

}
