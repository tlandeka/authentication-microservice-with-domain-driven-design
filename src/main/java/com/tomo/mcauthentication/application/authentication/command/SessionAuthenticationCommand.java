package com.tomo.mcauthentication.application.authentication.command;

import com.tomo.mcauthentication.application.contracts.security.AbstractAuthenticateRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SessionAuthenticationCommand extends AbstractAuthenticateRequest {

    String accessToken;

    public SessionAuthenticationCommand(String accessToken) {
        this.accessToken = accessToken;
    }
}
