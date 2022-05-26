package com.tomo.mcauthentication.application.configuration;

import com.tomo.mcauthentication.application.contracts.Query;
import com.tomo.mcauthentication.application.contracts.Response;

public interface QueryHandler<T extends Query, R extends Response> extends RequestHandler {
    R handle(T query);
}


