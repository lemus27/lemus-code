package org.mozilla.browser.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.mozilla.browser.MozillaAutomation;

public class RenderToImageTest extends MozillaTest {

    public void testRender() throws IOException {
        //load web page
        MozillaAutomation.blockingLoad(win, "about:"); //$NON-NLS-1$

        //render to image
        byte[] pngImage = MozillaAutomation.renderToImage(win);

        //test if the byte array can be parsed to a java image
        ByteArrayInputStream bis = new ByteArrayInputStream(pngImage);
        ImageIO.read(bis);

    }

}
