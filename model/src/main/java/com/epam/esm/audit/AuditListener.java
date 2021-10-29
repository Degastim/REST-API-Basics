package com.epam.esm.audit;

import com.epam.esm.entity.AbstractCustomEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditListener {

    @PrePersist
    public void onPrePersist(AbstractCustomEntity entity) {
        audit("INSERT", entity);
    }

    @PreUpdate
    public void onPreUpdate(AbstractCustomEntity entity) {
        audit("UPDATE", entity);
    }

    private void audit(String operation, AbstractCustomEntity entity) {
        entity.setOperation(operation);
        entity.setTimestamp(LocalDateTime.now());
    }
}
