package com.tomo.mcauthentication.application.contracts;

import java.util.UUID;

public class BaseQuery implements Query {

    private UUID id;

    public BaseQuery() {
        setId(UUID.randomUUID());
    }

    public BaseQuery(UUID anId) {
        this.id = anId;
    }

    @Override
    public UUID id() {
        return this.id;
    }

    protected void setId(UUID anId) {
        this.id = anId;
    }
}
