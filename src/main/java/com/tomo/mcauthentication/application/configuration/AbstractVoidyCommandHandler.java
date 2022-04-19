package com.tomo.mcauthentication.application.configuration;

import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.Voidy;

import javax.transaction.Transactional;

public abstract class AbstractVoidyCommandHandler<T extends Command> implements CommandHandler<T, Voidy> {

    @Override
    @Transactional
    public Voidy handle(T aCommand) {
        abstractHandle(aCommand);
        return new Voidy();
    }

    abstract protected void abstractHandle(T aCommand);
}
