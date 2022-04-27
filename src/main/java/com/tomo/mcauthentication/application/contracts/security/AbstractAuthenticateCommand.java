package com.tomo.mcauthentication.application.contracts.security;

import com.tomo.mcauthentication.application.contracts.Command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractAuthenticateCommand extends AbstractAuthenticateRequest implements Command {

    public AbstractAuthenticateCommand(String authToken) {
        super(authToken);
    }
}
