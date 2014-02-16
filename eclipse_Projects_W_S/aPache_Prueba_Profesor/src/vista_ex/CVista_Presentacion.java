package vista_ex;



import java.net.URL;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.LinkedList;

import modelo.CProfesor;
import control.logico.Clogica_Control;
import modelo.CArray_profesor;
import Hilos_sincronizacion.CHilo_Create;

public class CVista_Presentacion extends Window implements Bindable{
	
	private TableView tableView = null;
    private BoxPane nameBoxPane = null;
    
    private TextInput NombreTextInput = null;
    public TextInput DomicilioTextInput = null;
    private TextInput TelefonoTextInput = null;
    private TextInput ClaveTextInput = null;
    public TextInput CorreoTextInput =null;
    private Form.Flag  flag= null;
    private Label errorLabel = null;
    
    private PushButton createButton = null;
    private PushButton readButton = null;
    private PushButton updateButton = null;
    private PushButton deleteButton = null;
    private PushButton findButton = null;
    
    private String PNombre = "";
    private String PClave = "";
    public String PDomicilio = "";
    public String  PTelefono = "";
    public String PCorreo = "";
    
    private CHilo_Create Chilo_Create = new CHilo_Create ();
    private int posicion =0;
    public Clogica_Control ControlLogico=new Clogica_Control();
    private  CArray_profesor Carray_profesor = new CArray_profesor();
    private CProfesor Profesor =new CProfesor();
   
    
    private List <CProfesor> lista;
   
    @Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
        
        nameBoxPane = (BoxPane)namespace.get("nameBoxPane");
        NombreTextInput = (TextInput)namespace.get("NombreTextInput");
        ClaveTextInput = (TextInput)namespace.get("ClaveTextInput");
        CorreoTextInput = (TextInput)namespace.get("CorreoTextInput");
        DomicilioTextInput = (TextInput)namespace.get("DomicilioTextInput");
        TelefonoTextInput = (TextInput)namespace.get("TelefonoTextInput");
        createButton = (PushButton)namespace.get("createButton");
        readButton = (PushButton)namespace.get("readButton");
        updateButton = (PushButton)namespace.get("updateButton");
        deleteButton = (PushButton)namespace.get("deleteButton");
        findButton = (PushButton)namespace.get("findButton");
        errorLabel = (Label)namespace.get("errorLabel");
      
        tableView = (TableView) namespace.get("tableView");
        lista = new LinkedList <CProfesor>();
        tableView.setTableData(lista);
      
        

  createButton.getButtonPressListeners().add(new ButtonPressListener() {//boton crear
           
	   
  @Override
  public void buttonPressed(Button button) {
             
	    entrada_Datos();
	    salida_Datos();
        //ControlLogico.create(posicion, Profesor);
	    
	    Chilo_Create.run();
	    
           //Carray_profesor=Chilo_Create.getObjarrayP();
          Carray_profesor=ControlLogico.getObjarray();
          lista.add( Carray_profesor.getArray()[posicion]);//introduce los datos del array ala lista 
          posicion++;
          tableView.repaint();
             
          validandodatosForm();
          mandardatosForm();
         
        }
        });
   
   
  
  readButton.getButtonPressListeners().add(new ButtonPressListener() {//
       @Override
       public void buttonPressed(Button button) {
    	   
   }
   });
   
   
   
   updateButton.getButtonPressListeners().add(new ButtonPressListener() {//boton modificar
       @Override
           public void buttonPressed(Button button) {
    	  
    	   entrada_Datos();
    	   salida_Datos();
    	   PClave = ClaveTextInput.getText();
   	   	   //ControlLogico.update(Integer.parseInt(PClave),Profesor,posicion);
   	 	   
   	   // if(ControlLogico.getFlag()==true){
   	    	
   	     //Alert.alert(MessageType.INFO, "Datos modificados casilla:   "+ (ControlLogico.getPosicion ()+1), 
   	    		//CVista_Presentacion.this);
   	    		   
   	     
   	    // boolean flag = false;
   	     
   	    // ControlLogico.setFlag(flag);
   	    //}
   	   // else{
   	      Alert.alert(MessageType.INFO, "Datos  no encontrados", 
   	    		CVista_Presentacion.this);
          tableView.repaint();
   	   	  
   	   // }   
    	     
   }
   });
   
   
   
   deleteButton.getButtonPressListeners().add(new ButtonPressListener() {//boton eliminar
       @Override
       public void buttonPressed(Button button) {
    	
    	   PClave = ClaveTextInput.getText();
       //ControlLogico.delete(Integer.parseInt(PClave));
       
       tableView.repaint();
       
       }
   });
   
   
   
   findButton.getButtonPressListeners().add(new ButtonPressListener() {
     
	   @Override
    public void buttonPressed(Button button) {
		   
        	
		   
    PClave = ClaveTextInput.getText();
    	   	
     //ControlLogico.find(Integer.parseInt(PClave));//pasa la clave del profesor que debe buscar, al control 
    	   
    //if(ControlLogico.getFlag()==true){
    	
    // Alert.alert(MessageType.INFO, "Datos encontrados en la casilla:   "+ (ControlLogico.getPosicion ()+1), 
    		 //CVista_Presentacion.this);
    		   
     
    // boolean flag = false;
     
     //ControlLogico.setFlag(flag);
    //}
    //else{
      Alert.alert(MessageType.INFO, "Datos  no encontrados", 
    		  CVista_Presentacion.this);
    	   tableView.repaint();
    	   //}
    	   
       
       }
   });
   
    }
        
    private void mandardatosForm(){
        Form.setFlag(nameBoxPane, flag);
        
        if (flag == null) {
            errorLabel.setText(null);
           
            Alert.alert(MessageType.INFO, "Los datos se insertaron en el array ", 
            		CVista_Presentacion.this);
        
        }
        else {
            errorLabel.setText("Se Requiere llenar todos los campos.");
        }
      }
    	
    private void validandodatosForm(){
        	
    if (PNombre.length() == 0 ||PClave.length ()==0 ) {
                     
    	flag = new Form.Flag(MessageType.ERROR, "Se requiere llenar los campos....");
        	 }
 
        }
   
    public void entrada_Datos(){
        PNombre = NombreTextInput.getText();
        PClave = ClaveTextInput.getText();
        PDomicilio=DomicilioTextInput.getText();
        PTelefono=TelefonoTextInput.getText();
        PCorreo=CorreoTextInput.getText();
        
        
            }
    private  void salida_Datos() {
    	
    	CProfesor  objprofesor =new CProfesor();
    	
    	objprofesor.setNombre(PNombre);
    	objprofesor.setClave(Integer.parseInt(PClave));
    	objprofesor.setDomicilio(PDomicilio);
    	objprofesor.setTelefono(Integer.parseInt(PTelefono));
    	objprofesor.setCorreo(PCorreo);
    	Profesor =  objprofesor;
		
    }


}
 

