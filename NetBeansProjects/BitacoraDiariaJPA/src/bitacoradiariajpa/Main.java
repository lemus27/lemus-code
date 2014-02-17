/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bitacoradiariajpa;

import com.mgl.bitacoradiaria.dao.TblactividadJpaController;
import com.mgl.bitacoradiaria.dao.TblusuariosCreaActividadJpaController;
import com.mgl.bitacoradiaria.dao.TblusuariosJpaController;
import com.mgl.bitacoradiaria.dao.exceptions.IllegalOrphanException;
import com.mgl.bitacoradiaria.model.Tblactividad;
import com.mgl.bitacoradiaria.model.Tblbitacoradiaria;
import com.mgl.bitacoradiaria.model.Tblcategorias;
import com.mgl.bitacoradiaria.model.Tblusuarios;
import com.mgl.bitacoradiaria.model.TblusuariosCreaActividad;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
            // TODO code application logic here
     TblusuariosJpaController dao=new TblusuariosJpaController();
     List<Tblusuarios> lista=dao.findTblusuariosEntities();
     for(Tblusuarios usuario : lista)
     {System.out.println("IdUsuario "+usuario.getIdUsuario());
        System.out.println("Nombre "+usuario.getNombre());
    }
     new Main().InsertarActividad();
    }
 public  void InsertarActividad()
 { TblusuariosJpaController dao=new TblusuariosJpaController();
     List<Tblusuarios> lista=dao.findTblusuariosEntities();
  
   TblusuariosCreaActividad objUserCreator= new TblusuariosCreaActividad();
 TblusuariosCreaActividadJpaController daoUserCreatorCtrl = new  TblusuariosCreaActividadJpaController();
     TblactividadJpaController daoAct=new TblactividadJpaController();
     Tblactividad entAct= new Tblactividad();
      entAct.setIdActividad(new Long(1));
     entAct.setNombre("dormir");
     entAct.setDescripcion("dormir");
     entAct.setFechaCreacion(new Date());
     entAct.setHoraCreacion(new Date());
     entAct.setTblbitacoradiariaCollection(null);
     entAct.setTblusuariosCreaActividad(objUserCreator);
     entAct.setTipo("X");
      /*Tblusuarios objUser= lista.get(0);
     objUserCreator.setIdCreador(new Long(1));
     objUserCreator.setIdUsuarioCrea(objUser);
   objUserCreator.setIdActividadNuevo(entAct);
        try {
            daoUserCreatorCtrl.create(objUserCreator);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
     daoAct.create(entAct);

  
 }

}
