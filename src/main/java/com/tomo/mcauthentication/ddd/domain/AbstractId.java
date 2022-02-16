package com.tomo.mcauthentication.ddd.domain;

import com.tomo.mcauthentication.ddd.AssertionConcern;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractId extends AssertionConcern implements Identity, Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;

    public UUID id() {
        return this.id;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            AbstractId typedObject = (AbstractId) anObject;
            equalObjects = this.id().equals(typedObject.id());
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                + (this.hashOddValue() * this.hashPrimeValue())
                        + this.id().hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + id + "]";
    }

    protected AbstractId(UUID id) {
        this();

        this.setId(id);
    }

    protected AbstractId() {
        super();
    }

    protected void validateId(UUID anId) {
        // implemented by subclasses for validation.
        // throws a runtime exception if invalid.
    }

    protected abstract int hashPrimeValue();

    protected abstract int hashOddValue();

    protected void setId(UUID anId) {
        this.assertArgumentNotNull(anId, "Id cannot be null.");

        this.validateId(anId);

        this.id = anId;
    }

}
