package com.tomo.mcauthentication.infrastructure.http.oauth2;

import com.tomo.mcauthentication.domain.oauth2.OAuth2Authentication;
import com.tomo.mcauthentication.domain.oauth2.OAuth2Principal;
import com.tomo.mcauthentication.infrastructure.http.oauth2.user.FacebookOAuth2UserInfo;
import com.tomo.mcauthentication.infrastructure.http.oauth2.user.OAuth2UserInfoFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class FacebookOAuth2Authentication extends AbstractOAuth2Authentication implements OAuth2Authentication {

    public FacebookOAuth2Authentication(
            @Qualifier("facebookClientRegistration") ClientRegistration clientRegistration,
            CustomOAuth2UserService customOAuth2UserService) {
        super(clientRegistration, customOAuth2UserService);
    }

    @Override
    public OAuth2Principal authenticate(String anAccessCode) {
        OAuth2User oAuth2User = super.authenticateUser(anAccessCode);
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
