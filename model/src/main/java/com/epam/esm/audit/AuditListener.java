package com.epam.esm.audit;

import com.epam.esm.entity.AbstractEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * Auditor for entities.
 *
 * @author Yauheni Tstiov
 */
public class AuditListener {
    /**
     * Audits the entity when added
     *
     * @param entity which is added to the database
     */
    @PrePersist
    public void onPrePersist(AbstractEntity entity) {
        entity.setCreateDate(LocalDateTime.now());
        audit("INSERT", entity);
    }

    /**
     * Audits the entity on update.
     *
     * @param entity which is update to the database.
     */
    @PreUpdate
    public void onPreUpdate(AbstractEntity entity) {
        audit("UPDATE", entity);
    }

    private void audit(String operation, AbstractEntity entity) {
        entity.setOperation(operation);
        entity.setLastUpdateDate(LocalDateTime.now());
    }
}
