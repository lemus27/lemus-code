package Hilos_sincronizacion;

import modelo.CArray_profesor;
import modelo.CProfesor;
import control.logico.Clogica_Control;




public class CHilo_Create extends Thread {
	
	int posicion;
	private boolean flag;	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public CProfesor profesor =new CProfesor();  
    public CArray_profesor objarrayP = new CArray_profesor();
    private Clogica_Control objControl =new Clogica_Control();
    
    

public CArray_profesor getObjarrayP() {
	return objarrayP;
}
public void setObjarrayP(CArray_profesor objarrayP) {
	this.objarrayP = objarrayP;
}

public CProfesor getProfesor() {
	return profesor;
}
public void setProfesor(CProfesor profesor) {
	this.profesor = profesor;
}
public int getPosicion() {
	return posicion;
}
public void setPosicion(int posicion) {
	this.posicion = posicion;
}

public Clogica_Control getObjControl() {
	return objControl;
}
public void setObjControl(Clogica_Control objControl) {
	this.objControl = objControl;
}
public void run(){
	
	
	  while (flag==true)  
      {               
		  objControl.setObjarray(objarrayP);
     	
		  objControl.create(posicion, profesor);
     	
      flag=false;
      }   
      
	
}


}
