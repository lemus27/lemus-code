/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.IllegalOrphanException;
import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.model.Tblusuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblperfiles;
import com.mgl.bitacoradiaria.model.TblusuariosCreaUsuarios;
import java.util.ArrayList;
import java.util.Collection;
import com.mgl.bitacoradiaria.model.Tblbitacoradiaria;
import com.mgl.bitacoradiaria.model.TblusuariosCreaPerfiles;
import com.mgl.bitacoradiaria.model.TblusuariosCreaTblpermisos;
import com.mgl.bitacoradiaria.model.TblusuariosCreaCategorias;
import com.mgl.bitacoradiaria.model.TblusuariosCreaInterrupciones;
import com.mgl.bitacoradiaria.model.TblusuariosCreaActividad;

/**
 *
 * @author mike
 */
public class TblusuariosJpaController {

    public TblusuariosJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblusuarios tblusuarios) {
        if (tblusuarios.getTblusuariosCreaUsuariosCollection() == null) {
            tblusuarios.setTblusuariosCreaUsuariosCollection(new ArrayList<TblusuariosCreaUsuarios>());
        }
        if (tblusuarios.getTblusuariosCreaUsuariosCollection1() == null) {
            tblusuarios.setTblusuariosCreaUsuariosCollection1(new ArrayList<TblusuariosCreaUsuarios>());
        }
        if (tblusuarios.getTblbitacoradiariaCollection() == null) {
            tblusuarios.setTblbitacoradiariaCollection(new ArrayList<Tblbitacoradiaria>());
        }
        if (tblusuarios.getTblusuariosCreaPerfilesCollection() == null) {
            tblusuarios.setTblusuariosCreaPerfilesCollection(new ArrayList<TblusuariosCreaPerfiles>());
        }
        if (tblusuarios.getTblusuariosCreaTblpermisosCollection() == null) {
            tblusuarios.setTblusuariosCreaTblpermisosCollection(new ArrayList<TblusuariosCreaTblpermisos>());
        }
        if (tblusuarios.getTblusuariosCreaCategoriasCollection() == null) {
            tblusuarios.setTblusuariosCreaCategoriasCollection(new ArrayList<TblusuariosCreaCategorias>());
        }
        if (tblusuarios.getTblusuariosCreaInterrupcionesCollection() == null) {
            tblusuarios.setTblusuariosCreaInterrupcionesCollection(new ArrayList<TblusuariosCreaInterrupciones>());
        }
        if (tblusuarios.getTblusuariosCreaActividadCollection() == null) {
            tblusuarios.setTblusuariosCreaActividadCollection(new ArrayList<TblusuariosCreaActividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblperfiles idPerfilTiene = tblusuarios.getIdPerfilTiene();
            if (idPerfilTiene != null) {
                idPerfilTiene = em.getReference(idPerfilTiene.getClass(), idPerfilTiene.getIdPerfil());
                tblusuarios.setIdPerfilTiene(idPerfilTiene);
            }
            Collection<TblusuariosCreaUsuarios> attachedTblusuariosCreaUsuariosCollection = new ArrayList<TblusuariosCreaUsuarios>();
            for (TblusuariosCreaUsuarios tblusuariosCreaUsuariosCollectionTblusuariosCreaUsuariosToAttach : tblusuarios.getTblusuariosCreaUsuariosCollection()) {
                tblusuariosCreaUsuariosCollectionTblusuariosCreaUsuariosToAttach = em.getReference(tblusuariosCreaUsuariosCollectionTblusuariosCreaUsuariosToAttach.getClass(), tblusuariosCreaUsuariosCollectionTblusuariosCreaUsuariosToAttach.getIdCreador());
                attachedTblusuariosCreaUsuariosCollection.add(tblusuariosCreaUsuariosCollectionTblusuariosCreaUsuariosToAttach);
            }
            tblusuarios.setTblusuariosCreaUsuariosCollection(attachedTblusuariosCreaUsuariosCollection);
            Collection<TblusuariosCreaUsuarios> attachedTblusuariosCreaUsuariosCollection1 = new ArrayList<TblusuariosCreaUsuarios>();
            for (TblusuariosCreaUsuarios tblusuariosCreaUsuariosCollection1TblusuariosCreaUsuariosToAttach : tblusuarios.getTblusuariosCreaUsuariosCollection1()) {
                tblusuariosCreaUsuariosCollection1TblusuariosCreaUsuariosToAttach = em.getReference(tblusuariosCreaUsuariosCollection1TblusuariosCreaUsuariosToAttach.getClass(), tblusuariosCreaUsuariosCollection1TblusuariosCreaUsuariosToAttach.getIdCreador());
                attachedTblusuariosCreaUsuariosCollection1.add(tblusuariosCreaUsuariosCollection1TblusuariosCreaUsuariosToAttach);
            }
            tblusuarios.setTblusuariosCreaUsuariosCollection1(attachedTblusuariosCreaUsuariosCollection1);
            Collection<Tblbitacoradiaria> attachedTblbitacoradiariaCollection = new ArrayList<Tblbitacoradiaria>();
            for (Tblbitacoradiaria tblbitacoradiariaCollectionTblbitacoradiariaToAttach : tblusuarios.getTblbitacoradiariaCollection()) {
                tblbitacoradiariaCollectionTblbitacoradiariaToAttach = em.getReference(tblbitacoradiariaCollectionTblbitacoradiariaToAttach.getClass(), tblbitacoradiariaCollectionTblbitacoradiariaToAttach.getIdBitacora());
                attachedTblbitacoradiariaCollection.add(tblbitacoradiariaCollectionTblbitacoradiariaToAttach);
            }
            tblusuarios.setTblbitacoradiariaCollection(attachedTblbitacoradiariaCollection);
            Collection<TblusuariosCreaPerfiles> attachedTblusuariosCreaPerfilesCollection = new ArrayList<TblusuariosCreaPerfiles>();
            for (TblusuariosCreaPerfiles tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfilesToAttach : tblusuarios.getTblusuariosCreaPerfilesCollection()) {
                tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfilesToAttach = em.getReference(tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfilesToAttach.getClass(), tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfilesToAttach.getIdCreador());
                attachedTblusuariosCreaPerfilesCollection.add(tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfilesToAttach);
            }
            tblusuarios.setTblusuariosCreaPerfilesCollection(attachedTblusuariosCreaPerfilesCollection);
            Collection<TblusuariosCreaTblpermisos> attachedTblusuariosCreaTblpermisosCollection = new ArrayList<TblusuariosCreaTblpermisos>();
            for (TblusuariosCreaTblpermisos tblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisosToAttach : tblusuarios.getTblusuariosCreaTblpermisosCollection()) {
                tblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisosToAttach = em.getReference(tblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisosToAttach.getClass(), tblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisosToAttach.getIdCreador());
                attachedTblusuariosCreaTblpermisosCollection.add(tblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisosToAttach);
            }
            tblusuarios.setTblusuariosCreaTblpermisosCollection(attachedTblusuariosCreaTblpermisosCollection);
            Collection<TblusuariosCreaCategorias> attachedTblusuariosCreaCategoriasCollection = new ArrayList<TblusuariosCreaCategorias>();
            for (TblusuariosCreaCategorias tblusuariosCreaCategoriasCollectionTblusuariosCreaCategoriasToAttach : tblusuarios.getTblusuariosCreaCategoriasCollection()) {
                tblusuariosCreaCategoriasCollectionTblusuariosCreaCategoriasToAttach = em.getReference(tblusuariosCreaCategoriasCollectionTblusuariosCreaCategoriasToAttach.getClass(), tblusuariosCreaCategoriasCollectionTblusuariosCreaCategoriasToAttach.getIdCreador());
                attachedTblusuariosCreaCategoriasCollection.add(tblusuariosCreaCategoriasCollectionTblusuariosCreaCategoriasToAttach);
            }
            tblusuarios.setTblusuariosCreaCategoriasCollection(attachedTblusuariosCreaCategoriasCollection);
            Collection<TblusuariosCreaInterrupciones> attachedTblusuariosCreaInterrupcionesCollection = new ArrayList<TblusuariosCreaInterrupciones>();
            for (TblusuariosCreaInterrupciones tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupcionesToAttach : tblusuarios.getTblusuariosCreaInterrupcionesCollection()) {
                tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupcionesToAttach = em.getReference(tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupcionesToAttach.getClass(), tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupcionesToAttach.getIdCreador());
                attachedTblusuariosCreaInterrupcionesCollection.add(tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupcionesToAttach);
            }
            tblusuarios.setTblusuariosCreaInterrupcionesCollection(attachedTblusuariosCreaInterrupcionesCollection);
            Collection<TblusuariosCreaActividad> attachedTblusuariosCreaActividadCollection = new ArrayList<TblusuariosCreaActividad>();
            for (TblusuariosCreaActividad tblusuariosCreaActividadCollectionTblusuariosCreaActividadToAttach : tblusuarios.getTblusuariosCreaActividadCollection()) {
                tblusuariosCreaActividadCollectionTblusuariosCreaActividadToAttach = em.getReference(tblusuariosCreaActividadCollectionTblusuariosCreaActividadToAttach.getClass(), tblusuariosCreaActividadCollectionTblusuariosCreaActividadToAttach.getIdCreador());
                attachedTblusuariosCreaActividadCollection.add(tblusuariosCreaActividadCollectionTblusuariosCreaActividadToAttach);
            }
            tblusuarios.setTblusuariosCreaActividadCollection(attachedTblusuariosCreaActividadCollection);
            em.persist(tblusuarios);
            if (idPerfilTiene != null) {
                idPerfilTiene.getTblusuariosCollection().add(tblusuarios);
                idPerfilTiene = em.merge(idPerfilTiene);
            }
            for (TblusuariosCreaUsuarios tblusuariosCreaUsuariosCollectionTblusuariosCreaUsuarios : tblusuarios.getTblusuariosCreaUsuariosCollection()) {
                Tblusuarios oldIdUsuarioCreadorOfTblusuariosCreaUsuariosCollectionTblusuariosCreaUsuarios = tblusuariosCreaUsuariosCollectionTblusuariosCreaUsuarios.getIdUsuarioCreador();
                tblusuariosCreaUsuariosCollectionTblusuariosCreaUsuarios.setIdUsuarioCreador(tblusuarios);
                tblusuariosCreaUsuariosCollectionTblusuariosCreaUsuarios = em.merge(tblusuariosCreaUsuariosCollectionTblusuariosCreaUsuarios);
                if (oldIdUsuarioCreadorOfTblusuariosCreaUsuariosCollectionTblusuariosCreaUsuarios != null) {
                    oldIdUsuarioCreadorOfTblusuariosCreaUsuariosCollectionTblusuariosCreaUsuarios.getTblusuariosCreaUsuariosCollection().remove(tblusuariosCreaUsuariosCollectionTblusuariosCreaUsuarios);
                    oldIdUsuarioCreadorOfTblusuariosCreaUsuariosCollectionTblusuariosCreaUsuarios = em.merge(oldIdUsuarioCreadorOfTblusuariosCreaUsuariosCollectionTblusuariosCreaUsuarios);
                }
            }
            for (TblusuariosCreaUsuarios tblusuariosCreaUsuariosCollection1TblusuariosCreaUsuarios : tblusuarios.getTblusuariosCreaUsuariosCollection1()) {
                Tblusuarios oldTblusuariosIdUsuarioOfTblusuariosCreaUsuariosCollection1TblusuariosCreaUsuarios = tblusuariosCreaUsuariosCollection1TblusuariosCreaUsuarios.getTblusuariosIdUsuario();
                tblusuariosCreaUsuariosCollection1TblusuariosCreaUsuarios.setTblusuariosIdUsuario(tblusuarios);
                tblusuariosCreaUsuariosCollection1TblusuariosCreaUsuarios = em.merge(tblusuariosCreaUsuariosCollection1TblusuariosCreaUsuarios);
                if (oldTblusuariosIdUsuarioOfTblusuariosCreaUsuariosCollection1TblusuariosCreaUsuarios != null) {
                    oldTblusuariosIdUsuarioOfTblusuariosCreaUsuariosCollection1TblusuariosCreaUsuarios.getTblusuariosCreaUsuariosCollection1().remove(tblusuariosCreaUsuariosCollection1TblusuariosCreaUsuarios);
                    oldTblusuariosIdUsuarioOfTblusuariosCreaUsuariosCollection1TblusuariosCreaUsuarios = em.merge(oldTblusuariosIdUsuarioOfTblusuariosCreaUsuariosCollection1TblusuariosCreaUsuarios);
                }
            }
            for (Tblbitacoradiaria tblbitacoradiariaCollectionTblbitacoradiaria : tblusuarios.getTblbitacoradiariaCollection()) {
                Tblusuarios oldIdUsuarioCreoOfTblbitacoradiariaCollectionTblbitacoradiaria = tblbitacoradiariaCollectionTblbitacoradiaria.getIdUsuarioCreo();
                tblbitacoradiariaCollectionTblbitacoradiaria.setIdUsuarioCreo(tblusuarios);
                tblbitacoradiariaCollectionTblbitacoradiaria = em.merge(tblbitacoradiariaCollectionTblbitacoradiaria);
                if (oldIdUsuarioCreoOfTblbitacoradiariaCollectionTblbitacoradiaria != null) {
                    oldIdUsuarioCreoOfTblbitacoradiariaCollectionTblbitacoradiaria.getTblbitacoradiariaCollection().remove(tblbitacoradiariaCollectionTblbitacoradiaria);
                    oldIdUsuarioCreoOfTblbitacoradiariaCollectionTblbitacoradiaria = em.merge(oldIdUsuarioCreoOfTblbitacoradiariaCollectionTblbitacoradiaria);
                }
            }
            for (TblusuariosCreaPerfiles tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles : tblusuarios.getTblusuariosCreaPerfilesCollection()) {
                Tblusuarios oldIdUsuarioCreaOfTblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles = tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles.getIdUsuarioCrea();
                tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles.setIdUsuarioCrea(tblusuarios);
                tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles = em.merge(tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles);
                if (oldIdUsuarioCreaOfTblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles != null) {
                    oldIdUsuarioCreaOfTblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles.getTblusuariosCreaPerfilesCollection().remove(tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles);
                    oldIdUsuarioCreaOfTblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles = em.merge(oldIdUsuarioCreaOfTblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles);
                }
            }
            for (TblusuariosCreaTblpermisos tblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisos : tblusuarios.getTblusuariosCreaTblpermisosCollection()) {
                Tblusuarios oldIdUsuarioCreaOfTblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisos = tblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisos.getIdUsuarioCrea();
                tblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisos.setIdUsuarioCrea(tblusuarios);
                tblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisos = em.merge(tblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisos);
                if (oldIdUsuarioCreaOfTblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisos != null) {
                    oldIdUsuarioCreaOfTblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisos.getTblusuariosCreaTblpermisosCollection().remove(tblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisos);
                    oldIdUsuarioCreaOfTblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisos = em.merge(oldIdUsuarioCreaOfTblusuariosCreaTblpermisosCollectionTblusuariosCreaTblpermisos);
                }
            }
            for (TblusuariosCreaCategorias tblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias : tblusuarios.getTblusuariosCreaCategoriasCollection()) {
                Tblusuarios oldIdUsuarioCreaOfTblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias = tblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias.getIdUsuarioCrea();
                tblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias.setIdUsuarioCrea(tblusuarios);
                tblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias = em.merge(tblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias);
                if (oldIdUsuarioCreaOfTblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias != null) {
                    oldIdUsuarioCreaOfTblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias.getTblusuariosCreaCategoriasCollection().remove(tblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias);
                    oldIdUsuarioCreaOfTblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias = em.merge(oldIdUsuarioCreaOfTblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias);
                }
            }
            for (TblusuariosCreaInterrupciones tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones : tblusuarios.getTblusuariosCreaInterrupcionesCollection()) {
                Tblusuarios oldIdUsuarioCreaOfTblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones = tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones.getIdUsuarioCrea();
                tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones.setIdUsuarioCrea(tblusuarios);
                tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones = em.merge(tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones);
                if (oldIdUsuarioCreaOfTblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones != null) {
                    oldIdUsuarioCreaOfTblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones.getTblusuariosCreaInterrupcionesCollection().remove(tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones);
                    oldIdUsuarioCreaOfTblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones = em.merge(oldIdUsuarioCreaOfTblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones);
                }
            }
            for (TblusuariosCreaActividad tblusuariosCreaActividadCollectionTblusuariosCreaActividad : tblusuarios.getTblusuariosCreaActividadCollection()) {
                Tblusuarios oldIdUsuarioCreaOfTblusuariosCreaActividadCollectionTblusuariosCreaActividad = tblusuariosCreaActividadCollectionTblusuariosCreaActividad.getIdUsuarioCrea();
                tblusuariosCreaActividadCollectionTblusuariosCreaActividad.setIdUsuarioCrea(tblusuarios);
                tblusuariosCreaActividadCollectionTblusuariosCreaActividad = em.merge(tblusuariosCreaActividadCollectionTblusuariosCreaActividad);
                if (oldIdUsuarioCreaOfTblusuariosCreaActividadCollectionTblusuariosCreaActividad != null) {
                    oldIdUsuarioCreaOfTblusuariosCreaActividadCollectionTblusuariosCreaActividad.getTblusuariosCreaActividadCollection().remove(tblusuariosCreaActividadCollectionTblusuariosCreaActividad);
                    oldIdUsuarioCreaOfTblusuariosCreaActividadCollectionTblusuariosCreaActividad = em.merge(oldIdUsuarioCreaOfTblusuariosCreaActividadCollectionTblusuariosCreaActividad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblusuarios tblusuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblusuarios persistentTblusuarios = em.find(Tblusuarios.class, tblusuarios.getIdUsuario());
            Tblperfiles idPerfilTieneOld = persistentTblusuarios.getIdPerfilTiene();
            Tblperfiles idPerfilTieneNew = tblusuarios.getIdPerfilTiene();
            Collection<TblusuariosCreaUsuarios> tblusuariosCreaUsuariosCollectionOld = persistentTblusuarios.getTblusuariosCreaUsuariosCollection();
            Collection<TblusuariosCreaUsuarios> tblusuariosCreaUsuariosCollectionNew = tblusuarios.getTblusuariosCreaUsuariosCollection();
            Collection<TblusuariosCreaUsuarios> tblusuariosCreaUsuariosCollection1Old = persistentTblusuarios.getTblusuariosCreaUsuariosCollection1();
            Collection<TblusuariosCreaUsuarios> tblusuariosCreaUsuariosCollection1New = tblusuarios.getTblusuariosCreaUsuariosCollection1();
            Collection<Tblbitacoradiaria> tblbitacoradiariaCollectionOld = persistentTblusuarios.getTblbitacoradiariaCollection();
            Collection<Tblbitacoradiaria> tblbitacoradiariaCollectionNew = tblusuarios.getTblbitacoradiariaCollection();
            Collection<TblusuariosCreaPerfiles> tblusuariosCreaPerfilesCollectionOld = persistentTblusuarios.getTblusuariosCreaPerfilesCollection();
            Collection<TblusuariosCreaPerfiles> tblusuariosCreaPerfilesCollectionNew = tblusuarios.getTblusuariosCreaPerfilesCollection();
            Collection<TblusuariosCreaTblpermisos> tblusuariosCreaTblpermisosCollectionOld = persistentTblusuarios.getTblusuariosCreaTblpermisosCollection();
            Collection<TblusuariosCreaTblpermisos> tblusuariosCreaTblpermisosCollectionNew = tblusuarios.getTblusuariosCreaTblpermisosCollection();
            Collection<TblusuariosCreaCategorias> tblusuariosCreaCategoriasCollectionOld = persistentTblusuarios.getTblusuariosCreaCategoriasCollection();
            Collection<TblusuariosCreaCategorias> tblusuariosCreaCategoriasCollectionNew = tblusuarios.getTblusuariosCreaCategoriasCollection();
            Collection<TblusuariosCreaInterrupciones> tblusuariosCreaInterrupcionesCollectionOld = persistentTblusuarios.getTblusuariosCreaInterrupcionesCollection();
            Collection<TblusuariosCreaInterrupciones> tblusuariosCreaInterrupcionesCollectionNew = tblusuarios.getTblusuariosCreaInterrupcionesCollection();
            Collection<TblusuariosCreaActividad> tblusuariosCreaActividadCollectionOld = persistentTblusuarios.getTblusuariosCreaActividadCollection();
            Collection<TblusuariosCreaActividad> tblusuariosCreaActividadCollectionNew = tblusuarios.getTblusuariosCreaActividadCollection();
            List<String> illegalOrphanMessages = null;
            for (TblusuariosCreaUsuarios tblusuariosCreaUsuariosCollectionOldTblusuariosCreaUsuarios : tblusuariosCreaUsuariosCollectionOld) {
                if (!tblusuariosCreaUsuariosCollectionNew.contains(tblusuariosCreaUsuariosCollectionOldTblusuariosCreaUsuarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TblusuariosCreaUsuarios " + tblusuariosCreaUsuariosCollectionOldTblusuariosCreaUsuarios + " since its idUsuarioCreador field is not nullable.");
                }
            }
            for (TblusuariosCreaUsuarios tblusuariosCreaUsuariosCollection1OldTblusuariosCreaUsuarios : tblusuariosCreaUsuariosCollection1Old) {
                if (!tblusuariosCreaUsuariosCollection1New.contains(tblusuariosCreaUsuariosCollection1OldTblusuariosCreaUsuarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TblusuariosCreaUsuarios " + tblusuariosCreaUsuariosCollection1OldTblusuariosCreaUsuarios + " since its tblusuariosIdUsuario field is not nullable.");
                }
            }
            for (Tblbitacoradiaria tblbitacoradiariaCollectionOldTblbitacoradiaria : tblbitacoradiariaCollectionOld) {
                if (!tblbitacoradiariaCollectionNew.contains(tblbitacoradiariaCollectionOldTblbitacoradiaria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblbitacoradiaria " + tblbitacoradiariaCollectionOldTblbitacoradiaria + " since its idUsuarioCreo field is not nullable.");
                }
            }
            for (TblusuariosCreaPerfiles tblusuariosCreaPerfilesCollectionOldTblusuariosCreaPerfiles : tblusuariosCreaPerfilesCollectionOld) {
                if (!tblusuariosCreaPerfilesCollectionNew.contains(tblusuariosCreaPerfilesCollectionOldTblusuariosCreaPerfiles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TblusuariosCreaPerfiles " + tblusuariosCreaPerfilesCollectionOldTblusuariosCreaPerfiles + " since its idUsuarioCrea field is not nullable.");
                }
            }
            for (TblusuariosCreaTblpermisos tblusuariosCreaTblpermisosCollectionOldTblusuariosCreaTblpermisos : tblusuariosCreaTblpermisosCollectionOld) {
                if (!tblusuariosCreaTblpermisosCollectionNew.contains(tblusuariosCreaTblpermisosCollectionOldTblusuariosCreaTblpermisos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TblusuariosCreaTblpermisos " + tblusuariosCreaTblpermisosCollectionOldTblusuariosCreaTblpermisos + " since its idUsuarioCrea field is not nullable.");
                }
            }
            for (TblusuariosCreaCategorias tblusuariosCreaCategoriasCollectionOldTblusuariosCreaCategorias : tblusuariosCreaCategoriasCollectionOld) {
                if (!tblusuariosCreaCategoriasCollectionNew.contains(tblusuariosCreaCategoriasCollectionOldTblusuariosCreaCategorias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TblusuariosCreaCategorias " + tblusuariosCreaCategoriasCollectionOldTblusuariosCreaCategorias + " since its idUsuarioCrea field is not nullable.");
                }
            }
            for (TblusuariosCreaInterrupciones tblusuariosCreaInterrupcionesCollectionOldTblusuariosCreaInterrupciones : tblusuariosCreaInterrupcionesCollectionOld) {
                if (!tblusuariosCreaInterrupcionesCollectionNew.contains(tblusuariosCreaInterrupcionesCollectionOldTblusuariosCreaInterrupciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TblusuariosCreaInterrupciones " + tblusuariosCreaInterrupcionesCollectionOldTblusuariosCreaInterrupciones + " since its idUsuarioCrea field is not nullable.");
                }
            }
            for (TblusuariosCreaActividad tblusuariosCreaActividadCollectionOldTblusuariosCreaActividad : tblusuariosCreaActividadCollectionOld) {
                if (!tblusuariosCreaActividadCollectionNew.contains(tblusuariosCreaActividadCollectionOldTblusuariosCreaActividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TblusuariosCreaActividad " + tblusuariosCreaActividadCollectionOldTblusuariosCreaActividad + " since its idUsuarioCrea field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPerfilTieneNew != null) {
                idPerfilTieneNew = em.getReference(idPerfilTieneNew.getClass(), idPerfilTieneNew.getIdPerfil());
                tblusuarios.setIdPerfilTiene(idPerfilTieneNew);
            }
            Collection<TblusuariosCreaUsuarios> attachedTblusuariosCreaUsuariosCollectionNew = new ArrayList<TblusuariosCreaUsuarios>();
            for (TblusuariosCreaUsuarios tblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuariosToAttach : tblusuariosCreaUsuariosCollectionNew) {
                tblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuariosToAttach = em.getReference(tblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuariosToAttach.getClass(), tblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuariosToAttach.getIdCreador());
                attachedTblusuariosCreaUsuariosCollectionNew.add(tblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuariosToAttach);
            }
            tblusuariosCreaUsuariosCollectionNew = attachedTblusuariosCreaUsuariosCollectionNew;
            tblusuarios.setTblusuariosCreaUsuariosCollection(tblusuariosCreaUsuariosCollectionNew);
            Collection<TblusuariosCreaUsuarios> attachedTblusuariosCreaUsuariosCollection1New = new ArrayList<TblusuariosCreaUsuarios>();
            for (TblusuariosCreaUsuarios tblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuariosToAttach : tblusuariosCreaUsuariosCollection1New) {
                tblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuariosToAttach = em.getReference(tblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuariosToAttach.getClass(), tblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuariosToAttach.getIdCreador());
                attachedTblusuariosCreaUsuariosCollection1New.add(tblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuariosToAttach);
            }
            tblusuariosCreaUsuariosCollection1New = attachedTblusuariosCreaUsuariosCollection1New;
            tblusuarios.setTblusuariosCreaUsuariosCollection1(tblusuariosCreaUsuariosCollection1New);
            Collection<Tblbitacoradiaria> attachedTblbitacoradiariaCollectionNew = new ArrayList<Tblbitacoradiaria>();
            for (Tblbitacoradiaria tblbitacoradiariaCollectionNewTblbitacoradiariaToAttach : tblbitacoradiariaCollectionNew) {
                tblbitacoradiariaCollectionNewTblbitacoradiariaToAttach = em.getReference(tblbitacoradiariaCollectionNewTblbitacoradiariaToAttach.getClass(), tblbitacoradiariaCollectionNewTblbitacoradiariaToAttach.getIdBitacora());
                attachedTblbitacoradiariaCollectionNew.add(tblbitacoradiariaCollectionNewTblbitacoradiariaToAttach);
            }
            tblbitacoradiariaCollectionNew = attachedTblbitacoradiariaCollectionNew;
            tblusuarios.setTblbitacoradiariaCollection(tblbitacoradiariaCollectionNew);
            Collection<TblusuariosCreaPerfiles> attachedTblusuariosCreaPerfilesCollectionNew = new ArrayList<TblusuariosCreaPerfiles>();
            for (TblusuariosCreaPerfiles tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfilesToAttach : tblusuariosCreaPerfilesCollectionNew) {
                tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfilesToAttach = em.getReference(tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfilesToAttach.getClass(), tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfilesToAttach.getIdCreador());
                attachedTblusuariosCreaPerfilesCollectionNew.add(tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfilesToAttach);
            }
            tblusuariosCreaPerfilesCollectionNew = attachedTblusuariosCreaPerfilesCollectionNew;
            tblusuarios.setTblusuariosCreaPerfilesCollection(tblusuariosCreaPerfilesCollectionNew);
            Collection<TblusuariosCreaTblpermisos> attachedTblusuariosCreaTblpermisosCollectionNew = new ArrayList<TblusuariosCreaTblpermisos>();
            for (TblusuariosCreaTblpermisos tblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisosToAttach : tblusuariosCreaTblpermisosCollectionNew) {
                tblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisosToAttach = em.getReference(tblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisosToAttach.getClass(), tblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisosToAttach.getIdCreador());
                attachedTblusuariosCreaTblpermisosCollectionNew.add(tblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisosToAttach);
            }
            tblusuariosCreaTblpermisosCollectionNew = attachedTblusuariosCreaTblpermisosCollectionNew;
            tblusuarios.setTblusuariosCreaTblpermisosCollection(tblusuariosCreaTblpermisosCollectionNew);
            Collection<TblusuariosCreaCategorias> attachedTblusuariosCreaCategoriasCollectionNew = new ArrayList<TblusuariosCreaCategorias>();
            for (TblusuariosCreaCategorias tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategoriasToAttach : tblusuariosCreaCategoriasCollectionNew) {
                tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategoriasToAttach = em.getReference(tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategoriasToAttach.getClass(), tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategoriasToAttach.getIdCreador());
                attachedTblusuariosCreaCategoriasCollectionNew.add(tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategoriasToAttach);
            }
            tblusuariosCreaCategoriasCollectionNew = attachedTblusuariosCreaCategoriasCollectionNew;
            tblusuarios.setTblusuariosCreaCategoriasCollection(tblusuariosCreaCategoriasCollectionNew);
            Collection<TblusuariosCreaInterrupciones> attachedTblusuariosCreaInterrupcionesCollectionNew = new ArrayList<TblusuariosCreaInterrupciones>();
            for (TblusuariosCreaInterrupciones tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupcionesToAttach : tblusuariosCreaInterrupcionesCollectionNew) {
                tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupcionesToAttach = em.getReference(tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupcionesToAttach.getClass(), tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupcionesToAttach.getIdCreador());
                attachedTblusuariosCreaInterrupcionesCollectionNew.add(tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupcionesToAttach);
            }
            tblusuariosCreaInterrupcionesCollectionNew = attachedTblusuariosCreaInterrupcionesCollectionNew;
            tblusuarios.setTblusuariosCreaInterrupcionesCollection(tblusuariosCreaInterrupcionesCollectionNew);
            Collection<TblusuariosCreaActividad> attachedTblusuariosCreaActividadCollectionNew = new ArrayList<TblusuariosCreaActividad>();
            for (TblusuariosCreaActividad tblusuariosCreaActividadCollectionNewTblusuariosCreaActividadToAttach : tblusuariosCreaActividadCollectionNew) {
                tblusuariosCreaActividadCollectionNewTblusuariosCreaActividadToAttach = em.getReference(tblusuariosCreaActividadCollectionNewTblusuariosCreaActividadToAttach.getClass(), tblusuariosCreaActividadCollectionNewTblusuariosCreaActividadToAttach.getIdCreador());
                attachedTblusuariosCreaActividadCollectionNew.add(tblusuariosCreaActividadCollectionNewTblusuariosCreaActividadToAttach);
            }
            tblusuariosCreaActividadCollectionNew = attachedTblusuariosCreaActividadCollectionNew;
            tblusuarios.setTblusuariosCreaActividadCollection(tblusuariosCreaActividadCollectionNew);
            tblusuarios = em.merge(tblusuarios);
            if (idPerfilTieneOld != null && !idPerfilTieneOld.equals(idPerfilTieneNew)) {
                idPerfilTieneOld.getTblusuariosCollection().remove(tblusuarios);
                idPerfilTieneOld = em.merge(idPerfilTieneOld);
            }
            if (idPerfilTieneNew != null && !idPerfilTieneNew.equals(idPerfilTieneOld)) {
                idPerfilTieneNew.getTblusuariosCollection().add(tblusuarios);
                idPerfilTieneNew = em.merge(idPerfilTieneNew);
            }
            for (TblusuariosCreaUsuarios tblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios : tblusuariosCreaUsuariosCollectionNew) {
                if (!tblusuariosCreaUsuariosCollectionOld.contains(tblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios)) {
                    Tblusuarios oldIdUsuarioCreadorOfTblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios = tblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios.getIdUsuarioCreador();
                    tblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios.setIdUsuarioCreador(tblusuarios);
                    tblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios = em.merge(tblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios);
                    if (oldIdUsuarioCreadorOfTblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios != null && !oldIdUsuarioCreadorOfTblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios.equals(tblusuarios)) {
                        oldIdUsuarioCreadorOfTblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios.getTblusuariosCreaUsuariosCollection().remove(tblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios);
                        oldIdUsuarioCreadorOfTblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios = em.merge(oldIdUsuarioCreadorOfTblusuariosCreaUsuariosCollectionNewTblusuariosCreaUsuarios);
                    }
                }
            }
            for (TblusuariosCreaUsuarios tblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios : tblusuariosCreaUsuariosCollection1New) {
                if (!tblusuariosCreaUsuariosCollection1Old.contains(tblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios)) {
                    Tblusuarios oldTblusuariosIdUsuarioOfTblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios = tblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios.getTblusuariosIdUsuario();
                    tblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios.setTblusuariosIdUsuario(tblusuarios);
                    tblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios = em.merge(tblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios);
                    if (oldTblusuariosIdUsuarioOfTblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios != null && !oldTblusuariosIdUsuarioOfTblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios.equals(tblusuarios)) {
                        oldTblusuariosIdUsuarioOfTblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios.getTblusuariosCreaUsuariosCollection1().remove(tblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios);
                        oldTblusuariosIdUsuarioOfTblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios = em.merge(oldTblusuariosIdUsuarioOfTblusuariosCreaUsuariosCollection1NewTblusuariosCreaUsuarios);
                    }
                }
            }
            for (Tblbitacoradiaria tblbitacoradiariaCollectionNewTblbitacoradiaria : tblbitacoradiariaCollectionNew) {
                if (!tblbitacoradiariaCollectionOld.contains(tblbitacoradiariaCollectionNewTblbitacoradiaria)) {
                    Tblusuarios oldIdUsuarioCreoOfTblbitacoradiariaCollectionNewTblbitacoradiaria = tblbitacoradiariaCollectionNewTblbitacoradiaria.getIdUsuarioCreo();
                    tblbitacoradiariaCollectionNewTblbitacoradiaria.setIdUsuarioCreo(tblusuarios);
                    tblbitacoradiariaCollectionNewTblbitacoradiaria = em.merge(tblbitacoradiariaCollectionNewTblbitacoradiaria);
                    if (oldIdUsuarioCreoOfTblbitacoradiariaCollectionNewTblbitacoradiaria != null && !oldIdUsuarioCreoOfTblbitacoradiariaCollectionNewTblbitacoradiaria.equals(tblusuarios)) {
                        oldIdUsuarioCreoOfTblbitacoradiariaCollectionNewTblbitacoradiaria.getTblbitacoradiariaCollection().remove(tblbitacoradiariaCollectionNewTblbitacoradiaria);
                        oldIdUsuarioCreoOfTblbitacoradiariaCollectionNewTblbitacoradiaria = em.merge(oldIdUsuarioCreoOfTblbitacoradiariaCollectionNewTblbitacoradiaria);
                    }
                }
            }
            for (TblusuariosCreaPerfiles tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles : tblusuariosCreaPerfilesCollectionNew) {
                if (!tblusuariosCreaPerfilesCollectionOld.contains(tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles)) {
                    Tblusuarios oldIdUsuarioCreaOfTblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles = tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles.getIdUsuarioCrea();
                    tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles.setIdUsuarioCrea(tblusuarios);
                    tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles = em.merge(tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles);
                    if (oldIdUsuarioCreaOfTblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles != null && !oldIdUsuarioCreaOfTblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles.equals(tblusuarios)) {
                        oldIdUsuarioCreaOfTblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles.getTblusuariosCreaPerfilesCollection().remove(tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles);
                        oldIdUsuarioCreaOfTblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles = em.merge(oldIdUsuarioCreaOfTblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles);
                    }
                }
            }
            for (TblusuariosCreaTblpermisos tblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos : tblusuariosCreaTblpermisosCollectionNew) {
                if (!tblusuariosCreaTblpermisosCollectionOld.contains(tblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos)) {
                    Tblusuarios oldIdUsuarioCreaOfTblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos = tblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos.getIdUsuarioCrea();
                    tblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos.setIdUsuarioCrea(tblusuarios);
                    tblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos = em.merge(tblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos);
                    if (oldIdUsuarioCreaOfTblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos != null && !oldIdUsuarioCreaOfTblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos.equals(tblusuarios)) {
                        oldIdUsuarioCreaOfTblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos.getTblusuariosCreaTblpermisosCollection().remove(tblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos);
                        oldIdUsuarioCreaOfTblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos = em.merge(oldIdUsuarioCreaOfTblusuariosCreaTblpermisosCollectionNewTblusuariosCreaTblpermisos);
                    }
                }
            }
            for (TblusuariosCreaCategorias tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias : tblusuariosCreaCategoriasCollectionNew) {
                if (!tblusuariosCreaCategoriasCollectionOld.contains(tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias)) {
                    Tblusuarios oldIdUsuarioCreaOfTblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias = tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias.getIdUsuarioCrea();
                    tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias.setIdUsuarioCrea(tblusuarios);
                    tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias = em.merge(tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias);
                    if (oldIdUsuarioCreaOfTblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias != null && !oldIdUsuarioCreaOfTblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias.equals(tblusuarios)) {
                        oldIdUsuarioCreaOfTblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias.getTblusuariosCreaCategoriasCollection().remove(tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias);
                        oldIdUsuarioCreaOfTblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias = em.merge(oldIdUsuarioCreaOfTblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias);
                    }
                }
            }
            for (TblusuariosCreaInterrupciones tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones : tblusuariosCreaInterrupcionesCollectionNew) {
                if (!tblusuariosCreaInterrupcionesCollectionOld.contains(tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones)) {
                    Tblusuarios oldIdUsuarioCreaOfTblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones = tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones.getIdUsuarioCrea();
                    tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones.setIdUsuarioCrea(tblusuarios);
                    tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones = em.merge(tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones);
                    if (oldIdUsuarioCreaOfTblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones != null && !oldIdUsuarioCreaOfTblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones.equals(tblusuarios)) {
                        oldIdUsuarioCreaOfTblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones.getTblusuariosCreaInterrupcionesCollection().remove(tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones);
                        oldIdUsuarioCreaOfTblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones = em.merge(oldIdUsuarioCreaOfTblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones);
                    }
                }
            }
            for (TblusuariosCreaActividad tblusuariosCreaActividadCollectionNewTblusuariosCreaActividad : tblusuariosCreaActividadCollectionNew) {
                if (!tblusuariosCreaActividadCollectionOld.contains(tblusuariosCreaActividadCollectionNewTblusuariosCreaActividad)) {
                    Tblusuarios oldIdUsuarioCreaOfTblusuariosCreaActividadCollectionNewTblusuariosCreaActividad = tblusuariosCreaActividadCollectionNewTblusuariosCreaActividad.getIdUsuarioCrea();
                    tblusuariosCreaActividadCollectionNewTblusuariosCreaActividad.setIdUsuarioCrea(tblusuarios);
                    tblusuariosCreaActividadCollectionNewTblusuariosCreaActividad = em.merge(tblusuariosCreaActividadCollectionNewTblusuariosCreaActividad);
                    if (oldIdUsuarioCreaOfTblusuariosCreaActividadCollectionNewTblusuariosCreaActividad != null && !oldIdUsuarioCreaOfTblusuariosCreaActividadCollectionNewTblusuariosCreaActividad.equals(tblusuarios)) {
                        oldIdUsuarioCreaOfTblusuariosCreaActividadCollectionNewTblusuariosCreaActividad.getTblusuariosCreaActividadCollection().remove(tblusuariosCreaActividadCollectionNewTblusuariosCreaActividad);
                        oldIdUsuarioCreaOfTblusuariosCreaActividadCollectionNewTblusuariosCreaActividad = em.merge(oldIdUsuarioCreaOfTblusuariosCreaActividadCollectionNewTblusuariosCreaActividad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblusuarios.getIdUsuario();
                if (findTblusuarios(id) == null) {
                    throw new NonexistentEntityException("The tblusuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblusuarios tblusuarios;
            try {
                tblusuarios = em.getReference(Tblusuarios.class, id);
                tblusuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblusuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TblusuariosCreaUsuarios> tblusuariosCreaUsuariosCollectionOrphanCheck = tblusuarios.getTblusuariosCreaUsuariosCollection();
            for (TblusuariosCreaUsuarios tblusuariosCreaUsuariosCollectionOrphanCheckTblusuariosCreaUsuarios : tblusuariosCreaUsuariosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblusuarios (" + tblusuarios + ") cannot be destroyed since the TblusuariosCreaUsuarios " + tblusuariosCreaUsuariosCollectionOrphanCheckTblusuariosCreaUsuarios + " in its tblusuariosCreaUsuariosCollection field has a non-nullable idUsuarioCreador field.");
            }
            Collection<TblusuariosCreaUsuarios> tblusuariosCreaUsuariosCollection1OrphanCheck = tblusuarios.getTblusuariosCreaUsuariosCollection1();
            for (TblusuariosCreaUsuarios tblusuariosCreaUsuariosCollection1OrphanCheckTblusuariosCreaUsuarios : tblusuariosCreaUsuariosCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblusuarios (" + tblusuarios + ") cannot be destroyed since the TblusuariosCreaUsuarios " + tblusuariosCreaUsuariosCollection1OrphanCheckTblusuariosCreaUsuarios + " in its tblusuariosCreaUsuariosCollection1 field has a non-nullable tblusuariosIdUsuario field.");
            }
            Collection<Tblbitacoradiaria> tblbitacoradiariaCollectionOrphanCheck = tblusuarios.getTblbitacoradiariaCollection();
            for (Tblbitacoradiaria tblbitacoradiariaCollectionOrphanCheckTblbitacoradiaria : tblbitacoradiariaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblusuarios (" + tblusuarios + ") cannot be destroyed since the Tblbitacoradiaria " + tblbitacoradiariaCollectionOrphanCheckTblbitacoradiaria + " in its tblbitacoradiariaCollection field has a non-nullable idUsuarioCreo field.");
            }
            Collection<TblusuariosCreaPerfiles> tblusuariosCreaPerfilesCollectionOrphanCheck = tblusuarios.getTblusuariosCreaPerfilesCollection();
            for (TblusuariosCreaPerfiles tblusuariosCreaPerfilesCollectionOrphanCheckTblusuariosCreaPerfiles : tblusuariosCreaPerfilesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblusuarios (" + tblusuarios + ") cannot be destroyed since the TblusuariosCreaPerfiles " + tblusuariosCreaPerfilesCollectionOrphanCheckTblusuariosCreaPerfiles + " in its tblusuariosCreaPerfilesCollection field has a non-nullable idUsuarioCrea field.");
            }
            Collection<TblusuariosCreaTblpermisos> tblusuariosCreaTblpermisosCollectionOrphanCheck = tblusuarios.getTblusuariosCreaTblpermisosCollection();
            for (TblusuariosCreaTblpermisos tblusuariosCreaTblpermisosCollectionOrphanCheckTblusuariosCreaTblpermisos : tblusuariosCreaTblpermisosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblusuarios (" + tblusuarios + ") cannot be destroyed since the TblusuariosCreaTblpermisos " + tblusuariosCreaTblpermisosCollectionOrphanCheckTblusuariosCreaTblpermisos + " in its tblusuariosCreaTblpermisosCollection field has a non-nullable idUsuarioCrea field.");
            }
            Collection<TblusuariosCreaCategorias> tblusuariosCreaCategoriasCollectionOrphanCheck = tblusuarios.getTblusuariosCreaCategoriasCollection();
            for (TblusuariosCreaCategorias tblusuariosCreaCategoriasCollectionOrphanCheckTblusuariosCreaCategorias : tblusuariosCreaCategoriasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblusuarios (" + tblusuarios + ") cannot be destroyed since the TblusuariosCreaCategorias " + tblusuariosCreaCategoriasCollectionOrphanCheckTblusuariosCreaCategorias + " in its tblusuariosCreaCategoriasCollection field has a non-nullable idUsuarioCrea field.");
            }
            Collection<TblusuariosCreaInterrupciones> tblusuariosCreaInterrupcionesCollectionOrphanCheck = tblusuarios.getTblusuariosCreaInterrupcionesCollection();
            for (TblusuariosCreaInterrupciones tblusuariosCreaInterrupcionesCollectionOrphanCheckTblusuariosCreaInterrupciones : tblusuariosCreaInterrupcionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblusuarios (" + tblusuarios + ") cannot be destroyed since the TblusuariosCreaInterrupciones " + tblusuariosCreaInterrupcionesCollectionOrphanCheckTblusuariosCreaInterrupciones + " in its tblusuariosCreaInterrupcionesCollection field has a non-nullable idUsuarioCrea field.");
            }
            Collection<TblusuariosCreaActividad> tblusuariosCreaActividadCollectionOrphanCheck = tblusuarios.getTblusuariosCreaActividadCollection();
            for (TblusuariosCreaActividad tblusuariosCreaActividadCollectionOrphanCheckTblusuariosCreaActividad : tblusuariosCreaActividadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblusuarios (" + tblusuarios + ") cannot be destroyed since the TblusuariosCreaActividad " + tblusuariosCreaActividadCollectionOrphanCheckTblusuariosCreaActividad + " in its tblusuariosCreaActividadCollection field has a non-nullable idUsuarioCrea field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tblperfiles idPerfilTiene = tblusuarios.getIdPerfilTiene();
            if (idPerfilTiene != null) {
                idPerfilTiene.getTblusuariosCollection().remove(tblusuarios);
                idPerfilTiene = em.merge(idPerfilTiene);
            }
            em.remove(tblusuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblusuarios> findTblusuariosEntities() {
        return findTblusuariosEntities(true, -1, -1);
    }

    public List<Tblusuarios> findTblusuariosEntities(int maxResults, int firstResult) {
        return findTblusuariosEntities(false, maxResults, firstResult);
    }

    private List<Tblusuarios> findTblusuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tblusuarios as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tblusuarios findTblusuarios(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblusuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblusuariosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Tblusuarios as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
