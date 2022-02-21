package com.tomo.mcauthentication.infrastructure.http.oauth2;

import com.tomo.mcauthentication.domain.oauth2.OAuth2Authentication;
import com.tomo.mcauthentication.domain.oauth2.OAuth2Principal;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class AbstractOAuth2Authentication {

    ClientRegistration clientRegistration;
    CustomOAuth2UserService customOAuth2UserService;

    public AbstractOAuth2Authentication(ClientRegistration clientRegistration,
            CustomOAuth2UserService customOAuth2UserService) {
        this.clientRegistration = clientRegistration;
        this.customOAuth2UserService = customOAuth2UserService;
    }

    public OAuth2User authenticate(OAuth2UserRequest oAuth2UserRequest) {
        return customOAuth2UserService.loadUser(oAuth2UserRequest);
    }
}
