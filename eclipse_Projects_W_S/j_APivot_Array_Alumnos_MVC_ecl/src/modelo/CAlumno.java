package modelo;

public class CAlumno {

	int nocontrol=0;
	String nombre="";
	String carrera="";
	int semestre=0;
	int generacion=0;




	public int getNocontrol(){
	return this.nocontrol;

	}

	public void setNocontrol(int nocontrol){
		
		this.nocontrol=nocontrol;
	}	

	public String getNombre(){
		return this.nombre;
	}

	public void setNombre(String nombre){
		
		this.nombre=nombre;
		
	}

	public String getCarrera(){
		
		return this.carrera;
		
	}
		
	public void setCarrera(String carrera){
		this.carrera=carrera;
		
	}


	public int getSemestre(){
		return this.semestre;
	}

	public void setSemestre(int semestre){
		
		this.semestre=semestre;
		
	}

	public int getGeneracion(){

		return this.generacion;
		
	}

	public void setGeneracion(int generacion){
		
		this.generacion=generacion;
	}
}
