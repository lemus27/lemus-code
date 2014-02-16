package modelo;

public class CAlumno {
	
	private Integer no_Control=0;
	
	private String nombre="";
	private Integer edad=0;
	private String carrera="";
	private Integer Semestre=0;
	public CAlumno()
	{
		no_Control=-10;
		nombre="no tiene un valor correcto";
		edad=-100;
		carrera="no tiene un valor correcto";
		Semestre=-1000;
	}
	public Integer getNo_Control() {
		return no_Control;
	}
	public void setNo_Control(Integer no_Control) {
		this.no_Control = no_Control;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public Integer getSemestre() {
		return Semestre;
	}
	public void setSemestre(Integer semestre) {
		Semestre = semestre;
	}
	@Override
	public String toString() {
		return "CAlumno [no_Control=" + no_Control + ", nombre=" + nombre
				+ ", edad=" + edad + ", carrera=" + carrera + ", Semestre="
				+ Semestre + "]";
	}
	

}
