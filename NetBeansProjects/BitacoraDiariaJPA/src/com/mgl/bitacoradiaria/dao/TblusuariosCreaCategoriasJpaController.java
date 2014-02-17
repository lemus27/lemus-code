/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.dao.exceptions.PreexistingEntityException;
import com.mgl.bitacoradiaria.model.TblusuariosCreaCategorias;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblusuarios;
import com.mgl.bitacoradiaria.model.Tblcategorias;

/**
 *
 * @author mike
 */
public class TblusuariosCreaCategoriasJpaController {

    public TblusuariosCreaCategoriasJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblusuariosCreaCategorias tblusuariosCreaCategorias) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblusuarios idUsuarioCrea = tblusuariosCreaCategorias.getIdUsuarioCrea();
            if (idUsuarioCrea != null) {
                idUsuarioCrea = em.getReference(idUsuarioCrea.getClass(), idUsuarioCrea.getIdUsuario());
                tblusuariosCreaCategorias.setIdUsuarioCrea(idUsuarioCrea);
            }
            Tblcategorias idCategoriaNuevo = tblusuariosCreaCategorias.getIdCategoriaNuevo();
            if (idCategoriaNuevo != null) {
                idCategoriaNuevo = em.getReference(idCategoriaNuevo.getClass(), idCategoriaNuevo.getIdCategoria());
                tblusuariosCreaCategorias.setIdCategoriaNuevo(idCategoriaNuevo);
            }
            em.persist(tblusuariosCreaCategorias);
            if (idUsuarioCrea != null) {
                idUsuarioCrea.getTblusuariosCreaCategoriasCollection().add(tblusuariosCreaCategorias);
                idUsuarioCrea = em.merge(idUsuarioCrea);
            }
            if (idCategoriaNuevo != null) {
                idCategoriaNuevo.getTblusuariosCreaCategoriasCollection().add(tblusuariosCreaCategorias);
                idCategoriaNuevo = em.merge(idCategoriaNuevo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTblusuariosCreaCategorias(tblusuariosCreaCategorias.getIdCreador()) != null) {
                throw new PreexistingEntityException("TblusuariosCreaCategorias " + tblusuariosCreaCategorias + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblusuariosCreaCategorias tblusuariosCreaCategorias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblusuariosCreaCategorias persistentTblusuariosCreaCategorias = em.find(TblusuariosCreaCategorias.class, tblusuariosCreaCategorias.getIdCreador());
            Tblusuarios idUsuarioCreaOld = persistentTblusuariosCreaCategorias.getIdUsuarioCrea();
            Tblusuarios idUsuarioCreaNew = tblusuariosCreaCategorias.getIdUsuarioCrea();
            Tblcategorias idCategoriaNuevoOld = persistentTblusuariosCreaCategorias.getIdCategoriaNuevo();
            Tblcategorias idCategoriaNuevoNew = tblusuariosCreaCategorias.getIdCategoriaNuevo();
            if (idUsuarioCreaNew != null) {
                idUsuarioCreaNew = em.getReference(idUsuarioCreaNew.getClass(), idUsuarioCreaNew.getIdUsuario());
                tblusuariosCreaCategorias.setIdUsuarioCrea(idUsuarioCreaNew);
            }
            if (idCategoriaNuevoNew != null) {
                idCategoriaNuevoNew = em.getReference(idCategoriaNuevoNew.getClass(), idCategoriaNuevoNew.getIdCategoria());
                tblusuariosCreaCategorias.setIdCategoriaNuevo(idCategoriaNuevoNew);
            }
            tblusuariosCreaCategorias = em.merge(tblusuariosCreaCategorias);
            if (idUsuarioCreaOld != null && !idUsuarioCreaOld.equals(idUsuarioCreaNew)) {
                idUsuarioCreaOld.getTblusuariosCreaCategoriasCollection().remove(tblusuariosCreaCategorias);
                idUsuarioCreaOld = em.merge(idUsuarioCreaOld);
            }
            if (idUsuarioCreaNew != null && !idUsuarioCreaNew.equals(idUsuarioCreaOld)) {
                idUsuarioCreaNew.getTblusuariosCreaCategoriasCollection().add(tblusuariosCreaCategorias);
                idUsuarioCreaNew = em.merge(idUsuarioCreaNew);
            }
            if (idCategoriaNuevoOld != null && !idCategoriaNuevoOld.equals(idCategoriaNuevoNew)) {
                idCategoriaNuevoOld.getTblusuariosCreaCategoriasCollection().remove(tblusuariosCreaCategorias);
                idCategoriaNuevoOld = em.merge(idCategoriaNuevoOld);
            }
            if (idCategoriaNuevoNew != null && !idCategoriaNuevoNew.equals(idCategoriaNuevoOld)) {
                idCategoriaNuevoNew.getTblusuariosCreaCategoriasCollection().add(tblusuariosCreaCategorias);
                idCategoriaNuevoNew = em.merge(idCategoriaNuevoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tblusuariosCreaCategorias.getIdCreador();
                if (findTblusuariosCreaCategorias(id) == null) {
                    throw new NonexistentEntityException("The tblusuariosCreaCategorias with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblusuariosCreaCategorias tblusuariosCreaCategorias;
            try {
                tblusuariosCreaCategorias = em.getReference(TblusuariosCreaCategorias.class, id);
                tblusuariosCreaCategorias.getIdCreador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblusuariosCreaCategorias with id " + id + " no longer exists.", enfe);
            }
            Tblusuarios idUsuarioCrea = tblusuariosCreaCategorias.getIdUsuarioCrea();
            if (idUsuarioCrea != null) {
                idUsuarioCrea.getTblusuariosCreaCategoriasCollection().remove(tblusuariosCreaCategorias);
                idUsuarioCrea = em.merge(idUsuarioCrea);
            }
            Tblcategorias idCategoriaNuevo = tblusuariosCreaCategorias.getIdCategoriaNuevo();
            if (idCategoriaNuevo != null) {
                idCategoriaNuevo.getTblusuariosCreaCategoriasCollection().remove(tblusuariosCreaCategorias);
                idCategoriaNuevo = em.merge(idCategoriaNuevo);
            }
            em.remove(tblusuariosCreaCategorias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblusuariosCreaCategorias> findTblusuariosCreaCategoriasEntities() {
        return findTblusuariosCreaCategoriasEntities(true, -1, -1);
    }

    public List<TblusuariosCreaCategorias> findTblusuariosCreaCategoriasEntities(int maxResults, int firstResult) {
        return findTblusuariosCreaCategoriasEntities(false, maxResults, firstResult);
    }

    private List<TblusuariosCreaCategorias> findTblusuariosCreaCategoriasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TblusuariosCreaCategorias as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TblusuariosCreaCategorias findTblusuariosCreaCategorias(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblusuariosCreaCategorias.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblusuariosCreaCategoriasCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TblusuariosCreaCategorias as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
