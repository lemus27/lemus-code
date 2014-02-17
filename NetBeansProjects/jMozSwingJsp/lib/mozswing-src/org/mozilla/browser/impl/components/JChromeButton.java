package org.mozilla.browser.impl.components;

/**
 * Chrome toolbar button
 */
public class JChromeButton extends JImageButton
{

    private static final long serialVersionUID = -7335320862277898283L;

    public JChromeButton(String imgName,
                         String toolTip)
    {
        super(null,
              String.format("%sNormal.png",imgName), //$NON-NLS-1$
              String.format("%sPressed.png",imgName), //$NON-NLS-1$
              String.format("%sRollover.png",imgName), //$NON-NLS-1$
              String.format("%sDisabled.png",imgName)); //$NON-NLS-1$
        setToolTipText(toolTip);
        setFocusable(false);
        setBorderPainted(false);
    }

}
