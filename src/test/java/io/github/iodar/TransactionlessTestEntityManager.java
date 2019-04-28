package io.github.iodar;

import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@Service
@AutoConfigureTestEntityManager
@Transactional
public class TransactionlessTestEntityManager {
    @Inject
    private TestEntityManager testEntityManager;

    public Object persistAndGetId(final Object entity) {
        return testEntityManager.persistAndGetId(entity);
    }

    public <T> T persistAndGetId(final Object entity, final Class<T> idType) {
        return testEntityManager.persistAndGetId(entity, idType);
    }

    public <E> E persist(final E entity) {
        return testEntityManager.persist(entity);
    }

    public <E> E persistFlushFind(final E entity) {
        return testEntityManager.persistFlushFind(entity);
    }

    public <E> E persistAndFlush(final E entity) {
        return testEntityManager.persistAndFlush(entity);
    }

    public <E> E merge(final E entity) {
        return testEntityManager.merge(entity);
    }

    public void remove(final Object entity) {
        testEntityManager.remove(entity);
    }

    public <E> E find(final Class<E> entityClass, final Object primaryKey) {
        return testEntityManager.find(entityClass, primaryKey);
    }

    public void flush() {
        testEntityManager.flush();
    }

    public <E> E refresh(final E entity) {
        return testEntityManager.refresh(entity);
    }

    public void clear() {
        testEntityManager.clear();
    }

    public void detach(final Object entity) {
        testEntityManager.detach(entity);
    }

    public Object getId(final Object entity) {
        return testEntityManager.getId(entity);
    }

    public <T> T getId(final Object entity, final Class<T> idType) {
        return testEntityManager.getId(entity, idType);
    }

    public EntityManager getEntityManager() {
        return testEntityManager.getEntityManager();
    }

    public <T> List<T> findAllOf(final Class<T> type) {
        return getEntityManager().createQuery("SELECT t FROM " + type.getName() + " t").getResultList();
    }

    public void doTransactional(final Runnable runnable) {
        runnable.run();
    }

    public <T> T doTransactional(final Supplier<T> runnable) {
        return runnable.get();
    }
}