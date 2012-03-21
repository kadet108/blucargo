package com.blucargo.testing;


import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.WorkManager;

public class TransactionRollbackTestBase {

    @Inject
    private WorkManager unitOfWork;
    @Inject
    private Provider<EntityManager> entityManagerProvider;

    @Before
    public void beginTransaction() {
        unitOfWork.beginWork();
        entityManagerProvider.get().getTransaction().begin();
    }

    @After
    public void rollbackTransaction() {
        entityManagerProvider.get().getTransaction().rollback();
        unitOfWork.endWork();
    }

    public EntityManager getEntityManager() {
        return entityManagerProvider.get();
    }

}
