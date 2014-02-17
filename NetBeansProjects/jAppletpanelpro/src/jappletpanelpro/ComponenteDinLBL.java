package jappletpanelpro;
import javax.swing.*;
import java.awt.*;

/****************************************************************************
 * Clase que encapsula el componente de tipo JLabel
 ***************************************************************************/
public class ComponenteDinLBL extends ComponenteDin {
   private boolean alineamientoIzq;        // Alineamiento a la izquierda

   /***************************************************************************
    * valor: valor por defecto
    * titulo: cadena de titulo (etiqueta)
    * tamanio: dimensiones del componente. Si es null, se adapta al ancho del valor
    * alineamientoIzq: si es true se alinea a la izquierda. False: a la derecha
    * habilitado: valor de setEnabled
    **************************************************************************/
   public ComponenteDinLBL( Object valor, Dimension tamanio,
			 boolean alineamientoIzq, boolean habilitado ) {
      super( valor, null, tamanio, habilitado );
      this.alineamientoIzq = alineamientoIzq;
      configurarComponente();
   }
   public ComponenteDinLBL( Object valor, Dimension tamanio, boolean alineamientoIzq ) {
      this( valor, tamanio, alineamientoIzq, true );
   }
   public ComponenteDinLBL( Object valor, Dimension tamanio ) {
      this( valor, tamanio, true, true );
   }
   public ComponenteDinLBL( Object valor ) {
      this( valor, null, true, true );
   }

   /***************************************************************************
    * METODO DE IMPLEMENTACION OBLIGADA. Crea el componente y lo retorna
    * El texto del componente ser� el atributo 'valor'.
    * Si hay excepci�n retorna un JLabel vacio.
    **************************************************************************/
   protected JComponent crearComponente() {
      try {
	 return new JLabel( getValor().toString());
      }
      catch (Exception e) {
	 e.printStackTrace();
	 return new JLabel();
      }
   }

   /***************************************************************************
    * METODO DE IMPLEMENTACION OBLIGADA. Configura el componente y lo retorna
    **************************************************************************/
   protected JComponent configurarComponente() {
      ((JLabel)getComponenteGrafico()).setHorizontalAlignment( (alineamientoIzq ? SwingConstants.LEFT : SwingConstants.RIGHT));
      return getComponenteGrafico();
   }

   /***************************************************************************
    * METODO DE IMPLEMENTACION OBLIGADA. A�ade componente al subpanel (argumento)
    **************************************************************************/
   protected void aniadirAlSubpanel( JPanel subpanel ) {
      subpanel.add( getComponenteGrafico() );
   }

   /***************************************************************************
    * METODO DE IMPLEMENTACION OBLIGADA.
    * Pone en el atributo 'valor' lo que el usuario ha dejado en componente gr�fico
    **************************************************************************/
   public void actualizarValor() {
      setValor( ((JLabel)getComponenteGrafico()).getText() );
   }

}
