package com.tomo.mcauthentication.infrastructure.springboot.controller;

import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.application.contracts.Query;
import com.tomo.mcauthentication.application.contracts.Response;
import com.tomo.mcauthentication.infrastructure.springboot.configuration.AppProperties;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {

    @Autowired
    protected McAuthenticationModule authenticationModule;

    @Autowired
    protected AppProperties properties;

    protected Response executeCommand(Command command) {
        return authenticationModule.executeCommand(command);
    }

    protected <T extends Response> T executeCommand(Command command, Class<T> tclass) {
        return tclass.cast(authenticationModule.executeCommand(command));
    }

    protected Response executeQuery(Query query) {
        return authenticationModule.executeQuery(query);
    }

}
