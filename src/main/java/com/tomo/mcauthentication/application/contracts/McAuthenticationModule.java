package com.tomo.mcauthentication.application.contracts;

import org.springframework.stereotype.Component;

@Component
public interface McAuthenticationModule {
        Response executeCommand(Command command);

        Response executeQuery(Query query);
}
