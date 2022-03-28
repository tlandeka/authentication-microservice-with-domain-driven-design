package com.tomo.mcauthentication.application.configuration;

import com.tomo.mcauthentication.application.contracts.Command;

public interface CommandHandler<T extends Command, R> extends RequestHandler {
    R handle(T aCommand);
}
