package com.tomo.mcauthentication.infrastructure;

import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.application.contracts.Query;
import com.tomo.mcauthentication.application.contracts.Response;
import com.tomo.mcauthentication.infrastructure.processing.builder.CommandHandlerPipelineBuilder;

import org.springframework.stereotype.Component;

@Component
public class McAuthenticationModuleExecutor implements McAuthenticationModule {

    CommandHandlerPipelineBuilder commandHandlerPipelineBuilder;

    public McAuthenticationModuleExecutor(CommandHandlerPipelineBuilder commandHandlerPipelineBuilder) {
        this.commandHandlerPipelineBuilder = commandHandlerPipelineBuilder;
    }

    @Override
    public void executeCommand(Command command) {
        CommandHandler commandHandler = commandHandlerPipelineBuilder
                .with(command)
                .build();

        commandHandler.handle(command);
    }

    @Override
    public Response executeCommandWithResult(Command command) {
        return null;
    }

    @Override
    public Response executeQuery(Query query) {
        return null;
    }
}
