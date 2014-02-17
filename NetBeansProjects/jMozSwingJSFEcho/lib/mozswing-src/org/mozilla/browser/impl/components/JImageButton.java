package org.mozilla.browser.impl.components;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Button with images
 */
public class JImageButton extends JButton
{

    private static final long serialVersionUID = 7017355128969755464L;

    static Log log = LogFactory.getLog(JImageButton.class);

    public JImageButton(String label, String iconNormal)
    {
        this(label, iconNormal, null, null, null);


    }

    public JImageButton(String label,
                        String normalIcon,
                        String pressedIcon,
                        String rolloverIcon,
                        String disabledIcon)
    {
        super(label);
        setIcon(createImageIcon(normalIcon));
        setPressedIcon(createImageIcon(pressedIcon));
        setRolloverIcon(createImageIcon(rolloverIcon));
        setDisabledIcon(createImageIcon(disabledIcon));
    }

    public JImageButton(String label,
                        Icon normalIcon,
                        Icon pressedIcon,
                        Icon rolloverIcon,
                        Icon disabledIcon)
    {
        super(label);
        setIcon(normalIcon);
        setPressedIcon(pressedIcon);
        setRolloverIcon(rolloverIcon);
        setDisabledIcon(disabledIcon);
    }

    public static ImageIcon createImageIcon(String filename) {
        if (filename==null) return null;

        String path = "images/" + filename;
        URL u = JImageButton.class.getResource(path);
        if (u==null) return null;

        try {
            ImageIcon icon = new ImageIcon(u);
            return icon;
        } catch (Exception e) {
            log.error("failed to load image", e); //$NON-NLS-1$
            return null;
        }
    }

}
