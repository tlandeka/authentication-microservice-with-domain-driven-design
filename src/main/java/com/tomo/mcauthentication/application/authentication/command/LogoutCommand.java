package com.tomo.mcauthentication.application.authentication.command;

import com.tomo.mcauthentication.application.contracts.security.AbstractAuthenticateRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LogoutCommand extends AbstractAuthenticateRequest {

    public LogoutCommand(String authToken) {
        super(authToken);
    }
}
