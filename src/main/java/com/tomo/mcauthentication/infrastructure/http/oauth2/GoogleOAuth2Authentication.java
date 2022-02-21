package com.tomo.mcauthentication.infrastructure.http.oauth2;

import com.tomo.mcauthentication.domain.oauth2.OAuth2Authentication;
import com.tomo.mcauthentication.domain.oauth2.OAuth2Principal;
import com.tomo.mcauthentication.infrastructure.http.oauth2.user.FacebookOAuth2UserInfo;
import com.tomo.mcauthentication.infrastructure.http.oauth2.user.OAuth2UserInfoFactory;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.Instant;

public class GoogleOAuth2Authentication extends AbstractOAuth2Authentication implements OAuth2Authentication {

    public GoogleOAuth2Authentication(ClientRegistration clientRegistration, CustomOAuth2UserService customOAuth2UserService) {
        super(clientRegistration, customOAuth2UserService);
    }

    @Override public OAuth2Principal authenticate(String anAccessCode) {
        OAuth2User oAuth2User = super.authenticate(new OAuth2UserRequest(clientRegistration, new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                anAccessCode,
                Instant.now(), Instant.now().plusSeconds(10000L))));
        FacebookOAuth2UserInfo userInfo = (FacebookOAuth2UserInfo) OAuth2UserInfoFactory
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

