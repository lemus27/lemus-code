package jappletpanelpro;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/****************************************************************************
 * Clase que encapsula el componente JTextField.
 * Con el m�todo actualizarValor() almacena en el valor lo que el usuario
 * ha introducido en el componente gr�fico. OJO: si no se introduce nada, devuelve null.
 ***************************************************************************/
public class ComponenteDinCBB extends ComponenteDin {
   private Vector rango;
   private JLabel etiqueta;                // Etiqueta asociada al componente

   ////////////////////////// CONSTRUCTORES QUE MANEJAN TAMA�O EN PIXELES \\\\\\\\\\\\\\
   /***************************************************************************
    * valor: valor por defecto
    * rangoValores: vector de Strings con los items del combobox
    * titulo: cadena de titulo (etiqueta)
    * tamanio: dimensiones del componente. Si es null, se adapta al ancho del valor
    * habilitado: valor de setEnabled
    **************************************************************************/
   public ComponenteDinCBB( Object valor, Vector rangoValores, String titulo, Dimension tamanio,
			 boolean habilitado ) {
      super( valor, titulo, tamanio, habilitado );
      rango = rangoValores;
      configurarComponente();
   }
   public ComponenteDinCBB( Object valor, Vector rangoValores, String titulo, Dimension tamanio ) {
      this(valor, rangoValores, titulo, tamanio, true );
   }
   public ComponenteDinCBB( Object valor, Vector rangoValores, String titulo ) {
      this(valor, rangoValores, titulo, null, true );
   }

   /***************************************************************************
    * METODO DE IMPLEMENTACION OBLIGADA. Crea el componente y lo retorna
    * Los items del combobox son los Strings del vector 'rango'.
    * Si hay excepci�n retorna un componente vacio.
    **************************************************************************/
   protected JComponent crearComponente() {
      try {
	 return new JComboBox();
      }
      catch (Exception e) {
	 e.printStackTrace();
	 return new JComboBox();
      }
   }

   /***************************************************************************
    * METODO DE IMPLEMENTACION OBLIGADA. Configura el componente y lo retorna
    **************************************************************************/
   protected JComponent configurarComponente() {
      try {
	 //// Cada JComboBox se acompa�a de una etiqueta
	 etiqueta = new JLabel(getTitulo(), SwingConstants.LEFT);

	 getComponenteGrafico().setName(getTitulo()); // Nombre invisible

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

	 JComboBox cb = (JComboBox)getComponenteGrafico();  //// Obtengo el combo

	 cb.setEditable( false ); // No es editable

	 //// Si no hay rango de valores, no hago nada m�s
	 if ( rango == null || rango.size() == 0 )
	    return getComponenteGrafico();

	 //// Creo un modelo de datos con el vector 'rango' y a�ado modelo al combo
	 DefaultComboBoxModel modelo = new DefaultComboBoxModel( rango );
	 cb.setModel( modelo );

	 //// Si hay valor por defecto, lo selecciona. Si
	 //// Si no hay selecci�n (setSelectedItem() no encuentra el valor), selecciono el primero
	 if ( getValor() != null )
	    cb.setSelectedItem( getValor().toString());

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
    * Pone en el atributo 'valor' lo que el usuario introducido en componente gr�fico
    * Si no hay selecci�n el valor devuelto es null
    **************************************************************************/
   public void actualizarValor() {
      if ( ((JComboBox)getComponenteGrafico()).getSelectedIndex() >= 0 ) {
	 String itemSeleccionado = (String) ((JComboBox)getComponenteGrafico()).getSelectedItem();
	 setValor( itemSeleccionado );
      }
      else
	 setValor( null );

   }
}
