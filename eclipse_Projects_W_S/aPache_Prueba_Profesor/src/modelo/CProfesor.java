package modelo;



public class CProfesor {
 public String nombre;
 public  String correo;
 public  String domicilio;
 public int  telefono;
 public int clave;


public void setNombre(String nombre){
    this.nombre=nombre;
}


public void setClave(int clave){
    this.clave=clave;
}


public void setDomicilio(String domicilio){
    this.domicilio=domicilio;
}

public void setCorreo(String correo){
    this.correo=correo;
}
public String getNombre(){
	return this.nombre;
	
}
public String getCorreo(){
	return this.correo;
}
public int getClave(){
	return this.clave;
}
public String getDomicilio(){
	return this.domicilio;
}
public void setTelefono(int telefono){
    this.telefono=telefono;
}
public int getTelefono(){
	return this.telefono;
}

}
