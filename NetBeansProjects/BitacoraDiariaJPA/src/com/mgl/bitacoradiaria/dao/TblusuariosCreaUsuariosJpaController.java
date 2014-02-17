/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.model.TblusuariosCreaUsuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblusuarios;

/**
 *
 * @author mike
 */
public class TblusuariosCreaUsuariosJpaController {

    public TblusuariosCreaUsuariosJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblusuariosCreaUsuarios tblusuariosCreaUsuarios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblusuarios idUsuarioCreador = tblusuariosCreaUsuarios.getIdUsuarioCreador();
            if (idUsuarioCreador != null) {
                idUsuarioCreador = em.getReference(idUsuarioCreador.getClass(), idUsuarioCreador.getIdUsuario());
                tblusuariosCreaUsuarios.setIdUsuarioCreador(idUsuarioCreador);
            }
            Tblusuarios tblusuariosIdUsuario = tblusuariosCreaUsuarios.getTblusuariosIdUsuario();
            if (tblusuariosIdUsuario != null) {
                tblusuariosIdUsuario = em.getReference(tblusuariosIdUsuario.getClass(), tblusuariosIdUsuario.getIdUsuario());
                tblusuariosCreaUsuarios.setTblusuariosIdUsuario(tblusuariosIdUsuario);
            }
            em.persist(tblusuariosCreaUsuarios);
            if (idUsuarioCreador != null) {
                idUsuarioCreador.getTblusuariosCreaUsuariosCollection().add(tblusuariosCreaUsuarios);
                idUsuarioCreador = em.merge(idUsuarioCreador);
            }
            if (tblusuariosIdUsuario != null) {
                tblusuariosIdUsuario.getTblusuariosCreaUsuariosCollection().add(tblusuariosCreaUsuarios);
                tblusuariosIdUsuario = em.merge(tblusuariosIdUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblusuariosCreaUsuarios tblusuariosCreaUsuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblusuariosCreaUsuarios persistentTblusuariosCreaUsuarios = em.find(TblusuariosCreaUsuarios.class, tblusuariosCreaUsuarios.getIdCreador());
            Tblusuarios idUsuarioCreadorOld = persistentTblusuariosCreaUsuarios.getIdUsuarioCreador();
            Tblusuarios idUsuarioCreadorNew = tblusuariosCreaUsuarios.getIdUsuarioCreador();
            Tblusuarios tblusuariosIdUsuarioOld = persistentTblusuariosCreaUsuarios.getTblusuariosIdUsuario();
            Tblusuarios tblusuariosIdUsuarioNew = tblusuariosCreaUsuarios.getTblusuariosIdUsuario();
            if (idUsuarioCreadorNew != null) {
                idUsuarioCreadorNew = em.getReference(idUsuarioCreadorNew.getClass(), idUsuarioCreadorNew.getIdUsuario());
                tblusuariosCreaUsuarios.setIdUsuarioCreador(idUsuarioCreadorNew);
            }
            if (tblusuariosIdUsuarioNew != null) {
                tblusuariosIdUsuarioNew = em.getReference(tblusuariosIdUsuarioNew.getClass(), tblusuariosIdUsuarioNew.getIdUsuario());
                tblusuariosCreaUsuarios.setTblusuariosIdUsuario(tblusuariosIdUsuarioNew);
            }
            tblusuariosCreaUsuarios = em.merge(tblusuariosCreaUsuarios);
            if (idUsuarioCreadorOld != null && !idUsuarioCreadorOld.equals(idUsuarioCreadorNew)) {
                idUsuarioCreadorOld.getTblusuariosCreaUsuariosCollection().remove(tblusuariosCreaUsuarios);
                idUsuarioCreadorOld = em.merge(idUsuarioCreadorOld);
            }
            if (idUsuarioCreadorNew != null && !idUsuarioCreadorNew.equals(idUsuarioCreadorOld)) {
                idUsuarioCreadorNew.getTblusuariosCreaUsuariosCollection().add(tblusuariosCreaUsuarios);
                idUsuarioCreadorNew = em.merge(idUsuarioCreadorNew);
            }
            if (tblusuariosIdUsuarioOld != null && !tblusuariosIdUsuarioOld.equals(tblusuariosIdUsuarioNew)) {
                tblusuariosIdUsuarioOld.getTblusuariosCreaUsuariosCollection().remove(tblusuariosCreaUsuarios);
                tblusuariosIdUsuarioOld = em.merge(tblusuariosIdUsuarioOld);
            }
            if (tblusuariosIdUsuarioNew != null && !tblusuariosIdUsuarioNew.equals(tblusuariosIdUsuarioOld)) {
                tblusuariosIdUsuarioNew.getTblusuariosCreaUsuariosCollection().add(tblusuariosCreaUsuarios);
                tblusuariosIdUsuarioNew = em.merge(tblusuariosIdUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblusuariosCreaUsuarios.getIdCreador();
                if (findTblusuariosCreaUsuarios(id) == null) {
                    throw new NonexistentEntityException("The tblusuariosCreaUsuarios with id " + id + " no longer exists.");
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
            TblusuariosCreaUsuarios tblusuariosCreaUsuarios;
            try {
                tblusuariosCreaUsuarios = em.getReference(TblusuariosCreaUsuarios.class, id);
                tblusuariosCreaUsuarios.getIdCreador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblusuariosCreaUsuarios with id " + id + " no longer exists.", enfe);
            }
            Tblusuarios idUsuarioCreador = tblusuariosCreaUsuarios.getIdUsuarioCreador();
            if (idUsuarioCreador != null) {
                idUsuarioCreador.getTblusuariosCreaUsuariosCollection().remove(tblusuariosCreaUsuarios);
                idUsuarioCreador = em.merge(idUsuarioCreador);
            }
            Tblusuarios tblusuariosIdUsuario = tblusuariosCreaUsuarios.getTblusuariosIdUsuario();
            if (tblusuariosIdUsuario != null) {
                tblusuariosIdUsuario.getTblusuariosCreaUsuariosCollection().remove(tblusuariosCreaUsuarios);
                tblusuariosIdUsuario = em.merge(tblusuariosIdUsuario);
            }
            em.remove(tblusuariosCreaUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblusuariosCreaUsuarios> findTblusuariosCreaUsuariosEntities() {
        return findTblusuariosCreaUsuariosEntities(true, -1, -1);
    }

    public List<TblusuariosCreaUsuarios> findTblusuariosCreaUsuariosEntities(int maxResults, int firstResult) {
        return findTblusuariosCreaUsuariosEntities(false, maxResults, firstResult);
    }

    private List<TblusuariosCreaUsuarios> findTblusuariosCreaUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TblusuariosCreaUsuarios as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TblusuariosCreaUsuarios findTblusuariosCreaUsuarios(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblusuariosCreaUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblusuariosCreaUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TblusuariosCreaUsuarios as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
