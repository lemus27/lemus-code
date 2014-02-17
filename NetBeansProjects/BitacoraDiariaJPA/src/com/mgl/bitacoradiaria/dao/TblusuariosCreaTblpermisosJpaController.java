/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.IllegalOrphanException;
import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.model.TblusuariosCreaTblpermisos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblusuarios;
import com.mgl.bitacoradiaria.model.Tblpermisos;
import java.util.ArrayList;

/**
 *
 * @author mike
 */
public class TblusuariosCreaTblpermisosJpaController {

    public TblusuariosCreaTblpermisosJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblusuariosCreaTblpermisos tblusuariosCreaTblpermisos) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Tblpermisos idPermisoNuevoOrphanCheck = tblusuariosCreaTblpermisos.getIdPermisoNuevo();
        if (idPermisoNuevoOrphanCheck != null) {
            TblusuariosCreaTblpermisos oldTblusuariosCreaTblpermisosOfIdPermisoNuevo = idPermisoNuevoOrphanCheck.getTblusuariosCreaTblpermisos();
            if (oldTblusuariosCreaTblpermisosOfIdPermisoNuevo != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Tblpermisos " + idPermisoNuevoOrphanCheck + " already has an item of type TblusuariosCreaTblpermisos whose idPermisoNuevo column cannot be null. Please make another selection for the idPermisoNuevo field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblusuarios idUsuarioCrea = tblusuariosCreaTblpermisos.getIdUsuarioCrea();
            if (idUsuarioCrea != null) {
                idUsuarioCrea = em.getReference(idUsuarioCrea.getClass(), idUsuarioCrea.getIdUsuario());
                tblusuariosCreaTblpermisos.setIdUsuarioCrea(idUsuarioCrea);
            }
            Tblpermisos idPermisoNuevo = tblusuariosCreaTblpermisos.getIdPermisoNuevo();
            if (idPermisoNuevo != null) {
                idPermisoNuevo = em.getReference(idPermisoNuevo.getClass(), idPermisoNuevo.getIdPermiso());
                tblusuariosCreaTblpermisos.setIdPermisoNuevo(idPermisoNuevo);
            }
            em.persist(tblusuariosCreaTblpermisos);
            if (idUsuarioCrea != null) {
                idUsuarioCrea.getTblusuariosCreaTblpermisosCollection().add(tblusuariosCreaTblpermisos);
                idUsuarioCrea = em.merge(idUsuarioCrea);
            }
            if (idPermisoNuevo != null) {
                idPermisoNuevo.setTblusuariosCreaTblpermisos(tblusuariosCreaTblpermisos);
                idPermisoNuevo = em.merge(idPermisoNuevo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblusuariosCreaTblpermisos tblusuariosCreaTblpermisos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblusuariosCreaTblpermisos persistentTblusuariosCreaTblpermisos = em.find(TblusuariosCreaTblpermisos.class, tblusuariosCreaTblpermisos.getIdCreador());
            Tblusuarios idUsuarioCreaOld = persistentTblusuariosCreaTblpermisos.getIdUsuarioCrea();
            Tblusuarios idUsuarioCreaNew = tblusuariosCreaTblpermisos.getIdUsuarioCrea();
            Tblpermisos idPermisoNuevoOld = persistentTblusuariosCreaTblpermisos.getIdPermisoNuevo();
            Tblpermisos idPermisoNuevoNew = tblusuariosCreaTblpermisos.getIdPermisoNuevo();
            List<String> illegalOrphanMessages = null;
            if (idPermisoNuevoNew != null && !idPermisoNuevoNew.equals(idPermisoNuevoOld)) {
                TblusuariosCreaTblpermisos oldTblusuariosCreaTblpermisosOfIdPermisoNuevo = idPermisoNuevoNew.getTblusuariosCreaTblpermisos();
                if (oldTblusuariosCreaTblpermisosOfIdPermisoNuevo != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Tblpermisos " + idPermisoNuevoNew + " already has an item of type TblusuariosCreaTblpermisos whose idPermisoNuevo column cannot be null. Please make another selection for the idPermisoNuevo field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsuarioCreaNew != null) {
                idUsuarioCreaNew = em.getReference(idUsuarioCreaNew.getClass(), idUsuarioCreaNew.getIdUsuario());
                tblusuariosCreaTblpermisos.setIdUsuarioCrea(idUsuarioCreaNew);
            }
            if (idPermisoNuevoNew != null) {
                idPermisoNuevoNew = em.getReference(idPermisoNuevoNew.getClass(), idPermisoNuevoNew.getIdPermiso());
                tblusuariosCreaTblpermisos.setIdPermisoNuevo(idPermisoNuevoNew);
            }
            tblusuariosCreaTblpermisos = em.merge(tblusuariosCreaTblpermisos);
            if (idUsuarioCreaOld != null && !idUsuarioCreaOld.equals(idUsuarioCreaNew)) {
                idUsuarioCreaOld.getTblusuariosCreaTblpermisosCollection().remove(tblusuariosCreaTblpermisos);
                idUsuarioCreaOld = em.merge(idUsuarioCreaOld);
            }
            if (idUsuarioCreaNew != null && !idUsuarioCreaNew.equals(idUsuarioCreaOld)) {
                idUsuarioCreaNew.getTblusuariosCreaTblpermisosCollection().add(tblusuariosCreaTblpermisos);
                idUsuarioCreaNew = em.merge(idUsuarioCreaNew);
            }
            if (idPermisoNuevoOld != null && !idPermisoNuevoOld.equals(idPermisoNuevoNew)) {
                idPermisoNuevoOld.setTblusuariosCreaTblpermisos(null);
                idPermisoNuevoOld = em.merge(idPermisoNuevoOld);
            }
            if (idPermisoNuevoNew != null && !idPermisoNuevoNew.equals(idPermisoNuevoOld)) {
                idPermisoNuevoNew.setTblusuariosCreaTblpermisos(tblusuariosCreaTblpermisos);
                idPermisoNuevoNew = em.merge(idPermisoNuevoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblusuariosCreaTblpermisos.getIdCreador();
                if (findTblusuariosCreaTblpermisos(id) == null) {
                    throw new NonexistentEntityException("The tblusuariosCreaTblpermisos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblusuariosCreaTblpermisos tblusuariosCreaTblpermisos;
            try {
                tblusuariosCreaTblpermisos = em.getReference(TblusuariosCreaTblpermisos.class, id);
                tblusuariosCreaTblpermisos.getIdCreador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblusuariosCreaTblpermisos with id " + id + " no longer exists.", enfe);
            }
            Tblusuarios idUsuarioCrea = tblusuariosCreaTblpermisos.getIdUsuarioCrea();
            if (idUsuarioCrea != null) {
                idUsuarioCrea.getTblusuariosCreaTblpermisosCollection().remove(tblusuariosCreaTblpermisos);
                idUsuarioCrea = em.merge(idUsuarioCrea);
            }
            Tblpermisos idPermisoNuevo = tblusuariosCreaTblpermisos.getIdPermisoNuevo();
            if (idPermisoNuevo != null) {
                idPermisoNuevo.setTblusuariosCreaTblpermisos(null);
                idPermisoNuevo = em.merge(idPermisoNuevo);
            }
            em.remove(tblusuariosCreaTblpermisos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblusuariosCreaTblpermisos> findTblusuariosCreaTblpermisosEntities() {
        return findTblusuariosCreaTblpermisosEntities(true, -1, -1);
    }

    public List<TblusuariosCreaTblpermisos> findTblusuariosCreaTblpermisosEntities(int maxResults, int firstResult) {
        return findTblusuariosCreaTblpermisosEntities(false, maxResults, firstResult);
    }

    private List<TblusuariosCreaTblpermisos> findTblusuariosCreaTblpermisosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TblusuariosCreaTblpermisos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TblusuariosCreaTblpermisos findTblusuariosCreaTblpermisos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblusuariosCreaTblpermisos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblusuariosCreaTblpermisosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TblusuariosCreaTblpermisos as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
