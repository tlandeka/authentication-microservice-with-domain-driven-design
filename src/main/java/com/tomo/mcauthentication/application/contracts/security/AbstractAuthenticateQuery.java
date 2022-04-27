package com.tomo.mcauthentication.application.contracts.security;

import com.tomo.mcauthentication.application.contracts.BaseRequest;
import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.Query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractAuthenticateQuery extends AbstractAuthenticateRequest implements Query {
}
