/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.model.TblusuariosCreaInterrupciones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblusuarios;
import com.mgl.bitacoradiaria.model.Tblinterrupciones;

/**
 *
 * @author mike
 */
public class TblusuariosCreaInterrupcionesJpaController {

    public TblusuariosCreaInterrupcionesJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblusuariosCreaInterrupciones tblusuariosCreaInterrupciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblusuarios idUsuarioCrea = tblusuariosCreaInterrupciones.getIdUsuarioCrea();
            if (idUsuarioCrea != null) {
                idUsuarioCrea = em.getReference(idUsuarioCrea.getClass(), idUsuarioCrea.getIdUsuario());
                tblusuariosCreaInterrupciones.setIdUsuarioCrea(idUsuarioCrea);
            }
            Tblinterrupciones idInterrupcionNuevo = tblusuariosCreaInterrupciones.getIdInterrupcionNuevo();
            if (idInterrupcionNuevo != null) {
                idInterrupcionNuevo = em.getReference(idInterrupcionNuevo.getClass(), idInterrupcionNuevo.getIdInterrupcion());
                tblusuariosCreaInterrupciones.setIdInterrupcionNuevo(idInterrupcionNuevo);
            }
            em.persist(tblusuariosCreaInterrupciones);
            if (idUsuarioCrea != null) {
                idUsuarioCrea.getTblusuariosCreaInterrupcionesCollection().add(tblusuariosCreaInterrupciones);
                idUsuarioCrea = em.merge(idUsuarioCrea);
            }
            if (idInterrupcionNuevo != null) {
                idInterrupcionNuevo.getTblusuariosCreaInterrupcionesCollection().add(tblusuariosCreaInterrupciones);
                idInterrupcionNuevo = em.merge(idInterrupcionNuevo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblusuariosCreaInterrupciones tblusuariosCreaInterrupciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblusuariosCreaInterrupciones persistentTblusuariosCreaInterrupciones = em.find(TblusuariosCreaInterrupciones.class, tblusuariosCreaInterrupciones.getIdCreador());
            Tblusuarios idUsuarioCreaOld = persistentTblusuariosCreaInterrupciones.getIdUsuarioCrea();
            Tblusuarios idUsuarioCreaNew = tblusuariosCreaInterrupciones.getIdUsuarioCrea();
            Tblinterrupciones idInterrupcionNuevoOld = persistentTblusuariosCreaInterrupciones.getIdInterrupcionNuevo();
            Tblinterrupciones idInterrupcionNuevoNew = tblusuariosCreaInterrupciones.getIdInterrupcionNuevo();
            if (idUsuarioCreaNew != null) {
                idUsuarioCreaNew = em.getReference(idUsuarioCreaNew.getClass(), idUsuarioCreaNew.getIdUsuario());
                tblusuariosCreaInterrupciones.setIdUsuarioCrea(idUsuarioCreaNew);
            }
            if (idInterrupcionNuevoNew != null) {
                idInterrupcionNuevoNew = em.getReference(idInterrupcionNuevoNew.getClass(), idInterrupcionNuevoNew.getIdInterrupcion());
                tblusuariosCreaInterrupciones.setIdInterrupcionNuevo(idInterrupcionNuevoNew);
            }
            tblusuariosCreaInterrupciones = em.merge(tblusuariosCreaInterrupciones);
            if (idUsuarioCreaOld != null && !idUsuarioCreaOld.equals(idUsuarioCreaNew)) {
                idUsuarioCreaOld.getTblusuariosCreaInterrupcionesCollection().remove(tblusuariosCreaInterrupciones);
                idUsuarioCreaOld = em.merge(idUsuarioCreaOld);
            }
            if (idUsuarioCreaNew != null && !idUsuarioCreaNew.equals(idUsuarioCreaOld)) {
                idUsuarioCreaNew.getTblusuariosCreaInterrupcionesCollection().add(tblusuariosCreaInterrupciones);
                idUsuarioCreaNew = em.merge(idUsuarioCreaNew);
            }
            if (idInterrupcionNuevoOld != null && !idInterrupcionNuevoOld.equals(idInterrupcionNuevoNew)) {
                idInterrupcionNuevoOld.getTblusuariosCreaInterrupcionesCollection().remove(tblusuariosCreaInterrupciones);
                idInterrupcionNuevoOld = em.merge(idInterrupcionNuevoOld);
            }
            if (idInterrupcionNuevoNew != null && !idInterrupcionNuevoNew.equals(idInterrupcionNuevoOld)) {
                idInterrupcionNuevoNew.getTblusuariosCreaInterrupcionesCollection().add(tblusuariosCreaInterrupciones);
                idInterrupcionNuevoNew = em.merge(idInterrupcionNuevoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblusuariosCreaInterrupciones.getIdCreador();
                if (findTblusuariosCreaInterrupciones(id) == null) {
                    throw new NonexistentEntityException("The tblusuariosCreaInterrupciones with id " + id + " no longer exists.");
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
            TblusuariosCreaInterrupciones tblusuariosCreaInterrupciones;
            try {
                tblusuariosCreaInterrupciones = em.getReference(TblusuariosCreaInterrupciones.class, id);
                tblusuariosCreaInterrupciones.getIdCreador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblusuariosCreaInterrupciones with id " + id + " no longer exists.", enfe);
            }
            Tblusuarios idUsuarioCrea = tblusuariosCreaInterrupciones.getIdUsuarioCrea();
            if (idUsuarioCrea != null) {
                idUsuarioCrea.getTblusuariosCreaInterrupcionesCollection().remove(tblusuariosCreaInterrupciones);
                idUsuarioCrea = em.merge(idUsuarioCrea);
            }
            Tblinterrupciones idInterrupcionNuevo = tblusuariosCreaInterrupciones.getIdInterrupcionNuevo();
            if (idInterrupcionNuevo != null) {
                idInterrupcionNuevo.getTblusuariosCreaInterrupcionesCollection().remove(tblusuariosCreaInterrupciones);
                idInterrupcionNuevo = em.merge(idInterrupcionNuevo);
            }
            em.remove(tblusuariosCreaInterrupciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblusuariosCreaInterrupciones> findTblusuariosCreaInterrupcionesEntities() {
        return findTblusuariosCreaInterrupcionesEntities(true, -1, -1);
    }

    public List<TblusuariosCreaInterrupciones> findTblusuariosCreaInterrupcionesEntities(int maxResults, int firstResult) {
        return findTblusuariosCreaInterrupcionesEntities(false, maxResults, firstResult);
    }

    private List<TblusuariosCreaInterrupciones> findTblusuariosCreaInterrupcionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TblusuariosCreaInterrupciones as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TblusuariosCreaInterrupciones findTblusuariosCreaInterrupciones(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblusuariosCreaInterrupciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblusuariosCreaInterrupcionesCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TblusuariosCreaInterrupciones as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
