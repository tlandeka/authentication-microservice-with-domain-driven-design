package com.tomo.mcauthentication.ddd.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(TimestampableListener.class)
public abstract class RootEntity extends BusinessRuleValidator implements Timestampable, Serializable {
    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @Override
    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public LocalDateTime getModified() {
        return modified;
    }

    @Override
    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
