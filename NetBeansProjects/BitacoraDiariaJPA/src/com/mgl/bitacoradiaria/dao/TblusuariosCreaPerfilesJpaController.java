/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.model.TblusuariosCreaPerfiles;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblusuarios;
import com.mgl.bitacoradiaria.model.Tblperfiles;

/**
 *
 * @author mike
 */
public class TblusuariosCreaPerfilesJpaController {

    public TblusuariosCreaPerfilesJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblusuariosCreaPerfiles tblusuariosCreaPerfiles) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblusuarios idUsuarioCrea = tblusuariosCreaPerfiles.getIdUsuarioCrea();
            if (idUsuarioCrea != null) {
                idUsuarioCrea = em.getReference(idUsuarioCrea.getClass(), idUsuarioCrea.getIdUsuario());
                tblusuariosCreaPerfiles.setIdUsuarioCrea(idUsuarioCrea);
            }
            Tblperfiles idPerfilNuevo = tblusuariosCreaPerfiles.getIdPerfilNuevo();
            if (idPerfilNuevo != null) {
                idPerfilNuevo = em.getReference(idPerfilNuevo.getClass(), idPerfilNuevo.getIdPerfil());
                tblusuariosCreaPerfiles.setIdPerfilNuevo(idPerfilNuevo);
            }
            em.persist(tblusuariosCreaPerfiles);
            if (idUsuarioCrea != null) {
                idUsuarioCrea.getTblusuariosCreaPerfilesCollection().add(tblusuariosCreaPerfiles);
                idUsuarioCrea = em.merge(idUsuarioCrea);
            }
            if (idPerfilNuevo != null) {
                idPerfilNuevo.getTblusuariosCreaPerfilesCollection().add(tblusuariosCreaPerfiles);
                idPerfilNuevo = em.merge(idPerfilNuevo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblusuariosCreaPerfiles tblusuariosCreaPerfiles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblusuariosCreaPerfiles persistentTblusuariosCreaPerfiles = em.find(TblusuariosCreaPerfiles.class, tblusuariosCreaPerfiles.getIdCreador());
            Tblusuarios idUsuarioCreaOld = persistentTblusuariosCreaPerfiles.getIdUsuarioCrea();
            Tblusuarios idUsuarioCreaNew = tblusuariosCreaPerfiles.getIdUsuarioCrea();
            Tblperfiles idPerfilNuevoOld = persistentTblusuariosCreaPerfiles.getIdPerfilNuevo();
            Tblperfiles idPerfilNuevoNew = tblusuariosCreaPerfiles.getIdPerfilNuevo();
            if (idUsuarioCreaNew != null) {
                idUsuarioCreaNew = em.getReference(idUsuarioCreaNew.getClass(), idUsuarioCreaNew.getIdUsuario());
                tblusuariosCreaPerfiles.setIdUsuarioCrea(idUsuarioCreaNew);
            }
            if (idPerfilNuevoNew != null) {
                idPerfilNuevoNew = em.getReference(idPerfilNuevoNew.getClass(), idPerfilNuevoNew.getIdPerfil());
                tblusuariosCreaPerfiles.setIdPerfilNuevo(idPerfilNuevoNew);
            }
            tblusuariosCreaPerfiles = em.merge(tblusuariosCreaPerfiles);
            if (idUsuarioCreaOld != null && !idUsuarioCreaOld.equals(idUsuarioCreaNew)) {
                idUsuarioCreaOld.getTblusuariosCreaPerfilesCollection().remove(tblusuariosCreaPerfiles);
                idUsuarioCreaOld = em.merge(idUsuarioCreaOld);
            }
            if (idUsuarioCreaNew != null && !idUsuarioCreaNew.equals(idUsuarioCreaOld)) {
                idUsuarioCreaNew.getTblusuariosCreaPerfilesCollection().add(tblusuariosCreaPerfiles);
                idUsuarioCreaNew = em.merge(idUsuarioCreaNew);
            }
            if (idPerfilNuevoOld != null && !idPerfilNuevoOld.equals(idPerfilNuevoNew)) {
                idPerfilNuevoOld.getTblusuariosCreaPerfilesCollection().remove(tblusuariosCreaPerfiles);
                idPerfilNuevoOld = em.merge(idPerfilNuevoOld);
            }
            if (idPerfilNuevoNew != null && !idPerfilNuevoNew.equals(idPerfilNuevoOld)) {
                idPerfilNuevoNew.getTblusuariosCreaPerfilesCollection().add(tblusuariosCreaPerfiles);
                idPerfilNuevoNew = em.merge(idPerfilNuevoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblusuariosCreaPerfiles.getIdCreador();
                if (findTblusuariosCreaPerfiles(id) == null) {
                    throw new NonexistentEntityException("The tblusuariosCreaPerfiles with id " + id + " no longer exists.");
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
            TblusuariosCreaPerfiles tblusuariosCreaPerfiles;
            try {
                tblusuariosCreaPerfiles = em.getReference(TblusuariosCreaPerfiles.class, id);
                tblusuariosCreaPerfiles.getIdCreador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblusuariosCreaPerfiles with id " + id + " no longer exists.", enfe);
            }
            Tblusuarios idUsuarioCrea = tblusuariosCreaPerfiles.getIdUsuarioCrea();
            if (idUsuarioCrea != null) {
                idUsuarioCrea.getTblusuariosCreaPerfilesCollection().remove(tblusuariosCreaPerfiles);
                idUsuarioCrea = em.merge(idUsuarioCrea);
            }
            Tblperfiles idPerfilNuevo = tblusuariosCreaPerfiles.getIdPerfilNuevo();
            if (idPerfilNuevo != null) {
                idPerfilNuevo.getTblusuariosCreaPerfilesCollection().remove(tblusuariosCreaPerfiles);
                idPerfilNuevo = em.merge(idPerfilNuevo);
            }
            em.remove(tblusuariosCreaPerfiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblusuariosCreaPerfiles> findTblusuariosCreaPerfilesEntities() {
        return findTblusuariosCreaPerfilesEntities(true, -1, -1);
    }

    public List<TblusuariosCreaPerfiles> findTblusuariosCreaPerfilesEntities(int maxResults, int firstResult) {
        return findTblusuariosCreaPerfilesEntities(false, maxResults, firstResult);
    }

    private List<TblusuariosCreaPerfiles> findTblusuariosCreaPerfilesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TblusuariosCreaPerfiles as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TblusuariosCreaPerfiles findTblusuariosCreaPerfiles(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblusuariosCreaPerfiles.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblusuariosCreaPerfilesCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TblusuariosCreaPerfiles as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
