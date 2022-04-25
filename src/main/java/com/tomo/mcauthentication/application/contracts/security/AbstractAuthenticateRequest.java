package com.tomo.mcauthentication.application.contracts.security;

import com.tomo.mcauthentication.application.contracts.BaseRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractAuthenticateRequest extends BaseRequest implements AuthenticatableRequest {

    String accessToken;

    public AbstractAuthenticateRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}
