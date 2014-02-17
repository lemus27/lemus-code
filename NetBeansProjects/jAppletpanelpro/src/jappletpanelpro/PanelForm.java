package jappletpanelpro;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.awt.event.*;

/*******************************************************************************
 * Panel que recibe un vector de componentes (elementos del tipo ComponenteDin)
 * Los organiza en la forma de un formulario.
 ******************************************************************************/
public class PanelForm extends JPanel {
   private Vector vec;
   private Dimension tamanio;                  // Tama�o del panel
   private String tituloBorde;                 // Titulo del borde
   private Color colorFondo;                   // Color de fondo
   private JButton btnAceptar = new JButton(); // Boton Aceptar
   private Dimension tamanioBoton  = new Dimension(100,22); // Tama�o del bot�n (por defecto)

   /****************************************************************************
    * Recibe un vector de componentes y mediante iniciar() se los a�ade a si mismo
    * con el formato de formulario.
    * tamanio: tama�o del panel. Si es null, iniciar() crea un panel con aviso y
    *          no hace nada
    * tituloBorde: el t�tulo del borde. Si es null o vacio, entonces iniciar() no
    *              pone borde.
    * colorFondo: color de fondo del panel
    ***************************************************************************/
   public PanelForm( Vector vec, Dimension tamanio, String tituloBorde, Color colorFondo ) {
      this.vec = vec;
      this.tamanio = tamanio;
      this.tituloBorde = tituloBorde;
      this.colorFondo = colorFondo;
   }

   /****************************************************************************
    * Inicia componentes
    * Este panel se divide en dos paneles: panel de edici�n (donde van los
    * componentes) y panel de botones
    ***************************************************************************/
   public void iniciar() {

      try {
	 ///// Panel donde situaremos a los componentes
	 JPanel pnlEdicion = new JPanel();
	 pnlEdicion.setBackground( colorFondo );

	 //// Panel para botones
	 JPanel pnlBotones = new JPanel();
	 pnlBotones.setBackground( colorFondo );
	 pnlBotones.setMaximumSize( new Dimension( (int) tamanio.getWidth(),tamanioBoton.height+10) );
	 pnlBotones.setPreferredSize( new Dimension( (int) tamanio.getWidth(),tamanioBoton.height+10) );
	 btnAceptar.setAlignmentY(Panel.CENTER_ALIGNMENT);   // Bot�n centrado
	 pnlBotones.add( btnAceptar );

	 if (tamanio == null) {
	    add( new JLabel("El tama�o del panel no es correcto"));
	    return;
	 }

	 //// Define caracteristicas del panel
	 setLayout( new BoxLayout( this, BoxLayout.PAGE_AXIS));
	 setBackground( colorFondo );
	 if ( tituloBorde != null ) {
	   pnlEdicion.setBorder(BorderFactory.createCompoundBorder(
	       BorderFactory.createTitledBorder(tituloBorde), BorderFactory.createEmptyBorder(2,2,2,2)));
	 }
	 setOpaque( true );
	 setVisible( true );

	 //// Recorrer el vector para crear subpaneles y a�adirlos al panel
	 JPanel subpanel;
	 boolean esPrimero = false;  // Para saber cual es el primer componente que puede recibir foco
	 for ( int i = 0; i < vec.size(); i++ ) {
	    ComponenteDin comp = (ComponenteDin) vec.get(i);// Extraigo el componente del vector
	    subpanel = comp.iniciar( tamanio, colorFondo);  // Crear subpanel contenedor de componente
	    pnlEdicion.add( subpanel );                     // A�ado subpanel al panel

	    //// Si todav�a no he encontrado al primero, lo examino
	    if ( !esPrimero ) {
	       //// Si es campo de texto, entonces es el primero que puede tener el foco
	       if ( comp.getComponenteGrafico() instanceof JTextField ) {
		  ((JTextField)comp.getComponenteGrafico()).selectAll();
		  esPrimero = true;
	       }
	    }
	 }  //////// Fin del recorrido del vector de componentes

	 //// A�ado al panel
	 add( pnlEdicion );
	 add( Box.createVerticalStrut(5) );
	 add( pnlBotones );
      }
      catch(Exception e) {
	 e.printStackTrace();
      }
   }

   /////////////////////// SOBRECARGA DE CONSTRUCTOR
   public PanelForm( Vector vec, Dimension tamanio, String tituloBorde ) {
      this(vec, tamanio, tituloBorde, Color.WHITE );
   }
   public PanelForm( Vector vec, Dimension tamanio ) {
      this(vec, tamanio, null, Color.WHITE );
   }

   /***************************************************************************
    * Ordena a todos los componentes que actualicen su valor, es decir, que
    * tomen como valor lo que el usuario ha introducido en el componente gr�fico
    **************************************************************************/
   public void actualizarValores() {
      for ( int i = 0; i < vec.size(); i++ ) {
	 ComponenteDin comp = (ComponenteDin) vec.get(i);// Extraigo el componente del vector
	 comp.actualizarValor();                         // Actualiza valor del componente
      }
   }

   /***************************************************************************
    * Configura y a�ade bot�n. El cuarto argumento es el listener del bot�n.
    * Nota: para que surta efecto visual esta configuraci�n se debe llamar a este
    *       m�todo antes de iniciar()
    **************************************************************************/
   public void configBoton( String tituloBoton, String actionCommand, Dimension tamanioBoton,
			    ActionListener oyente ) {
      this.tamanioBoton = tamanioBoton;
      btnAceptar = new JButton( tituloBoton );
      btnAceptar.setActionCommand( actionCommand );
      if ( tamanio != null ) {
	 btnAceptar.setPreferredSize(tamanioBoton);
	 btnAceptar.setMaximumSize(tamanioBoton);
	 btnAceptar.setMinimumSize(tamanioBoton);
      }
      btnAceptar.addActionListener(oyente);
   }
}

