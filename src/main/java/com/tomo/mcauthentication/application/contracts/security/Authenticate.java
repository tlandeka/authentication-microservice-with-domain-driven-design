package com.tomo.mcauthentication.application.contracts.security;

public interface Authenticate {

    String authToken();

    void setAuthToken(String authToken);

}
