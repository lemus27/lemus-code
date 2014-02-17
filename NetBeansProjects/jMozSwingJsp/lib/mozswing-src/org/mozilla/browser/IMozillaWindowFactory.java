package org.mozilla.browser;

/**
 * Factory that creates news mozilla windows.
 *
 * Called when mozilla opens a new HTML popup.
 */
public interface IMozillaWindowFactory {

    /**
     * Called when a new mozilla window is needed.
     *
     * @param attachNewBrowserOnCreation false if only the
     *   swing window should be created without attaching
     *   the native mozilla area
     *
     * @return new mozilla window
     */
    public IMozillaWindow create(boolean attachNewBrowserOnCreation);

}
