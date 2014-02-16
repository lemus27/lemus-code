package logica.control;

import modelo.CAlumno;
import modelo.CArray_Alumno;

public class CLogica_Control {

	private CArray_Alumno objArr;
         private CFileManager objFM;


	public CLogica_Control() {
	}
 /**
     * @return the objFM
     */
    public CFileManager getObjFM() {
        return objFM;
    }

    /**
     * @param objFM the objFM to set
     */
    public void setObjFM(CFileManager objFM) {
        this.objFM = objFM;
    }
	public CArray_Alumno getObjArr() {
		return objArr;
	}

	public void setObjArr(CArray_Alumno val) {
		this.objArr = val;
	}

	public boolean create(int posicion, CAlumno valor) {
		  
		objArr.insert_At_Position(posicion, valor);
		return true;
	}
	public  CAlumno read(int posicion){
		return objArr.read_At_Position(posicion);
	}
	public boolean update(int posicion, CAlumno valor) {
		objArr.insert_At_Position(posicion, valor);
		return true;
	}

	public boolean delete(int posicion) {
		objArr.delete_At_Position(posicion);
		return true;
	}

           public  boolean  store_File( )
    {

        return true;
    }
     public  boolean  load_File()
    {
         return true;
    }

	public int find(Integer no_Control) {

		int tam = this.objArr.getTam();
		for (int posicion = 0; posicion < tam; posicion++)
                {
			if (this.objArr.read_At_Position(posicion).getNo_Control().equals(no_Control) )
                        {
				return posicion;
			}
		}
		return -1;
	}

   
	
	

}
