package com.tomo.mcauthentication.domain.oauth2;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OAuth2Principal {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private String provider;

    public OAuth2Principal(String id, String email, String firstName, String lastName, String imageUrl, String provider) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
        this.provider = provider;
    }
}
