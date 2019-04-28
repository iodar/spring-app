package io.github.iodar;

import io.github.iodar.persistence.entities.UserDbo;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Named
public class DBCleanupService {

    private final EntityManager entityManager;

    public DBCleanupService(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void deleteAllFrom(final Class<?>... entityTypes) {
        for (final Class<?> entityType : entityTypes) {
            try {
                deleteAllFrom(entityType);
            } catch (final Exception e) {
                throw new RuntimeException("Fehler bei der Löschung von " + entityType, e);
            }
        }
        entityManager.flush();
    }

    private void deleteAllFrom(final Class<?> entityType) {
        entityManager.createQuery("DELETE FROM " + entityType.getName()).executeUpdate();
    }

    @Transactional
    public void cleanupDb() {
        deleteAllFrom(
                UserDbo.class
        );
    }
}
