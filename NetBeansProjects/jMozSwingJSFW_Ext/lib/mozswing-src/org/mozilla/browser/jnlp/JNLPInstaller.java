package org.mozilla.browser.jnlp;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.jnlp.ExtensionInstallerService;
import javax.jnlp.FileContents;
import javax.jnlp.ServiceManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.browser.common.XULRunnerFinder;
import org.mozilla.browser.common.XULRunnerFinder.FindMethod;
import org.mozilla.browser.common.XULRunnerFinder.JNLPConfigFinder;
import org.mozilla.browser.common.XULRunnerFinder.JavaPreferencesFinder;

/**
 * Used for installing XULRunner runtime
 * when deployed via Java WebStart.
 */
public class JNLPInstaller {
    static Log log = LogFactory.getLog(JNLPInstaller.class);

    private static void log(String s) {
        log.info(s);
    }

    private static void unzip(ExtensionInstallerService ext,
                              ZipInputStream zin,
                              File destDir)
        throws Exception
    {
        log("unzip begin"); //$NON-NLS-1$
        ext.setStatus(mt.t("JNLPInstaller.Installing_Native_Components")); //$NON-NLS-1$
        double progress = 1.0d;

        int numEntries = 0;
        ZipEntry ze;
        while((ze=zin.getNextEntry())!=null)
        {
            numEntries++;
            if(ze.isDirectory()){
                zin.closeEntry();
                continue;
            }

            //log("zip entry: "+ze.getName());
            String fname =
                destDir.getAbsolutePath() +
                File.separator + ze.getName().replace('/', File.separatorChar);
            File f = new File(fname);
            ext.setStatus(mt.t("JNLPInstaller.Installing_File")+ze.getName()); //$NON-NLS-1$

            if(f.getParent()!=null){
                File parent = f.getParentFile();
                parent.mkdirs();
            }

            //unzip the current file
            //log("writing to file: "+f.getAbsolutePath());
            byte[] buf = new byte[2048];
            int len;
            FileOutputStream fos = new FileOutputStream(f);
            while((len = zin.read(buf))!= -1){
                fos.write(buf,0,len);
            }
            fos.close();

            zin.closeEntry();

            //the archive will contain apx. 300 files
            progress += 100d/300;
            if (progress>98d) progress = 98d;
            ext.updateProgress((int)progress);
        }
        zin.close();
        log("unzip end ("+numEntries+" entries)"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private static void recDelete(File f) {
        if (f.exists()) {
           if (f.isDirectory()) {
             //if dir is found, delete all children first
             String [] flist = f.list();
             for (int i=0; i<flist.length; i++) {
                recDelete(new File(f,flist[i]));
             }
           }
           //delete the file, or empty dir
           f.delete();
        }
    }

    private static String getPlatform()
    {
        String osname = System.getProperty("os.name"); //$NON-NLS-1$
        if ("Mac OS X".equals(osname)) { //$NON-NLS-1$
            return "macosx"; //$NON-NLS-1$
        } else if ("Linux".equals(osname)) { //$NON-NLS-1$
            return "linux"; //$NON-NLS-1$
        } else if ("SunOS".equals(osname)) { //$NON-NLS-1$
            return "solaris"; //$NON-NLS-1$
        } else {
            return "win32"; //$NON-NLS-1$
        }
    }

    public static void main(String [] args)
        throws Exception
    {
        if (args.length<1) return;

        ExtensionInstallerService ext =
            (ExtensionInstallerService)
            ServiceManager.lookup("javax.jnlp.ExtensionInstallerService"); //$NON-NLS-1$

        if (args[0].equals("install")) //$NON-NLS-1$
        {
            try {
                // TODO check if we need to actually install anything
                // by first looking for a XULRunner already installed.
                // we probably still need to set the nativelib folder though
                FindMethod [] findMethods = {
                        new JavaPreferencesFinder(),
                        new JNLPConfigFinder(),
                };

                String installerVersion = System.getProperty("xulrunner.version"); //$NON-NLS-1$
                if(installerVersion == null){
                    throw new Exception(mt.t("JNLPInstaller.Missing_version_property_file")); //$NON-NLS-1$
                }

                File xulrunnerHome = XULRunnerFinder.findXULRunner(findMethods, installerVersion);

                if(xulrunnerHome != null){
                    // we found a prexisting xulrunner with the same version as this one, so
                    // we don't need to install this one.
                    log("xulrunner is already installed at: " + xulrunnerHome); //$NON-NLS-1$
                    ext.installSucceeded(false);
                    System.exit(0);
                }

                URL u = JNLPInstaller.class.getResource("native.zip"); //$NON-NLS-1$
                log("url of installer class: "+u); //$NON-NLS-1$

                String jarName = "mozswing-native"; //$NON-NLS-1$
                String upath = u.getPath();
                if (upath!=null) {
                    upath = upath.substring(0, upath.indexOf('!'));
                    File f = new File(upath);
                    String uname = f.getName();
                    if (uname!=null && uname.endsWith(".jar")) { //$NON-NLS-1$
                        jarName = uname.substring(0, uname.length()-4);
                    }
                }

                ext.updateProgress(1);
                String parentDir = ext.getInstallPath();
                File destDir = new File(parentDir, jarName);
                log("unzipping to: "+destDir.getAbsolutePath()); //$NON-NLS-1$
                if (destDir.exists()) {
                    recDelete(destDir);
                }
                destDir.mkdir();

                ZipInputStream zin = new ZipInputStream(new BufferedInputStream(u.openStream()));
                unzip(ext, zin, destDir);

                //store location where the native binaries are unzipped
                log("storing install location to setting"); //$NON-NLS-1$
                FileContents fc = JNLPConfig.getConfigFile();
                LinkedHashMap<String, String> conf = JNLPConfig.loadConfig(fc);
                conf.put("native.dir", destDir.getAbsolutePath()); //$NON-NLS-1$
                JNLPConfig.writeConfig(fc, conf);

                File nativeDir = new File(destDir, "native/"+getPlatform()); //$NON-NLS-1$


                xulrunnerHome = new File(nativeDir, "xulrunner"); //$NON-NLS-1$
                registerPreference(xulrunnerHome.getAbsolutePath(), installerVersion);

                //register native dir
                log("set native libs dir: "+nativeDir.getAbsolutePath()); //$NON-NLS-1$
                ext.setNativeLibraryInfo(nativeDir.getAbsolutePath());

                ext.setStatus(mt.t("JNLPInstaller.Installed_Native_Components")); //$NON-NLS-1$
                ext.updateProgress(100);

                ext.installSucceeded(false);
                log("instalation succeeded"); //$NON-NLS-1$
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                log("instalation failed "+sw.toString()); //$NON-NLS-1$
                ext.installFailed();
            }
        } else if (args[0].equals("uninstall")) { //$NON-NLS-1$
        }

        System.exit(0);
    }

    private static void registerPreference(String nativeDir, String version)
    {
        Preferences node =
            Preferences.systemRoot().node(XULRunnerFinder.JavaPreferencesFinder.PREFERENCE_XULRUNNER_INSTALL_DIRS);

        Preferences versionNode = node.node(version);

        String[] keys;
        try {
            int maxKeyNumber = 0;
            keys = versionNode.keys();
            for (String key : keys) {
                String directory = versionNode.get(key, null);
                if(directory != null && directory.equals(nativeDir)){
                    // this directory has already been registered
                    return;
                }
                try {
                    int keyInt = Integer.parseInt(key);
                    if(keyInt > maxKeyNumber){
                        maxKeyNumber = keyInt;
                    }
                } catch (NumberFormatException e){
                    log("invalid key: " + key + " in preference node: " + versionNode.absolutePath()); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }

            // the directory has not been registered
            versionNode.put("" + (maxKeyNumber + 1), nativeDir); //$NON-NLS-1$
        } catch (BackingStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
