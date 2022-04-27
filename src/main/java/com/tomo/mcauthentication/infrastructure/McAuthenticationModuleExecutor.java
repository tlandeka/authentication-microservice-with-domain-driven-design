package com.tomo.mcauthentication.infrastructure;

import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.application.configuration.QueryHandler;
import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.application.contracts.Query;
import com.tomo.mcauthentication.application.contracts.Response;
import com.tomo.mcauthentication.infrastructure.processing.builder.CommandHandlerPipelineBuilder;
import com.tomo.mcauthentication.infrastructure.processing.builder.QueryHandlerPipelineBuilder;

import org.springframework.stereotype.Component;

@Component
public class McAuthenticationModuleExecutor implements McAuthenticationModule {

    CommandHandlerPipelineBuilder commandHandlerPipelineBuilder;
    QueryHandlerPipelineBuilder queryHandlerPipelineBuilder;

    public McAuthenticationModuleExecutor(
            CommandHandlerPipelineBuilder commandHandlerPipelineBuilder,
            QueryHandlerPipelineBuilder queryHandlerPipelineBuilder) {
        this.commandHandlerPipelineBuilder = commandHandlerPipelineBuilder;
        this.queryHandlerPipelineBuilder = queryHandlerPipelineBuilder;
    }

    @Override
    public Response executeCommand(Command command) {
        CommandHandler commandHandler = commandHandlerPipelineBuilder
                .with(command)
                .build();

        return commandHandler.handle(command);
    }

    @Override
    public Response executeQuery(Query query) {
        QueryHandler queryHandler = queryHandlerPipelineBuilder
                .with(query)
                .build();

        return queryHandler.handle(query);
    }
}
