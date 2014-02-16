package main;


import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.DesktopApplicationContext;

import vista.CVista_Presentacion_Al_Bg;


public class CMain_Pivot_App implements Application {
    private CVista_Presentacion_Al_Bg window = null;
    @Override
    public void startup(Display display, Map<String, String> properties)
        throws Exception {
        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        window = (CVista_Presentacion_Al_Bg)bxmlSerializer.readObject(CMain_Pivot_App.class, "/vista/FormAlumnos.bxml");
        
        
        
        window.open(display);   
        
        
        
        
    }
    @Override
    public boolean shutdown(boolean optional) {
        if (window != null) {
            window.close();
 
        }
        return false;
    }
    @Override
    public void suspend() {
    }
    @Override
    public void resume() {
    } 
   
}