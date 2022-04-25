package com.tomo.mcauthentication.application.contracts.security;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractAuthorizeRequest extends AbstractAuthenticateRequest implements AuthorizeableRequest {

    @Override
    public List<String> getAuthorities() {
        return Arrays.asList("USER");
    }
}
