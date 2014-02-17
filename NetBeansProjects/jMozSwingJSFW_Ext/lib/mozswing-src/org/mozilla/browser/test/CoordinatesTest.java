package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.blockingLoad;

import java.awt.Rectangle;

import org.mozilla.browser.MozillaAutomation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Testcases for element to coordinates conversion
 */
public class CoordinatesTest extends MozillaTest {

    public void testElement2Rects() {
        assertFalse(blockingLoad(win, "about:license")); //$NON-NLS-1$
        win.setSize(200, 500);

        Document doc = win.getDocument();

        int frameFlowsNum = 0;
        NodeList nl = doc.getElementsByTagName("a"); //$NON-NLS-1$
        for (int j=0; j<nl.getLength(); j++) {
            Element e = (Element) nl.item(j);
            //System.err.println(e.getTextContent());

            Rectangle[] r = MozillaAutomation.getElementRects(e);
            if (r.length>1) frameFlowsNum++;

            //System.err.println(r.length);
            //for (int i = 0; i < r.length; i++) {
            //    System.err.println("r="+r[i]);
            //}
        }
        assertTrue(frameFlowsNum>0);
    }

    public void testPoint2Element() {
        assertFalse(blockingLoad(win, "about:")); //$NON-NLS-1$

        Document doc = win.getDocument();
        Element e = MozillaAutomation.getElementFromPoint(doc, 100, 150);
        String name = e.getTagName();
        assertTrue("img".equals(name)); //$NON-NLS-1$
    }

    public void testPoint2ElementWithFrames() {
        assertFalse(blockingLoad(win, resolveURL("frames.html"))); //$NON-NLS-1$

        Document doc = win.getDocument();
        Element e = MozillaAutomation.getElementFromPoint(doc, 100, 100);
        String name = e.getTagName();
        assertTrue("img".equals(name)); //$NON-NLS-1$
    }

}
