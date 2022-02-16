package com.tomo.mcauthentication.infrastructure.processing;

import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.application.contracts.Command;

public class LoggingCommandHandlerDecorator implements CommandHandler {

    CommandHandler commandHandler;

    public LoggingCommandHandlerDecorator(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void handle(Command command) {
        commandHandler.handle(command);
    }
}
