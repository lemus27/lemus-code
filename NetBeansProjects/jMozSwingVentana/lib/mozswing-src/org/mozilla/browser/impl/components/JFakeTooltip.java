package org.mozilla.browser.impl.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.mozilla.browser.IMozillaWindow;


public class JFakeTooltip extends JDialog {

    private static final long serialVersionUID = -5265135238377781454L;

    private final IMozillaWindow owner;
    private JLabel tooltipLabel;

    //workaround for JDialog(Window, ...) constructor
    //awailable only in 1.6
    public static JFakeTooltip create(IMozillaWindow owner) {
        Window win = SwingUtilities.getWindowAncestor(owner.getMozillaContainer());
        if (win instanceof Frame)
            return new JFakeTooltip((Frame) win, owner);
        else if (win instanceof Dialog)
            return new JFakeTooltip((Dialog) win, owner);
        else
            return new JFakeTooltip((Frame) null, owner);

    }

    public JFakeTooltip(Frame ownerFrame, IMozillaWindow owner) {
        super(ownerFrame);
        this.owner = owner;
        init();
    }

    public JFakeTooltip(Dialog ownerDialog, IMozillaWindow owner) {
        super(ownerDialog);
        this.owner = owner;
        init();
    }

    public void init() {
        setDefaultLookAndFeelDecorated(false);
        setUndecorated(true);
        setFocusable(false);
        setFocusableWindowState(false);
        //alwaysonTop paints tooltip also
        //above other windows
        //setAlwaysOnTop(true);

        JPanel box = new JPanel();
        Border border = UIManager.getBorder("ToolTip.border"); //$NON-NLS-1$
        box.setBorder(border);
        box.setLayout(new BorderLayout());

        tooltipLabel = new JLabel(""); //$NON-NLS-1$
        tooltipLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Color fg = UIManager.getColor("ToolTip.foreground"); //$NON-NLS-1$
        Color bg = UIManager.getColor("ToolTip.background"); //$NON-NLS-1$
        Font f = UIManager.getFont("ToolTip.font"); //$NON-NLS-1$
        tooltipLabel.setOpaque(true);
        tooltipLabel.setForeground(fg);
        tooltipLabel.setBackground(bg);
        tooltipLabel.setFont(f);
        //add space at the sides
        Border b = BorderFactory.createEmptyBorder(0,3,0,3);
        tooltipLabel.setBorder(b);
        box.add(tooltipLabel, BorderLayout.CENTER);

        setContentPane(box);
    }

    public void setup(int x, int y, String aText)
    {
        tooltipLabel.setText(aText);

        //start with coordinates relative to mozilla canvas
        Point loc = new Point(x,y);
        //relocate coordinates relative to parent window
        Component parent = owner.getMozillaContainer();
        SwingUtilities.convertPointToScreen(loc, parent);
        //add size of the cursor
        loc.translate(15, 15);
        setLocation(loc);

        pack();
    }

    public JLabel getTooltipLabel() {
        return tooltipLabel;
    }

}
