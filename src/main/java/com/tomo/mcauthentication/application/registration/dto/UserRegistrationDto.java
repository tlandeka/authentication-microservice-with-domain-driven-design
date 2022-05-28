package com.tomo.mcauthentication.application.registration.dto;

import com.tomo.mcauthentication.application.contracts.Response;
import com.tomo.mcauthentication.domain.registration.UserRegistrationId;
import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto implements Response {

    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime registerDate;
    private UserRegistrationStatus status;

}
