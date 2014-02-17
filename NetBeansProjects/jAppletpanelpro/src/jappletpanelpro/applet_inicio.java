package jappletpanelpro;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.util.Vector;

/*************************************************************************
 * Applet que utiliza un panel de estilo formulario.
 * Tanto la creaci�n de los componentes como la organizaci�n del panel
 * esta encapsulada para el programador.
 **************************************************************************/
public class applet_inicio extends JApplet {
   PanelForm panel;                    // Panel formulario
   Vector componentes = new Vector();  // Componentes del panel

   public void init() {

      try {
	 //// Contenedor raiz:
	 Container contRaiz = getContentPane();
	 contRaiz.setLayout( new BorderLayout() );
	 contRaiz.setBackground( Color.cyan);

	 //// Creamos componentes de texto, indicando valor, titulo, tama�o del componente y alineamiento
	 //// El tama�o se puede especificar como Dimension (pixels) o como columnas (caracteres)
	 ComponenteDin.ANCHURA_ETIQUETA = 70;
	 componentes.add( new ComponenteDinTXT( "Lopez Quesada", "Apellidos", new Dimension( 160, 20)) );
	 componentes.add( new ComponenteDinTXT( "Pedro", "Nombre", new Dimension( 100, 20)) );
	 componentes.add( new ComponenteDinTXT( "34", "Edad", 2 ) );  // Columnas
	 componentes.add( new ComponenteDinTXT( "V", "Sexo") );
	 //// Alineado a la derecha
	 componentes.add( new ComponenteDinTXT( "2000", "Cr�dito", new Dimension( 90, 20), false ) );

	 //// Creamos componente combobox, antes creamos el vector de items
	 Vector oficinas = new Vector();
	 oficinas.add( "Madrid");
	 oficinas.add( "Bilbao");
	 oficinas.add( "Barcelona");
	 componentes.add( new ComponenteDinCBB( "Bilbao", oficinas, "Oficina", new Dimension( 120, 20)) );

	 //// Creamos componente para etiqueta con color rojo
	 ComponenteDin c = new ComponenteDinLBL( "En baja laboral" );
	 c.getComponenteGrafico().setForeground( Color.RED);
	 componentes.add( c );

	 //// Creamos el panel, mandandole vector de componentes, tama�o y titulo
	 panel = new PanelForm( componentes, getSize(), "Ficha personal");
	 //// Configuro el bot�n
	 panel.configBoton( "Guardar", "GGG", new Dimension(90,24), new listener_botonAceptar(this));

	 //// Inicio de presentaci�n
	 panel.iniciar();

	 contRaiz.add( panel, BorderLayout.CENTER );
      }
      catch(Exception e) {
	 e.printStackTrace();
      }
   }

   /********************************************************************************
    * Recepci�n de evento de pulsar bot�n. Actualizo los valores e imprimo
    *******************************************************************************/
   public void actionPerformed_BotonAceptar(ActionEvent e) {
      panel.actualizarValores();
      for (int i = 0; i < componentes.size(); i++) {
	 ComponenteDin comp = (ComponenteDin) componentes.get(i);// Extraigo el componente del vector
	 System.out.println( "Componente: " + comp.getTitulo() + " Valor:" + comp.getValor());
      }
   }

}

/********************************************************************************
 * Listener de eventos de bot�n
 * 'objetivo' es el contenedor que recibe la llamada
 *******************************************************************************/
class listener_botonAceptar implements java.awt.event.ActionListener {
   applet_inicio objetivo;

   listener_botonAceptar(applet_inicio objetivo) {
      this.objetivo = objetivo;
   }
   public void actionPerformed(ActionEvent e) {
      objetivo.actionPerformed_BotonAceptar(e);
   }
}
