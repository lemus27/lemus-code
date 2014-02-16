package modelo;

public class CArray_Alumno {
	private Integer tam=2;
	CAlumno[] array;
	public Integer getTam() {
		return tam;
	}

	public void setTam(Integer tam) {
		this.tam = tam;
	}
	
	public CAlumno[] getArray() {
		return array;
	}

	public void setArray(CAlumno[] array) {
		this.array = array;
	}

   public CArray_Alumno(int tam){
	   this.tam=tam;
	   array= new  CAlumno[tam];
	   
   }
   public CArray_Alumno(){
	   
	   array= new  CAlumno[tam];
	   
   }
   public  void  initialicer(){
	   for( int i=0; i<=tam; i++)
       {
             this.array[i]=new   CAlumno() ;
       }

   }
   public  boolean  create(){
	   array= new  CAlumno[tam];
	   return true;
   }
   public  boolean  insert_At_Position(int posicion, CAlumno valor)
   {
	 
	   this.array[posicion]=valor;  
	   return true;
   }
   public  CAlumno  read_At_Position(int posicion){
	   return this.array[posicion];
	    
   }
   public  boolean  update_At_Position(int posicion, CAlumno valor){
	   this.insert_At_Position( posicion,  valor);
	   return true;   
   }
   public  boolean  delete_At_Position(int posicion){
	   this.array[posicion]=new CAlumno();  
	   return true;
   }
  

}
