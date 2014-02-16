package vista;

import logica.control.CLogica_Control;
import modelo.CAlumno;
import modelo.CArray_Alumno;


public class CVista_Presentacion {
	
	
	
	private Integer tam=2;
	private CLogica_Control objLC;
	private CAlumno objAm;
	private CArray_Alumno objAray;
	
	public CVista_Presentacion(Integer tam, CLogica_Control objLC,
			CAlumno objAm, CArray_Alumno objAray) {
		super();
		this.tam = tam;
		this.objLC = objLC;
		this.objAm = objAm;
		this.objAray = objAray;
	}
	public CVista_Presentacion() {
		super();
		this.tam = 2;
		this.objLC =new CLogica_Control() ;
		this.objAm= new CAlumno();
		this.objAray = new CArray_Alumno(2);
	}
	public  void entrada_Datos(){
		this.objAm= new CAlumno();
		tam = this.objAray.getTam();
		Integer no_Control=1020;
		 String nombre="mike";
		Integer edad=20;
		 String carrera="isc";
		 Integer semestre=2;
		System.out.println("introduzaca el numerod e control");
		  no_Control= CReadConsole.GetConsoleInteger();
		 System.out.println("introduzaca el nombre");
		  nombre=CReadConsole.GetConsoleString();
		 System.out.println("introduzaca la edad");
		 edad= CReadConsole.GetConsoleInteger();
		System.out.println("introduzaca la carrera");
		  carrera=CReadConsole.GetConsoleString();
		 System.out.println("introduzaca el numero de smestre");
		  semestre= CReadConsole.GetConsoleInteger();
		for (int posicion = 0; posicion < tam; posicion++) {

			objAm.setNo_Control(no_Control);
			objAm.setNombre(nombre);
			objAm.setEdad(edad);
			objAm.setCarrera(carrera);
			objAm.setSemestre(semestre);
			
			this.create_Datos( posicion, objAm);
		
			
		}
		
	}
	public  void salida_Datos_Datos(){
		
	}
public  void create_Datos(int posicion, CAlumno valor){
	System.out.println("*********************************************************************");
	System.out.println("create_Datos");
	System.out.println("*********************************************************************");
	if (objLC.create( posicion,  valor))
	{
		read_Datos(posicion);
	}
	
	}
public  void read_Datos(int posicion){
	objAm=objLC.read(posicion);
	if (objAm!=null){
		
		System.out.println("operacion exitosa: ["+ posicion+"]"+objAm.toString() );
	}
}
public  void update_Datos(int posicion, CAlumno valor){
	System.out.println("*********************************************************************");
	System.out.println("update_Datos");
	System.out.println("*********************************************************************");
	if (objLC.update(posicion, valor))
	{
		read_Datos(posicion);
	}
}
public  void delete_Datos(int posicion){
	System.out.println("*********************************************************************");
	System.out.println("delete_Datos");
	System.out.println("*********************************************************************");
	if (objLC.delete(posicion))
	{
		read_Datos(posicion);
	}
	
}
public  void find_Datos_by_No_control(Integer no_Control){
	System.out.println("*********************************************************************");
	System.out.println("find_Datos_by_No_control");
	System.out.println("*********************************************************************");
	int posicion=objLC.find(no_Control);
	if (posicion!=-1){//-1: indica  que no se encontro una posicion con los datos buscado

	
		read_Datos(posicion);
	}
	else
	{
		System.out.println(" opercion fallida");
	}
	
	}

public  int  print_All_Alumnos(){
	
	for (int posicion = 0; posicion < tam; posicion++) {
		objAm=objLC.read(posicion);
		System.out.println("["+ posicion+"]"+objAm.toString() );
	}
	return 0;	
}


public  void iniciar(){
	this.tam = 2;
	this.objLC =new CLogica_Control() ;
	this.objAm= new CAlumno();
	this.objAray = new CArray_Alumno(2);
	this.objLC.setObjArr(objAray);
	
	Integer no_Control=1020;
	 String nombre="mike";
	Integer edad=20;
	 String carrera="isc";
	 Integer semestre=2;
	 
	System.out.println("*********************************************************************");
	System.out.println(" bienvenidos al sistema");
	System.out.println("*********************************************************************");
	System.out.println("++++++++++++++++los datos que debs  instroducir, para probar el sistema  son+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	System.out.println("no_Control="+no_Control);
	System.out.println("nombre="+nombre);
	System.out.println("edad="+edad);
	System.out.println("carrera="+carrera);
	System.out.println("semestre="+semestre);
	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	System.out.println("/////////////////Introduciendo Datos/////////////////////////////////////////////////////");
	this.entrada_Datos();
	System.out.println("/////////////////Imprimiendo todos los datos/////////////////////////////////////////////////////");	
	print_All_Alumnos();
	System.out.println("/////////////////leyendo datos en la posicion 1/////////////////////////////////////////////////////");
	this.read_Datos(1);
	System.out.println("/////////////////Buscando Datos por el numero de no_Control="+no_Control+"/////////////////////////////////////////////////////");
	this.find_Datos_by_No_control(1020);
	System.out.println("/////////////////Modificando los  datos en la posicion 1, cambiando el numero de contro a 22/////////////////////////////////////////////////////");
	this.objAm.setNo_Control(22);
	this.update_Datos(1, this.objAm);
	System.out.println("/////////////////eliminando los datos de la posicion 1/////////////////////////////////////////////////////");
	this.delete_Datos(1);
	System.out.println("/////////////////Imprimiendo todos los datos/////////////////////////////////////////////////////");
	print_All_Alumnos();
	//this.Salida_Datos();
	
}	




}
