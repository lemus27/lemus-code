/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.dao;

import com.mgl.bitacoradiaria.dao.exceptions.IllegalOrphanException;
import com.mgl.bitacoradiaria.dao.exceptions.NonexistentEntityException;
import com.mgl.bitacoradiaria.model.Tblinterrupciones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.mgl.bitacoradiaria.model.Tblcategorias;
import com.mgl.bitacoradiaria.model.Tblbitacoradiaria;
import com.mgl.bitacoradiaria.model.TblusuariosCreaInterrupciones;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author mike
 */
public class TblinterrupcionesJpaController {

    public TblinterrupcionesJpaController() {
        emf = Persistence.createEntityManagerFactory("BitacoraDiariaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblinterrupciones tblinterrupciones) {
        if (tblinterrupciones.getTblusuariosCreaInterrupcionesCollection() == null) {
            tblinterrupciones.setTblusuariosCreaInterrupcionesCollection(new ArrayList<TblusuariosCreaInterrupciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblcategorias idCategoriaPertenece = tblinterrupciones.getIdCategoriaPertenece();
            if (idCategoriaPertenece != null) {
                idCategoriaPertenece = em.getReference(idCategoriaPertenece.getClass(), idCategoriaPertenece.getIdCategoria());
                tblinterrupciones.setIdCategoriaPertenece(idCategoriaPertenece);
            }
            Tblbitacoradiaria idBitacoraPertenece = tblinterrupciones.getIdBitacoraPertenece();
            if (idBitacoraPertenece != null) {
                idBitacoraPertenece = em.getReference(idBitacoraPertenece.getClass(), idBitacoraPertenece.getIdBitacora());
                tblinterrupciones.setIdBitacoraPertenece(idBitacoraPertenece);
            }
            Collection<TblusuariosCreaInterrupciones> attachedTblusuariosCreaInterrupcionesCollection = new ArrayList<TblusuariosCreaInterrupciones>();
            for (TblusuariosCreaInterrupciones tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupcionesToAttach : tblinterrupciones.getTblusuariosCreaInterrupcionesCollection()) {
                tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupcionesToAttach = em.getReference(tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupcionesToAttach.getClass(), tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupcionesToAttach.getIdCreador());
                attachedTblusuariosCreaInterrupcionesCollection.add(tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupcionesToAttach);
            }
            tblinterrupciones.setTblusuariosCreaInterrupcionesCollection(attachedTblusuariosCreaInterrupcionesCollection);
            em.persist(tblinterrupciones);
            if (idCategoriaPertenece != null) {
                idCategoriaPertenece.getTblinterrupcionesCollection().add(tblinterrupciones);
                idCategoriaPertenece = em.merge(idCategoriaPertenece);
            }
            if (idBitacoraPertenece != null) {
                idBitacoraPertenece.getTblinterrupcionesCollection().add(tblinterrupciones);
                idBitacoraPertenece = em.merge(idBitacoraPertenece);
            }
            for (TblusuariosCreaInterrupciones tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones : tblinterrupciones.getTblusuariosCreaInterrupcionesCollection()) {
                Tblinterrupciones oldIdInterrupcionNuevoOfTblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones = tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones.getIdInterrupcionNuevo();
                tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones.setIdInterrupcionNuevo(tblinterrupciones);
                tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones = em.merge(tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones);
                if (oldIdInterrupcionNuevoOfTblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones != null) {
                    oldIdInterrupcionNuevoOfTblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones.getTblusuariosCreaInterrupcionesCollection().remove(tblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones);
                    oldIdInterrupcionNuevoOfTblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones = em.merge(oldIdInterrupcionNuevoOfTblusuariosCreaInterrupcionesCollectionTblusuariosCreaInterrupciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblinterrupciones tblinterrupciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblinterrupciones persistentTblinterrupciones = em.find(Tblinterrupciones.class, tblinterrupciones.getIdInterrupcion());
            Tblcategorias idCategoriaPerteneceOld = persistentTblinterrupciones.getIdCategoriaPertenece();
            Tblcategorias idCategoriaPerteneceNew = tblinterrupciones.getIdCategoriaPertenece();
            Tblbitacoradiaria idBitacoraPerteneceOld = persistentTblinterrupciones.getIdBitacoraPertenece();
            Tblbitacoradiaria idBitacoraPerteneceNew = tblinterrupciones.getIdBitacoraPertenece();
            Collection<TblusuariosCreaInterrupciones> tblusuariosCreaInterrupcionesCollectionOld = persistentTblinterrupciones.getTblusuariosCreaInterrupcionesCollection();
            Collection<TblusuariosCreaInterrupciones> tblusuariosCreaInterrupcionesCollectionNew = tblinterrupciones.getTblusuariosCreaInterrupcionesCollection();
            List<String> illegalOrphanMessages = null;
            for (TblusuariosCreaInterrupciones tblusuariosCreaInterrupcionesCollectionOldTblusuariosCreaInterrupciones : tblusuariosCreaInterrupcionesCollectionOld) {
                if (!tblusuariosCreaInterrupcionesCollectionNew.contains(tblusuariosCreaInterrupcionesCollectionOldTblusuariosCreaInterrupciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TblusuariosCreaInterrupciones " + tblusuariosCreaInterrupcionesCollectionOldTblusuariosCreaInterrupciones + " since its idInterrupcionNuevo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCategoriaPerteneceNew != null) {
                idCategoriaPerteneceNew = em.getReference(idCategoriaPerteneceNew.getClass(), idCategoriaPerteneceNew.getIdCategoria());
                tblinterrupciones.setIdCategoriaPertenece(idCategoriaPerteneceNew);
            }
            if (idBitacoraPerteneceNew != null) {
                idBitacoraPerteneceNew = em.getReference(idBitacoraPerteneceNew.getClass(), idBitacoraPerteneceNew.getIdBitacora());
                tblinterrupciones.setIdBitacoraPertenece(idBitacoraPerteneceNew);
            }
            Collection<TblusuariosCreaInterrupciones> attachedTblusuariosCreaInterrupcionesCollectionNew = new ArrayList<TblusuariosCreaInterrupciones>();
            for (TblusuariosCreaInterrupciones tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupcionesToAttach : tblusuariosCreaInterrupcionesCollectionNew) {
                tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupcionesToAttach = em.getReference(tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupcionesToAttach.getClass(), tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupcionesToAttach.getIdCreador());
                attachedTblusuariosCreaInterrupcionesCollectionNew.add(tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupcionesToAttach);
            }
            tblusuariosCreaInterrupcionesCollectionNew = attachedTblusuariosCreaInterrupcionesCollectionNew;
            tblinterrupciones.setTblusuariosCreaInterrupcionesCollection(tblusuariosCreaInterrupcionesCollectionNew);
            tblinterrupciones = em.merge(tblinterrupciones);
            if (idCategoriaPerteneceOld != null && !idCategoriaPerteneceOld.equals(idCategoriaPerteneceNew)) {
                idCategoriaPerteneceOld.getTblinterrupcionesCollection().remove(tblinterrupciones);
                idCategoriaPerteneceOld = em.merge(idCategoriaPerteneceOld);
            }
            if (idCategoriaPerteneceNew != null && !idCategoriaPerteneceNew.equals(idCategoriaPerteneceOld)) {
                idCategoriaPerteneceNew.getTblinterrupcionesCollection().add(tblinterrupciones);
                idCategoriaPerteneceNew = em.merge(idCategoriaPerteneceNew);
            }
            if (idBitacoraPerteneceOld != null && !idBitacoraPerteneceOld.equals(idBitacoraPerteneceNew)) {
                idBitacoraPerteneceOld.getTblinterrupcionesCollection().remove(tblinterrupciones);
                idBitacoraPerteneceOld = em.merge(idBitacoraPerteneceOld);
            }
            if (idBitacoraPerteneceNew != null && !idBitacoraPerteneceNew.equals(idBitacoraPerteneceOld)) {
                idBitacoraPerteneceNew.getTblinterrupcionesCollection().add(tblinterrupciones);
                idBitacoraPerteneceNew = em.merge(idBitacoraPerteneceNew);
            }
            for (TblusuariosCreaInterrupciones tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones : tblusuariosCreaInterrupcionesCollectionNew) {
                if (!tblusuariosCreaInterrupcionesCollectionOld.contains(tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones)) {
                    Tblinterrupciones oldIdInterrupcionNuevoOfTblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones = tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones.getIdInterrupcionNuevo();
                    tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones.setIdInterrupcionNuevo(tblinterrupciones);
                    tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones = em.merge(tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones);
                    if (oldIdInterrupcionNuevoOfTblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones != null && !oldIdInterrupcionNuevoOfTblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones.equals(tblinterrupciones)) {
                        oldIdInterrupcionNuevoOfTblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones.getTblusuariosCreaInterrupcionesCollection().remove(tblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones);
                        oldIdInterrupcionNuevoOfTblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones = em.merge(oldIdInterrupcionNuevoOfTblusuariosCreaInterrupcionesCollectionNewTblusuariosCreaInterrupciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tblinterrupciones.getIdInterrupcion();
                if (findTblinterrupciones(id) == null) {
                    throw new NonexistentEntityException("The tblinterrupciones with id " + id + " no longer exists.");
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
            Tblinterrupciones tblinterrupciones;
            try {
                tblinterrupciones = em.getReference(Tblinterrupciones.class, id);
                tblinterrupciones.getIdInterrupcion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblinterrupciones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TblusuariosCreaInterrupciones> tblusuariosCreaInterrupcionesCollectionOrphanCheck = tblinterrupciones.getTblusuariosCreaInterrupcionesCollection();
            for (TblusuariosCreaInterrupciones tblusuariosCreaInterrupcionesCollectionOrphanCheckTblusuariosCreaInterrupciones : tblusuariosCreaInterrupcionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblinterrupciones (" + tblinterrupciones + ") cannot be destroyed since the TblusuariosCreaInterrupciones " + tblusuariosCreaInterrupcionesCollectionOrphanCheckTblusuariosCreaInterrupciones + " in its tblusuariosCreaInterrupcionesCollection field has a non-nullable idInterrupcionNuevo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tblcategorias idCategoriaPertenece = tblinterrupciones.getIdCategoriaPertenece();
            if (idCategoriaPertenece != null) {
                idCategoriaPertenece.getTblinterrupcionesCollection().remove(tblinterrupciones);
                idCategoriaPertenece = em.merge(idCategoriaPertenece);
            }
            Tblbitacoradiaria idBitacoraPertenece = tblinterrupciones.getIdBitacoraPertenece();
            if (idBitacoraPertenece != null) {
                idBitacoraPertenece.getTblinterrupcionesCollection().remove(tblinterrupciones);
                idBitacoraPertenece = em.merge(idBitacoraPertenece);
            }
            em.remove(tblinterrupciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblinterrupciones> findTblinterrupcionesEntities() {
        return findTblinterrupcionesEntities(true, -1, -1);
    }

    public List<Tblinterrupciones> findTblinterrupcionesEntities(int maxResults, int firstResult) {
        return findTblinterrupcionesEntities(false, maxResults, firstResult);
    }

    private List<Tblinterrupciones> findTblinterrupcionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tblinterrupciones as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tblinterrupciones findTblinterrupciones(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblinterrupciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblinterrupcionesCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Tblinterrupciones as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
