package com.tomo.mcauthentication.domain.registration.events;

import com.tomo.ddd.domain.BaseDomainEvent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordChanged extends BaseDomainEvent  {
    private long userRegistrationId;
    private String password; //encrypted

    public PasswordChanged(long userRegistrationId, String password) {
        this.userRegistrationId = userRegistrationId;
        this.password = password;
    }
}
