package com.tomo.mcauthentication.infrastructure.processing.builder;

import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.infrastructure.processing.LoggingCommandHandlerDecorator;
import com.tomo.mcauthentication.infrastructure.processing.PipelineBuilder;

public class CommandHandlerPipelineBuilder extends AbstractPipelineBuilder<Command, CommandHandler> {

    public CommandHandlerPipelineBuilder() {}

    @Override
    public CommandHandlerPipelineBuilder with(Command aRequest) {
        return (CommandHandlerPipelineBuilder) super.with(aRequest);
    }

    @Override
    public CommandHandler build() {
        return new LoggingCommandHandlerDecorator(handler);
    }
}
