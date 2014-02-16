package modelo;


	
	public class CAlumno extends CPersona
{
    private int semestre;

public  CAlumno()
{
    super();
}

public CAlumno(int semestre)
{
        super();
        this.semestre = semestre;
    }


//---set y get de semestre---
public void setSemestre(int valor)
{
    semestre=valor;
}

public int getSemestre()
{
    return semestre;
}

}//llave de la clase

