package modelo;

public class CArray_profesor {
	
	

    CProfesor Profesor_array[]=new CProfesor[10];//declaracion del arreglo profesor
    private int casillas=0;//se encarga de medir el array
    private int tam;
    private boolean flag=false;

    

    public void initialicer(){//metodos sin uso
    	
    }
    //public void create(){//metodo sin uso
    	
    //}

     public  void insert_At_Position(int posicion, CProfesor valor){//metodo insertar al array
	
     Profesor_array[posicion]=valor;

     }//termina metodo insertar
     
     
     

     public void find(int clave){//metodo buscar objarray
	
	try{//si ocurre un error, se encarga de cacharlo
	
	while (casillas<Profesor_array.length){//compara la variable tam con el tamaño del arreglo para poder continuar 
		
		if(Profesor_array[casillas].getClave()==clave){
			flag=true;
			
			tam=(casillas);
			 break;
		}
		casillas++;
	    }
	    casillas=0;//regresa el puntero a la posicion cero para una nueva busqueda
	
}
		
catch(Exception exception){}
	
}//termina metodo buscar

     public void delete_At_Position(int posicion){//metodo borrar objarray

    		Profesor_array[posicion].setNombre("Defaul");
    		Profesor_array[posicion].setClave(000);
    		Profesor_array[posicion].setDomicilio("Defaul");
    		Profesor_array[posicion].setTelefono(000);
    		Profesor_array[posicion].setCorreo("Defaul");

    	    }//termina el metodo borrar

   public void update_At_Position(int posicion, CProfesor profesor){//metodo modificar objarray
	

	        Profesor_array[posicion].setNombre(profesor.nombre);
	        Profesor_array[posicion].setClave(profesor.clave);
        	Profesor_array[posicion].setDomicilio(profesor.domicilio);
	        Profesor_array[posicion].setTelefono(profesor.telefono);
	        Profesor_array[posicion].setCorreo(profesor.correo);
   
   }//termina el metodo  

  //metodos set y gets
  public CProfesor[] getArray() {
	   return this.Profesor_array;
}
  public void setArray(CProfesor[] array) {
		this.Profesor_array = array;
 }

public boolean getFlag(){//devuelve el valor de la bandera
	return this.flag;
}
public void setFlag(boolean flag){//asigna el valor de la bandera
	this.flag=flag;
}

public int getPosicion(){//usado para saber la posicion de los datos en el array
	                     //y saber cuando se llena
	return this.tam;
}
public void setPosicion(int posicion){
	this.tam=posicion;
}	


}
