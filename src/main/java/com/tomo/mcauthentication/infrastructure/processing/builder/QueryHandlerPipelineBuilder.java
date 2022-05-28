package com.tomo.mcauthentication.infrastructure.processing.builder;

import com.tomo.mcauthentication.application.configuration.QueryHandler;
import com.tomo.mcauthentication.application.contracts.Query;
import com.tomo.mcauthentication.infrastructure.processing.LoggingQueryHandlerDecorator;
import com.tomo.mcauthentication.infrastructure.processing.PipelineBuilder;

public class QueryHandlerPipelineBuilder extends AbstractPipelineBuilder<Query, QueryHandler> {

    public QueryHandlerPipelineBuilder() {
    }

    @Override
    public QueryHandlerPipelineBuilder with(Query aRequest) {
        return (QueryHandlerPipelineBuilder) super.with(aRequest);
    }

    @Override
    public QueryHandler build() {
        return new LoggingQueryHandlerDecorator(handler);
    }
}
