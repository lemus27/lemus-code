package org.mozilla.browser.impl.components;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.mozilla.browser.mt;

public class JErrorPanel extends JPanel {

    private static final long serialVersionUID = 5050576854897063304L;

    public static final String showDetailsLabel = mt.t("JErrorPanel.Show_Details"); //$NON-NLS-1$
    public static final String hideDetailsLabel = mt.t("JErrorPanel.Hide_Details"); //$NON-NLS-1$

    public JErrorPanel(String errorMessage, String errorDetails) {
        if (errorMessage==null) errorMessage = mt.t("JErrorPanel.An_unexpected_error_occured"); //$NON-NLS-1$

        setBorder(new EmptyBorder(15, 20, 10, 20));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;

        Insets inleft = new Insets(0, 10, 0, 0);
        Insets inleft2 = new Insets(0, 25, 0, 0);
        Insets inlefttop = new Insets(10, 5, 0, 0);

        JLabel lb1 = new JLabel(UIManager.getIcon("OptionPane.errorIcon")); //$NON-NLS-1$
        c.gridx = 0;
        c.weightx = 0.0;
        add(lb1, c);

        JMultiLineLabel lb2 = new JMultiLineLabel();
        lb2.setText(errorMessage);
        lb2.setHorizontalAlignment(SwingConstants.LEFT);
        c.gridx++;
        c.weightx = 0.0;
        c.insets = inleft;
        add(lb2, c);

        final JImageButton detailsButton;
        if (errorDetails!=null) {
            detailsButton = new JImageButton(showDetailsLabel, null);
            c.gridx++;
            c.weightx = 0.0;
            c.fill = GridBagConstraints.NONE;
            c.insets = inleft2;
            add(detailsButton, c);
        } else {
            detailsButton = null;
        }

        Component hglue1 = Box.createHorizontalGlue();
        c.gridx++;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(hglue1, c);
        int gridCols = c.gridx + 1;

        c.gridy++;

        if (errorDetails!=null) {
            JTextArea ta = new JTextArea(5, 10);
            ta.setLineWrap(true);
            ta.setWrapStyleWord(true);
            ta.setEditable(false);

            ta.setText(errorDetails);

            final JScrollPane sp = new JScrollPane(ta);
            c.gridx = 0;
            c.weightx = 1.0; c.weighty = 1.0;
            c.fill = GridBagConstraints.BOTH;
            c.gridwidth = gridCols;
            c.insets = inlefttop;
            add(sp, c);
            sp.setVisible(false);

            detailsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boolean oldVis = sp.isVisible();
                    sp.setVisible(!oldVis);
                    detailsButton.setText(!oldVis ? hideDetailsLabel : showDetailsLabel);
                }
            });
        }

        c.gridy++;

        Component hglue = Box.createHorizontalGlue();
        c.gridx = 0;
        c.weightx = 1.0; c.weighty = 0.000001;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = gridCols;
        add(hglue, c);

    }

    public static String stackTraceToString(Throwable t) {
        if (t==null) return null;

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        String s = sw.getBuffer().toString();
        s = s.replaceAll("\t", "    "); //$NON-NLS-1$ //$NON-NLS-2$

        return s;
    }

}
