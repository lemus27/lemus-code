package vista;

import java.net.URL;





import java.io.FileInputStream;

import logica.control.CLogica_Control;
import modelo.CAlumno;
import modelo.CArray_ALumno;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.collections.LinkedList;
import org.apache.pivot.collections.List;
import org.apache.pivot.wtk.Alert;

import org.omg.CORBA.ExceptionList;


public class CVista_Presentacion_Al_Bg  extends Window implements Bindable{

	private CAlumno objalumno=new CAlumno();
	private CLogica_Control objcontrol=new CLogica_Control();
	private CArray_ALumno objarray=new CArray_ALumno();
	
	
	private TextInput NombreBxml = null;
	private TextInput NocontrolBxml = null;
	private TextInput CarreraBxml = null;
	private TextInput SemestreBxml = null;
	private TextInput GeneracionBxml = null;
	
	private PushButton BotonAgregar = null;
	private PushButton BotonBorrar = null;
	private PushButton BotonModificar = null;
	private PushButton BotonBuscar = null;



    
	private TableView tableView;
	
	private String VentNocontrol="";
	private String VentNombre="";
	private String VentCarrera="";
	private String VentSemestre="";
	private String VentGeneracion="";
	
	private List <CAlumno> lista;
	
	private int position=0;

    
	

	
	
	public void initialize(Map<String, Object> namespace, URL location,
			Resources resources) {
		
		
		NombreBxml = (TextInput) namespace.get("NombreBxml");
		NocontrolBxml = (TextInput) namespace.get("NocontrolBxml");
		CarreraBxml = (TextInput) namespace.get("CarreraBxml");
		SemestreBxml = (TextInput) namespace.get("SemestreBxml");
		GeneracionBxml = (TextInput) namespace.get("GeneracionBxml");
		
		BotonAgregar = (PushButton) namespace.get("BotonAgregar");
		BotonBorrar = (PushButton) namespace.get("BotonBorrar");
		BotonModificar = (PushButton) namespace.get("BotonModificar");
		BotonBuscar = (PushButton) namespace.get("BotonBuscar");

        tableView = (TableView) namespace.get("laTabla");
        lista = new LinkedList <CAlumno>();
        tableView.setTableData(lista);
       
          
		
 BotonAgregar.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {

				
				BajarValForm();
				EnviarAlModelo();
				 objcontrol.insertar(position, objalumno);
		           
	         	 objarray=objcontrol.getObjarray();
	         	 
	         	 lista.add(objarray.getArray()[position]); 
	         	
	           
	             tableView.repaint();
	             
	             position++;
	          
	             EvitarQueSelleneLalistayArreglo();
		
			}
		});	
 
 BotonBorrar.getButtonPressListeners().add(new ButtonPressListener() {
		@Override
		public void buttonPressed(Button button) {
			
			Alert.alert(MessageType.INFO, "Recuerda solo poner el No. CLogica_Control para poderlo borrar ! ",CVista_Presentacion_Al_Bg.this);
	         
			 VentNocontrol = NocontrolBxml.getText();
        	
			 objcontrol.borrar(Integer.parseInt(VentNocontrol));
		
			 tableView.repaint();    
	
		}
	});
 
 BotonBuscar.getButtonPressListeners().add(new ButtonPressListener() {
		@Override
		public void buttonPressed(Button button) {

			 Alert.alert(MessageType.INFO, "Recuerda poner el No. CLogica_Control para poderlo buscar ! ",CVista_Presentacion_Al_Bg.this);
	         
			 VentNocontrol = NocontrolBxml.getText();
   	
			 objcontrol.buscar(Integer.parseInt(VentNocontrol));
			 if(objcontrol.getFlag()==true){
				 Alert.alert(MessageType.INFO, "Si se encontro en la posicion "+objcontrol.getPosicion() +" del arreglo que va desde 0 hasta 10 !",CVista_Presentacion_Al_Bg.this);
				boolean flag=false;
				objcontrol.setFlag(flag);
		
			 }
			 else{Alert.alert(MessageType.INFO, "No se encontro ! ",CVista_Presentacion_Al_Bg.this);}
		
              tableView.repaint();
			}
		
	
	});	
 
 BotonModificar.getButtonPressListeners().add(new ButtonPressListener() {
		@Override
		public void buttonPressed(Button button) {

	   Alert.alert(MessageType.INFO, "Recuerda poner el No. CLogica_Control para saber cual modificar y los demas campos! ",CVista_Presentacion_Al_Bg.this);
	         
		BajarValForm();
		EnviarAlModelo();
			
		VentNocontrol = NocontrolBxml.getText();	
		objcontrol.modificar( Integer.parseInt(VentNocontrol),objalumno);
		tableView.repaint();
			 
		}
	});	


 

		
		
	}
	
	private void EnviarAlModelo(){
    
	CAlumno objtemporal = new CAlumno();
		objtemporal.setNombre(VentNombre);
		objtemporal.setCarrera(VentCarrera);
		objtemporal.setGeneracion(Integer.parseInt(VentGeneracion));
		objtemporal.setSemestre(Integer.parseInt(VentSemestre));
		objtemporal.setNocontrol(Integer.parseInt(VentNocontrol));
	
                objalumno = objtemporal;
		
	}
	
	private void BajarValForm(){
		
		VentNombre = NombreBxml.getText();
		VentNocontrol= NocontrolBxml.getText();
		VentSemestre= SemestreBxml.getText();
		VentGeneracion= GeneracionBxml.getText();
		VentCarrera= CarreraBxml.getText();
		
	}
	
    private void EvitarQueSelleneLalistayArreglo(){
    	
 	   if(position==10){
  		   
   		  Alert.alert(MessageType.INFO, "La lista se lleno! ",CVista_Presentacion_Al_Bg.this);
   	  position=0;
   	  lista.clear();
   	   }
 }
}
