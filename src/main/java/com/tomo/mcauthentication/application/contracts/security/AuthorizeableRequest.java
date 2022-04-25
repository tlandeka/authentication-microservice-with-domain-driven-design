package com.tomo.mcauthentication.application.contracts.security;

import java.util.List;

public interface AuthorizeableRequest extends AuthenticatableRequest {

    List<String> getAuthorities();

}
