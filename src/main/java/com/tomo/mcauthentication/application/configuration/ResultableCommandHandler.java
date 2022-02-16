package com.tomo.mcauthentication.application.configuration;

import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.Result;

public interface ResultableCommandHandler<T extends Command, R extends Result> {
    R handle(T command);
}
