/*
 * DO NOT EDIT.  THIS FILE IS GENERATED FROM
 */

package org.mozilla.interfaces;

public interface nsIDocShell extends nsISupports {

  String NS_IDOCSHELL_IID =
    "{7d1cf6b9-daa3-476d-8f9f-9eb2a971a95c}"; //$NON-NLS-1$

  /**
   * Loads a given URI.  This will give priority to loading the requested URI
   * in the object implementing	this interface.  If it can't be loaded here
   * however, the URL dispatcher will go through its normal process of content
   * loading.
   *
   * @param uri        - The URI to load.
   * @param loadInfo   - This is the extended load info for this load.  This
   *                     most often will be null, but if you need to do
   *                     additional setup for this load you can get a loadInfo
   *                     object by calling createLoadInfo.  Once you have this
   *                     object you can set the needed properties on it and
   *                     then pass it to loadURI.
   * @param aLoadFlags - Flags to modify load behaviour. Flags are defined
   *                     in nsIWebNavigation.
   */
  void loadURI(nsIURI uri, nsIDocShellLoadInfo loadInfo, long aLoadFlags, boolean firstParty);

  /**
   * Loads a given stream. This will give priority to loading the requested
   * stream in the object implementing this interface. If it can't be loaded
   * here however, the URL dispatched will go through its normal process of
   * content loading.
   *
   * @param aStream         - The input stream that provides access to the data
   *                          to be loaded.  This must be a blocking, threadsafe
   *                          stream implementation.
   * @param aURI            - The URI representing the stream, or null.
   * @param aContentType    - The type (MIME) of data being loaded (empty if unknown).
   * @param aContentCharset - The charset of the data being loaded (empty if unknown).
   * @param aLoadInfo       - This is the extended load info for this load.  This
   *                          most often will be null, but if you need to do
   *                          additional setup for this load you can get a
   *                          loadInfo object by calling createLoadInfo.  Once
   *                          you have this object you can set the needed
   *                          properties on it and then pass it to loadStream.
   */
  void loadStream(nsIInputStream aStream, nsIURI aURI, String aContentType, String aContentCharset, nsIDocShellLoadInfo aLoadInfo);

  int INTERNAL_LOAD_FLAGS_NONE = 0;

  int INTERNAL_LOAD_FLAGS_INHERIT_OWNER = 1;

  int INTERNAL_LOAD_FLAGS_DONT_SEND_REFERRER = 2;

  int INTERNAL_LOAD_FLAGS_ALLOW_THIRD_PARTY_FIXUP = 4;

  int INTERNAL_LOAD_FLAGS_FIRST_LOAD = 8;

  int INTERNAL_LOAD_FLAGS_BYPASS_CLASSIFIER = 16;

  /**
   * Loads the given URI.  This method is identical to loadURI(...) except
   * that its parameter list is broken out instead of being packaged inside
   * of an nsIDocShellLoadInfo object...
   *
   * @param aURI            - The URI to load.
   * @param aReferrer       - Referring URI
   * @param aOwner          - Owner (security principal)
   * @param aInheritOwner   - Flag indicating whether the owner of the current
   *                          document should be inherited if aOwner is null.
   * @param aStopActiveDoc  - Flag indicating whether loading the current
   *                          document should be stopped.
   * @param aWindowTarget   - Window target for the load.
   * @param aTypeHint       - A hint as to the content-type of the resulting
   *                          data.  May be null or empty if no hint.
   * @param aPostDataStream - Post data stream (if POSTing)
   * @param aHeadersStream  - Stream containing "extra" request headers...
   * @param aLoadFlags      - Flags to modify load behaviour. Flags are defined
   *                          in nsIWebNavigation.
   * @param aSHEntry        - Active Session History entry (if loading from SH)
   */
  void internalLoad(nsIURI aURI, nsIURI aReferrer, nsISupports aOwner, long aFlags, String aWindowTarget, String aTypeHint, nsIInputStream aPostDataStream, nsIInputStream aHeadersStream, long aLoadFlags, nsISHEntry aSHEntry, boolean firstParty, nsIDocShell[] aDocShell, nsIRequest[] aRequest);

  /**
   * Creates a DocShellLoadInfo object that you can manipulate and then pass
   * to loadURI.
   */
  void createLoadInfo(nsIDocShellLoadInfo[] loadInfo);

  /**
   * Reset state to a new content model within the current document and the document
   * viewer.  Called by the document before initiating an out of band document.write().
   */
  void prepareForNewContentModel();

  /**
   * For editors and suchlike who wish to change the URI associated with the
   * document. Note if you want to get the current URI, use the read-only
   * property on nsIWebNavigation.
   */
  void setCurrentURI(nsIURI aURI);

  /**
   * Notify the associated content viewer and all child docshells that they are
   * about to be hidden.  If |isUnload| is true, then the document is being
   * unloaded as well.
   *
   * @param isUnload if true, fire the unload event in addition to the pagehide
   *                 event.
   */
  void firePageHideNotification(boolean isUnload);

  /**
   * Presentation context for the currently loaded document.  This may be null.
   */
  long getPresContext();

  /**
   * Presentation shell for the currently loaded document.  This may be null.
   */
  long getPresShell();

  /**
   * Presentation shell for the oldest document, if this docshell is
   * currently transitioning between documents.
   */
  long getEldestPresShell();

  /**
   * Content Viewer that is currently loaded for this DocShell.  This may
   * change as the underlying content changes.
   */
  nsIContentViewer getContentViewer();

  /**
   * This attribute allows chrome to tie in to handle DOM events that may
   * be of interest to chrome.
   */
  nsIDOMEventTarget getChromeEventHandler();

  /**
   * This attribute allows chrome to tie in to handle DOM events that may
   * be of interest to chrome.
   */
  void setChromeEventHandler(nsIDOMEventTarget aChromeEventHandler);

  /**
   * The document charset info.  This is used by a load to determine priorities
   * for charset detection etc.
   */
  nsIDocumentCharsetInfo getDocumentCharsetInfo();

  /**
   * The document charset info.  This is used by a load to determine priorities
   * for charset detection etc.
   */
  void setDocumentCharsetInfo(nsIDocumentCharsetInfo aDocumentCharsetInfo);

  /**
   * Whether to allow plugin execution
   */
  boolean getAllowPlugins();

  /**
   * Whether to allow plugin execution
   */
  void setAllowPlugins(boolean aAllowPlugins);

  /**
   * Whether to allow Javascript execution
   */
  boolean getAllowJavascript();

  /**
   * Whether to allow Javascript execution
   */
  void setAllowJavascript(boolean aAllowJavascript);

  /**
   * Attribute stating if refresh based redirects can be allowed
   */
  boolean getAllowMetaRedirects();

  /**
   * Attribute stating if refresh based redirects can be allowed
   */
  void setAllowMetaRedirects(boolean aAllowMetaRedirects);

  /**
   * Attribute stating if it should allow subframes (framesets/iframes) or not
   */
  boolean getAllowSubframes();

  /**
   * Attribute stating if it should allow subframes (framesets/iframes) or not
   */
  void setAllowSubframes(boolean aAllowSubframes);

  /**
   * Attribute stating whether or not images should be loaded.
   */
  boolean getAllowImages();

  /**
   * Attribute stating whether or not images should be loaded.
   */
  void setAllowImages(boolean aAllowImages);

  /**
   * Get an enumerator over this docShell and its children.
   *
   * @param aItemType  - Only include docShells of this type, or if typeAll,
   *                     include all child shells.
   *                     Uses types from nsIDocShellTreeItem.
   * @param aDirection - Whether to enumerate forwards or backwards.
   */
  int ENUMERATE_FORWARDS = 0;

  int ENUMERATE_BACKWARDS = 1;

  nsISimpleEnumerator getDocShellEnumerator(int aItemType, int aDirection);

  /**
   * The type of application that created this window
   */
  long APP_TYPE_UNKNOWN = 0L;

  long APP_TYPE_MAIL = 1L;

  long APP_TYPE_EDITOR = 2L;

  long getAppType();

  void setAppType(long aAppType);

  /**
   * certain dochshells (like the message pane)
   * should not throw up auth dialogs
   * because it can act as a password trojan
   */
  boolean getAllowAuth();

  /**
   * certain dochshells (like the message pane)
   * should not throw up auth dialogs
   * because it can act as a password trojan
   */
  void setAllowAuth(boolean aAllowAuth);

  /**
   * Set/Get the document scale factor.  When setting this attribute, a
   * NS_ERROR_NOT_IMPLEMENTED error may be returned by implementations
   * not supporting zoom.  Implementations not supporting zoom should return
   * 1.0 all the time for the Get operation.  1.0 by the way is the default
   * of zoom.  This means 100% of normal scaling or in other words normal size
   * no zoom.
   */
  float getZoom();

  /**
   * Set/Get the document scale factor.  When setting this attribute, a
   * NS_ERROR_NOT_IMPLEMENTED error may be returned by implementations
   * not supporting zoom.  Implementations not supporting zoom should return
   * 1.0 all the time for the Get operation.  1.0 by the way is the default
   * of zoom.  This means 100% of normal scaling or in other words normal size
   * no zoom.
   */
  void setZoom(float aZoom);

  int getMarginWidth();

  void setMarginWidth(int aMarginWidth);

  int getMarginHeight();

  void setMarginHeight(int aMarginHeight);

  boolean getHasFocus();

  void setHasFocus(boolean aHasFocus);

  boolean getCanvasHasFocus();

  void setCanvasHasFocus(boolean aCanvasHasFocus);

  void tabToTreeOwner(boolean forward, boolean[] tookFocus);

  /**
   * Current busy state for DocShell
   */
  long BUSY_FLAGS_NONE = 0L;

  long BUSY_FLAGS_BUSY = 1L;

  long BUSY_FLAGS_BEFORE_PAGE_LOAD = 2L;

  long BUSY_FLAGS_PAGE_LOADING = 4L;

  /**
   * Load commands for the document
   */
  long LOAD_CMD_NORMAL = 1L;

  long LOAD_CMD_RELOAD = 2L;

  long LOAD_CMD_HISTORY = 4L;

  long getBusyFlags();

  long getLoadType();

  void setLoadType(long aLoadType);

  boolean isBeingDestroyed();

  boolean getIsExecutingOnLoadHandler();

  nsISupports getLayoutHistoryState();

  void setLayoutHistoryState(nsISupports aLayoutHistoryState);

  boolean getShouldSaveLayoutState();

  /**
   * The SecureBrowserUI object for this docshell.  This is set by XUL
   * <browser> or nsWebBrowser for their root docshell.
   */
  nsISecureBrowserUI getSecurityUI();

  /**
   * The SecureBrowserUI object for this docshell.  This is set by XUL
   * <browser> or nsWebBrowser for their root docshell.
   */
  void setSecurityUI(nsISecureBrowserUI aSecurityUI);

  /**
   * Cancel the XPCOM timers for each meta-refresh URI in this docshell,
   * and this docshell's children, recursively. The meta-refresh timers can be
   * restarted using resumeRefreshURIs().  If the timers are already suspended,
   * this has no effect.
   */
  void suspendRefreshURIs();

  /**
   * Restart the XPCOM timers for each meta-refresh URI in this docshell,
   * and this docshell's children, recursively.  If the timers are already
   * running, this has no effect.
   */
  void resumeRefreshURIs();

  /**
   * Begin firing WebProgressListener notifications for restoring a page
   * presentation. |viewer| is the content viewer whose document we are
   * starting to load.  If null, it defaults to the docshell's current content
   * viewer, creating one if necessary.  |top| should be true for the toplevel
   * docshell that is being restored; it will be set to false when this method
   * is called for child docshells.  This method will post an event to
   * complete the simulated load after returning to the event loop.
   */
  void beginRestore(nsIContentViewer viewer, boolean top);

  /**
   * Finish firing WebProgressListener notifications and DOM events for
   * restoring a page presentation.  This should only be called via
   * beginRestore().
   */
  void finishRestore();

  boolean getRestoringDocument();

  boolean getUseErrorPages();

  void setUseErrorPages(boolean aUseErrorPages);

  /**
   * Keeps track of the previous SHTransaction index and the current
   * SHTransaction index at the time that the doc shell begins to load.
   * Used for ContentViewer eviction.
   */
  int getPreviousTransIndex();

  int getLoadedTransIndex();

  /**
   * Notification that entries have been removed from the beginning of a
   * nsSHistory which has this as its rootDocShell.
   *
   * @param numEntries - The number of entries removed
   */
  void historyPurged(int numEntries);

  nsIDOMStorage getSessionStorageForURI(nsIURI uri);

  void addSessionStorage(String aDomain, nsIDOMStorage storage);

  /**
   * Gets the channel for the currently loaded document, if any.
   * For a new document load, this will be the channel of the previous document
   * until after OnLocationChange fires.
   */
  nsIChannel getCurrentDocumentChannel();

  /**
   * Set the offset of this child in its container.
   */
  void setChildOffset(long offset);

  /**
   * Find out whether the docshell is currently in the middle of a page
   * transition (after the onunload event has fired, but before the new
   * document has been set up)
   */
  boolean getIsInUnload();

  /**
   * Find out if the currently loaded document came from a suspicious channel
   * (such as a JAR channel where the server-returned content type isn't a
   * known JAR type).
   */
  boolean getChannelIsUnsafe();

}
