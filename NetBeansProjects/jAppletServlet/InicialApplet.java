package docen_servlet_applet;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.*;
import java.io.*;

/**************************************************************************************
 * Práctica inicial de tunneling. El applet se conecta a un servlet por el método POST.
 * Este applet permite al usuario escribir en los campos de edición (atributos)
 * 'txtParam' y 'txtValor' el nombre y valor del parametro de la conexión POST.
 * Además se abre un stream de entrada para que el applet pueda recibir el texto que
 * manda el servlet. El texto se manda a un JEditorPane.
 **************************************************************************************/
public class InicialApplet extends JApplet  implements java.awt.event.ActionListener {

   Utilidades util;   // Clase de utilidad, entre otras cosas, maneja archivo de propiedades

   //// Componentes del panel de texto
   private JLabel lblTitulo;
   private JTextField txtParam;
   private JTextField txtValor;

   //// Botón que aparece en la parte inferior del applet. Para el envio
   JButton bot_enviar = new JButton( "Conexión" );  // Botón de conexión con servlet

   //// Editor que aparece centrado sobre el applet
   JEditorPane editor = new JEditorPane();

   /********** Inicializar el applet **********/
   public void init() {
      try { jbInit(); }
      catch(Exception e) {  e.printStackTrace(); }
   }

   /****************************************************************************
    *  Inicializar componentes
    ****************************************************************************/
   private void jbInit() throws Exception {
      Dimension tamEtiquetas = new Dimension( 100,22);
      //// Panel de campos de texto
      JPanel pnlTexto = new JPanel();
      pnlTexto.setLayout( new GridLayout(3, 1) );

      //// Panel de texto: titulo
      lblTitulo = new JLabel( "Si presiona 'Conexión' recibiremos datos del servlet",SwingConstants.CENTER);
      lblTitulo.setBorder(BorderFactory.createRaisedBevelBorder());

      //// Panel de texto: nombre del parametro que enviamos
      JPanel pnlParam = new JPanel();
      pnlParam.setLayout( new BorderLayout() );
      JLabel lblParam = new JLabel( "Parámetro:");
      lblParam.setPreferredSize( tamEtiquetas );
      txtParam = new JTextField();
      pnlParam.add( lblParam, BorderLayout.WEST);
      pnlParam.add( txtParam, BorderLayout.CENTER);

      //// Panel de texto: valor del parametro que enviamos
      JPanel pnlValor = new JPanel();
      pnlValor.setLayout( new BorderLayout() );
      JLabel lblValor = new JLabel( "Valor:");
      lblValor.setPreferredSize( tamEtiquetas );
      txtValor = new JTextField();
      pnlValor.add( lblValor, BorderLayout.WEST);
      pnlValor.add( txtValor, BorderLayout.CENTER);

      //// Añadir a panel de texto
      pnlTexto.add( lblTitulo );
      pnlTexto.add( pnlParam );
      pnlTexto.add( pnlValor );

      //// Configurar editor con scroll
      editor.setBackground(Color.CYAN);
      editor.setBorder(BorderFactory.createLoweredBevelBorder());
      println("En este espacio aparecerá el resultado de la conexión.");
      JScrollPane pnlScrollEditor = new JScrollPane( editor, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						       JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
      bot_enviar.addActionListener( this );    // El listener del botón es el applet

      //// Añadir componentes al contenedor raiz
      Container cont_raiz = getContentPane();
      cont_raiz.setLayout( new BorderLayout() );
      cont_raiz.add( pnlTexto, BorderLayout.NORTH );
      cont_raiz.add( pnlScrollEditor, BorderLayout.CENTER );
      cont_raiz.add( bot_enviar, BorderLayout.SOUTH );

      util = new Utilidades();
      /*
       El path donde está el archivo de propiedades es host+ 'propiedades/paquete/'
       Para encontrar el paquete no sirve getClass().getPackage().getName(), ya que en este
       applet el cargador de clase no instancia la clase package
      */
     String pathPropiedades = getCodeBase().getProtocol() + "://" + getCodeBase().getHost() + ":" +
			       getCodeBase().getPort() +"/propiedades/docen_servlet_applet/";
      util.cargarPropiedades( pathPropiedades);

   }
   /************************************************************************
    * Manda al editor el argumento. Como el clásico println()
    ***********************************************************************/
   private void println( String mensaje ) {
      editor.setText(editor.getText() + mensaje + "\n");
   }
   /************************************************************************
    * Manda al editor el argumento. Como el clásico print()
    ***********************************************************************/
   private void print( String mensaje ) {
      editor.setText(editor.getText() + mensaje );
   }

   /************************************************************************
    * Gestión de evento clic en botón 'Enviar'. Conexión al servlet.
    ***********************************************************************/
   public void actionPerformed(ActionEvent e) {

      try {

	 //// Si hay error al abrir el archivo de propiedades, aviso y salgo
	 if ( util.mensajeError != null ) {
	    println( "Se ha intentado abrir " + util.getPathPropiedades() + util.getArchivoPropiedades());
	    println( "Error. " + util.mensajeError );
	    return;
	 }
	 //// Si no hay error, aviso y continuo
	 else
	    println( "Abierto archivo de propiedades: " + util.getPathPropiedades()+util.getArchivoPropiedades() );

	 //// Obtengo la URL del servlet y a partir de la URL abro la conexión
	 String pathServlet = util.getHostHTTP()+"servlet/inicialservlet";
	 URL urlServlet = new URL( pathServlet );
	 URLConnection conServlet = urlServlet.openConnection(); // Abro conexión con url
	 conServlet.setRequestProperty( "accept-language", "es");

	 println( "CONEXION REALIZADA SOBRE " + pathServlet );  // Visualizar éxito de la conexión

	 //// Arrays de parametros y valores
	 String params[] = { txtParam.getText() };
	 String valores[] = { txtValor.getText() };
	 //// Codifico parámetros y valores. Aunque está muy extendida, si uso codificación UTF-8
	 //// no pueden aparecer tildes ni eñes
	 String paramsCodificados = Utilidades.getParamsCodificados( params, valores, "iso-8859-1");

	 enviarParams( conServlet, paramsCodificados );   // Envio los parámetros

	 recibirRespuesta( conServlet );                  // Recibo el texto del servlet
      }
      catch (MalformedURLException em) {                    // Hija de IOException
	 println( "URL INCORRECTA. " + em.getMessage() );
	 println( Utilidades.getStrings(em) );
      }
      catch (IOException eio) {                              // Hija de Exception
	 println( "EXCEPCION IO. " + eio.getMessage() );
	 println( Utilidades.getStrings(eio) );
      }
      catch (Exception ex) {
	 println( "EXCEPCION  GENERICA. " + ex.getMessage() );
	 println( Utilidades.getStrings(ex) );
      }
   }

   /********************************************************************************
    * Envio de parametros a la conexión 'con'. Los parámetros deben venir codificados.
    ********************************************************************************/
   public void enviarParams( URLConnection con, String params ) throws IOException {
      con.setDoOutput( true);                         // Conexión para salida
      con.setUseCaches( false );                      // El navegador no usa caché
      PrintWriter salida = new PrintWriter( con.getOutputStream() );
      salida.print( params );
      salida.close();
   }
   /********************************************************************************
    * Recibo respuesta del servidor al que me he conectado con 'con'.
    * El texto recibido se pone en el editor.
    ********************************************************************************/
   public void recibirRespuesta( URLConnection con ) throws IOException {
      //// Creamos un stream de entrada a partir de la conexión
      InputStreamReader streamEntrada = new InputStreamReader (con.getInputStream());
      //// Creamos un lector de buffer que tiene como entrada el stream
      BufferedReader bfEntrada = new BufferedReader( streamEntrada );

      //// Leemos el buffer linea a linea, cada una de ellas la enviamos al editor
      String linea;
      while ((linea = bfEntrada.readLine()) != null )
	 println( linea );

      bfEntrada.close();
   }
}