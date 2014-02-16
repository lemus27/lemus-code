package main;

public class Sinc {

	private Recurso array_int;
	private boolean disponible = false;

	public Recurso getArray_int() {
		return array_int;
	}

	public void setArray_int(Recurso array_int) {
		this.array_int = array_int;
	}

	public Sinc() {
		array_int = new Recurso(2);
	}

	public Sinc(int Tam) {

		array_int = new Recurso(Tam);
	}

	public Sinc(Recurso obj) {
		super();
		this.array_int = obj;
	}

	public synchronized void insertar_At_Position(int pos, int val) {
		/*
		 * IllegalMonitorException is thrown to indicate that a thread has
		 * attempted to wait on an object's monitor or to notify other threads
		 * waiting on an object's monitor without owning the specified monitor.
		 * 
		 * This line again and again says, IllegalMonitorException comes when
		 * one of the 2 situation occurs....
		 * 
		 * 1> wait on an object's monitor without owning the specified monitor.
		 * 
		 * 2> notify other threads waiting on an object's monitor without owning
		 * the specified monitor.
		 * 
		 * Some might have got their answers... who all doesn't, then please
		 * check 2 statements....
		 * 
		 * synchronized (object)
		 * 
		 * object.wait()
		 * 
		 * If both object are same... then no illegalMonitorException can come.
		 * 
		 * Now again read the IllegalMonitorException definition and you wont
		 * forget it again...
		 */
		// System.out.println("desde sync: insertar");
		/*
		 * while(disponible==true){ try{ wait(); }catch(InterruptedException
		 * ex){} }
		 */

		array_int.insertar(pos, val);
		// disponible=true;
		// this.notifyAll();

	}

	public synchronized int get_At_Position(int pos) {
		/*
		 * IllegalMonitorException is thrown to indicate that a thread has
		 * attempted to wait on an object's monitor or to notify other threads
		 * waiting on an object's monitor without owning the specified monitor.
		 * 
		 * This line again and again says, IllegalMonitorException comes when
		 * one of the 2 situation occurs....
		 * 
		 * 1> wait on an object's monitor without owning the specified monitor.
		 * 
		 * 2> notify other threads waiting on an object's monitor without owning
		 * the specified monitor.
		 * 
		 * Some might have got their answers... who all doesn't, then please
		 * check 2 statements....
		 * 
		 * synchronized (object)
		 * 
		 * object.wait()
		 * 
		 * If both object are same... then no illegalMonitorException can come.
		 * 
		 * Now again read the IllegalMonitorException definition and you wont
		 * forget it again...
		 */
		int var = -1;
		// System.out.println("desde sync: get_At_Position");
		/*
		 * while(disponible==false){ try{ wait(); }catch(InterruptedException
		 * ex){} }
		 */

		var = array_int.getVal(pos);
		// disponible=false;
		// this.notifyAll();
		return var;
	}

	public synchronized int sumar() {
		int suma = 0;
		for (int i = 0; i <= 2; i++) {
			suma = suma + array_int.getVal(i);
		}
		return suma;
	}

	public synchronized int[] getRecursoArray()

	{
		return array_int.getArray();
	}

}
