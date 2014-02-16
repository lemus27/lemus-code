/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logica.control;

import modelo.CArray_Alumno;

/**
 *
 * @author mike
 */
public class CFileManager {


    private CArray_Alumno objArr;
    private String name_File;

	public CFileManager() {
	}

        public CFileManager(CArray_Alumno objArr,String name_File) {
            this.objArr=objArr;
            this.name_File=name_File;
	}
	public CArray_Alumno getObjArr() {
		return objArr;
	}

	public void setObjArr(CArray_Alumno val) {
		this.objArr = val;
        }

    /**
     * @return the name_File
     */
 public String getName_File() {
        return name_File;
    }

    /**
     * @param name_File the name_File to set
     */
    public void setName_File(String name_File) {
        this.name_File = name_File;
    }
 
   


}
