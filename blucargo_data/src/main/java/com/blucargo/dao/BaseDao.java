package com.blucargo.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;

@Transactional
public abstract class BaseDao<T, ID extends Serializable> {

    protected Class<T> persistentClass;
    @Inject
    protected Provider<EntityManager> entityManager;

    protected BaseDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }
    
    public Provider<EntityManager> getSessionProvider() {
        return this.entityManager;
    }

    public void delete(T entity) {
        entityManager.get().remove(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
    	String queryString = "SELECT e FROM "+persistentClass.getName()+" e";
        Query query = entityManager.get().createQuery(queryString);
        return (List<T>) query.getResultList();
    }

    public T findById(ID id) {
        return (T) entityManager.get().find(persistentClass, id);
    }

    public void saveOrUpdate(T entity) {
        entityManager.get().persist(entity);
    }
    
}
