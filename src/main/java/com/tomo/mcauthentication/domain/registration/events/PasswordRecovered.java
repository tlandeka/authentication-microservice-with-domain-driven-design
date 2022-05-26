package com.tomo.mcauthentication.domain.registration.events;

import com.tomo.mcauthentication.ddd.domain.BaseDomainEvent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordRecovered extends BaseDomainEvent  {
    private long userRegistrationId;
    private String password; //encrypted
    private String recoveryCode; //encrypted

    public PasswordRecovered(long userRegistrationId, String password, String recoveryCode) {
        this.userRegistrationId = userRegistrationId;
        this.password = password;
        this.recoveryCode = recoveryCode;
    }
}
