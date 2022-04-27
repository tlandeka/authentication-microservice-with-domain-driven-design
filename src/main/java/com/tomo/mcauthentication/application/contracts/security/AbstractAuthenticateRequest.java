package com.tomo.mcauthentication.application.contracts.security;

import com.tomo.mcauthentication.application.contracts.BaseRequest;
import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractAuthenticateRequest extends BaseRequest implements Authenticate, Request {

    String authToken;

    public AbstractAuthenticateRequest(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String authToken() {
        return this.authToken;
    }

    @Override
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
