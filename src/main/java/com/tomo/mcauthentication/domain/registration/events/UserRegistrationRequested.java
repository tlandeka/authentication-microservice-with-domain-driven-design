package com.tomo.mcauthentication.domain.registration.events;

import com.tomo.mcauthentication.ddd.domain.BaseDomainEvent;
import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationRequested extends BaseDomainEvent  {
    private long userRegistrationId;
    private String email;
    private String confirmationCode;
    private String firstName;
    private String lastName;
    private LocalDateTime registerDate;
    private UserRegistrationStatus status;

    public UserRegistrationRequested(
            String email,
            String confirmationCode,
            String firstName,
            String lastName,
            LocalDateTime registerDate,
            UserRegistrationStatus status) {
        this.email = email;
        this.confirmationCode = confirmationCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registerDate = registerDate;
        this.status = status;
    }
}
