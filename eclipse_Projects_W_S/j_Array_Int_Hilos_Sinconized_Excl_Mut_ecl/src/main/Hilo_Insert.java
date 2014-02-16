package main;

public class Hilo_Insert  implements Runnable  
{  
	private String Name="";
	 private int Tam=10;
	private boolean terminar = false;  
	private Control obCtrl= new Control();
private  Recurso array_int;
public Control getObCtrl() {
	return obCtrl;
}
public void setObCtrl(Control obCtrl) {
	this.obCtrl = obCtrl;
}
	
	public Recurso getArray_int() {
		return array_int;
	}
	public void setArray_int(Recurso array_int) {
		this.array_int = array_int;
	}
	public String getName() {
		return Name;
	}
	public int getTam() {
		return Tam;
	}
	public void setTam(int tam) {
		Tam = tam;
	}
	public void setName(String name) {
		Name = name;
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
   	// System.out.println(Name+": run: antes del while, insertando");
         while (!terminar)  
         {               // Alguna tarea a realizar 
        	 obCtrl.setName(Name);
        	 obCtrl.setTam(Tam);
        	 obCtrl.setArray_int(array_int);
        	// System.out.println(Name+": insertando");
    		 for (int i=0; i <Tam;i++)
 			{
    			 System.out.println(Name+": insertando  en pos "+i+";valor"+i*i);
				obCtrl.insertar_At_Position(i, i * i);
 			}
        	
        	/* try{
        		 Thread.sleep(100);
             }catch (InterruptedException e) { }
         */
         }    
         }  
}  
