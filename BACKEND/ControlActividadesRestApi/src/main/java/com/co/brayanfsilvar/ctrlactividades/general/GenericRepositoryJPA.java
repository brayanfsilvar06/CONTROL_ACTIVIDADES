/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.general;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author BrayanFSilvaR
 */
public class GenericRepositoryJPA<T> implements GenericRepository<T> {

    protected EntityManager entityManager;
    private Class<T> type;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public GenericRepositoryJPA() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    /**
     * @author BrayanFSilvaR
     * @param t
     * @return T
     */
    @Override
    public T create(T t) {
        T rtaCreate = null;
        ConnectionDBJPA connDBJPA = new ConnectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(t);
            this.entityManager.getTransaction().commit();
            rtaCreate = t;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;

        } finally {
            connDBJPA.closeEntityManager();
            this.closeEntityManager();
        }
        return rtaCreate;
    }

    /**
     * @author BrayanFSilvaR
     * @param t
     * @return T
     */
    @Override
    public T update(T t) {
        T rtaUpdate = null;
        ConnectionDBJPA connDBJPA = new ConnectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            this.entityManager.getTransaction().begin();
            this.entityManager.merge(t);
            this.entityManager.getTransaction().commit();
            rtaUpdate = t;
        } catch (Exception e) {
            if (this.entityManager.getTransaction().isActive()) {
                this.entityManager.getTransaction().rollback();
            }
            throw e;

        } finally {
            connDBJPA.closeEntityManager();
            this.closeEntityManager();
        }
        return rtaUpdate;
    }

    /**
     * @author BrayanFSilvaR
     * @param t
     */
    @Override
    public void delete(final T t) {
        ConnectionDBJPA connDBJPA = new ConnectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(entityManager.merge(t));
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (this.entityManager.getTransaction().isActive()) {
                this.entityManager.getTransaction().rollback();
            }
            throw e;

        } finally {
            connDBJPA.closeEntityManager();
            this.closeEntityManager();
        }

    }

    /**
     * @author BrayanFSilvaR
     * @param id
     * @return T
     */
    @Override
    public T find(final Object id) {
        T findClass = null;
        ConnectionDBJPA connDBJPA = new ConnectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            findClass = (T) this.entityManager.find(type, id);
        } catch (Exception e) {
            throw e;

        } finally {
            connDBJPA.closeEntityManager();
            this.closeEntityManager();
        }
        return findClass;
    }

    /**
     * @autor BrayanFSilvaR
     * @return List<T>
     */
    @Override
    public List<T> findAll() {
        List<T> lstFindAll = new ArrayList<>();
        ConnectionDBJPA connDBJPA = new ConnectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
            Root<T> root = criteriaQuery.from(type);
            criteriaQuery.select(root);
            criteriaQuery.orderBy(cb.asc(root.get("iCodigo")));
            TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);
            lstFindAll = query.getResultList();
        } catch (Exception e) {
            lstFindAll = null;
            throw e;
        } finally {
            connDBJPA.closeEntityManager();
            this.closeEntityManager();
        }

        return lstFindAll;
    }

    /**
     * @autor BrayanFSilvaR
     * @param sTipoOrder
     * @param sCampoOrder
     * @return List<T>
     */
    @Override
    public List<T> findAllOrderByCampo(String sTipoOrder, String sCampoOrder) {

        List<T> listFindAllOrderByCampo = new ArrayList<>();
        ConnectionDBJPA connDBJPA = new ConnectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
            Root<T> root = criteriaQuery.from(type);
            criteriaQuery.select(root);
            switch (sTipoOrder.toUpperCase()) {
                case "ASC":
                    criteriaQuery.orderBy(cb.asc(root.get(sCampoOrder)));
                    break;
                case "DESC":
                    criteriaQuery.orderBy(cb.desc(root.get(sCampoOrder)));
                    break;
                default:
                    criteriaQuery.orderBy(cb.desc(root.get(sCampoOrder)));
                    break;
            }

            TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
            listFindAllOrderByCampo = query.getResultList();
        } catch (Exception e) {
            listFindAllOrderByCampo = null;
            throw e;
        } finally {
            connDBJPA.closeEntityManager();
            this.closeEntityManager();
        }

        return listFindAllOrderByCampo;
    }

    /**
     * @autor BrayanFSilvaR
     * @param t
     * @param sNameQuery
     * @param sCampoFind
     * @param sValueFind
     * @return List<T>
     */
    @Override
    public List<T> findByNamedQueryForValueString(T t, String sNameQuery, String sCampoFind, String sValueFind) {
        List<T> list = new ArrayList<>();
        ConnectionDBJPA connDBJPA = new ConnectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            TypedQuery<T> query = this.entityManager.createNamedQuery(sNameQuery, type);
            query.setParameter(sCampoFind, sValueFind);
            list = query.getResultList();
        } catch (Exception e) {
            list = null;
            throw e;
        } finally {
            connDBJPA.closeEntityManager();
            this.closeEntityManager();
        }
        return list;
    }

    /**
     * @autor BrayanFSilvaR
     * @param t
     * @param sNameQuery
     * @param sCampoFind
     * @param valueEntity
     * @return List<T>
     */
    @Override
    public List<T> findByNamedQueryForValueEntity(T t, String sNameQuery, String sCampoFind, T valueEntity) {
        List<T> list = new ArrayList<>();
        ConnectionDBJPA connDBJPA = new ConnectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            TypedQuery<T> query = this.entityManager.createNamedQuery(sNameQuery, type);
            query.setParameter(sCampoFind, valueEntity);
            list = query.getResultList();
        } catch (Exception e) {
            list = null;
            throw e;
        } finally {
            connDBJPA.closeEntityManager();
            this.closeEntityManager();
        }
        return list;
    }

    /**
     * @autor BrayanFSilvaR
     */
    public void closeEntityManager() {
        if (this.entityManager != null && this.entityManager.isOpen()) {
            this.entityManager.close();
        }
    }

}
