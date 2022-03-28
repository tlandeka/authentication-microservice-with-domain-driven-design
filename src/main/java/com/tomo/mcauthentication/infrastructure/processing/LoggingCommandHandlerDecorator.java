package com.tomo.mcauthentication.infrastructure.processing;

import com.tomo.mcauthentication.application.configuration.AbstractVoidyCommandHandler;
import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.application.contracts.Command;

public class LoggingCommandHandlerDecorator<T extends Command> extends AbstractVoidyCommandHandler<T> {

    CommandHandler commandHandler;

    public LoggingCommandHandlerDecorator(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    protected void abstractHandle(T aCommand) {
        commandHandler.handle(aCommand);
    }
}
