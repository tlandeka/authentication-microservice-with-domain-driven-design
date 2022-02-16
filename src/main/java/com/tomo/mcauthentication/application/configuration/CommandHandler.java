package com.tomo.mcauthentication.application.configuration;

import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.Result;

public interface CommandHandler<T extends Command> extends RequestHandler {
    void handle(T aCommand);
}
