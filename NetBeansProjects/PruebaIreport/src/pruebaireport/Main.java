/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebaireport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Administrador
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public  static void main(String[] args) {
        // TODO code application logic here
        System.out.println("hola");
        imprimirReporte();
    }
private static  void selectRestaurants()
    {
          Statement stmt = null;
        String tableName = "antibioticos";
         Connection  conn =getConnection();
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");
            }

            System.out.println("\n-------------------------------------------------");

            while(results.next())
            {
                int id = results.getInt(2);
                int restName = results.getInt(3);
                int cityName = results.getInt(4);
                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
     public static Connection getConnection()  {
        //Configuración de la conexión.
       // String driver = "org.apache.derby.jdbc.ClientDriver";
        //String connectString = "jdbc:derby://localhost:1527/antb";
         //Creamos la conexión a Derby
 Connection  conn =null;

        //String user = "app";
        //String password = "app";

       // Class.forName(driver);
        //Connection conn  = DriverManager.getConnection("jdbc:derby:antb;create=true");
        //Connection conn = DriverManager.getConnection(connectString, user, password);
         try {//
              //Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            try {
                try {
                    //
                    //Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
                    Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
    conn = DriverManager.getConnection("jdbc:derby://localhost:1527/antb;create=true;user=app;password=app");

   }    catch (InstantiationException ex) {
         
        }catch(SQLException ex) {}

//Retornamos la conexión establecida.
    return conn;
    }

     public static void imprimirReporte(){
        // selectRestaurants();
         /*fdfsdfsdfsdf
             try{
 //Cargar el archivo de reporte generado con el IReport
        JasperDesign jd = JRXmlLoader.load(“/home/miltonlab/voto2009/src/Inscripciones.jrxml”);
        JasperReport report = JasperCompileManager.compileReport(jd);
        Connection conexion = new Voto2009Dao().getConexion();
        Map params = new HashMap();
 //Pedimos al usuario el numero del partido del cual quiere imprimir sus candidatos
        int lista = Integer.parseInt(JOptionPane.showInputDialog(“Ingrese el Numero de Lista:”));
        params.put(“numeroPartido”,lista);

        JasperPrint print = JasperFillManager.fillReport(report, params,conexion);
 // Se visualiza la ventana con la vista previa del reporte
        JasperViewer.viewReport(print);

    }catch(Exception ex){
        System.out.println(“Error al generar reporte: ” + ex);
    }
          *
           */
  //La Ruta de nuestro reporte
  //String filejasper="C:\\Documents and Settings\\Administrador\\Mis documentos\\NetBeansProjects\\jsfAntibioticos\\src\\jsfantibioticos\\rptAntibioticos.jrxml";
  String filejasper="src/pruebaireport/rptAntibioticos.jrxml";//ok
//Ruta de Archivo Jasper
            //String fileName="C:/Documents and Settings/Administrador/Mis documentos/NetBeansProjects/jsfAntibioticos/src/jsfantibioticos/rptAntibioticos.jasper";//ok
             String fileName="rptAntibioticos.jasper";//ok
   try
        {

        //cargamos parametros del reporte (si tiene).
            Map parameters = new HashMap();
            //parameters.put("REPORT_LOCALE",new java.util.Locale("es","CL"));
           // parameters.put("parametro1","Hola Mundo!");

           // JasperDesign jasperDesign= JRXmlLoader.load(getClass().getResourceAsStream("jsfantibioticos/rptAntibioticos.jasper"));

//lê o arquivo dentro do pacote
            //mecessário caso gere um JAR para distribuir
            //InputStream in = this.getClass().getResourceAsStream(fileName);

            JasperDesign jasperDesign = JRXmlLoader.load(filejasper);
                        //Compilar el Reporte.
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,getConnection());
            //Preparacion del reporte (en esta etapa se inserta el valor del query en el reporte).
           //
            //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,getConnection());
            //Cargar reporte en el visor.
            JasperViewer jasperviewer = new JasperViewer(jasperPrint,false);

            //Le ponemos un titulo personalizado al visor, y desplegamos el reporte.

            jasperviewer.setTitle("Reporte de Prueba");
             //exibe o relatório com viewReport
			JasperViewer.viewReport(jasperPrint, false);
            //jasperviewer.show();


        }
        catch(Exception e)
        {
                        System.out.println(" ya  cayo el cathc");
             System.out.println(e.toString());
            System.out.println( e.getMessage());
        // JOptionPane.showMessageDialog(null,"Hay datos no validos, Revise y calculenuevamente","Validacion de campos",JOptionPane.WARNING_MESSAGE);
        }
    }
}
