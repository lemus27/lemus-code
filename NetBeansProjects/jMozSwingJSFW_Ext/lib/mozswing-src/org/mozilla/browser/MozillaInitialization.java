package org.mozilla.browser;

import static org.mozilla.browser.MozillaExecutor.mozInit;
import static org.mozilla.browser.common.Platform.Linux;
import static org.mozilla.browser.common.Platform.Solaris;
import static org.mozilla.browser.common.Platform.platform;
import static org.mozilla.browser.impl.jna.LibC.libc;

import java.io.File;
import java.io.IOException;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.browser.common.Platform;
import org.mozilla.browser.common.XULRunnerFinder;
import org.mozilla.browser.impl.LocationProvider;
import org.mozilla.browser.impl.WindowCreator;
import org.mozilla.browser.impl.XREAppData;
import org.mozilla.dom.ThreadProxy;
import org.mozilla.interfaces.nsIExtensionManager;
import org.mozilla.interfaces.nsIPrefBranch;
import org.mozilla.interfaces.nsIWindowWatcher;
import org.mozilla.xpcom.Mozilla;

/**
 * Mozilla intialization.
 */
public class MozillaInitialization {

    static Log log = LogFactory.getLog(MozillaInitialization.class);

    /**
     * Status of mozilla initialization.
     */
    public static enum InitStatus {
        /**
         * Mozilla has not been initialized yet.
         */
        NONE,
        /**
         * Mozilla was successfully initialized.
         */
        INITIALIZED,
        /**
         * Mozilla initialization failed.
         */
        FAILED
    };
    private static InitStatus status = InitStatus.NONE;
    static Throwable error = null;

    static WindowCreator winCreator;

    /**
     * Executes Mozilla (XULRunner) initialization.
     */
    public static void initialize () {
        if (status!=InitStatus.NONE) return;

        //set the all-permissions rights also for other classloaders,
        //for more details see
        //http://saloon.javaranch.com/cgi-bin/ubb/ultimatebb.cgi?ubb=get_topic&f=53&t=000106
        //this is needed when running under a secure classloader like the webstart
        //environment.
        Policy.setPolicy( new Policy() {
            public PermissionCollection getPermissions(CodeSource codesource) {
                Permissions perms = new Permissions();
                perms.add(new AllPermission());
                return(perms);
            }
            public void refresh(){}
        });

        try {

            File xulrunnerDir = MozillaConfig.getXULRunnerHome();
            if (xulrunnerDir != null) {
                if (!XULRunnerFinder.isXULRunnerDir(xulrunnerDir)) {
                    // this isn't a valid home directory
                    log.error("invalid current xulrunner location "+xulrunnerDir.getAbsolutePath()); //$NON-NLS-1$
                    log.info("Continuing to search for other xulrunners"); //$NON-NLS-1$
                    xulrunnerDir = null;
                }
            }

            if (xulrunnerDir == null) {
                xulrunnerDir = XULRunnerFinder.findXULRunner();
                MozillaConfig.setXULRunnerHome(xulrunnerDir);
            }

            if (xulrunnerDir==null) {
                throw new IOException(mt.t("MozillaInitialization.Unable_to_resolve_XULRunner_home")); //$NON-NLS-1$
            }

            log.info("Using xul runner dir: " + xulrunnerDir.getAbsolutePath()); //$NON-NLS-1$
            final File foundXulrunnerDir = xulrunnerDir;

            //delete xpcom registry files for
            //safe upgrade to newer xulrunner version,
            //less confusions when adding own xpt components
            File componentsDir = new File(xulrunnerDir, "components"); //$NON-NLS-1$
            File compreg = new File(componentsDir, "compreg.dat"); //$NON-NLS-1$
            if (compreg.isFile())
                compreg.delete();
            File xpti = new File(componentsDir, "xpti.dat"); //$NON-NLS-1$
            if (xpti.isFile())
                xpti.delete();

            final File profDir;
            if (MozillaConfig.isInitializeWithProfile()) {
                if (MozillaConfig.getProfileDir()!=null) {
                    //specified from outside
                    profDir = MozillaConfig.getProfileDir();
                } else {
                    //create temp file, and delete it
                    File f = File.createTempFile("swing-mozilla", ""); //$NON-NLS-1$ //$NON-NLS-2$
                    f.delete();
                    //use the temp file as a name for the temporary profile dir
                    profDir = f;
                    profDir.mkdirs();
                    MozillaConfig.setProfileDir(profDir);
                }
            } else {
                profDir = null;
            }

            File nativeLibsDir = foundXulrunnerDir.getParentFile();
            if (Platform.platform==Platform.OSX &&
                !new File(nativeLibsDir, "libcocoautils.jnilib").exists()) //$NON-NLS-1$
            {
                log.error("Unable to resolve location of MozSwing native libraries"); //$NON-NLS-1$
                log.info("Continuing assuming java.library.path is set"); //$NON-NLS-1$
                nativeLibsDir = null;
            }
            final File foundNativeLibsDir = nativeLibsDir;

            mozInit(foundXulrunnerDir, foundNativeLibsDir,
                    new RunnableEx() { public void run() throws Exception {

                if (platform==Solaris || platform==Linux) {
                    //sighandlers are overridden only on XP_UNIX
                    int ret = libc.setenv("MOZ_DISABLE_SIG_HANDLER","1",true); //$NON-NLS-1$ //$NON-NLS-2$
                    assert ret==0;
                    //String val = libc.getenv("MOZ_DISABLE_SIG_HANDLER");
                    //assert val!=null && val.equals("1");
                }

                Mozilla moz = Mozilla.getInstance();
                moz.initialize(foundXulrunnerDir);
                LocationProvider locProvider = new LocationProvider(foundXulrunnerDir, profDir);
                moz.initEmbedding(foundXulrunnerDir, foundXulrunnerDir, locProvider, new XREAppData());

                if (profDir!=null) {
                    moz.lockProfileDirectory(profDir);
                    moz.notifyProfile();
                }

                // Get the window watcher service
                winCreator = new WindowCreator();
                nsIWindowWatcher winWatcher = XPCOMUtils.getService("@mozilla.org/embedcomp/window-watcher;1", nsIWindowWatcher.class); //$NON-NLS-1$
                winWatcher.setWindowCreator(winCreator);

                //needed for open/save unknown content dialog,
                //because otherwise code
                // var autodownload = prefs.getBoolPref("browser.download.useDownloadDir");
                //in nsHelperAppDlg.js:92 fails
                nsIPrefBranch pref = XPCOMUtils.getService("@mozilla.org/preferences-service;1", nsIPrefBranch.class); //$NON-NLS-1$
                pref.setBoolPref("browser.download.useDownloadDir", 0); //$NON-NLS-1$

                //workaround known java plugin bug
                //on win32 javaplugin runs in-process, therefore
                //loading a webpage with a java applet inside
                //a java-based mozilla embedding applications
                //hangs the whole jvm
                if (Platform.platform==Platform.Win32 ||
                    Platform.platform==Platform.OSX)
                {
                    pref.setBoolPref("security.enable_java", 0); //$NON-NLS-1$
                }

                //when page loading fails, display an error page,
                //similar as firefox does
                pref.setBoolPref("browser.xul.error_pages.enabled", 1); //$NON-NLS-1$

                //disable various security warning dialogs
                //such as when entering/leaving https site or
                //submitting form
                pref.setBoolPref("security.warn_entering_secure", 0); //$NON-NLS-1$
                pref.setBoolPref("security.warn_entering_weak", 0); //$NON-NLS-1$
                pref.setBoolPref("security.warn_leaving_secure", 0); //$NON-NLS-1$
                pref.setBoolPref("security.warn_submit_insecure", 0); //$NON-NLS-1$
                pref.setBoolPref("security.warn_viewing_mixed", 0); //$NON-NLS-1$

                //nsIXULAppInfo appInfo = XPCOMUtils.getService("@mozilla.org/xre/app-info;1", nsIXULAppInfo.class);
                //String ver = appInfo.getPlatformVersion();
                //System.err.println("s="+ver);

                nsIExtensionManager em = XPCOMUtils.getService("@mozilla.org/extensions/manager;1", nsIExtensionManager.class); //$NON-NLS-1$
                em.start(null);

                //configure mozdom4java
                ThreadProxy.setSingleton(new ThreadProxy() {
                    @Override
                    public boolean isMozillaThread() {
                        return MozillaExecutor.isMozillaThread();
                    }
                    @Override
                    public void syncExec(Runnable task) {
                        MozillaExecutor.mozSyncExec(task);
                    }
                    @Override
                    public <V> V syncExec(Callable<V> task) {
                        try {
                            return MozillaExecutor.mozSyncExec(task);
                        } catch (MozillaException e) {
                            throw new MozillaRuntimeException(e);
                        }
                    }
                });

                status = InitStatus.INITIALIZED;

                //run possible pre-init tasks such
                //as proxy settings, disable images
                MozillaExecutor.runPostponedPreInitTasks();
            }});
        } catch (Exception e) {
            log.error("failed to initialize mozilla", e); //$NON-NLS-1$
            status = InitStatus.FAILED;
            error = e;
        }
    }

    /**
     * Returns true, if mozilla was successfully initialized.
     *
     * @return true if mozilla was successfully initialized.
     */
    public static boolean isInitialized() {
        return status==InitStatus.INITIALIZED;
    }

    /**
     * Returns exception that occurred during mozilla
     * initialization or null.
     *
     * @return initialization exception
     */
    public static Throwable getError() {
        return error;
    }
    /**
     * Returns mozilla initialization status, one
     * of NONE, INITIALIZED, FAILED.
     *
     * @return mozilla initialization status
     */
    public static InitStatus getStatus() {
        return status;
    }

    /**
     * Returns MozSwing's implementation of the
     * WindowCreator XPCOM component.
     *
     * @return WindowCreator XPCOM component
     */
    public static WindowCreator getWinCreator() {
        return winCreator;
    }
}
