package main;

public class Recurso {
	private int[] array;

	public Recurso() {

	}

	public Recurso(int Tam) {
		super();
		this.array = new int[Tam];
	}

	public Recurso(int[] array) {
		super();
		this.array = array;
	}

	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	public void insertar(int pos, int val) { // System.out.println("desde Recurso: insertar");
		array[pos] = val;

	}

	public int getVal(int pos) {
		return array[pos];

	}

}
