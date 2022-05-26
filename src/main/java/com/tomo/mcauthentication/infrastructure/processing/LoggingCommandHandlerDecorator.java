package com.tomo.mcauthentication.infrastructure.processing;

import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.Response;

public class LoggingCommandHandlerDecorator implements CommandHandler<Command, Response> {

    CommandHandler commandHandler;

    public LoggingCommandHandlerDecorator(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public Response handle(Command aCommand) {
        //todo log
        return commandHandler.handle(aCommand);
    }
}
