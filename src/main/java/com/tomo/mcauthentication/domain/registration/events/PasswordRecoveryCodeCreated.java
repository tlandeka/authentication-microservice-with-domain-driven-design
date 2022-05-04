package com.tomo.mcauthentication.domain.registration.events;

import com.tomo.mcauthentication.ddd.domain.BaseDomainEvent;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordRecoveryCodeCreated extends BaseDomainEvent  {
    private String email;
    private String recoveryCode;
    private LocalDateTime recoveryCodeExpirationDate;

    public PasswordRecoveryCodeCreated(String email, String recoveryCode, LocalDateTime recoveryCodeExpirationDate) {
        this.email = email;
        this.recoveryCode = recoveryCode;
        this.recoveryCodeExpirationDate = recoveryCodeExpirationDate;
    }
}
