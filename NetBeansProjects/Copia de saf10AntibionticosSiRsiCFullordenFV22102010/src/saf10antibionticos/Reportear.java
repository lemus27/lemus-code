/*
 * Reportear.java
 */

package saf10antibionticos;
//*****************************************
import consultaantibioticosxnombre.InputNombreAntibiotico;
import consultaantibioticosxnombre.buscarAntibioticos;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
//*****************************************************
/**
 * clases  apar  conectarse a la bd "antb " e  imprimir  reporte de la tabla "antibioticos".
 */
public class Reportear 
{
//*******************************************************************************
    public static Connection getConnection()  {

         //Creamos la conexi?n a Derby
 Connection  conn =null;

         try {//
              Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
               //Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
    conn = DriverManager.getConnection("jdbc:derby:C:\\Antibioticos\\antb;create=true;user=app;password=app");

   }    catch (InstantiationException ex) {
            Logger.getLogger(Saf10AntibionticosView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Saf10AntibionticosView.class.getName()).log(Level.SEVERE, null, ex);
        }    catch (ClassNotFoundException ex) {
            Logger.getLogger(Saf10AntibionticosView.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex) {}

//Retornamos la conexi?n establecida.*/
    return conn;
    }


	public static  void imprimirReporteJRXML(){

  //La Ruta de nuestro reporte
   String filejasper="C:\\Antibioticos\\reportes\\rptAntibioticos.jrxml";//ok
   String[] frameWork={"derbyclient"};
   try
        {
        //cargamos parametros del reporte (si tiene).
            Map parameters = new HashMap();
            //parameters.put("REPORT_LOCALE",new java.util.Locale("es","CL"));
           // parameters.put("parametro1","Hola Mundo!");
            JasperDesign jasperDesign = JRXmlLoader.load(filejasper);
                       //Compilar el Reporte.
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,getConnection());
              //Cargar reporte en el visor.
            JasperViewer jasperviewer = new JasperViewer(jasperPrint,false);
            //Le ponemos un titulo personalizado al visor, y desplegamos el reporte.
            jasperviewer.setTitle("Reporte de Prueba");
             //exibe o relat?rio com viewReport
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
//************************************************************************************
}