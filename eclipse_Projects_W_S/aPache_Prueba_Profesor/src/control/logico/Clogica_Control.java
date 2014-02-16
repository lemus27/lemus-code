package control.logico;


import modelo.CArray_profesor;
import modelo.CProfesor;

 


public class Clogica_Control{
	
	
	public CArray_profesor objarray=new CArray_profesor();
	 public CProfesor profesor= new CProfesor();
	 public boolean flag =false;
	 int posicion;
	 
	 


	public void setObjarray(CArray_profesor objarray) {
		this.objarray = objarray;
	}
	public CArray_profesor getObjarray() {
		return objarray;
	}



public void create(int posicion,CProfesor profesor){//metodo insertar con parametros

	
objarray.insert_At_Position(posicion, profesor);
	
}



//public void update( int clave,CProfesor profesor,int position){//metodo modificar datos
	
	    //find(clave);//se llama al metodo buscar
	
	    //if(flag==true){
		//posicion=objarray.getPosicion();
	    //objarray.update_At_Position(posicion, profesor);
	    
	//}
	
//}   
//public void delete( int clave){//metodo modificar datos
	    	
	   //find(clave);//se llama al metodo buscar
	    	
	  // if(flag==true){
	  // posicion=objarray.getPosicion();
	  // objarray.delete_At_Position(posicion);
	    	    
	    	//}
	
//}
//public void find(int clave){//metodo buscar se debe crear en segunda porque
    //los demas metodos lo usan

//objarray.find(clave);//buscara partiendo de la clave del maestro, que le pasa
 //de la vista
//if(objarray.getFlag()==true){
//posicion=objarray.getPosicion(); 

//flag=true;
//objarray.setFlag(false);
//}else{

//flag=false;
//}

//}
//public CHilo_Create getMetodorun() {
	//return metodorun;
//}

//public void setMetodorun(CHilo_Create metodorun) {
	//this.metodorun = metodorun;
//}






public boolean getFlag(){
		
		return this.flag;
		}
public void setFlag(boolean flag){ 
		this.flag=flag;
		}

public int getPosicion(){
		
		return this.posicion;
		}
	public void setPosicion(int posicion){ 
		this.posicion=posicion;
		}


	
}
