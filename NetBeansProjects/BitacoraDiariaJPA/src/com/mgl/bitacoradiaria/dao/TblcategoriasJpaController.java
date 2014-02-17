/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.IllegalOrphanException;
import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.model.Tblcategorias;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblactividad;
import java.util.ArrayList;
import java.util.Collection;
import com.mgl.bitacoradiaria.model.TblusuariosCreaCategorias;
import com.mgl.bitacoradiaria.model.Tblinterrupciones;

/**
 *
 * @author mike
 */
public class TblcategoriasJpaController {

    public TblcategoriasJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblcategorias tblcategorias) {
        if (tblcategorias.getTblactividadCollection() == null) {
            tblcategorias.setTblactividadCollection(new ArrayList<Tblactividad>());
        }
        if (tblcategorias.getTblusuariosCreaCategoriasCollection() == null) {
            tblcategorias.setTblusuariosCreaCategoriasCollection(new ArrayList<TblusuariosCreaCategorias>());
        }
        if (tblcategorias.getTblinterrupcionesCollection() == null) {
            tblcategorias.setTblinterrupcionesCollection(new ArrayList<Tblinterrupciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tblactividad> attachedTblactividadCollection = new ArrayList<Tblactividad>();
            for (Tblactividad tblactividadCollectionTblactividadToAttach : tblcategorias.getTblactividadCollection()) {
                tblactividadCollectionTblactividadToAttach = em.getReference(tblactividadCollectionTblactividadToAttach.getClass(), tblactividadCollectionTblactividadToAttach.getIdActividad());
                attachedTblactividadCollection.add(tblactividadCollectionTblactividadToAttach);
            }
            tblcategorias.setTblactividadCollection(attachedTblactividadCollection);
            Collection<TblusuariosCreaCategorias> attachedTblusuariosCreaCategoriasCollection = new ArrayList<TblusuariosCreaCategorias>();
            for (TblusuariosCreaCategorias tblusuariosCreaCategoriasCollectionTblusuariosCreaCategoriasToAttach : tblcategorias.getTblusuariosCreaCategoriasCollection()) {
                tblusuariosCreaCategoriasCollectionTblusuariosCreaCategoriasToAttach = em.getReference(tblusuariosCreaCategoriasCollectionTblusuariosCreaCategoriasToAttach.getClass(), tblusuariosCreaCategoriasCollectionTblusuariosCreaCategoriasToAttach.getIdCreador());
                attachedTblusuariosCreaCategoriasCollection.add(tblusuariosCreaCategoriasCollectionTblusuariosCreaCategoriasToAttach);
            }
            tblcategorias.setTblusuariosCreaCategoriasCollection(attachedTblusuariosCreaCategoriasCollection);
            Collection<Tblinterrupciones> attachedTblinterrupcionesCollection = new ArrayList<Tblinterrupciones>();
            for (Tblinterrupciones tblinterrupcionesCollectionTblinterrupcionesToAttach : tblcategorias.getTblinterrupcionesCollection()) {
                tblinterrupcionesCollectionTblinterrupcionesToAttach = em.getReference(tblinterrupcionesCollectionTblinterrupcionesToAttach.getClass(), tblinterrupcionesCollectionTblinterrupcionesToAttach.getIdInterrupcion());
                attachedTblinterrupcionesCollection.add(tblinterrupcionesCollectionTblinterrupcionesToAttach);
            }
            tblcategorias.setTblinterrupcionesCollection(attachedTblinterrupcionesCollection);
            em.persist(tblcategorias);
            for (Tblactividad tblactividadCollectionTblactividad : tblcategorias.getTblactividadCollection()) {
                Tblcategorias oldIdCategoriaPerteneceOfTblactividadCollectionTblactividad = tblactividadCollectionTblactividad.getIdCategoriaPertenece();
                tblactividadCollectionTblactividad.setIdCategoriaPertenece(tblcategorias);
                tblactividadCollectionTblactividad = em.merge(tblactividadCollectionTblactividad);
                if (oldIdCategoriaPerteneceOfTblactividadCollectionTblactividad != null) {
                    oldIdCategoriaPerteneceOfTblactividadCollectionTblactividad.getTblactividadCollection().remove(tblactividadCollectionTblactividad);
                    oldIdCategoriaPerteneceOfTblactividadCollectionTblactividad = em.merge(oldIdCategoriaPerteneceOfTblactividadCollectionTblactividad);
                }
            }
            for (TblusuariosCreaCategorias tblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias : tblcategorias.getTblusuariosCreaCategoriasCollection()) {
                Tblcategorias oldIdCategoriaNuevoOfTblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias = tblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias.getIdCategoriaNuevo();
                tblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias.setIdCategoriaNuevo(tblcategorias);
                tblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias = em.merge(tblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias);
                if (oldIdCategoriaNuevoOfTblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias != null) {
                    oldIdCategoriaNuevoOfTblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias.getTblusuariosCreaCategoriasCollection().remove(tblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias);
                    oldIdCategoriaNuevoOfTblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias = em.merge(oldIdCategoriaNuevoOfTblusuariosCreaCategoriasCollectionTblusuariosCreaCategorias);
                }
            }
            for (Tblinterrupciones tblinterrupcionesCollectionTblinterrupciones : tblcategorias.getTblinterrupcionesCollection()) {
                Tblcategorias oldIdCategoriaPerteneceOfTblinterrupcionesCollectionTblinterrupciones = tblinterrupcionesCollectionTblinterrupciones.getIdCategoriaPertenece();
                tblinterrupcionesCollectionTblinterrupciones.setIdCategoriaPertenece(tblcategorias);
                tblinterrupcionesCollectionTblinterrupciones = em.merge(tblinterrupcionesCollectionTblinterrupciones);
                if (oldIdCategoriaPerteneceOfTblinterrupcionesCollectionTblinterrupciones != null) {
                    oldIdCategoriaPerteneceOfTblinterrupcionesCollectionTblinterrupciones.getTblinterrupcionesCollection().remove(tblinterrupcionesCollectionTblinterrupciones);
                    oldIdCategoriaPerteneceOfTblinterrupcionesCollectionTblinterrupciones = em.merge(oldIdCategoriaPerteneceOfTblinterrupcionesCollectionTblinterrupciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblcategorias tblcategorias) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblcategorias persistentTblcategorias = em.find(Tblcategorias.class, tblcategorias.getIdCategoria());
            Collection<Tblactividad> tblactividadCollectionOld = persistentTblcategorias.getTblactividadCollection();
            Collection<Tblactividad> tblactividadCollectionNew = tblcategorias.getTblactividadCollection();
            Collection<TblusuariosCreaCategorias> tblusuariosCreaCategoriasCollectionOld = persistentTblcategorias.getTblusuariosCreaCategoriasCollection();
            Collection<TblusuariosCreaCategorias> tblusuariosCreaCategoriasCollectionNew = tblcategorias.getTblusuariosCreaCategoriasCollection();
            Collection<Tblinterrupciones> tblinterrupcionesCollectionOld = persistentTblcategorias.getTblinterrupcionesCollection();
            Collection<Tblinterrupciones> tblinterrupcionesCollectionNew = tblcategorias.getTblinterrupcionesCollection();
            List<String> illegalOrphanMessages = null;
            for (Tblactividad tblactividadCollectionOldTblactividad : tblactividadCollectionOld) {
                if (!tblactividadCollectionNew.contains(tblactividadCollectionOldTblactividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblactividad " + tblactividadCollectionOldTblactividad + " since its idCategoriaPertenece field is not nullable.");
                }
            }
            for (TblusuariosCreaCategorias tblusuariosCreaCategoriasCollectionOldTblusuariosCreaCategorias : tblusuariosCreaCategoriasCollectionOld) {
                if (!tblusuariosCreaCategoriasCollectionNew.contains(tblusuariosCreaCategoriasCollectionOldTblusuariosCreaCategorias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TblusuariosCreaCategorias " + tblusuariosCreaCategoriasCollectionOldTblusuariosCreaCategorias + " since its idCategoriaNuevo field is not nullable.");
                }
            }
            for (Tblinterrupciones tblinterrupcionesCollectionOldTblinterrupciones : tblinterrupcionesCollectionOld) {
                if (!tblinterrupcionesCollectionNew.contains(tblinterrupcionesCollectionOldTblinterrupciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblinterrupciones " + tblinterrupcionesCollectionOldTblinterrupciones + " since its idCategoriaPertenece field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tblactividad> attachedTblactividadCollectionNew = new ArrayList<Tblactividad>();
            for (Tblactividad tblactividadCollectionNewTblactividadToAttach : tblactividadCollectionNew) {
                tblactividadCollectionNewTblactividadToAttach = em.getReference(tblactividadCollectionNewTblactividadToAttach.getClass(), tblactividadCollectionNewTblactividadToAttach.getIdActividad());
                attachedTblactividadCollectionNew.add(tblactividadCollectionNewTblactividadToAttach);
            }
            tblactividadCollectionNew = attachedTblactividadCollectionNew;
            tblcategorias.setTblactividadCollection(tblactividadCollectionNew);
            Collection<TblusuariosCreaCategorias> attachedTblusuariosCreaCategoriasCollectionNew = new ArrayList<TblusuariosCreaCategorias>();
            for (TblusuariosCreaCategorias tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategoriasToAttach : tblusuariosCreaCategoriasCollectionNew) {
                tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategoriasToAttach = em.getReference(tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategoriasToAttach.getClass(), tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategoriasToAttach.getIdCreador());
                attachedTblusuariosCreaCategoriasCollectionNew.add(tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategoriasToAttach);
            }
            tblusuariosCreaCategoriasCollectionNew = attachedTblusuariosCreaCategoriasCollectionNew;
            tblcategorias.setTblusuariosCreaCategoriasCollection(tblusuariosCreaCategoriasCollectionNew);
            Collection<Tblinterrupciones> attachedTblinterrupcionesCollectionNew = new ArrayList<Tblinterrupciones>();
            for (Tblinterrupciones tblinterrupcionesCollectionNewTblinterrupcionesToAttach : tblinterrupcionesCollectionNew) {
                tblinterrupcionesCollectionNewTblinterrupcionesToAttach = em.getReference(tblinterrupcionesCollectionNewTblinterrupcionesToAttach.getClass(), tblinterrupcionesCollectionNewTblinterrupcionesToAttach.getIdInterrupcion());
                attachedTblinterrupcionesCollectionNew.add(tblinterrupcionesCollectionNewTblinterrupcionesToAttach);
            }
            tblinterrupcionesCollectionNew = attachedTblinterrupcionesCollectionNew;
            tblcategorias.setTblinterrupcionesCollection(tblinterrupcionesCollectionNew);
            tblcategorias = em.merge(tblcategorias);
            for (Tblactividad tblactividadCollectionNewTblactividad : tblactividadCollectionNew) {
                if (!tblactividadCollectionOld.contains(tblactividadCollectionNewTblactividad)) {
                    Tblcategorias oldIdCategoriaPerteneceOfTblactividadCollectionNewTblactividad = tblactividadCollectionNewTblactividad.getIdCategoriaPertenece();
                    tblactividadCollectionNewTblactividad.setIdCategoriaPertenece(tblcategorias);
                    tblactividadCollectionNewTblactividad = em.merge(tblactividadCollectionNewTblactividad);
                    if (oldIdCategoriaPerteneceOfTblactividadCollectionNewTblactividad != null && !oldIdCategoriaPerteneceOfTblactividadCollectionNewTblactividad.equals(tblcategorias)) {
                        oldIdCategoriaPerteneceOfTblactividadCollectionNewTblactividad.getTblactividadCollection().remove(tblactividadCollectionNewTblactividad);
                        oldIdCategoriaPerteneceOfTblactividadCollectionNewTblactividad = em.merge(oldIdCategoriaPerteneceOfTblactividadCollectionNewTblactividad);
                    }
                }
            }
            for (TblusuariosCreaCategorias tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias : tblusuariosCreaCategoriasCollectionNew) {
                if (!tblusuariosCreaCategoriasCollectionOld.contains(tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias)) {
                    Tblcategorias oldIdCategoriaNuevoOfTblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias = tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias.getIdCategoriaNuevo();
                    tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias.setIdCategoriaNuevo(tblcategorias);
                    tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias = em.merge(tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias);
                    if (oldIdCategoriaNuevoOfTblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias != null && !oldIdCategoriaNuevoOfTblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias.equals(tblcategorias)) {
                        oldIdCategoriaNuevoOfTblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias.getTblusuariosCreaCategoriasCollection().remove(tblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias);
                        oldIdCategoriaNuevoOfTblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias = em.merge(oldIdCategoriaNuevoOfTblusuariosCreaCategoriasCollectionNewTblusuariosCreaCategorias);
                    }
                }
            }
            for (Tblinterrupciones tblinterrupcionesCollectionNewTblinterrupciones : tblinterrupcionesCollectionNew) {
                if (!tblinterrupcionesCollectionOld.contains(tblinterrupcionesCollectionNewTblinterrupciones)) {
                    Tblcategorias oldIdCategoriaPerteneceOfTblinterrupcionesCollectionNewTblinterrupciones = tblinterrupcionesCollectionNewTblinterrupciones.getIdCategoriaPertenece();
                    tblinterrupcionesCollectionNewTblinterrupciones.setIdCategoriaPertenece(tblcategorias);
                    tblinterrupcionesCollectionNewTblinterrupciones = em.merge(tblinterrupcionesCollectionNewTblinterrupciones);
                    if (oldIdCategoriaPerteneceOfTblinterrupcionesCollectionNewTblinterrupciones != null && !oldIdCategoriaPerteneceOfTblinterrupcionesCollectionNewTblinterrupciones.equals(tblcategorias)) {
                        oldIdCategoriaPerteneceOfTblinterrupcionesCollectionNewTblinterrupciones.getTblinterrupcionesCollection().remove(tblinterrupcionesCollectionNewTblinterrupciones);
                        oldIdCategoriaPerteneceOfTblinterrupcionesCollectionNewTblinterrupciones = em.merge(oldIdCategoriaPerteneceOfTblinterrupcionesCollectionNewTblinterrupciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblcategorias.getIdCategoria();
                if (findTblcategorias(id) == null) {
                    throw new NonexistentEntityException("The tblcategorias with id " + id + " no longer exists.");
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
            Tblcategorias tblcategorias;
            try {
                tblcategorias = em.getReference(Tblcategorias.class, id);
                tblcategorias.getIdCategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblcategorias with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tblactividad> tblactividadCollectionOrphanCheck = tblcategorias.getTblactividadCollection();
            for (Tblactividad tblactividadCollectionOrphanCheckTblactividad : tblactividadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblcategorias (" + tblcategorias + ") cannot be destroyed since the Tblactividad " + tblactividadCollectionOrphanCheckTblactividad + " in its tblactividadCollection field has a non-nullable idCategoriaPertenece field.");
            }
            Collection<TblusuariosCreaCategorias> tblusuariosCreaCategoriasCollectionOrphanCheck = tblcategorias.getTblusuariosCreaCategoriasCollection();
            for (TblusuariosCreaCategorias tblusuariosCreaCategoriasCollectionOrphanCheckTblusuariosCreaCategorias : tblusuariosCreaCategoriasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblcategorias (" + tblcategorias + ") cannot be destroyed since the TblusuariosCreaCategorias " + tblusuariosCreaCategoriasCollectionOrphanCheckTblusuariosCreaCategorias + " in its tblusuariosCreaCategoriasCollection field has a non-nullable idCategoriaNuevo field.");
            }
            Collection<Tblinterrupciones> tblinterrupcionesCollectionOrphanCheck = tblcategorias.getTblinterrupcionesCollection();
            for (Tblinterrupciones tblinterrupcionesCollectionOrphanCheckTblinterrupciones : tblinterrupcionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblcategorias (" + tblcategorias + ") cannot be destroyed since the Tblinterrupciones " + tblinterrupcionesCollectionOrphanCheckTblinterrupciones + " in its tblinterrupcionesCollection field has a non-nullable idCategoriaPertenece field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tblcategorias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblcategorias> findTblcategoriasEntities() {
        return findTblcategoriasEntities(true, -1, -1);
    }

    public List<Tblcategorias> findTblcategoriasEntities(int maxResults, int firstResult) {
        return findTblcategoriasEntities(false, maxResults, firstResult);
    }

    private List<Tblcategorias> findTblcategoriasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tblcategorias as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tblcategorias findTblcategorias(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblcategorias.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblcategoriasCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Tblcategorias as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
