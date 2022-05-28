package com.tomo.mcauthentication.application.authentication.command;

import com.tomo.mcauthentication.application.contracts.security.AbstractAuthenticateCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LogoutCommand extends AbstractAuthenticateCommand {

    public LogoutCommand(String authToken) {
        super(authToken);
    }
}
