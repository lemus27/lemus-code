/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.IllegalOrphanException;
import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.model.Tblperfiles;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblpermisos;
import java.util.ArrayList;
import java.util.Collection;
import com.mgl.bitacoradiaria.model.TblusuariosCreaPerfiles;
import com.mgl.bitacoradiaria.model.Tblusuarios;

/**
 *
 * @author mike
 */
public class TblperfilesJpaController {

    public TblperfilesJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblperfiles tblperfiles) {
        if (tblperfiles.getTblpermisosCollection() == null) {
            tblperfiles.setTblpermisosCollection(new ArrayList<Tblpermisos>());
        }
        if (tblperfiles.getTblusuariosCreaPerfilesCollection() == null) {
            tblperfiles.setTblusuariosCreaPerfilesCollection(new ArrayList<TblusuariosCreaPerfiles>());
        }
        if (tblperfiles.getTblusuariosCollection() == null) {
            tblperfiles.setTblusuariosCollection(new ArrayList<Tblusuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tblpermisos> attachedTblpermisosCollection = new ArrayList<Tblpermisos>();
            for (Tblpermisos tblpermisosCollectionTblpermisosToAttach : tblperfiles.getTblpermisosCollection()) {
                tblpermisosCollectionTblpermisosToAttach = em.getReference(tblpermisosCollectionTblpermisosToAttach.getClass(), tblpermisosCollectionTblpermisosToAttach.getIdPermiso());
                attachedTblpermisosCollection.add(tblpermisosCollectionTblpermisosToAttach);
            }
            tblperfiles.setTblpermisosCollection(attachedTblpermisosCollection);
            Collection<TblusuariosCreaPerfiles> attachedTblusuariosCreaPerfilesCollection = new ArrayList<TblusuariosCreaPerfiles>();
            for (TblusuariosCreaPerfiles tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfilesToAttach : tblperfiles.getTblusuariosCreaPerfilesCollection()) {
                tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfilesToAttach = em.getReference(tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfilesToAttach.getClass(), tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfilesToAttach.getIdCreador());
                attachedTblusuariosCreaPerfilesCollection.add(tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfilesToAttach);
            }
            tblperfiles.setTblusuariosCreaPerfilesCollection(attachedTblusuariosCreaPerfilesCollection);
            Collection<Tblusuarios> attachedTblusuariosCollection = new ArrayList<Tblusuarios>();
            for (Tblusuarios tblusuariosCollectionTblusuariosToAttach : tblperfiles.getTblusuariosCollection()) {
                tblusuariosCollectionTblusuariosToAttach = em.getReference(tblusuariosCollectionTblusuariosToAttach.getClass(), tblusuariosCollectionTblusuariosToAttach.getIdUsuario());
                attachedTblusuariosCollection.add(tblusuariosCollectionTblusuariosToAttach);
            }
            tblperfiles.setTblusuariosCollection(attachedTblusuariosCollection);
            em.persist(tblperfiles);
            for (Tblpermisos tblpermisosCollectionTblpermisos : tblperfiles.getTblpermisosCollection()) {
                tblpermisosCollectionTblpermisos.getTblperfilesCollection().add(tblperfiles);
                tblpermisosCollectionTblpermisos = em.merge(tblpermisosCollectionTblpermisos);
            }
            for (TblusuariosCreaPerfiles tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles : tblperfiles.getTblusuariosCreaPerfilesCollection()) {
                Tblperfiles oldIdPerfilNuevoOfTblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles = tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles.getIdPerfilNuevo();
                tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles.setIdPerfilNuevo(tblperfiles);
                tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles = em.merge(tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles);
                if (oldIdPerfilNuevoOfTblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles != null) {
                    oldIdPerfilNuevoOfTblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles.getTblusuariosCreaPerfilesCollection().remove(tblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles);
                    oldIdPerfilNuevoOfTblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles = em.merge(oldIdPerfilNuevoOfTblusuariosCreaPerfilesCollectionTblusuariosCreaPerfiles);
                }
            }
            for (Tblusuarios tblusuariosCollectionTblusuarios : tblperfiles.getTblusuariosCollection()) {
                Tblperfiles oldIdPerfilTieneOfTblusuariosCollectionTblusuarios = tblusuariosCollectionTblusuarios.getIdPerfilTiene();
                tblusuariosCollectionTblusuarios.setIdPerfilTiene(tblperfiles);
                tblusuariosCollectionTblusuarios = em.merge(tblusuariosCollectionTblusuarios);
                if (oldIdPerfilTieneOfTblusuariosCollectionTblusuarios != null) {
                    oldIdPerfilTieneOfTblusuariosCollectionTblusuarios.getTblusuariosCollection().remove(tblusuariosCollectionTblusuarios);
                    oldIdPerfilTieneOfTblusuariosCollectionTblusuarios = em.merge(oldIdPerfilTieneOfTblusuariosCollectionTblusuarios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblperfiles tblperfiles) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblperfiles persistentTblperfiles = em.find(Tblperfiles.class, tblperfiles.getIdPerfil());
            Collection<Tblpermisos> tblpermisosCollectionOld = persistentTblperfiles.getTblpermisosCollection();
            Collection<Tblpermisos> tblpermisosCollectionNew = tblperfiles.getTblpermisosCollection();
            Collection<TblusuariosCreaPerfiles> tblusuariosCreaPerfilesCollectionOld = persistentTblperfiles.getTblusuariosCreaPerfilesCollection();
            Collection<TblusuariosCreaPerfiles> tblusuariosCreaPerfilesCollectionNew = tblperfiles.getTblusuariosCreaPerfilesCollection();
            Collection<Tblusuarios> tblusuariosCollectionOld = persistentTblperfiles.getTblusuariosCollection();
            Collection<Tblusuarios> tblusuariosCollectionNew = tblperfiles.getTblusuariosCollection();
            List<String> illegalOrphanMessages = null;
            for (TblusuariosCreaPerfiles tblusuariosCreaPerfilesCollectionOldTblusuariosCreaPerfiles : tblusuariosCreaPerfilesCollectionOld) {
                if (!tblusuariosCreaPerfilesCollectionNew.contains(tblusuariosCreaPerfilesCollectionOldTblusuariosCreaPerfiles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TblusuariosCreaPerfiles " + tblusuariosCreaPerfilesCollectionOldTblusuariosCreaPerfiles + " since its idPerfilNuevo field is not nullable.");
                }
            }
            for (Tblusuarios tblusuariosCollectionOldTblusuarios : tblusuariosCollectionOld) {
                if (!tblusuariosCollectionNew.contains(tblusuariosCollectionOldTblusuarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblusuarios " + tblusuariosCollectionOldTblusuarios + " since its idPerfilTiene field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tblpermisos> attachedTblpermisosCollectionNew = new ArrayList<Tblpermisos>();
            for (Tblpermisos tblpermisosCollectionNewTblpermisosToAttach : tblpermisosCollectionNew) {
                tblpermisosCollectionNewTblpermisosToAttach = em.getReference(tblpermisosCollectionNewTblpermisosToAttach.getClass(), tblpermisosCollectionNewTblpermisosToAttach.getIdPermiso());
                attachedTblpermisosCollectionNew.add(tblpermisosCollectionNewTblpermisosToAttach);
            }
            tblpermisosCollectionNew = attachedTblpermisosCollectionNew;
            tblperfiles.setTblpermisosCollection(tblpermisosCollectionNew);
            Collection<TblusuariosCreaPerfiles> attachedTblusuariosCreaPerfilesCollectionNew = new ArrayList<TblusuariosCreaPerfiles>();
            for (TblusuariosCreaPerfiles tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfilesToAttach : tblusuariosCreaPerfilesCollectionNew) {
                tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfilesToAttach = em.getReference(tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfilesToAttach.getClass(), tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfilesToAttach.getIdCreador());
                attachedTblusuariosCreaPerfilesCollectionNew.add(tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfilesToAttach);
            }
            tblusuariosCreaPerfilesCollectionNew = attachedTblusuariosCreaPerfilesCollectionNew;
            tblperfiles.setTblusuariosCreaPerfilesCollection(tblusuariosCreaPerfilesCollectionNew);
            Collection<Tblusuarios> attachedTblusuariosCollectionNew = new ArrayList<Tblusuarios>();
            for (Tblusuarios tblusuariosCollectionNewTblusuariosToAttach : tblusuariosCollectionNew) {
                tblusuariosCollectionNewTblusuariosToAttach = em.getReference(tblusuariosCollectionNewTblusuariosToAttach.getClass(), tblusuariosCollectionNewTblusuariosToAttach.getIdUsuario());
                attachedTblusuariosCollectionNew.add(tblusuariosCollectionNewTblusuariosToAttach);
            }
            tblusuariosCollectionNew = attachedTblusuariosCollectionNew;
            tblperfiles.setTblusuariosCollection(tblusuariosCollectionNew);
            tblperfiles = em.merge(tblperfiles);
            for (Tblpermisos tblpermisosCollectionOldTblpermisos : tblpermisosCollectionOld) {
                if (!tblpermisosCollectionNew.contains(tblpermisosCollectionOldTblpermisos)) {
                    tblpermisosCollectionOldTblpermisos.getTblperfilesCollection().remove(tblperfiles);
                    tblpermisosCollectionOldTblpermisos = em.merge(tblpermisosCollectionOldTblpermisos);
                }
            }
            for (Tblpermisos tblpermisosCollectionNewTblpermisos : tblpermisosCollectionNew) {
                if (!tblpermisosCollectionOld.contains(tblpermisosCollectionNewTblpermisos)) {
                    tblpermisosCollectionNewTblpermisos.getTblperfilesCollection().add(tblperfiles);
                    tblpermisosCollectionNewTblpermisos = em.merge(tblpermisosCollectionNewTblpermisos);
                }
            }
            for (TblusuariosCreaPerfiles tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles : tblusuariosCreaPerfilesCollectionNew) {
                if (!tblusuariosCreaPerfilesCollectionOld.contains(tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles)) {
                    Tblperfiles oldIdPerfilNuevoOfTblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles = tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles.getIdPerfilNuevo();
                    tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles.setIdPerfilNuevo(tblperfiles);
                    tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles = em.merge(tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles);
                    if (oldIdPerfilNuevoOfTblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles != null && !oldIdPerfilNuevoOfTblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles.equals(tblperfiles)) {
                        oldIdPerfilNuevoOfTblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles.getTblusuariosCreaPerfilesCollection().remove(tblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles);
                        oldIdPerfilNuevoOfTblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles = em.merge(oldIdPerfilNuevoOfTblusuariosCreaPerfilesCollectionNewTblusuariosCreaPerfiles);
                    }
                }
            }
            for (Tblusuarios tblusuariosCollectionNewTblusuarios : tblusuariosCollectionNew) {
                if (!tblusuariosCollectionOld.contains(tblusuariosCollectionNewTblusuarios)) {
                    Tblperfiles oldIdPerfilTieneOfTblusuariosCollectionNewTblusuarios = tblusuariosCollectionNewTblusuarios.getIdPerfilTiene();
                    tblusuariosCollectionNewTblusuarios.setIdPerfilTiene(tblperfiles);
                    tblusuariosCollectionNewTblusuarios = em.merge(tblusuariosCollectionNewTblusuarios);
                    if (oldIdPerfilTieneOfTblusuariosCollectionNewTblusuarios != null && !oldIdPerfilTieneOfTblusuariosCollectionNewTblusuarios.equals(tblperfiles)) {
                        oldIdPerfilTieneOfTblusuariosCollectionNewTblusuarios.getTblusuariosCollection().remove(tblusuariosCollectionNewTblusuarios);
                        oldIdPerfilTieneOfTblusuariosCollectionNewTblusuarios = em.merge(oldIdPerfilTieneOfTblusuariosCollectionNewTblusuarios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblperfiles.getIdPerfil();
                if (findTblperfiles(id) == null) {
                    throw new NonexistentEntityException("The tblperfiles with id " + id + " no longer exists.");
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
            Tblperfiles tblperfiles;
            try {
                tblperfiles = em.getReference(Tblperfiles.class, id);
                tblperfiles.getIdPerfil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblperfiles with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TblusuariosCreaPerfiles> tblusuariosCreaPerfilesCollectionOrphanCheck = tblperfiles.getTblusuariosCreaPerfilesCollection();
            for (TblusuariosCreaPerfiles tblusuariosCreaPerfilesCollectionOrphanCheckTblusuariosCreaPerfiles : tblusuariosCreaPerfilesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblperfiles (" + tblperfiles + ") cannot be destroyed since the TblusuariosCreaPerfiles " + tblusuariosCreaPerfilesCollectionOrphanCheckTblusuariosCreaPerfiles + " in its tblusuariosCreaPerfilesCollection field has a non-nullable idPerfilNuevo field.");
            }
            Collection<Tblusuarios> tblusuariosCollectionOrphanCheck = tblperfiles.getTblusuariosCollection();
            for (Tblusuarios tblusuariosCollectionOrphanCheckTblusuarios : tblusuariosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblperfiles (" + tblperfiles + ") cannot be destroyed since the Tblusuarios " + tblusuariosCollectionOrphanCheckTblusuarios + " in its tblusuariosCollection field has a non-nullable idPerfilTiene field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tblpermisos> tblpermisosCollection = tblperfiles.getTblpermisosCollection();
            for (Tblpermisos tblpermisosCollectionTblpermisos : tblpermisosCollection) {
                tblpermisosCollectionTblpermisos.getTblperfilesCollection().remove(tblperfiles);
                tblpermisosCollectionTblpermisos = em.merge(tblpermisosCollectionTblpermisos);
            }
            em.remove(tblperfiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblperfiles> findTblperfilesEntities() {
        return findTblperfilesEntities(true, -1, -1);
    }

    public List<Tblperfiles> findTblperfilesEntities(int maxResults, int firstResult) {
        return findTblperfilesEntities(false, maxResults, firstResult);
    }

    private List<Tblperfiles> findTblperfilesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tblperfiles as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tblperfiles findTblperfiles(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblperfiles.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblperfilesCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Tblperfiles as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
