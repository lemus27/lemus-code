/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.IllegalOrphanException;
import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.model.TblusuariosCreaActividad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblusuarios;
import com.mgl.bitacoradiaria.model.Tblactividad;
import java.util.ArrayList;

/**
 *
 * @author mike
 */
public class TblusuariosCreaActividadJpaController {

    public TblusuariosCreaActividadJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblusuariosCreaActividad tblusuariosCreaActividad) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Tblactividad idActividadNuevoOrphanCheck = tblusuariosCreaActividad.getIdActividadNuevo();
        if (idActividadNuevoOrphanCheck != null) {
            TblusuariosCreaActividad oldTblusuariosCreaActividadOfIdActividadNuevo = idActividadNuevoOrphanCheck.getTblusuariosCreaActividad();
            if (oldTblusuariosCreaActividadOfIdActividadNuevo != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Tblactividad " + idActividadNuevoOrphanCheck + " already has an item of type TblusuariosCreaActividad whose idActividadNuevo column cannot be null. Please make another selection for the idActividadNuevo field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblusuarios idUsuarioCrea = tblusuariosCreaActividad.getIdUsuarioCrea();
            if (idUsuarioCrea != null) {
                idUsuarioCrea = em.getReference(idUsuarioCrea.getClass(), idUsuarioCrea.getIdUsuario());
                tblusuariosCreaActividad.setIdUsuarioCrea(idUsuarioCrea);
            }
            Tblactividad idActividadNuevo = tblusuariosCreaActividad.getIdActividadNuevo();
            if (idActividadNuevo != null) {
                idActividadNuevo = em.getReference(idActividadNuevo.getClass(), idActividadNuevo.getIdActividad());
                tblusuariosCreaActividad.setIdActividadNuevo(idActividadNuevo);
            }
            em.persist(tblusuariosCreaActividad);
            if (idUsuarioCrea != null) {
                idUsuarioCrea.getTblusuariosCreaActividadCollection().add(tblusuariosCreaActividad);
                idUsuarioCrea = em.merge(idUsuarioCrea);
            }
            if (idActividadNuevo != null) {
                idActividadNuevo.setTblusuariosCreaActividad(tblusuariosCreaActividad);
                idActividadNuevo = em.merge(idActividadNuevo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblusuariosCreaActividad tblusuariosCreaActividad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblusuariosCreaActividad persistentTblusuariosCreaActividad = em.find(TblusuariosCreaActividad.class, tblusuariosCreaActividad.getIdCreador());
            Tblusuarios idUsuarioCreaOld = persistentTblusuariosCreaActividad.getIdUsuarioCrea();
            Tblusuarios idUsuarioCreaNew = tblusuariosCreaActividad.getIdUsuarioCrea();
            Tblactividad idActividadNuevoOld = persistentTblusuariosCreaActividad.getIdActividadNuevo();
            Tblactividad idActividadNuevoNew = tblusuariosCreaActividad.getIdActividadNuevo();
            List<String> illegalOrphanMessages = null;
            if (idActividadNuevoNew != null && !idActividadNuevoNew.equals(idActividadNuevoOld)) {
                TblusuariosCreaActividad oldTblusuariosCreaActividadOfIdActividadNuevo = idActividadNuevoNew.getTblusuariosCreaActividad();
                if (oldTblusuariosCreaActividadOfIdActividadNuevo != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Tblactividad " + idActividadNuevoNew + " already has an item of type TblusuariosCreaActividad whose idActividadNuevo column cannot be null. Please make another selection for the idActividadNuevo field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsuarioCreaNew != null) {
                idUsuarioCreaNew = em.getReference(idUsuarioCreaNew.getClass(), idUsuarioCreaNew.getIdUsuario());
                tblusuariosCreaActividad.setIdUsuarioCrea(idUsuarioCreaNew);
            }
            if (idActividadNuevoNew != null) {
                idActividadNuevoNew = em.getReference(idActividadNuevoNew.getClass(), idActividadNuevoNew.getIdActividad());
                tblusuariosCreaActividad.setIdActividadNuevo(idActividadNuevoNew);
            }
            tblusuariosCreaActividad = em.merge(tblusuariosCreaActividad);
            if (idUsuarioCreaOld != null && !idUsuarioCreaOld.equals(idUsuarioCreaNew)) {
                idUsuarioCreaOld.getTblusuariosCreaActividadCollection().remove(tblusuariosCreaActividad);
                idUsuarioCreaOld = em.merge(idUsuarioCreaOld);
            }
            if (idUsuarioCreaNew != null && !idUsuarioCreaNew.equals(idUsuarioCreaOld)) {
                idUsuarioCreaNew.getTblusuariosCreaActividadCollection().add(tblusuariosCreaActividad);
                idUsuarioCreaNew = em.merge(idUsuarioCreaNew);
            }
            if (idActividadNuevoOld != null && !idActividadNuevoOld.equals(idActividadNuevoNew)) {
                idActividadNuevoOld.setTblusuariosCreaActividad(null);
                idActividadNuevoOld = em.merge(idActividadNuevoOld);
            }
            if (idActividadNuevoNew != null && !idActividadNuevoNew.equals(idActividadNuevoOld)) {
                idActividadNuevoNew.setTblusuariosCreaActividad(tblusuariosCreaActividad);
                idActividadNuevoNew = em.merge(idActividadNuevoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblusuariosCreaActividad.getIdCreador();
                if (findTblusuariosCreaActividad(id) == null) {
                    throw new NonexistentEntityException("The tblusuariosCreaActividad with id " + id + " no longer exists.");
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
            TblusuariosCreaActividad tblusuariosCreaActividad;
            try {
                tblusuariosCreaActividad = em.getReference(TblusuariosCreaActividad.class, id);
                tblusuariosCreaActividad.getIdCreador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblusuariosCreaActividad with id " + id + " no longer exists.", enfe);
            }
            Tblusuarios idUsuarioCrea = tblusuariosCreaActividad.getIdUsuarioCrea();
            if (idUsuarioCrea != null) {
                idUsuarioCrea.getTblusuariosCreaActividadCollection().remove(tblusuariosCreaActividad);
                idUsuarioCrea = em.merge(idUsuarioCrea);
            }
            Tblactividad idActividadNuevo = tblusuariosCreaActividad.getIdActividadNuevo();
            if (idActividadNuevo != null) {
                idActividadNuevo.setTblusuariosCreaActividad(null);
                idActividadNuevo = em.merge(idActividadNuevo);
            }
            em.remove(tblusuariosCreaActividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblusuariosCreaActividad> findTblusuariosCreaActividadEntities() {
        return findTblusuariosCreaActividadEntities(true, -1, -1);
    }

    public List<TblusuariosCreaActividad> findTblusuariosCreaActividadEntities(int maxResults, int firstResult) {
        return findTblusuariosCreaActividadEntities(false, maxResults, firstResult);
    }

    private List<TblusuariosCreaActividad> findTblusuariosCreaActividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TblusuariosCreaActividad as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TblusuariosCreaActividad findTblusuariosCreaActividad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblusuariosCreaActividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblusuariosCreaActividadCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TblusuariosCreaActividad as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
