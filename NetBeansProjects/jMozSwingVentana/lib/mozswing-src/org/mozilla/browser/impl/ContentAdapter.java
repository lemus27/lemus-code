/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is mozilla.org code.
 *
 * The Initial Developer of the Original Code is
 * Christopher Blizzard. Portions created by Christopher Blizzard are Copyright (C) Christopher Blizzard.  All Rights Reserved.
 * Portions created by the Initial Developer are Copyright (C) 2001
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Christopher Blizzard <blizzard@mozilla.org>
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */
package org.mozilla.browser.impl;

import static org.mozilla.browser.XPCOMUtils.getService;
import static org.mozilla.browser.XPCOMUtils.qi;
import static org.mozilla.xpcom.IXPCOMError.NS_ERROR_NOT_IMPLEMENTED;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.interfaces.nsIRequest;
import org.mozilla.interfaces.nsIStreamListener;
import org.mozilla.interfaces.nsISupports;
import org.mozilla.interfaces.nsIURI;
import org.mozilla.interfaces.nsIURIContentListener;
import org.mozilla.interfaces.nsIWebNavigation;
import org.mozilla.interfaces.nsIWebNavigationInfo;
import org.mozilla.xpcom.Mozilla;
import org.mozilla.xpcom.XPCOMException;

/**
 * Listener for content events in mozilla browser
 */
public class ContentAdapter
    implements nsIURIContentListener
{

    static Log log = LogFactory.getLog(ContentAdapter.class);

    private final ChromeAdapter ma;

    public ContentAdapter(ChromeAdapter ma) {
        this.ma = ma;
    }

    public boolean onStartURIOpen(nsIURI aURI) {
        String uri = aURI.getSpec();
        log.trace("Loading "+uri); //$NON-NLS-1$
        boolean abortOpen = false;
        return abortOpen;
    }

    public boolean doContent(String aContentType, boolean aIsContentPreferred, nsIRequest aRequest, nsIStreamListener[] aContentHandler) {
        throw new XPCOMException(NS_ERROR_NOT_IMPLEMENTED);
    }

    public boolean isPreferred(String aContentType, String[] aDesiredContentType) {
        return canHandleContent(aContentType, true, aDesiredContentType);
    }

    public boolean canHandleContent(String aContentType,
                                    boolean aIsContentPreferred,
                                    String[] aDesiredContentType)
    {
        aDesiredContentType[0] = null;

        if (aContentType!=null) {
            nsIWebNavigationInfo webNavInfo = getService("@mozilla.org/webnavigation-info;1", nsIWebNavigationInfo.class); //$NON-NLS-1$
            if (webNavInfo!=null) {
                nsIWebNavigation webNav = qi(ma.getWebBrowser(), nsIWebNavigation.class);
                long canHandle = webNavInfo.isTypeSupported(aContentType, webNav);
                return canHandle!=nsIWebNavigationInfo.UNSUPPORTED;
            }
        }

        return false;
    }

    public nsISupports getLoadCookie() {
        throw new XPCOMException(NS_ERROR_NOT_IMPLEMENTED);
    }

    public void setLoadCookie(nsISupports aLoadCookie) {
        throw new XPCOMException(NS_ERROR_NOT_IMPLEMENTED);
    }

    public nsIURIContentListener getParentContentListener() {
        throw new XPCOMException(NS_ERROR_NOT_IMPLEMENTED);
    }

    public void setParentContentListener(nsIURIContentListener aParentContentListener) {
        throw new XPCOMException(NS_ERROR_NOT_IMPLEMENTED);
    }

    public nsISupports queryInterface(String uuid) {
        return Mozilla.queryInterface(this, uuid);
    }

}
