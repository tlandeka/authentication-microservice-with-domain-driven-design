package com.tomo.mcauthentication.application.contracts;

import java.util.UUID;

public class BaseCommand implements Command {

    private UUID id;

    public BaseCommand() {
        setId(UUID.randomUUID());
    }

    public BaseCommand(UUID anId) {
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
