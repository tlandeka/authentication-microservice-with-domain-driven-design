package com.tomo.mcauthentication.domain.oauth2;

public interface OAuth2Authentication {
     OAuth2Principal authenticate(String anAccessCode);
}
