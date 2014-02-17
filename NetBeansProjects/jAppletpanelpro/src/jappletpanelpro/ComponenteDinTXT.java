package jappletpanelpro;
import javax.swing.*;
import java.awt.*;

/****************************************************************************
 * Clase que encapsula el componente JTextField
 * Con el m�todo actualizarValor() almacena en el valor lo que el usuario
 * ha introducido en el componente gr�fico. OJO: si no se introduce nada, devuelve null.
 ***************************************************************************/
public class ComponenteDinTXT extends ComponenteDin {
   private int numColumnas = 0;            // Tama�o en columnas
   private boolean alineamientoIzq;        // Alineamiento a la izquierda
   private JLabel etiqueta;                // Etiqueta asociada al componente

   ////////////////////////// CONSTRUCTORES QUE MANEJAN TAMA�O EN PIXELES \\\\\\\\\\\\\\
   /***************************************************************************
    * valor: valor por defecto
    * titulo: cadena de titulo (etiqueta)
    * tamanio: dimensiones del componente. Si es null, se adapta al ancho del valor
    * alineamientoIzq: si es true se alinea a la izquierda. False: a la derecha
    * habilitado: valor de setEnabled
    **************************************************************************/
   public ComponenteDinTXT( Object valor, String titulo, Dimension tamanio,
			 boolean alineamientoIzq, boolean habilitado ) {
      super( valor, titulo, tamanio, habilitado );
      this.alineamientoIzq = alineamientoIzq;
      configurarComponente();
   }
   public ComponenteDinTXT( Object valor, String titulo, Dimension tamanio, boolean alineamientoIzq ) {
      this(valor, titulo, tamanio, alineamientoIzq, true );
   }
   public ComponenteDinTXT( Object valor, String titulo, Dimension tamanio ) {
      this(valor, titulo, tamanio, true, true );
   }
   public ComponenteDinTXT( Object valor, String titulo ) {
      this(valor, titulo, null, true, true );
   }
   ////////////////////////// CONSTRUCTORES QUE MANEJAN TAMA�O EN NUMERO DE COLUMNAS \\\\
   /***************************************************************************
    * valor: valor por defecto
    * titulo: cadena de titulo
    * numColumnas: tama�o medido en n�mero de columnas (caracteres). Si es 0, se adapta al valor
    * alineamientoIzq: si es true se alinea a la izquierda. False: a la derecha
    * habilitado: valor de setEnabled
    **************************************************************************/
   public ComponenteDinTXT( Object valor, String titulo, int numColumnas,
			 boolean alineamientoIzq, boolean habilitado ) {
      super( valor, titulo, null, habilitado );
      this.numColumnas = numColumnas;
      this.alineamientoIzq = alineamientoIzq;
      configurarComponente();
   }
   public ComponenteDinTXT( Object valor, String titulo, int numColumnas, boolean alineamientoIzq ) {
      this( valor, titulo, numColumnas, alineamientoIzq, true );
   }
   public ComponenteDinTXT( Object valor, String titulo, int numColumnas ) {
      this( valor, titulo, numColumnas, true, true );
   }

   /***************************************************************************
    * METODO DE IMPLEMENTACION OBLIGADA. Crea el componente y lo retorna
    * El texto del componente ser� el atributo 'valor'.
    * Si hay excepci�n retorna un JTextField vacio.
    **************************************************************************/
   protected JComponent crearComponente() {
      try {
	 return new JTextField(getValor().toString());
      }
      catch (Exception e) {
	 e.printStackTrace();
	 return new JTextField();
      }
   }

   /***************************************************************************
    * METODO DE IMPLEMENTACION OBLIGADA. Configura el componente y lo retorna
    **************************************************************************/
   protected JComponent configurarComponente() {
      try {
	 //// Cada JTextField se acompa�a de una etiqueta
	 etiqueta = new JLabel(getTitulo(), SwingConstants.LEFT);

	 getComponenteGrafico().setName(getTitulo()); // Nombre invisible

	 //// Alineamiento del texto
	 ((JTextField)getComponenteGrafico()).setHorizontalAlignment( (alineamientoIzq ? JTextField.LEFT : JTextField.RIGHT));

	 //// Columnas (si en el constructor se ha especificado el n� de columnas)
	 if (numColumnas>0)
	    ((JTextField)getComponenteGrafico()).setColumns( numColumnas );

	 //// Tama�o de la etiqueta
	 //// Si el tama�o del componente esta definido, le pongo a la etiqueta la altura del componente
	 if (getTamanio() != null) {
	    etiqueta.setMaximumSize(new Dimension(ANCHURA_ETIQUETA,this.getTamanio().height));
	    etiqueta.setPreferredSize(new Dimension(ANCHURA_ETIQUETA,this.getTamanio().height));
	 }
	 ///// Si el tama�o no est� definido, la altura de la etiqueta es el valor por defecto
	 else {
	    etiqueta.setMaximumSize(new Dimension(ANCHURA_ETIQUETA,ALTURA_COMPONENTE));
	    etiqueta.setPreferredSize(new Dimension(ANCHURA_ETIQUETA,ALTURA_COMPONENTE));
	 }
      }
      catch (Exception e) {
	 e.printStackTrace();
      }
      finally {
	 return getComponenteGrafico();
      }
   }

   /***************************************************************************
    * METODO DE IMPLEMENTACION OBLIGADA. A�ade etiqueta y componente al subpanel (argumento)
    **************************************************************************/
   protected void aniadirAlSubpanel( JPanel subpanel ) {
      subpanel.add( etiqueta );
      subpanel.add( getComponenteGrafico() );
   }

   /***************************************************************************
    * METODO DE IMPLEMENTACION OBLIGADA.
    * Pone en el atributo 'valor' el texto del componente gr�fico
    * Si no hay texto, el valor devuelto es null
    **************************************************************************/
   public void actualizarValor() {
      if ( ((JTextField)getComponenteGrafico()).getText().length() > 0 )
	 setValor( ((JTextField)getComponenteGrafico()).getText() );
      else
	 setValor( null );
   }

}
