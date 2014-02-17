package org.mozilla.browser.test;

import static org.mozilla.browser.MozillaAutomation.mousemove;
import static org.mozilla.browser.MozillaAutomation.blockingLoad;
import static org.mozilla.browser.MozillaExecutor.mozSyncExec;
import static org.mozilla.browser.MozillaExecutor.swingSyncExec;

import java.awt.Window;

import org.mozilla.browser.impl.components.JFakeTooltip;

public class TooltipTest extends MozillaTest {

    private static final String TEST_URL = resolveURL("tooltip.html"); //$NON-NLS-1$

    public void testTooltip() {
        assertFalse(blockingLoad(win, TEST_URL));

        //move mouse above the div element
        mozSyncExec(new Runnable() { public void run() {
            assertFalse(mousemove(win, "tip1", 0.5f, 0.5f)); //$NON-NLS-1$
        }});

        //check if the tooltip appears
        final boolean[] found = { false };
        int waited = 0;
        while (waited<10000 && !found[0]) {
            swingSyncExec(new Runnable() { public void run() {
                JFakeTooltip t = findTooltip();
                if (t!=null && t.isVisible()) {
                    found[0] = true;
                }
            }});
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                log.error("wait interrupted", e); //$NON-NLS-1$
            }
            waited += 300;
        }
        assertTrue(found[0]);
    }

    private JFakeTooltip findTooltip() {
        Window[] wins = win.getOwnedWindows();
        for (Window w : wins) {
            if (!(w instanceof JFakeTooltip)) continue;

            JFakeTooltip t = (JFakeTooltip) w;
            String l = t.getTooltipLabel().getText();
            if (l.equals("Title Tooltip")) { //$NON-NLS-1$
                return t;
            }
        }

        return null;
    }

}
