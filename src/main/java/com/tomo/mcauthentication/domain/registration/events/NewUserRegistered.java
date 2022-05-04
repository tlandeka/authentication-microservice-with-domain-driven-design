package com.tomo.mcauthentication.domain.registration.events;

import com.tomo.mcauthentication.ddd.domain.BaseDomainEvent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewUserRegistered extends BaseDomainEvent  {
    private String email;
    private String confirmationCode;

    public NewUserRegistered(String email, String confirmationCode) {
        this.email = email;
        this.confirmationCode = confirmationCode;
    }
}
