/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.IllegalOrphanException;
import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.model.Tblbitacoradiaria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblusuarios;
import com.mgl.bitacoradiaria.model.Tblactividad;
import java.util.ArrayList;
import java.util.Collection;
import com.mgl.bitacoradiaria.model.Tblinterrupciones;

/**
 *
 * @author mike
 */
public class TblbitacoradiariaJpaController {

    public TblbitacoradiariaJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblbitacoradiaria tblbitacoradiaria) {
        if (tblbitacoradiaria.getTblactividadCollection() == null) {
            tblbitacoradiaria.setTblactividadCollection(new ArrayList<Tblactividad>());
        }
        if (tblbitacoradiaria.getTblinterrupcionesCollection() == null) {
            tblbitacoradiaria.setTblinterrupcionesCollection(new ArrayList<Tblinterrupciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblusuarios idUsuarioCreo = tblbitacoradiaria.getIdUsuarioCreo();
            if (idUsuarioCreo != null) {
                idUsuarioCreo = em.getReference(idUsuarioCreo.getClass(), idUsuarioCreo.getIdUsuario());
                tblbitacoradiaria.setIdUsuarioCreo(idUsuarioCreo);
            }
            Collection<Tblactividad> attachedTblactividadCollection = new ArrayList<Tblactividad>();
            for (Tblactividad tblactividadCollectionTblactividadToAttach : tblbitacoradiaria.getTblactividadCollection()) {
                tblactividadCollectionTblactividadToAttach = em.getReference(tblactividadCollectionTblactividadToAttach.getClass(), tblactividadCollectionTblactividadToAttach.getIdActividad());
                attachedTblactividadCollection.add(tblactividadCollectionTblactividadToAttach);
            }
            tblbitacoradiaria.setTblactividadCollection(attachedTblactividadCollection);
            Collection<Tblinterrupciones> attachedTblinterrupcionesCollection = new ArrayList<Tblinterrupciones>();
            for (Tblinterrupciones tblinterrupcionesCollectionTblinterrupcionesToAttach : tblbitacoradiaria.getTblinterrupcionesCollection()) {
                tblinterrupcionesCollectionTblinterrupcionesToAttach = em.getReference(tblinterrupcionesCollectionTblinterrupcionesToAttach.getClass(), tblinterrupcionesCollectionTblinterrupcionesToAttach.getIdInterrupcion());
                attachedTblinterrupcionesCollection.add(tblinterrupcionesCollectionTblinterrupcionesToAttach);
            }
            tblbitacoradiaria.setTblinterrupcionesCollection(attachedTblinterrupcionesCollection);
            em.persist(tblbitacoradiaria);
            if (idUsuarioCreo != null) {
                idUsuarioCreo.getTblbitacoradiariaCollection().add(tblbitacoradiaria);
                idUsuarioCreo = em.merge(idUsuarioCreo);
            }
            for (Tblactividad tblactividadCollectionTblactividad : tblbitacoradiaria.getTblactividadCollection()) {
                tblactividadCollectionTblactividad.getTblbitacoradiariaCollection().add(tblbitacoradiaria);
                tblactividadCollectionTblactividad = em.merge(tblactividadCollectionTblactividad);
            }
            for (Tblinterrupciones tblinterrupcionesCollectionTblinterrupciones : tblbitacoradiaria.getTblinterrupcionesCollection()) {
                Tblbitacoradiaria oldIdBitacoraPerteneceOfTblinterrupcionesCollectionTblinterrupciones = tblinterrupcionesCollectionTblinterrupciones.getIdBitacoraPertenece();
                tblinterrupcionesCollectionTblinterrupciones.setIdBitacoraPertenece(tblbitacoradiaria);
                tblinterrupcionesCollectionTblinterrupciones = em.merge(tblinterrupcionesCollectionTblinterrupciones);
                if (oldIdBitacoraPerteneceOfTblinterrupcionesCollectionTblinterrupciones != null) {
                    oldIdBitacoraPerteneceOfTblinterrupcionesCollectionTblinterrupciones.getTblinterrupcionesCollection().remove(tblinterrupcionesCollectionTblinterrupciones);
                    oldIdBitacoraPerteneceOfTblinterrupcionesCollectionTblinterrupciones = em.merge(oldIdBitacoraPerteneceOfTblinterrupcionesCollectionTblinterrupciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblbitacoradiaria tblbitacoradiaria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblbitacoradiaria persistentTblbitacoradiaria = em.find(Tblbitacoradiaria.class, tblbitacoradiaria.getIdBitacora());
            Tblusuarios idUsuarioCreoOld = persistentTblbitacoradiaria.getIdUsuarioCreo();
            Tblusuarios idUsuarioCreoNew = tblbitacoradiaria.getIdUsuarioCreo();
            Collection<Tblactividad> tblactividadCollectionOld = persistentTblbitacoradiaria.getTblactividadCollection();
            Collection<Tblactividad> tblactividadCollectionNew = tblbitacoradiaria.getTblactividadCollection();
            Collection<Tblinterrupciones> tblinterrupcionesCollectionOld = persistentTblbitacoradiaria.getTblinterrupcionesCollection();
            Collection<Tblinterrupciones> tblinterrupcionesCollectionNew = tblbitacoradiaria.getTblinterrupcionesCollection();
            List<String> illegalOrphanMessages = null;
            for (Tblinterrupciones tblinterrupcionesCollectionOldTblinterrupciones : tblinterrupcionesCollectionOld) {
                if (!tblinterrupcionesCollectionNew.contains(tblinterrupcionesCollectionOldTblinterrupciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblinterrupciones " + tblinterrupcionesCollectionOldTblinterrupciones + " since its idBitacoraPertenece field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsuarioCreoNew != null) {
                idUsuarioCreoNew = em.getReference(idUsuarioCreoNew.getClass(), idUsuarioCreoNew.getIdUsuario());
                tblbitacoradiaria.setIdUsuarioCreo(idUsuarioCreoNew);
            }
            Collection<Tblactividad> attachedTblactividadCollectionNew = new ArrayList<Tblactividad>();
            for (Tblactividad tblactividadCollectionNewTblactividadToAttach : tblactividadCollectionNew) {
                tblactividadCollectionNewTblactividadToAttach = em.getReference(tblactividadCollectionNewTblactividadToAttach.getClass(), tblactividadCollectionNewTblactividadToAttach.getIdActividad());
                attachedTblactividadCollectionNew.add(tblactividadCollectionNewTblactividadToAttach);
            }
            tblactividadCollectionNew = attachedTblactividadCollectionNew;
            tblbitacoradiaria.setTblactividadCollection(tblactividadCollectionNew);
            Collection<Tblinterrupciones> attachedTblinterrupcionesCollectionNew = new ArrayList<Tblinterrupciones>();
            for (Tblinterrupciones tblinterrupcionesCollectionNewTblinterrupcionesToAttach : tblinterrupcionesCollectionNew) {
                tblinterrupcionesCollectionNewTblinterrupcionesToAttach = em.getReference(tblinterrupcionesCollectionNewTblinterrupcionesToAttach.getClass(), tblinterrupcionesCollectionNewTblinterrupcionesToAttach.getIdInterrupcion());
                attachedTblinterrupcionesCollectionNew.add(tblinterrupcionesCollectionNewTblinterrupcionesToAttach);
            }
            tblinterrupcionesCollectionNew = attachedTblinterrupcionesCollectionNew;
            tblbitacoradiaria.setTblinterrupcionesCollection(tblinterrupcionesCollectionNew);
            tblbitacoradiaria = em.merge(tblbitacoradiaria);
            if (idUsuarioCreoOld != null && !idUsuarioCreoOld.equals(idUsuarioCreoNew)) {
                idUsuarioCreoOld.getTblbitacoradiariaCollection().remove(tblbitacoradiaria);
                idUsuarioCreoOld = em.merge(idUsuarioCreoOld);
            }
            if (idUsuarioCreoNew != null && !idUsuarioCreoNew.equals(idUsuarioCreoOld)) {
                idUsuarioCreoNew.getTblbitacoradiariaCollection().add(tblbitacoradiaria);
                idUsuarioCreoNew = em.merge(idUsuarioCreoNew);
            }
            for (Tblactividad tblactividadCollectionOldTblactividad : tblactividadCollectionOld) {
                if (!tblactividadCollectionNew.contains(tblactividadCollectionOldTblactividad)) {
                    tblactividadCollectionOldTblactividad.getTblbitacoradiariaCollection().remove(tblbitacoradiaria);
                    tblactividadCollectionOldTblactividad = em.merge(tblactividadCollectionOldTblactividad);
                }
            }
            for (Tblactividad tblactividadCollectionNewTblactividad : tblactividadCollectionNew) {
                if (!tblactividadCollectionOld.contains(tblactividadCollectionNewTblactividad)) {
                    tblactividadCollectionNewTblactividad.getTblbitacoradiariaCollection().add(tblbitacoradiaria);
                    tblactividadCollectionNewTblactividad = em.merge(tblactividadCollectionNewTblactividad);
                }
            }
            for (Tblinterrupciones tblinterrupcionesCollectionNewTblinterrupciones : tblinterrupcionesCollectionNew) {
                if (!tblinterrupcionesCollectionOld.contains(tblinterrupcionesCollectionNewTblinterrupciones)) {
                    Tblbitacoradiaria oldIdBitacoraPerteneceOfTblinterrupcionesCollectionNewTblinterrupciones = tblinterrupcionesCollectionNewTblinterrupciones.getIdBitacoraPertenece();
                    tblinterrupcionesCollectionNewTblinterrupciones.setIdBitacoraPertenece(tblbitacoradiaria);
                    tblinterrupcionesCollectionNewTblinterrupciones = em.merge(tblinterrupcionesCollectionNewTblinterrupciones);
                    if (oldIdBitacoraPerteneceOfTblinterrupcionesCollectionNewTblinterrupciones != null && !oldIdBitacoraPerteneceOfTblinterrupcionesCollectionNewTblinterrupciones.equals(tblbitacoradiaria)) {
                        oldIdBitacoraPerteneceOfTblinterrupcionesCollectionNewTblinterrupciones.getTblinterrupcionesCollection().remove(tblinterrupcionesCollectionNewTblinterrupciones);
                        oldIdBitacoraPerteneceOfTblinterrupcionesCollectionNewTblinterrupciones = em.merge(oldIdBitacoraPerteneceOfTblinterrupcionesCollectionNewTblinterrupciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblbitacoradiaria.getIdBitacora();
                if (findTblbitacoradiaria(id) == null) {
                    throw new NonexistentEntityException("The tblbitacoradiaria with id " + id + " no longer exists.");
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
            Tblbitacoradiaria tblbitacoradiaria;
            try {
                tblbitacoradiaria = em.getReference(Tblbitacoradiaria.class, id);
                tblbitacoradiaria.getIdBitacora();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblbitacoradiaria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tblinterrupciones> tblinterrupcionesCollectionOrphanCheck = tblbitacoradiaria.getTblinterrupcionesCollection();
            for (Tblinterrupciones tblinterrupcionesCollectionOrphanCheckTblinterrupciones : tblinterrupcionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblbitacoradiaria (" + tblbitacoradiaria + ") cannot be destroyed since the Tblinterrupciones " + tblinterrupcionesCollectionOrphanCheckTblinterrupciones + " in its tblinterrupcionesCollection field has a non-nullable idBitacoraPertenece field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tblusuarios idUsuarioCreo = tblbitacoradiaria.getIdUsuarioCreo();
            if (idUsuarioCreo != null) {
                idUsuarioCreo.getTblbitacoradiariaCollection().remove(tblbitacoradiaria);
                idUsuarioCreo = em.merge(idUsuarioCreo);
            }
            Collection<Tblactividad> tblactividadCollection = tblbitacoradiaria.getTblactividadCollection();
            for (Tblactividad tblactividadCollectionTblactividad : tblactividadCollection) {
                tblactividadCollectionTblactividad.getTblbitacoradiariaCollection().remove(tblbitacoradiaria);
                tblactividadCollectionTblactividad = em.merge(tblactividadCollectionTblactividad);
            }
            em.remove(tblbitacoradiaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblbitacoradiaria> findTblbitacoradiariaEntities() {
        return findTblbitacoradiariaEntities(true, -1, -1);
    }

    public List<Tblbitacoradiaria> findTblbitacoradiariaEntities(int maxResults, int firstResult) {
        return findTblbitacoradiariaEntities(false, maxResults, firstResult);
    }

    private List<Tblbitacoradiaria> findTblbitacoradiariaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tblbitacoradiaria as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tblbitacoradiaria findTblbitacoradiaria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblbitacoradiaria.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblbitacoradiariaCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Tblbitacoradiaria as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
