package com.tomo.mcauthentication.application.configuration;

import com.tomo.mcauthentication.application.contracts.Query;
import com.tomo.mcauthentication.application.contracts.Result;

public interface QueryHandler<T extends Query, R extends Result> extends RequestHandler {
    R handle(T query);
}


