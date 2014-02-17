/*
 * DO NOT EDIT.  THIS FILE IS GENERATED FROM
 * nsIAppShellModal.idl
 */

package org.mozilla.interfaces;

/**
 *  Component for running nested (modal) event loop
 */
public interface nsIAppShellModal extends nsISupports {

  String NS_IAPPSHELLMODAL_IID =
    "{622c8420-9077-4f85-a8b0-234f5e28647a}"; //$NON-NLS-1$

  /**
     * Shows the window as a modal window.
     * @return (the function error code) the status value specified by
     *         in exitModalEventLoop.
     */
  void enterModalEventLoop(nsIBaseWindow win, nsIBaseWindow parentWin);

  /**
     * Is the window modal (that is, currently executing a modal loop)?
     * @return true if it's a modal window
     */
  boolean isInModalEventLoop();

  /**
     * Exit a modal event loop if we're in one. The implementation
     * should also exit out of the loop if the window is destroyed.
     * @param aStatus - the result code to return from showAsModal
     */
  void exitModalEventLoop();

}