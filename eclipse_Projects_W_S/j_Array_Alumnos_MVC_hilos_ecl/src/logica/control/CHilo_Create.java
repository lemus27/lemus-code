
package logica.control;

import modelo.CAlumno;

public class CHilo_Create implements Runnable  
{  
	private CLogica_Control Control=new CLogica_Control();

	private CAlumno objAlumno=new CAlumno();
	private int position=0;
    private boolean terminar = false; 
    public CHilo_Create() {
		super();
		
	}
    
    public CHilo_Create(CLogica_Control control, CAlumno malumno, int position,
			boolean terminar) {
		super();
		Control = control;
		objAlumno = malumno;
		this.position = position;
		this.terminar = terminar;
	}
	public CLogica_Control getControl() {
		return Control;
	}
	public void setControl(CLogica_Control control) {
		Control = control;
	}
	public CAlumno getObjAlumno() {
		return objAlumno;
	}
	public void setObjAlumno(CAlumno malumno) {
		objAlumno = malumno;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public boolean isTerminar() {
		return terminar;
	}
	
    public void setTerminar (boolean terminar)  
    {  
        this.terminar=terminar;  
    }  
    public void run ()  
    {  
         while (!terminar)  
         {  
            // Alguna tarea a realizar  
        	 Control.create(position, objAlumno);
         }  
    }  
}  