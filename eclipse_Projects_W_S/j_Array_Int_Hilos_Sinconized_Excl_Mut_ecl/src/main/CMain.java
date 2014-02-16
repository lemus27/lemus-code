package main;

public class CMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int Tam = 10;
		Hilo_Insert h = new Hilo_Insert();
		Hilo_Print h2 = new Hilo_Print();
		Recurso array = new Recurso(Tam);
		Control obCtrl = new Control();

		h.setName("hilo1");
		h2.setName("hilo2");
		h.setTam(Tam);// cada hilo deb saber el tamaño del arreglo, y desde aqui
						// se le va apasando a cada uno de los demas clases
		h2.setTam(Tam);
		h.setArray_int(array);// cada hilo debe sar cual es el arreglod e datos
								// cone l que se va a trabajar de amenra
								// compartida, y desde aqui se le va apasando a
								// cada uno de los demas clases
		h2.setArray_int(array);
		h2.setObCtrl(obCtrl);// cada hilo debe saber que objet estan
								// sincrinizando, y desde aqui se le va apasando
								// a cada uno de los demas clases
		h.setObCtrl(obCtrl);
		new Thread(h2).start();
		new Thread(h).start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			// aquítratamos la excepción como queramos, haciendo nada, sacando
			// por pantalla el error, ....
		}
		h.setTerminar(true);
		h2.setTerminar(true);

	}

}
