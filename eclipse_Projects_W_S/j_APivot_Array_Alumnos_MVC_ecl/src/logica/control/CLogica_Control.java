package logica.control;

import modelo.CAlumno;
import modelo.CArray_ALumno;





public class CLogica_Control {
	
	public CArray_ALumno objarreglo=new CArray_ALumno();
	public boolean flag=false;
	public int posicion;



public void insertar(int position, CAlumno alumno){
	
	
	objarreglo.insertar(position,alumno);
	
}

public void borrar(int id){
	buscar(id);
	if(flag==true){
    posicion=objarreglo.getPosicion();
	objarreglo.borrar(posicion);
	}
		
	
}
public void buscar(int id ){
	
	objarreglo.buscar(id);
	
	if(objarreglo.getFlag()==true){posicion=objarreglo.getPosicion(); flag=true; objarreglo.setFlag(false);}
	else{
		flag=false;
	}
	
	
}
public void modificar(int id, CAlumno alumno){
	 
  buscar(id);
  if(flag==true){
	posicion=objarreglo.getPosicion();
	objarreglo.modificar(posicion,alumno);
  }
 
}


public void setObjarray(CArray_ALumno objarray){
this.objarreglo=objarray;

}
public CArray_ALumno getObjarray(){
	return this.objarreglo;
}

public boolean getFlag(){return this.flag;}		
public void setFlag(boolean flag){this.flag=flag;}
	
public int getPosicion(){return this.posicion;}
public void setPosicion(int posicion){ this.posicion=posicion;}

}
