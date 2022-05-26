package com.tomo.mcauthentication.application.authentication.command;

import com.tomo.mcauthentication.application.contracts.security.AbstractAuthenticateCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SessionAuthenticationCommand extends AbstractAuthenticateCommand {

    public SessionAuthenticationCommand(String authToken) {
        super(authToken);
    }
}
