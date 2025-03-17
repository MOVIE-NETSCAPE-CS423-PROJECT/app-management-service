package com.movienetscape.appmanagementservice.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    private String id;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = generateId();
        }
    }

    protected abstract String generateId();
}