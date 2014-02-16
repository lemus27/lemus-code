package logica.control;

import modelo.CAlumno;
import modelo.CArray_Alumno;


public class CArregloSincronizado  
{  
   private CArray_Alumno array = new  CArray_Alumno();
   private Integer tam=2;
   public CArregloSincronizado(Integer tam) {
	super();
	this.tam = tam;
	this.array.setTam(this.tam);
	array = new  CArray_Alumno();
}
   public CArregloSincronizado() {
		super();
		this.array.setTam(this.tam);
		array = new  CArray_Alumno();
	}
	public Integer getTam() {
		return tam;
	}
	public void setTam(Integer tam) {
		this.tam = tam;
	}
    /**
     * @return the array
     */
    public synchronized CArray_Alumno getArray() {
        return array;
    }
    /**
     * @param array the array to set
     */
    public synchronized void setArray(CArray_Alumno array) {
        this.array = array;
    }
  public synchronized  void  initialicer(){
	  this.getArray().initialicer();
  }
  public synchronized  boolean  insert_At_Position(int posicion, CAlumno valor)
  {
	  getArray().insert_At_Position(posicion, valor);
	 
	   return true;
  }
  public synchronized  CAlumno  read_At_Position(int posicion){
	  
	   return this.getArray().getArray()[posicion];
	    
  }
  public synchronized  boolean  update_At_Position(int posicion, CAlumno valor){
	  getArray().update_At_Position(posicion, valor);
	   return true;   
  }
  public synchronized  boolean  delete_At_Position(int posicion){
		getArray().delete_At_Position(posicion);
	   return true;
  }

}   
