package com.tomo.mcauthentication.application.registration.dto;

import com.tomo.mcauthentication.application.contracts.Response;
import com.tomo.mcauthentication.domain.registration.UserRegistrationId;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto implements Response {
    private UUID id;

    public void setId(UserRegistrationId id) {
        this.id = id.id();
    }
}
