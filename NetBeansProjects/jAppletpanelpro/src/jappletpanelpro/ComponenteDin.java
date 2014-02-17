package jappletpanelpro;
import javax.swing.*;
import java.awt.*;

/****************************************************************************
 * Clase que recubre o encapsula un componente gr�fico
 * Con el m�todo actualizarValor() almacena en el valor lo que el usuario
 * ha introducido en el componente gr�fico.
 ***************************************************************************/
abstract public class ComponenteDin {
   private JComponent componenteGrafico;   // Componente gr�gico encapsulado
   private Object valor;                   // Valor inicial (por ejemplo, en JTextField es el texto)
   private String titulo;                  // Texto de la etiqueta
   private Dimension tamanio = null;       // Tama�o en pixels
   private boolean habilitado;             // Enable

   public static int ALTURA_COMPONENTE = 20;   // Altura por defecto
   public static int ANCHURA_COMPONENTE = 100; // Anchura por defecto
   public static int ANCHURA_ETIQUETA = 120;   // Ancho de la etiqueta por defecto

   ////////////////////////// CONSTRUCTORES QUE MANEJAN TAMA�O EN PIXELES \\\\\\\\\\\\\\
   /***************************************************************************
    * Crea el componente gr�fico encapsulado. Parametros:
    * valor: valor por defecto
    * titulo: cadena de titulo (etiqueta)
    * tamanio: dimensiones del componente. Si es null, se adapta al ancho del valor
    * habilitado: valor de setEnabled
    **************************************************************************/
   public ComponenteDin( Object valor, String titulo, Dimension tamanio, boolean habilitado ) {
      this.valor = (String) valor;
      this.titulo = titulo;
      this.tamanio = tamanio;
      this.habilitado = habilitado;
      setComponenteGrafico( crearComponente() );
   }
   public ComponenteDin( Object valor, String titulo, Dimension tamanio ) {
      this( valor, titulo, tamanio, true );
   }
   public ComponenteDin( Object valor, String titulo ) {
      this( valor, titulo, null, true );
   }

   /***************************************************************************
    * Crea el subpanel que engloba o contiene al componente. Retorna dicho subpanel.
    * Par�metros: tama�o y color del panel
    * Nota: el ancho del subpanel debe ser reducido respecto al tama�o del superpanel,
    *       para que el borde no se pinte sobre los componentes. Por ello, ver�
    *       en este metodo la operaci�n tamanioSuperPanel.width-X
    **************************************************************************/
   public JPanel crearSubpanel(Dimension tamanioSuperPanel, Color colorFondo) {
      JPanel subpanel = new JPanel();
      try {

	 //// Layout alineado a la izquierda
	 subpanel.setLayout( new FlowLayout(FlowLayout.LEFT));

	 //// Si se ha especificado tama�o de componente y de panel padre, ...
	 if ( tamanio != null && tamanioSuperPanel != null) {
	    //// Ancho: ancho del panel padre (contenedor). Alto: alto del componente + holgura
	    subpanel.setPreferredSize(new Dimension(tamanioSuperPanel.width-10,(int) tamanio.getHeight() + 8));
	    subpanel.setMaximumSize(new Dimension(tamanioSuperPanel.width-10,(int) tamanio.getHeight() + 8));
	 }
	 //// Si no se han especificado alguno de los tama�os, ...
	 else {
	    if ( tamanioSuperPanel != null ) {  // Si hay tama�o del panel padre
	       //// Ancho del panel padre y alto por defecto + holgura
	       subpanel.setPreferredSize(new Dimension(tamanioSuperPanel.width-10, ALTURA_COMPONENTE + 8));
	       subpanel.setMaximumSize(new Dimension(tamanioSuperPanel.width-10, ALTURA_COMPONENTE + 8));
	    }
	    else { // Si no hay tama�o del panel padre
	       //// Ancho y alto por defecto + holgura
	       subpanel.setPreferredSize( new Dimension( ANCHURA_COMPONENTE+150, ALTURA_COMPONENTE + 8));
	       subpanel.setMaximumSize( new Dimension(ANCHURA_COMPONENTE+150, ALTURA_COMPONENTE + 8));
	    }
	 }
	 subpanel.setBackground( colorFondo );  // Color de fondo

      }
      catch (Exception e) {
	 e.printStackTrace();
      }
      finally {
	 return subpanel;
      }
   }

   /***************************************************************************
    * METODO ABSTRACTO, que deben implementar las clases hijas.
    * Crea el componente y lo retorna
    **************************************************************************/
   abstract protected JComponent crearComponente();

   /***************************************************************************
    * METODO ABSTRACTO, que deben implementar las clases hijas.
    * Configura el componente y lo retorna
    **************************************************************************/
   abstract protected JComponent configurarComponente();

   /***************************************************************************
    * METODO ABSTRACTO, que deben implementar las clases hijas.
    * A�ade componente al subpanel (argumento)
    **************************************************************************/
   abstract protected void aniadirAlSubpanel( JPanel subpanel );

   /***************************************************************************
    * METODO ABSTRACTO, que deben implementar las clases hijas.
    * Pone en el atributo 'valor' lo que el usuario ha dejado en componente gr�fico
    **************************************************************************/
   abstract public void actualizarValor();

   /***************************************************************************
    * Inicializo componente gr�fico encapsulado, de acuerdo a sus atributos.
    * tamanioSuperPanel: tama�o del panel padre o contenedor.
    * colorFondo: color de fondo del componente (en realidad es el color de fondo
    *             del subpanel (ver crearSubpanel()).
    * Devuelve el subpanel en el que insertamos el componente.
    **************************************************************************/
   public JPanel iniciar( Dimension tamanioSuperPanel, Color colorFondo ) {
      JPanel subpanel = null;
      try {
	 //////////// 1. CREAR Y CONFIGURAR EL SUBPANEL
	 subpanel = crearSubpanel( tamanioSuperPanel, colorFondo );

	 /////////// 2. A�ADE COMPONENTE A SUBPANEL
	 aniadirAlSubpanel( subpanel );

	 //////////// 3. TAMA�O DEL COMPONENTE GRAFICO
	 if ( tamanio != null) { // Si tamanio es null, no hago nada
	    componenteGrafico.setMaximumSize(tamanio);
	    componenteGrafico.setPreferredSize(tamanio);
	 }
	 //////////// 4. COMPONENTE HABILITADO
	 componenteGrafico.setEnabled(habilitado);

	 //////////// 5. A�ADIMOS COMPONENTE AL SUBPANEL
	 subpanel.add( componenteGrafico );
      }
      catch (Exception e) {
	 e.printStackTrace();
      }
      finally {
	 return subpanel;
      }
   }

   public void setComponenteGrafico( JComponent componenteGrafico ) {
      this.componenteGrafico = componenteGrafico;
   }
   public JComponent getComponenteGrafico() { return componenteGrafico; }
   public void setValor( Object valor )  { this.valor = valor; }
   public Object getValor()              { return valor; }
   public void setTamanio()              { this.tamanio = tamanio; }
   public Dimension getTamanio()         { return tamanio; }
   public void setTitulo( String titulo ){ this.titulo = titulo; }
   public String getTitulo()             { return titulo; }
}