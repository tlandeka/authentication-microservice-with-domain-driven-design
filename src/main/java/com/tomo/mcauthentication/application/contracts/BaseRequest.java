package com.tomo.mcauthentication.application.contracts;

import java.util.UUID;

public class BaseRequest implements Request {

    private UUID id;

    public BaseRequest() {
        setId(UUID.randomUUID());
    }

    public BaseRequest(UUID anId) {
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
