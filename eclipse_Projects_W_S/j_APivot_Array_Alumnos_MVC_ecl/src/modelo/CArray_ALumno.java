package modelo;


public class CArray_ALumno {
	
	private CAlumno array[]=new CAlumno[10];
    private int i=0;
    private boolean flag=false;
    private int posicion;
	
    public CAlumno[] getArray() {
 
		return this.array;
	}
	public void setArray(CAlumno[] array) {
		this.array = array;
	}
	
public void insertar(int position, CAlumno alumno){
	
	array[position]=alumno;

		
}

public void buscar(int id){
try{
while(i<10){
	
	if(array[i].getNocontrol()==id){flag=true; posicion=i; break;}
	
i++;
}
i=0;

}
catch(Exception exception){}
}

public void borrar(int position){

	array[position].setCarrera("Default");
	array[position].setNocontrol(000);
	array[position].setNombre("Defaut");
	array[position].setGeneracion(000);
	array[position].setSemestre(000);
}

public void modificar(int position, CAlumno alumno){
	array[position].setNocontrol(alumno.nocontrol);
	array[position].setNombre(alumno.nombre);
	array[position].setSemestre(alumno.semestre);
	array[position].setGeneracion(alumno.generacion);
	array[position].setCarrera(alumno.carrera);
	
}


		

public boolean getFlag(){return this.flag;}		
public void setFlag(boolean flag){this.flag=flag;}
	
public int getPosicion(){return this.posicion;}
public void setPosicion(int posicion){ this.posicion=posicion;}
}


	

