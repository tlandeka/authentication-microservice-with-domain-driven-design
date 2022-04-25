package com.tomo.mcauthentication.application.contracts.security;

import com.tomo.mcauthentication.application.contracts.Command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractAuthenticateQuery extends AbstractAuthenticateRequest implements Command {

}
