package com.tomo.mcauthentication.application.contracts.security;

import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractAuthorizeRequest extends AbstractAuthenticateRequest implements Request, Authorize {

}
