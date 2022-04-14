package com.tomo.mcauthentication.application.configuration;

import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.Response;

public interface CommandHandler<T extends Command, R extends Response> extends RequestHandler {
    R handle(T aCommand);
}
