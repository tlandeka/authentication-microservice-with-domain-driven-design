package com.tomo.mcauthentication.application.contracts;

import java.util.UUID;

public class BaseQuery implements Query {

    private UUID id;

    public BaseQuery(UUID id) {
        this.id = id;
    }

    @Override
    public UUID id() {
        return id;
    }
}
