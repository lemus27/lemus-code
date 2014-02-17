package org.mozilla.browser.jnlp;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.jnlp.BasicService;
import javax.jnlp.FileContents;
import javax.jnlp.PersistenceService;
import javax.jnlp.ServiceManager;

public class JNLPConfig {

    public static FileContents getConfigFile()
        throws Exception
    {
        BasicService bs =
            (BasicService)
            ServiceManager.lookup("javax.jnlp.BasicService"); //$NON-NLS-1$
        PersistenceService pers =
            (PersistenceService)
            ServiceManager.lookup("javax.jnlp.PersistenceService"); //$NON-NLS-1$

        URL pu = new URL(bs.getCodeBase(), "mozswing-config.txt"); //$NON-NLS-1$
        FileContents fc;
        try {
            fc = pers.get(pu);
        } catch (FileNotFoundException e) {
            pers.create(pu, 4096);
            fc = pers.get(pu);
        }
        return fc;
    }

    public static LinkedHashMap<String, String> loadConfig(FileContents fc)
        throws Exception
    {
        LinkedHashMap<String, String> conf = new LinkedHashMap<String, String>();

        LineNumberReader r = new LineNumberReader(new InputStreamReader(fc.getInputStream(), "UTF-8")); //$NON-NLS-1$
        String line;
        while ((line=r.readLine())!=null) {
            int idx = line.indexOf('=');
            if (idx<0) continue;
            String key = line.substring(0, idx).trim();
            String val = line.substring(idx+1).trim();
            if (key.length()>0 && val.length()>0)
                conf.put(key, val);
        }
        r.close();

        return conf;
    }

    public static void writeConfig(FileContents fc, LinkedHashMap<String, String> conf)
        throws Exception
    {
        PrintWriter w = new PrintWriter(new OutputStreamWriter(fc.getOutputStream(true), "UTF-8")); //$NON-NLS-1$

        for(Map.Entry<String,String> en: conf.entrySet()) {
            w.write(en.getKey()+" = "+en.getValue()); //$NON-NLS-1$
        }

        w.close();
    }

    /**
     * This method is called by the XULRunnerFinder class in the mozilla event thread
     * which on OSX is the cocoa event thread.  On this thread things like the blocking modal 
     * dialogs of JOptionPane. 
     * 
     * @return
     * @throws Exception
     */
    public static String getNativeDir()
        throws Exception
    {
        System.err.println("Called getNativeDir method"); //$NON-NLS-1$

        FileContents fc = getConfigFile();
        LinkedHashMap<String, String> conf = loadConfig(fc);
        String jnlpDir = conf.get("native.dir"); //$NON-NLS-1$
        
        return jnlpDir;
    }
}
