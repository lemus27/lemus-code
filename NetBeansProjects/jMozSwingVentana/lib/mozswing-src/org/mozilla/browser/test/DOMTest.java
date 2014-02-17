package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.blockingLoad;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.ranges.DocumentRange;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;
import org.w3c.dom.views.AbstractView;
import org.w3c.dom.views.DocumentView;

public class DOMTest extends MozillaTest {

    public void testUserData() {
        assertFalse(blockingLoad(win, "about:")); //$NON-NLS-1$

        Document doc = win.getDocument();
        doNodeTest(doc);
    }

    private void doNodeTest(Node n) {
        Object o1 = new Object();
        Object o2 = new Object();

        for (String key : new String[]{"aaa", "bbb"} ) { //$NON-NLS-1$ //$NON-NLS-2$
            Object ret;
            ret = n.setUserData(key, o1, null);
            assertNull(ret);
            ret = n.getUserData(key);
            assertSame(ret, o1);

            ret = n.setUserData(key, o2, null);
            assertSame(ret, o1);
            ret = n.getUserData(key);
            assertSame(ret, o2);

            ret = n.setUserData(key, null, null);
            assertSame(ret, o2);
            ret = n.getUserData(key);
            assertSame(ret, null);
        }
    }

//not implemented in xulrunner 1.8
//    public void testHandler() {
//        assertFalse(blockingLoad(win, "about:"));
//
//        final boolean[] handlerCalled = { false };
//        UserDataHandler h = new UserDataHandler() {
//            public void handle(short operation, String key, Object data,
//                               Node src, Node dst)
//            {
//                assertEquals(key, "aaa");
//                assertEquals(data, "bbb");
//                handlerCalled[0] = true;
//            }
//        };
//
//
//        Document doc = win.getDocument();
//        NodeList nl = doc.getElementsByTagName("li");
//        assertTrue(nl.getLength()>=2);
//
//        Node n = nl.item(0);
//        n.setUserData("aaa", "bbb", h);
//        n.cloneNode(true);
//        assertTrue(handlerCalled[0]);
//    }

    public void testRange() {
        assertFalse(blockingLoad(win, "about:")); //$NON-NLS-1$

        Document doc = win.getDocument();
        DocumentRange dr = (DocumentRange) doc;
        Range r = dr.createRange();
        int s1 = r.getStartOffset();
        int e1 = r.getEndOffset();
        assertEquals(s1, 0);
        assertEquals(e1, 0);

        NodeList nl = doc.getElementsByTagName("li"); //$NON-NLS-1$
        assertTrue(nl.getLength()>=3);

        Node n1 = nl.item(1);
        Node n2 = nl.item(2);
        r.setStartBefore(n1);
        r.setEndAfter(n2);
        int s2 = r.getStartOffset();
        int e2 = r.getEndOffset();
        assertFalse(s2==0 && e2==0);

        DocumentFragment f = r.extractContents();
        String s = f.getTextContent();
        assertTrue(s.contains("Read")); //$NON-NLS-1$
    }

    public void testDocumentView() {
        assertFalse(blockingLoad(win, "about:")); //$NON-NLS-1$

        Document doc = win.getDocument();
        DocumentView docView = (DocumentView) doc;
        AbstractView view = docView.getDefaultView();
        assertNotNull(view);
    }

    public void testDocumentTraversal() {
        assertFalse(blockingLoad(win, "about:")); //$NON-NLS-1$

        Document doc = win.getDocument();
        DocumentTraversal dt = (DocumentTraversal) doc;
        TreeWalker w = dt.createTreeWalker(doc.getDocumentElement(), NodeFilter.SHOW_ALL, null, false);

        Node n = w.nextNode();
        while (n!=null) {
            assertNotNull(n);
            n = w.nextNode();
        }
    }

    public void testDocumentEvent() {
        assertFalse(blockingLoad(win, "about:")); //$NON-NLS-1$

        Document doc = win.getDocument();
        DocumentEvent de = (DocumentEvent) doc;
        Event ev = de.createEvent("mouseevent"); //$NON-NLS-1$
        assertNotNull(ev);
    }

}
