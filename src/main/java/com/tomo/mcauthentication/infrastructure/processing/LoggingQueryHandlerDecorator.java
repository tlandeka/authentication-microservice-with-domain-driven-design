package com.tomo.mcauthentication.infrastructure.processing;

import com.tomo.mcauthentication.application.configuration.QueryHandler;
import com.tomo.mcauthentication.application.contracts.Query;
import com.tomo.mcauthentication.application.contracts.Response;

public class LoggingQueryHandlerDecorator implements QueryHandler {

    QueryHandler queryHandler;

    public LoggingQueryHandlerDecorator(QueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @Override
    public Response handle(Query query) {
        return queryHandler.handle(query);
    }
}
