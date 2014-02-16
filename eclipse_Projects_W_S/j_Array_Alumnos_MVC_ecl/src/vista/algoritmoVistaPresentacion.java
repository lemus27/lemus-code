public class CVista_Presentacion {	
tam=2;//objeto global
objLC;//objeto global
objAlmm;//objeto global
objAray;//objeto global
	
	public CVista_Presentacion(Integer tam, CLogica_Control objLC,
			CAlumno objAm, CArray_Alumno objAray) {}
	public CVista_Presentacion() {}
	public  void entrada_Datos(){
		1.-crear objeto de alumno
		2.pedir cada  uno de los  datos  al usuario
		3.-almacenar cada 1 de los datos en el objeto de alumno
		4(a).-repetir lo siguiente
           4.1.- llamar a la funcion local encargada de crear Datso en earreglo	
			//this.create_Datos( posicion, objAm);
         4(b).-hasta que se alcanze el numero maximo de alumnos 
	}
public  void create_Datos(int posicion, CAlumno valor){
	1.-llamar a la  funcion que  en el objeto control que se encarga de  crear datos en el arreglo
		//objLC.create( posicion,  valor)
	}
public  void salida_Datos_Datos(){	}
public  void read_Datos(int posicion){}
public  void update_Datos(int posicion, CAlumno valor){}
public  void delete_Datos(int posicion){}
public  void find_Datos_by_No_control(Integer no_Control){}
public  int  print_All_Alumnos(){}
public  void iniciar(){	}	
}