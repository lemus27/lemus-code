/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.integrator;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Edson
 */
public class VisualizaRelatorio {
    private Connection conn;
    public VisualizaRelatorio( ) throws Exception{
        try {
            this.conn = Conexao.getConnection( );
        } catch( Exception e ) {
            throw new Exception( "Erro: \n" + e.getMessage( ) );
        }
    }

    public void visualizar(Integer numPedido){

        try{
        	HashMap<String, Integer> parameterMap =
						new HashMap<String, Integer>( );
			 //o Nome do parâmetro e o valor é passado ao HashMap
			parameterMap.put("PAR_PEDID", numPedido);

            //caminho relativo do relatório dentro do pacote
            String a = "relatorio/RelatorioAgrupado.jasper";

            //lê o arquivo dentro do pacote
            //mecessário caso gere um JAR para distribuir
            InputStream in = this.getClass().getResourceAsStream(a);


			 //chama fillReport
			JasperPrint jp = JasperFillManager.fillReport(in,
						parameterMap, conn);

			 //exibe o relatório com viewReport
			JasperViewer.viewReport(jp, false);

		}  catch (JRException e) {
            e.printStackTrace();
		} finally {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {}
		}

    }

}
