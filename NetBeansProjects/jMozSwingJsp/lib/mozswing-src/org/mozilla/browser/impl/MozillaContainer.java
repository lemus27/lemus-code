package org.mozilla.browser.impl;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.browser.MozillaConfig;
import org.mozilla.browser.MozillaInitialization;
import org.mozilla.browser.mt;
import org.mozilla.browser.impl.components.JErrorPanel;

public class MozillaContainer extends JPanel {

    private static final long serialVersionUID = 1893904117076267801L;

    static Log log = LogFactory.getLog(MozillaCanvas.class);

    private ChromeAdapter chromeAdapter;

    private MozillaCanvas mozCanvas;

//    private ContainerListener containerListener;

    public MozillaContainer() {
        setLayout(new BorderLayout());
        //setBackground(Color.RED);
        mozCanvas = new MozillaCanvas();
        if (MozillaInitialization.isInitialized()) {
            add(mozCanvas, BorderLayout.CENTER);
        } else {
            String details = MozillaConfig.getConfigSummary();
            String strace = JErrorPanel.stackTraceToString(MozillaInitialization.getError());
            if (strace!=null) {
                details += "\n"; //$NON-NLS-1$
                details += "--------------------------------------------------\n"; //$NON-NLS-1$
                details += "\n"; //$NON-NLS-1$
                details += strace;
            }

            JErrorPanel p =
                new JErrorPanel(mt.t("MozillaContainer.InitFailed"), details); //$NON-NLS-1$
            add(p, BorderLayout.CENTER);
        }
    }

    public void onAttachBrowser(ChromeAdapter chromeAdapter) {
        assert chromeAdapter!=null;
        assert this.chromeAdapter==null;

        this.chromeAdapter = chromeAdapter;

        assert mozCanvas!=null;
        mozCanvas.onAttachBrowser(chromeAdapter);
    }

    public void onDetachBrowser() {
        assert mozCanvas!=null;
        mozCanvas.onDetachBrowser();

        this.chromeAdapter = null;
    }

    public MozillaCanvas getMozillaCanvas() {
        return mozCanvas;
    }


    @Override
    public void addNotify() {
        // TODO Auto-generated method stub
        super.addNotify();


//        setFocusable(true);
    //    setFocusTraversalKeysEnabled(false);
//        containerListener = new ContainerListener();
//        addFocusListener(containerListener);
//        setFocusable(true);
//        setFocusCycleRoot(true);
//        setFocusTraversalPolicy(new MyOwnFocusTraversalPolicy());
//
//        KeyboardFocusManager focusManager =
//            KeyboardFocusManager.getCurrentKeyboardFocusManager();
//        focusManager.addPropertyChangeListener(
//            new PropertyChangeListener() {
//                public void propertyChange(PropertyChangeEvent e) {
//                    String prop = e.getPropertyName();
////                    log.debug("prop="+e);
////
//                    if (("focusOwner".equals(prop)) /*&&
//                          ((e.getNewValue()) instanceof Picture)*/) {
//                        if (mozHasFocus) {
////                            log.debug("prop="+prop);
////                            mozAsyncExec(new Runnable() { public void run() {
////                                nsIWebBrowserFocus webBrowserFocus = qi(chromeAdapter.getWebBrowser(), nsIWebBrowserFocus.class);
////                                webBrowserFocus.deactivate();
////                            }});
//                            //mozHasFocus = false;
//                        }
////                        Component comp = (Component)e.getNewValue();
////                        String name = comp.getName();
////                        Integer num = new Integer(name);
////                        int index = num.intValue();
////                        if (index < 0 || index > comments.length) {
////                            index = 0;
////                        }
////                        info.setText(comments[index]);
//                    }
//                }
//            }
//        );
    }

//    private boolean mozHasFocus = false;

//    private class ContainerListener implements FocusListener
//    {
//        public void focusGained(FocusEvent ev) {
//            log.debug("container focus in: id="+ev.paramString());
//            //if (!mozHasFocus) {
////                mozAsyncExec(new Runnable() { public void run() {
////                    nsIWebBrowserFocus webBrowserFocus = qi(chromeAdapter.getWebBrowser(), nsIWebBrowserFocus.class);
////                    webBrowserFocus.activate();
////                }});
////                mozHasFocus = true;
////            }
//        }
//
//        public void focusLost(FocusEvent e) {
//            log.debug("container focus out:");
//        }
//    }

//    @Override
//    public boolean requestFocus(boolean temporary) {
//        log.debug("REQUEST focus");
//        return super.requestFocus(temporary);
//    }

//    @Override
//    public void transferFocus() {
//        log.debug("container transfer focus");
//        //help in nsIWebBrowserFocus.deactivate
//        //suggest we should deactivate the browser
////        mozAsyncExec(new Runnable() { public void run() {
////            nsIWebBrowserFocus webBrowserFocus = qi(chromeAdapter.getWebBrowser(), nsIWebBrowserFocus.class);
////            webBrowserFocus.deactivate();
////        }});
//
////        super.transferFocus();
//    }

//    @Override
//    public void transferFocusBackward() {
//        log.debug("container transfer focus backward");
//        //super.transferFocusBackward();
//        super.transferFocusBackward();
//    }

//    @Override
//    public void transferFocusDownCycle() {
//        log.debug("down");
//        super.transferFocusDownCycle();
//    }
//
//    public class MyOwnFocusTraversalPolicy
//        extends FocusTraversalPolicy
//    {
//
//        public MyOwnFocusTraversalPolicy() {
//        }
//
//        public Component getComponentAfter(Container focusCycleRoot,
//                                           Component aComponent)
//        {
//            return mozCanvas;
//        }
//
//        public Component getComponentBefore(Container focusCycleRoot,
//                                          Component aComponent)
//        {
//            return mozCanvas;
//        }
//
//        public Component getDefaultComponent(Container focusCycleRoot) {
//            return mozCanvas;
//        }
//
//        public Component getLastComponent(Container focusCycleRoot) {
//            return mozCanvas;
//        }
//
//        public Component getFirstComponent(Container focusCycleRoot) {
//            return mozCanvas;
//        }
//    }
}
