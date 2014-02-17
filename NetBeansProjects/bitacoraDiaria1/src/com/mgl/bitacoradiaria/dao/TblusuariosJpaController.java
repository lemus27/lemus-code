/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import bitacoradiaria.exceptions.IllegalOrphanException;
import bitacoradiaria.exceptions.NonexistentEntityException;
import bitacoradiaria.exceptions.PreexistingEntityException;
import com.mgl.bitacoradiaria.model.Tblusuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblperfiles;
import java.util.ArrayList;
import java.util.Collection;
import com.mgl.bitacoradiaria.model.Tblactividad;
import com.mgl.bitacoradiaria.model.Tblinterrupciones;

/**
 *
 * @author mike
 */
public class TblusuariosJpaController {

    public TblusuariosJpaController() {
        emf = Persistence.createEntityManagerFactory("bitacoraDiariaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblusuarios tblusuarios) throws PreexistingEntityException, Exception {
        if (tblusuarios.getTblperfilesCollection() == null) {
            tblusuarios.setTblperfilesCollection(new ArrayList<Tblperfiles>());
        }
        if (tblusuarios.getTblactividadCollection() == null) {
            tblusuarios.setTblactividadCollection(new ArrayList<Tblactividad>());
        }
        if (tblusuarios.getTblinterrupcionesCollection() == null) {
            tblusuarios.setTblinterrupcionesCollection(new ArrayList<Tblinterrupciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tblperfiles> attachedTblperfilesCollection = new ArrayList<Tblperfiles>();
            for (Tblperfiles tblperfilesCollectionTblperfilesToAttach : tblusuarios.getTblperfilesCollection()) {
                tblperfilesCollectionTblperfilesToAttach = em.getReference(tblperfilesCollectionTblperfilesToAttach.getClass(), tblperfilesCollectionTblperfilesToAttach.getIdPerfil());
                attachedTblperfilesCollection.add(tblperfilesCollectionTblperfilesToAttach);
            }
            tblusuarios.setTblperfilesCollection(attachedTblperfilesCollection);
            Collection<Tblactividad> attachedTblactividadCollection = new ArrayList<Tblactividad>();
            for (Tblactividad tblactividadCollectionTblactividadToAttach : tblusuarios.getTblactividadCollection()) {
                tblactividadCollectionTblactividadToAttach = em.getReference(tblactividadCollectionTblactividadToAttach.getClass(), tblactividadCollectionTblactividadToAttach.getIdActividad());
                attachedTblactividadCollection.add(tblactividadCollectionTblactividadToAttach);
            }
            tblusuarios.setTblactividadCollection(attachedTblactividadCollection);
            Collection<Tblinterrupciones> attachedTblinterrupcionesCollection = new ArrayList<Tblinterrupciones>();
            for (Tblinterrupciones tblinterrupcionesCollectionTblinterrupcionesToAttach : tblusuarios.getTblinterrupcionesCollection()) {
                tblinterrupcionesCollectionTblinterrupcionesToAttach = em.getReference(tblinterrupcionesCollectionTblinterrupcionesToAttach.getClass(), tblinterrupcionesCollectionTblinterrupcionesToAttach.getIdInterrupcion());
                attachedTblinterrupcionesCollection.add(tblinterrupcionesCollectionTblinterrupcionesToAttach);
            }
            tblusuarios.setTblinterrupcionesCollection(attachedTblinterrupcionesCollection);
            em.persist(tblusuarios);
            for (Tblperfiles tblperfilesCollectionTblperfiles : tblusuarios.getTblperfilesCollection()) {
                Tblusuarios oldTblusuariosIdUsuarioOfTblperfilesCollectionTblperfiles = tblperfilesCollectionTblperfiles.getTblusuariosIdUsuario();
                tblperfilesCollectionTblperfiles.setTblusuariosIdUsuario(tblusuarios);
                tblperfilesCollectionTblperfiles = em.merge(tblperfilesCollectionTblperfiles);
                if (oldTblusuariosIdUsuarioOfTblperfilesCollectionTblperfiles != null) {
                    oldTblusuariosIdUsuarioOfTblperfilesCollectionTblperfiles.getTblperfilesCollection().remove(tblperfilesCollectionTblperfiles);
                    oldTblusuariosIdUsuarioOfTblperfilesCollectionTblperfiles = em.merge(oldTblusuariosIdUsuarioOfTblperfilesCollectionTblperfiles);
                }
            }
            for (Tblactividad tblactividadCollectionTblactividad : tblusuarios.getTblactividadCollection()) {
                Tblusuarios oldTblusuariosIdUsuarioOfTblactividadCollectionTblactividad = tblactividadCollectionTblactividad.getTblusuariosIdUsuario();
                tblactividadCollectionTblactividad.setTblusuariosIdUsuario(tblusuarios);
                tblactividadCollectionTblactividad = em.merge(tblactividadCollectionTblactividad);
                if (oldTblusuariosIdUsuarioOfTblactividadCollectionTblactividad != null) {
                    oldTblusuariosIdUsuarioOfTblactividadCollectionTblactividad.getTblactividadCollection().remove(tblactividadCollectionTblactividad);
                    oldTblusuariosIdUsuarioOfTblactividadCollectionTblactividad = em.merge(oldTblusuariosIdUsuarioOfTblactividadCollectionTblactividad);
                }
            }
            for (Tblinterrupciones tblinterrupcionesCollectionTblinterrupciones : tblusuarios.getTblinterrupcionesCollection()) {
                Tblusuarios oldTblusuariosIdUsuarioOfTblinterrupcionesCollectionTblinterrupciones = tblinterrupcionesCollectionTblinterrupciones.getTblusuariosIdUsuario();
                tblinterrupcionesCollectionTblinterrupciones.setTblusuariosIdUsuario(tblusuarios);
                tblinterrupcionesCollectionTblinterrupciones = em.merge(tblinterrupcionesCollectionTblinterrupciones);
                if (oldTblusuariosIdUsuarioOfTblinterrupcionesCollectionTblinterrupciones != null) {
                    oldTblusuariosIdUsuarioOfTblinterrupcionesCollectionTblinterrupciones.getTblinterrupcionesCollection().remove(tblinterrupcionesCollectionTblinterrupciones);
                    oldTblusuariosIdUsuarioOfTblinterrupcionesCollectionTblinterrupciones = em.merge(oldTblusuariosIdUsuarioOfTblinterrupcionesCollectionTblinterrupciones);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTblusuarios(tblusuarios.getIdUsuario()) != null) {
                throw new PreexistingEntityException("Tblusuarios " + tblusuarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblusuarios tblusuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblusuarios persistentTblusuarios = em.find(Tblusuarios.class, tblusuarios.getIdUsuario());
            Collection<Tblperfiles> tblperfilesCollectionOld = persistentTblusuarios.getTblperfilesCollection();
            Collection<Tblperfiles> tblperfilesCollectionNew = tblusuarios.getTblperfilesCollection();
            Collection<Tblactividad> tblactividadCollectionOld = persistentTblusuarios.getTblactividadCollection();
            Collection<Tblactividad> tblactividadCollectionNew = tblusuarios.getTblactividadCollection();
            Collection<Tblinterrupciones> tblinterrupcionesCollectionOld = persistentTblusuarios.getTblinterrupcionesCollection();
            Collection<Tblinterrupciones> tblinterrupcionesCollectionNew = tblusuarios.getTblinterrupcionesCollection();
            List<String> illegalOrphanMessages = null;
            for (Tblperfiles tblperfilesCollectionOldTblperfiles : tblperfilesCollectionOld) {
                if (!tblperfilesCollectionNew.contains(tblperfilesCollectionOldTblperfiles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblperfiles " + tblperfilesCollectionOldTblperfiles + " since its tblusuariosIdUsuario field is not nullable.");
                }
            }
            for (Tblactividad tblactividadCollectionOldTblactividad : tblactividadCollectionOld) {
                if (!tblactividadCollectionNew.contains(tblactividadCollectionOldTblactividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblactividad " + tblactividadCollectionOldTblactividad + " since its tblusuariosIdUsuario field is not nullable.");
                }
            }
            for (Tblinterrupciones tblinterrupcionesCollectionOldTblinterrupciones : tblinterrupcionesCollectionOld) {
                if (!tblinterrupcionesCollectionNew.contains(tblinterrupcionesCollectionOldTblinterrupciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblinterrupciones " + tblinterrupcionesCollectionOldTblinterrupciones + " since its tblusuariosIdUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tblperfiles> attachedTblperfilesCollectionNew = new ArrayList<Tblperfiles>();
            for (Tblperfiles tblperfilesCollectionNewTblperfilesToAttach : tblperfilesCollectionNew) {
                tblperfilesCollectionNewTblperfilesToAttach = em.getReference(tblperfilesCollectionNewTblperfilesToAttach.getClass(), tblperfilesCollectionNewTblperfilesToAttach.getIdPerfil());
                attachedTblperfilesCollectionNew.add(tblperfilesCollectionNewTblperfilesToAttach);
            }
            tblperfilesCollectionNew = attachedTblperfilesCollectionNew;
            tblusuarios.setTblperfilesCollection(tblperfilesCollectionNew);
            Collection<Tblactividad> attachedTblactividadCollectionNew = new ArrayList<Tblactividad>();
            for (Tblactividad tblactividadCollectionNewTblactividadToAttach : tblactividadCollectionNew) {
                tblactividadCollectionNewTblactividadToAttach = em.getReference(tblactividadCollectionNewTblactividadToAttach.getClass(), tblactividadCollectionNewTblactividadToAttach.getIdActividad());
                attachedTblactividadCollectionNew.add(tblactividadCollectionNewTblactividadToAttach);
            }
            tblactividadCollectionNew = attachedTblactividadCollectionNew;
            tblusuarios.setTblactividadCollection(tblactividadCollectionNew);
            Collection<Tblinterrupciones> attachedTblinterrupcionesCollectionNew = new ArrayList<Tblinterrupciones>();
            for (Tblinterrupciones tblinterrupcionesCollectionNewTblinterrupcionesToAttach : tblinterrupcionesCollectionNew) {
                tblinterrupcionesCollectionNewTblinterrupcionesToAttach = em.getReference(tblinterrupcionesCollectionNewTblinterrupcionesToAttach.getClass(), tblinterrupcionesCollectionNewTblinterrupcionesToAttach.getIdInterrupcion());
                attachedTblinterrupcionesCollectionNew.add(tblinterrupcionesCollectionNewTblinterrupcionesToAttach);
            }
            tblinterrupcionesCollectionNew = attachedTblinterrupcionesCollectionNew;
            tblusuarios.setTblinterrupcionesCollection(tblinterrupcionesCollectionNew);
            tblusuarios = em.merge(tblusuarios);
            for (Tblperfiles tblperfilesCollectionNewTblperfiles : tblperfilesCollectionNew) {
                if (!tblperfilesCollectionOld.contains(tblperfilesCollectionNewTblperfiles)) {
                    Tblusuarios oldTblusuariosIdUsuarioOfTblperfilesCollectionNewTblperfiles = tblperfilesCollectionNewTblperfiles.getTblusuariosIdUsuario();
                    tblperfilesCollectionNewTblperfiles.setTblusuariosIdUsuario(tblusuarios);
                    tblperfilesCollectionNewTblperfiles = em.merge(tblperfilesCollectionNewTblperfiles);
                    if (oldTblusuariosIdUsuarioOfTblperfilesCollectionNewTblperfiles != null && !oldTblusuariosIdUsuarioOfTblperfilesCollectionNewTblperfiles.equals(tblusuarios)) {
                        oldTblusuariosIdUsuarioOfTblperfilesCollectionNewTblperfiles.getTblperfilesCollection().remove(tblperfilesCollectionNewTblperfiles);
                        oldTblusuariosIdUsuarioOfTblperfilesCollectionNewTblperfiles = em.merge(oldTblusuariosIdUsuarioOfTblperfilesCollectionNewTblperfiles);
                    }
                }
            }
            for (Tblactividad tblactividadCollectionNewTblactividad : tblactividadCollectionNew) {
                if (!tblactividadCollectionOld.contains(tblactividadCollectionNewTblactividad)) {
                    Tblusuarios oldTblusuariosIdUsuarioOfTblactividadCollectionNewTblactividad = tblactividadCollectionNewTblactividad.getTblusuariosIdUsuario();
                    tblactividadCollectionNewTblactividad.setTblusuariosIdUsuario(tblusuarios);
                    tblactividadCollectionNewTblactividad = em.merge(tblactividadCollectionNewTblactividad);
                    if (oldTblusuariosIdUsuarioOfTblactividadCollectionNewTblactividad != null && !oldTblusuariosIdUsuarioOfTblactividadCollectionNewTblactividad.equals(tblusuarios)) {
                        oldTblusuariosIdUsuarioOfTblactividadCollectionNewTblactividad.getTblactividadCollection().remove(tblactividadCollectionNewTblactividad);
                        oldTblusuariosIdUsuarioOfTblactividadCollectionNewTblactividad = em.merge(oldTblusuariosIdUsuarioOfTblactividadCollectionNewTblactividad);
                    }
                }
            }
            for (Tblinterrupciones tblinterrupcionesCollectionNewTblinterrupciones : tblinterrupcionesCollectionNew) {
                if (!tblinterrupcionesCollectionOld.contains(tblinterrupcionesCollectionNewTblinterrupciones)) {
                    Tblusuarios oldTblusuariosIdUsuarioOfTblinterrupcionesCollectionNewTblinterrupciones = tblinterrupcionesCollectionNewTblinterrupciones.getTblusuariosIdUsuario();
                    tblinterrupcionesCollectionNewTblinterrupciones.setTblusuariosIdUsuario(tblusuarios);
                    tblinterrupcionesCollectionNewTblinterrupciones = em.merge(tblinterrupcionesCollectionNewTblinterrupciones);
                    if (oldTblusuariosIdUsuarioOfTblinterrupcionesCollectionNewTblinterrupciones != null && !oldTblusuariosIdUsuarioOfTblinterrupcionesCollectionNewTblinterrupciones.equals(tblusuarios)) {
                        oldTblusuariosIdUsuarioOfTblinterrupcionesCollectionNewTblinterrupciones.getTblinterrupcionesCollection().remove(tblinterrupcionesCollectionNewTblinterrupciones);
                        oldTblusuariosIdUsuarioOfTblinterrupcionesCollectionNewTblinterrupciones = em.merge(oldTblusuariosIdUsuarioOfTblinterrupcionesCollectionNewTblinterrupciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblusuarios.getIdUsuario();
                if (findTblusuarios(id) == null) {
                    throw new NonexistentEntityException("The tblusuarios with id " + id + " no longer exists.");
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
            Tblusuarios tblusuarios;
            try {
                tblusuarios = em.getReference(Tblusuarios.class, id);
                tblusuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblusuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tblperfiles> tblperfilesCollectionOrphanCheck = tblusuarios.getTblperfilesCollection();
            for (Tblperfiles tblperfilesCollectionOrphanCheckTblperfiles : tblperfilesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblusuarios (" + tblusuarios + ") cannot be destroyed since the Tblperfiles " + tblperfilesCollectionOrphanCheckTblperfiles + " in its tblperfilesCollection field has a non-nullable tblusuariosIdUsuario field.");
            }
            Collection<Tblactividad> tblactividadCollectionOrphanCheck = tblusuarios.getTblactividadCollection();
            for (Tblactividad tblactividadCollectionOrphanCheckTblactividad : tblactividadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblusuarios (" + tblusuarios + ") cannot be destroyed since the Tblactividad " + tblactividadCollectionOrphanCheckTblactividad + " in its tblactividadCollection field has a non-nullable tblusuariosIdUsuario field.");
            }
            Collection<Tblinterrupciones> tblinterrupcionesCollectionOrphanCheck = tblusuarios.getTblinterrupcionesCollection();
            for (Tblinterrupciones tblinterrupcionesCollectionOrphanCheckTblinterrupciones : tblinterrupcionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblusuarios (" + tblusuarios + ") cannot be destroyed since the Tblinterrupciones " + tblinterrupcionesCollectionOrphanCheckTblinterrupciones + " in its tblinterrupcionesCollection field has a non-nullable tblusuariosIdUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tblusuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblusuarios> findTblusuariosEntities() {
        return findTblusuariosEntities(true, -1, -1);
    }

    public List<Tblusuarios> findTblusuariosEntities(int maxResults, int firstResult) {
        return findTblusuariosEntities(false, maxResults, firstResult);
    }

    private List<Tblusuarios> findTblusuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tblusuarios as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tblusuarios findTblusuarios(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblusuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblusuariosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Tblusuarios as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
