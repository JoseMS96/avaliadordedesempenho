package br.fai.add.model.entities;

import java.sql.Timestamp;

public abstract class BaseEntity {

    private Timestamp createdAt;
    private String createdBy;
    private Timestamp lastModified;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp created) {
        this.createdAt = createdAt;
    }

}
