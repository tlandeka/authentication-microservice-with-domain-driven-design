package com.tomo.mcauthentication.infrastructure.processing;

import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.Result;

public class LoggingResultableCommandHandlerDecorator implements ResultableCommandHandler {

    ResultableCommandHandler commandHandler;

    public LoggingResultableCommandHandlerDecorator(ResultableCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public Result handle(Command command) {
        return this.commandHandler.handle(command);
    }
}
