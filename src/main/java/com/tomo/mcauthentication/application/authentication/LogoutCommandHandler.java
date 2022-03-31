package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.authentication.command.LogoutCommand;
import com.tomo.mcauthentication.application.configuration.AbstractVoidyCommandHandler;
import com.tomo.mcauthentication.domain.session.SessionAuthenticationService;

import org.springframework.stereotype.Service;

@Service
public class LogoutCommandHandler extends AbstractVoidyCommandHandler<LogoutCommand> {

    SessionAuthenticationService sessionAuthenticationService;

    public LogoutCommandHandler(SessionAuthenticationService sessionAuthenticationService) {
        this.sessionAuthenticationService = sessionAuthenticationService;
    }

    @Override
    protected void abstractHandle(LogoutCommand aCommand) {
        sessionAuthenticationService.logout(aCommand.getAccessToken());
    }
}
