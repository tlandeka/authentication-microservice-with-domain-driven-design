package com.tomo.mcauthentication.application.contracts;

import org.springframework.stereotype.Component;

@Component
public interface McAuthenticationModule {
        void executeCommand(Command command);

        Response executeCommandWithResult(Command command);

        Response executeQuery(Query query);
}
