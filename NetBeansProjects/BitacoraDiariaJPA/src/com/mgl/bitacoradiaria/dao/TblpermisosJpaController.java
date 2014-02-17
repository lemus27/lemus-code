/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.IllegalOrphanException;
import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.model.Tblpermisos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.TblusuariosCreaTblpermisos;
import com.mgl.bitacoradiaria.model.Tblperfiles;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author mike
 */
public class TblpermisosJpaController {

    public TblpermisosJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblpermisos tblpermisos) {
        if (tblpermisos.getTblperfilesCollection() == null) {
            tblpermisos.setTblperfilesCollection(new ArrayList<Tblperfiles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblusuariosCreaTblpermisos tblusuariosCreaTblpermisos = tblpermisos.getTblusuariosCreaTblpermisos();
            if (tblusuariosCreaTblpermisos != null) {
                tblusuariosCreaTblpermisos = em.getReference(tblusuariosCreaTblpermisos.getClass(), tblusuariosCreaTblpermisos.getIdCreador());
                tblpermisos.setTblusuariosCreaTblpermisos(tblusuariosCreaTblpermisos);
            }
            Collection<Tblperfiles> attachedTblperfilesCollection = new ArrayList<Tblperfiles>();
            for (Tblperfiles tblperfilesCollectionTblperfilesToAttach : tblpermisos.getTblperfilesCollection()) {
                tblperfilesCollectionTblperfilesToAttach = em.getReference(tblperfilesCollectionTblperfilesToAttach.getClass(), tblperfilesCollectionTblperfilesToAttach.getIdPerfil());
                attachedTblperfilesCollection.add(tblperfilesCollectionTblperfilesToAttach);
            }
            tblpermisos.setTblperfilesCollection(attachedTblperfilesCollection);
            em.persist(tblpermisos);
            if (tblusuariosCreaTblpermisos != null) {
                Tblpermisos oldIdPermisoNuevoOfTblusuariosCreaTblpermisos = tblusuariosCreaTblpermisos.getIdPermisoNuevo();
                if (oldIdPermisoNuevoOfTblusuariosCreaTblpermisos != null) {
                    oldIdPermisoNuevoOfTblusuariosCreaTblpermisos.setTblusuariosCreaTblpermisos(null);
                    oldIdPermisoNuevoOfTblusuariosCreaTblpermisos = em.merge(oldIdPermisoNuevoOfTblusuariosCreaTblpermisos);
                }
                tblusuariosCreaTblpermisos.setIdPermisoNuevo(tblpermisos);
                tblusuariosCreaTblpermisos = em.merge(tblusuariosCreaTblpermisos);
            }
            for (Tblperfiles tblperfilesCollectionTblperfiles : tblpermisos.getTblperfilesCollection()) {
                tblperfilesCollectionTblperfiles.getTblpermisosCollection().add(tblpermisos);
                tblperfilesCollectionTblperfiles = em.merge(tblperfilesCollectionTblperfiles);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblpermisos tblpermisos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblpermisos persistentTblpermisos = em.find(Tblpermisos.class, tblpermisos.getIdPermiso());
            TblusuariosCreaTblpermisos tblusuariosCreaTblpermisosOld = persistentTblpermisos.getTblusuariosCreaTblpermisos();
            TblusuariosCreaTblpermisos tblusuariosCreaTblpermisosNew = tblpermisos.getTblusuariosCreaTblpermisos();
            Collection<Tblperfiles> tblperfilesCollectionOld = persistentTblpermisos.getTblperfilesCollection();
            Collection<Tblperfiles> tblperfilesCollectionNew = tblpermisos.getTblperfilesCollection();
            List<String> illegalOrphanMessages = null;
            if (tblusuariosCreaTblpermisosOld != null && !tblusuariosCreaTblpermisosOld.equals(tblusuariosCreaTblpermisosNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain TblusuariosCreaTblpermisos " + tblusuariosCreaTblpermisosOld + " since its idPermisoNuevo field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tblusuariosCreaTblpermisosNew != null) {
                tblusuariosCreaTblpermisosNew = em.getReference(tblusuariosCreaTblpermisosNew.getClass(), tblusuariosCreaTblpermisosNew.getIdCreador());
                tblpermisos.setTblusuariosCreaTblpermisos(tblusuariosCreaTblpermisosNew);
            }
            Collection<Tblperfiles> attachedTblperfilesCollectionNew = new ArrayList<Tblperfiles>();
            for (Tblperfiles tblperfilesCollectionNewTblperfilesToAttach : tblperfilesCollectionNew) {
                tblperfilesCollectionNewTblperfilesToAttach = em.getReference(tblperfilesCollectionNewTblperfilesToAttach.getClass(), tblperfilesCollectionNewTblperfilesToAttach.getIdPerfil());
                attachedTblperfilesCollectionNew.add(tblperfilesCollectionNewTblperfilesToAttach);
            }
            tblperfilesCollectionNew = attachedTblperfilesCollectionNew;
            tblpermisos.setTblperfilesCollection(tblperfilesCollectionNew);
            tblpermisos = em.merge(tblpermisos);
            if (tblusuariosCreaTblpermisosNew != null && !tblusuariosCreaTblpermisosNew.equals(tblusuariosCreaTblpermisosOld)) {
                Tblpermisos oldIdPermisoNuevoOfTblusuariosCreaTblpermisos = tblusuariosCreaTblpermisosNew.getIdPermisoNuevo();
                if (oldIdPermisoNuevoOfTblusuariosCreaTblpermisos != null) {
                    oldIdPermisoNuevoOfTblusuariosCreaTblpermisos.setTblusuariosCreaTblpermisos(null);
                    oldIdPermisoNuevoOfTblusuariosCreaTblpermisos = em.merge(oldIdPermisoNuevoOfTblusuariosCreaTblpermisos);
                }
                tblusuariosCreaTblpermisosNew.setIdPermisoNuevo(tblpermisos);
                tblusuariosCreaTblpermisosNew = em.merge(tblusuariosCreaTblpermisosNew);
            }
            for (Tblperfiles tblperfilesCollectionOldTblperfiles : tblperfilesCollectionOld) {
                if (!tblperfilesCollectionNew.contains(tblperfilesCollectionOldTblperfiles)) {
                    tblperfilesCollectionOldTblperfiles.getTblpermisosCollection().remove(tblpermisos);
                    tblperfilesCollectionOldTblperfiles = em.merge(tblperfilesCollectionOldTblperfiles);
                }
            }
            for (Tblperfiles tblperfilesCollectionNewTblperfiles : tblperfilesCollectionNew) {
                if (!tblperfilesCollectionOld.contains(tblperfilesCollectionNewTblperfiles)) {
                    tblperfilesCollectionNewTblperfiles.getTblpermisosCollection().add(tblpermisos);
                    tblperfilesCollectionNewTblperfiles = em.merge(tblperfilesCollectionNewTblperfiles);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblpermisos.getIdPermiso();
                if (findTblpermisos(id) == null) {
                    throw new NonexistentEntityException("The tblpermisos with id " + id + " no longer exists.");
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
            Tblpermisos tblpermisos;
            try {
                tblpermisos = em.getReference(Tblpermisos.class, id);
                tblpermisos.getIdPermiso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblpermisos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            TblusuariosCreaTblpermisos tblusuariosCreaTblpermisosOrphanCheck = tblpermisos.getTblusuariosCreaTblpermisos();
            if (tblusuariosCreaTblpermisosOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblpermisos (" + tblpermisos + ") cannot be destroyed since the TblusuariosCreaTblpermisos " + tblusuariosCreaTblpermisosOrphanCheck + " in its tblusuariosCreaTblpermisos field has a non-nullable idPermisoNuevo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tblperfiles> tblperfilesCollection = tblpermisos.getTblperfilesCollection();
            for (Tblperfiles tblperfilesCollectionTblperfiles : tblperfilesCollection) {
                tblperfilesCollectionTblperfiles.getTblpermisosCollection().remove(tblpermisos);
                tblperfilesCollectionTblperfiles = em.merge(tblperfilesCollectionTblperfiles);
            }
            em.remove(tblpermisos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblpermisos> findTblpermisosEntities() {
        return findTblpermisosEntities(true, -1, -1);
    }

    public List<Tblpermisos> findTblpermisosEntities(int maxResults, int firstResult) {
        return findTblpermisosEntities(false, maxResults, firstResult);
    }

    private List<Tblpermisos> findTblpermisosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tblpermisos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tblpermisos findTblpermisos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblpermisos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblpermisosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Tblpermisos as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
