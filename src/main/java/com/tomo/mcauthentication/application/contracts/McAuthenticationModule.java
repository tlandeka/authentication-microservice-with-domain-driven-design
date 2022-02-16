package com.tomo.mcauthentication.application.contracts;

import org.springframework.stereotype.Component;

@Component
public interface McAuthenticationModule {
        void executeCommand(Command command);

        Result executeCommandWithResult(Command command);

        Result executeQuery(Query query);
}
