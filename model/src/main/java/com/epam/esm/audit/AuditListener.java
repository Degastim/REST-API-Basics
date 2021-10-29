package com.epam.esm.audit;

import com.epam.esm.entity.CustomEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditListener {

    @PrePersist
    public void onPrePersist(CustomEntity entity) {
        audit("INSERT", entity);
    }

    @PreUpdate
    public void onPreUpdate(CustomEntity entity) {
        audit("UPDATE", entity);
    }

    private void audit(String operation, CustomEntity entity) {
        entity.setOperation(operation);
        entity.setTimestamp(LocalDateTime.now());
    }
}
