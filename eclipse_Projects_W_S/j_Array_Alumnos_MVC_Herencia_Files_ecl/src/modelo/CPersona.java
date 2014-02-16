/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author mike
 */
public class CPersona
{

    private  int no_control;
    private  String nombre;
    private  int edad;
    private  String carrera;

    public CPersona()
    {
     super();
    }

    public CPersona(int no_control, String nombre, int edad, String carrera)
    {
        super();
        this.no_control = no_control;
        this.nombre = nombre;
        this.edad = edad;
        this.carrera = carrera;
    }

    public void setNo_Control(int valor)
    {
        this.no_control=valor;
    }

    public int getNo_Control()
    {
        return no_control;
    }

    public void setNombre(String valor)
    {
        this.nombre=valor;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setEdad(int valor)
    {
        this.edad=valor;
    }

    public int getEdad()
    {
        return edad;
    }

    public void setCarrera(String valor)
    {
        this.carrera=valor;
    }

    public String getCarrera()
    {
        return carrera;
    }

}//llave de la clase