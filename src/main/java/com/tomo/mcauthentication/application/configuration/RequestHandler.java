package com.tomo.mcauthentication.application.configuration;

import com.tomo.mcauthentication.application.contracts.Request;
import com.tomo.mcauthentication.application.contracts.Response;

public interface RequestHandler<T extends Request, R extends Response> {
}
