package Aplicacion_main;


import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
//import org.apache.pivot.wtk.Window;

import vista_ex.CVista_Presentacion;

public class FormProfesor implements Application{

	
	  private CVista_Presentacion window = null;
	    @Override
	    public void startup(Display display, Map<String, String> properties)
	        throws Exception {
	        BXMLSerializer bxmlSerializer = new BXMLSerializer();
	        window = (CVista_Presentacion)bxmlSerializer.readObject(FormProfesor.class, "/vista_ex/forms.bxml");
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
	    public static void main(String[] args)
	    {
	    	DesktopApplicationContext.main(FormProfesor.class,args);   	
	    }

}
