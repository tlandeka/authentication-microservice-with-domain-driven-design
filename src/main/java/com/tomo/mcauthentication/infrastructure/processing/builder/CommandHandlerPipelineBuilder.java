package com.tomo.mcauthentication.infrastructure.processing.builder;

import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.infrastructure.processing.LoggingCommandHandlerDecorator;
import com.tomo.mcauthentication.infrastructure.processing.PipelineBuilder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

public class CommandHandlerPipelineBuilder implements PipelineBuilder<Command, CommandHandler>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    Command command;
    CommandHandler commandHandler;

    private List<CommandHandler> commandHandlers;

    public CommandHandlerPipelineBuilder(List<CommandHandler> commandHandlers) {
        this.commandHandlers = commandHandlers;
    }

    @Override
    public CommandHandlerPipelineBuilder with(Command aRequest) {
        this.command = aRequest;
        this.commandHandler = this.getCommandHandler();
        return this;
    }

    protected CommandHandler getCommandHandler() {
        Class<? extends Command> commandClass = this.command.getClass();
        try {
            Class<? extends Command> commandHandlerClass = (Class<? extends Command>) Class.forName(commandClass.getName() + "Handler");
            return (CommandHandler) applicationContext.getBean(commandHandlerClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public CommandHandler build() {
        return new LoggingCommandHandlerDecorator(commandHandler);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
