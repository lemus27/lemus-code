package main;

public class Control {

	private String Name = "";
	private int Tam = 10;
	private Sinc objSinc = new Sinc(Tam);
	private Recurso array_int;
	private boolean disponible = false;

	public Recurso getArray_int() {
		return array_int;
	}

	public void setArray_int(Recurso array_int) {
		this.array_int = array_int;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getTam() {
		return Tam;
	}

	public void setTam(int tam) {
		Tam = tam;
	}

	public Sinc getObjSinc() {
		return objSinc;
	}

	public void setObjSinc(Sinc objSinc) {
		this.objSinc = objSinc;
	}

	public void insertar_At_Position(int i, int val) {
		// System.out.println(Name+": idesde el wgile del run");

		synchronized (objSinc) {

			/*
			 * IllegalMonitorException is thrown to indicate that a thread has
			 * attempted to wait on an object's monitor or to notify other
			 * threads waiting on an object's monitor without owning the
			 * specified monitor.
			 * 
			 * This line again and again says, IllegalMonitorException comes
			 * when one of the 2 situation occurs....
			 * 
			 * 1> wait on an object's monitor without owning the specified
			 * monitor.
			 * 
			 * 2> notify other threads waiting on an object's monitor without
			 * owning the specified monitor.
			 * 
			 * Some might have got their answers... who all doesn't, then please
			 * check 2 statements....
			 * 
			 * synchronized (object)
			 * 
			 * object.wait()
			 * 
			 * If both object are same... then no illegalMonitorException can
			 * come.
			 * 
			 * Now again read the IllegalMonitorException definition and you
			 * wont forget it again...
			 */

			// System.out.println(Name+": en sicronized");

			while (disponible == true) {
				try {
					objSinc.wait();
				} catch (InterruptedException ex) {
				}
			}

			// System.out.println(Name+": insertando");
			objSinc.setArray_int(array_int);
			objSinc.insertar_At_Position(i, i * i);
			disponible = true;
			objSinc.notify();

		}
		/* System.out.println(Name+": fin del while"); */
	}

	public int get_At_Position(int pos) {
		synchronized (objSinc) {
			/*
			 * IllegalMonitorException is thrown to indicate that a thread has
			 * attempted to wait on an object's monitor or to notify other
			 * threads waiting on an object's monitor without owning the
			 * specified monitor.
			 * 
			 * This line again and again says, IllegalMonitorException comes
			 * when one of the 2 situation occurs....
			 * 
			 * 1> wait on an object's monitor without owning the specified
			 * monitor.
			 * 
			 * 2> notify other threads waiting on an object's monitor without
			 * owning the specified monitor.
			 * 
			 * Some might have got their answers... who all doesn't, then please
			 * check 2 statements....
			 * 
			 * synchronized (object)
			 * 
			 * object.wait()
			 * 
			 * If both object are same... then no illegalMonitorException can
			 * come.
			 * 
			 * Now again read the IllegalMonitorException definition and you
			 * wont forget it again...
			 */

			while (disponible == false) {
				try {
					objSinc.wait();
				} catch (InterruptedException ex) {
				}
			}
			objSinc.setArray_int(array_int);

			disponible = false;
			objSinc.notify();
			return objSinc.get_At_Position(pos);

		}
	}

}
