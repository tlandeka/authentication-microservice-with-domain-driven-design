package com.tomo.mcauthentication.infrastructure.http.oauth2;

import com.tomo.mcauthentication.domain.oauth2.OAuth2Principal;
import com.tomo.mcauthentication.infrastructure.http.oauth2.user.OAuth2UserInfo;
import com.tomo.mcauthentication.infrastructure.http.oauth2.user.OAuth2UserInfoFactory;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.Instant;

public abstract class AbstractOAuth2Authentication {

    ClientRegistration clientRegistration;
    CustomOAuth2UserService customOAuth2UserService;

    public AbstractOAuth2Authentication(ClientRegistration clientRegistration,
            CustomOAuth2UserService customOAuth2UserService) {
        this.clientRegistration = clientRegistration;
        this.customOAuth2UserService = customOAuth2UserService;
    }

    protected OAuth2Principal authenticateUser(String anAccessCode) {
        OAuth2UserRequest oAuth2UserRequest = new OAuth2UserRequest(clientRegistration, new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                anAccessCode,
                Instant.now(), Instant.now().plusSeconds(10000L)));
        OAuth2User oAuth2User = customOAuth2UserService.loadUser(oAuth2UserRequest);
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory
                .getOAuth2UserInfo(clientRegistration.getRegistrationId(), oAuth2User.getAttributes());
        return new OAuth2Principal(
                userInfo.getId(),
                userInfo.getEmail(),
                userInfo.getName(),
                userInfo.getName(),
                userInfo.getImageUrl(),
                clientRegistration.getRegistrationId());

    }
}
