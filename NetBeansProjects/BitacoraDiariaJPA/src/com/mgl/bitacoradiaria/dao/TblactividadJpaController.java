/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.IllegalOrphanException;
import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.model.Tblactividad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblcategorias;
import com.mgl.bitacoradiaria.model.TblusuariosCreaActividad;
import com.mgl.bitacoradiaria.model.Tblbitacoradiaria;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author mike
 */
public class TblactividadJpaController {

    public TblactividadJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblactividad tblactividad) {
        if (tblactividad.getTblbitacoradiariaCollection() == null) {
            tblactividad.setTblbitacoradiariaCollection(new ArrayList<Tblbitacoradiaria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblcategorias idCategoriaPertenece = tblactividad.getIdCategoriaPertenece();
            if (idCategoriaPertenece != null) {
                idCategoriaPertenece = em.getReference(idCategoriaPertenece.getClass(), idCategoriaPertenece.getIdCategoria());
                tblactividad.setIdCategoriaPertenece(idCategoriaPertenece);
            }
            TblusuariosCreaActividad tblusuariosCreaActividad = tblactividad.getTblusuariosCreaActividad();
            if (tblusuariosCreaActividad != null) {
                tblusuariosCreaActividad = em.getReference(tblusuariosCreaActividad.getClass(), tblusuariosCreaActividad.getIdCreador());
                tblactividad.setTblusuariosCreaActividad(tblusuariosCreaActividad);
            }
            Collection<Tblbitacoradiaria> attachedTblbitacoradiariaCollection = new ArrayList<Tblbitacoradiaria>();
            for (Tblbitacoradiaria tblbitacoradiariaCollectionTblbitacoradiariaToAttach : tblactividad.getTblbitacoradiariaCollection()) {
                tblbitacoradiariaCollectionTblbitacoradiariaToAttach = em.getReference(tblbitacoradiariaCollectionTblbitacoradiariaToAttach.getClass(), tblbitacoradiariaCollectionTblbitacoradiariaToAttach.getIdBitacora());
                attachedTblbitacoradiariaCollection.add(tblbitacoradiariaCollectionTblbitacoradiariaToAttach);
            }
            tblactividad.setTblbitacoradiariaCollection(attachedTblbitacoradiariaCollection);
            em.persist(tblactividad);
            if (idCategoriaPertenece != null) {
                idCategoriaPertenece.getTblactividadCollection().add(tblactividad);
                idCategoriaPertenece = em.merge(idCategoriaPertenece);
            }
            if (tblusuariosCreaActividad != null) {
                Tblactividad oldIdActividadNuevoOfTblusuariosCreaActividad = tblusuariosCreaActividad.getIdActividadNuevo();
                if (oldIdActividadNuevoOfTblusuariosCreaActividad != null) {
                    oldIdActividadNuevoOfTblusuariosCreaActividad.setTblusuariosCreaActividad(null);
                    oldIdActividadNuevoOfTblusuariosCreaActividad = em.merge(oldIdActividadNuevoOfTblusuariosCreaActividad);
                }
                tblusuariosCreaActividad.setIdActividadNuevo(tblactividad);
                tblusuariosCreaActividad = em.merge(tblusuariosCreaActividad);
            }
            for (Tblbitacoradiaria tblbitacoradiariaCollectionTblbitacoradiaria : tblactividad.getTblbitacoradiariaCollection()) {
                tblbitacoradiariaCollectionTblbitacoradiaria.getTblactividadCollection().add(tblactividad);
                tblbitacoradiariaCollectionTblbitacoradiaria = em.merge(tblbitacoradiariaCollectionTblbitacoradiaria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblactividad tblactividad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblactividad persistentTblactividad = em.find(Tblactividad.class, tblactividad.getIdActividad());
            Tblcategorias idCategoriaPerteneceOld = persistentTblactividad.getIdCategoriaPertenece();
            Tblcategorias idCategoriaPerteneceNew = tblactividad.getIdCategoriaPertenece();
            TblusuariosCreaActividad tblusuariosCreaActividadOld = persistentTblactividad.getTblusuariosCreaActividad();
            TblusuariosCreaActividad tblusuariosCreaActividadNew = tblactividad.getTblusuariosCreaActividad();
            Collection<Tblbitacoradiaria> tblbitacoradiariaCollectionOld = persistentTblactividad.getTblbitacoradiariaCollection();
            Collection<Tblbitacoradiaria> tblbitacoradiariaCollectionNew = tblactividad.getTblbitacoradiariaCollection();
            List<String> illegalOrphanMessages = null;
            if (tblusuariosCreaActividadOld != null && !tblusuariosCreaActividadOld.equals(tblusuariosCreaActividadNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain TblusuariosCreaActividad " + tblusuariosCreaActividadOld + " since its idActividadNuevo field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCategoriaPerteneceNew != null) {
                idCategoriaPerteneceNew = em.getReference(idCategoriaPerteneceNew.getClass(), idCategoriaPerteneceNew.getIdCategoria());
                tblactividad.setIdCategoriaPertenece(idCategoriaPerteneceNew);
            }
            if (tblusuariosCreaActividadNew != null) {
                tblusuariosCreaActividadNew = em.getReference(tblusuariosCreaActividadNew.getClass(), tblusuariosCreaActividadNew.getIdCreador());
                tblactividad.setTblusuariosCreaActividad(tblusuariosCreaActividadNew);
            }
            Collection<Tblbitacoradiaria> attachedTblbitacoradiariaCollectionNew = new ArrayList<Tblbitacoradiaria>();
            for (Tblbitacoradiaria tblbitacoradiariaCollectionNewTblbitacoradiariaToAttach : tblbitacoradiariaCollectionNew) {
                tblbitacoradiariaCollectionNewTblbitacoradiariaToAttach = em.getReference(tblbitacoradiariaCollectionNewTblbitacoradiariaToAttach.getClass(), tblbitacoradiariaCollectionNewTblbitacoradiariaToAttach.getIdBitacora());
                attachedTblbitacoradiariaCollectionNew.add(tblbitacoradiariaCollectionNewTblbitacoradiariaToAttach);
            }
            tblbitacoradiariaCollectionNew = attachedTblbitacoradiariaCollectionNew;
            tblactividad.setTblbitacoradiariaCollection(tblbitacoradiariaCollectionNew);
            tblactividad = em.merge(tblactividad);
            if (idCategoriaPerteneceOld != null && !idCategoriaPerteneceOld.equals(idCategoriaPerteneceNew)) {
                idCategoriaPerteneceOld.getTblactividadCollection().remove(tblactividad);
                idCategoriaPerteneceOld = em.merge(idCategoriaPerteneceOld);
            }
            if (idCategoriaPerteneceNew != null && !idCategoriaPerteneceNew.equals(idCategoriaPerteneceOld)) {
                idCategoriaPerteneceNew.getTblactividadCollection().add(tblactividad);
                idCategoriaPerteneceNew = em.merge(idCategoriaPerteneceNew);
            }
            if (tblusuariosCreaActividadNew != null && !tblusuariosCreaActividadNew.equals(tblusuariosCreaActividadOld)) {
                Tblactividad oldIdActividadNuevoOfTblusuariosCreaActividad = tblusuariosCreaActividadNew.getIdActividadNuevo();
                if (oldIdActividadNuevoOfTblusuariosCreaActividad != null) {
                    oldIdActividadNuevoOfTblusuariosCreaActividad.setTblusuariosCreaActividad(null);
                    oldIdActividadNuevoOfTblusuariosCreaActividad = em.merge(oldIdActividadNuevoOfTblusuariosCreaActividad);
                }
                tblusuariosCreaActividadNew.setIdActividadNuevo(tblactividad);
                tblusuariosCreaActividadNew = em.merge(tblusuariosCreaActividadNew);
            }
            for (Tblbitacoradiaria tblbitacoradiariaCollectionOldTblbitacoradiaria : tblbitacoradiariaCollectionOld) {
                if (!tblbitacoradiariaCollectionNew.contains(tblbitacoradiariaCollectionOldTblbitacoradiaria)) {
                    tblbitacoradiariaCollectionOldTblbitacoradiaria.getTblactividadCollection().remove(tblactividad);
                    tblbitacoradiariaCollectionOldTblbitacoradiaria = em.merge(tblbitacoradiariaCollectionOldTblbitacoradiaria);
                }
            }
            for (Tblbitacoradiaria tblbitacoradiariaCollectionNewTblbitacoradiaria : tblbitacoradiariaCollectionNew) {
                if (!tblbitacoradiariaCollectionOld.contains(tblbitacoradiariaCollectionNewTblbitacoradiaria)) {
                    tblbitacoradiariaCollectionNewTblbitacoradiaria.getTblactividadCollection().add(tblactividad);
                    tblbitacoradiariaCollectionNewTblbitacoradiaria = em.merge(tblbitacoradiariaCollectionNewTblbitacoradiaria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblactividad.getIdActividad();
                if (findTblactividad(id) == null) {
                    throw new NonexistentEntityException("The tblactividad with id " + id + " no longer exists.");
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
            Tblactividad tblactividad;
            try {
                tblactividad = em.getReference(Tblactividad.class, id);
                tblactividad.getIdActividad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblactividad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            TblusuariosCreaActividad tblusuariosCreaActividadOrphanCheck = tblactividad.getTblusuariosCreaActividad();
            if (tblusuariosCreaActividadOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblactividad (" + tblactividad + ") cannot be destroyed since the TblusuariosCreaActividad " + tblusuariosCreaActividadOrphanCheck + " in its tblusuariosCreaActividad field has a non-nullable idActividadNuevo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tblcategorias idCategoriaPertenece = tblactividad.getIdCategoriaPertenece();
            if (idCategoriaPertenece != null) {
                idCategoriaPertenece.getTblactividadCollection().remove(tblactividad);
                idCategoriaPertenece = em.merge(idCategoriaPertenece);
            }
            Collection<Tblbitacoradiaria> tblbitacoradiariaCollection = tblactividad.getTblbitacoradiariaCollection();
            for (Tblbitacoradiaria tblbitacoradiariaCollectionTblbitacoradiaria : tblbitacoradiariaCollection) {
                tblbitacoradiariaCollectionTblbitacoradiaria.getTblactividadCollection().remove(tblactividad);
                tblbitacoradiariaCollectionTblbitacoradiaria = em.merge(tblbitacoradiariaCollectionTblbitacoradiaria);
            }
            em.remove(tblactividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblactividad> findTblactividadEntities() {
        return findTblactividadEntities(true, -1, -1);
    }

    public List<Tblactividad> findTblactividadEntities(int maxResults, int firstResult) {
        return findTblactividadEntities(false, maxResults, firstResult);
    }

    private List<Tblactividad> findTblactividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tblactividad as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tblactividad findTblactividad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblactividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblactividadCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Tblactividad as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
