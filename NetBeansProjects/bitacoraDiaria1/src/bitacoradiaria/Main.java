/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bitacoradiaria;
import com.mgl.bitacoradiaria.dao.*;

import com.mgl.bitacoradiaria.model.*;
import java.util.List;
/**
 *
 * @author mike
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        // TODO code application logic here


     TblusuariosJpaController dao=new TblusuariosJpaController();
     List<Tblusuarios> lista=dao.findTblusuariosEntities();
     for(Tblusuarios usuario : lista)
     {
        System.out.println("Nombre "+usuario.getNombre());
    }
    }

}
