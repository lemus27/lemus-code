/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.integrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edson
 */
public class DAO {
    private Connection conn;
    
     /**
     * MÃ©todo construtor que se conecta ao banco de dados
     * @throws java.lang.Exception se nÃ£o houver conexÃ£o
     */
    public DAO( ) throws Exception{
        try {
            this.conn = Conexao.getConnection( );
            
            
        } catch( Exception e ) {
            throw new Exception( "Erro: \n" + e.getMessage( ) );
        }
    }
 
    public List<Valor> todos( )  throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = this.conn;
            String sql = "SELECT PedidoID FROM pedidos";
            
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery( );
             List<Valor> list = new ArrayList<Valor>( );
            while( rs.next( ) ) {
                Integer id = rs.getInt( 1 );
                String pedido = rs.getString( 1 );

                
               list.add( new Valor(id,pedido) );
                
            }
            return list;
            
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }
    
 
}
