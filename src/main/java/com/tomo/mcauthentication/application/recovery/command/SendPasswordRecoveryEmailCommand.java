package com.tomo.mcauthentication.application.recovery.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SendPasswordRecoveryEmailCommand extends BaseCommand {

    String email;
    String recoveryCode;
    String recoveryLink;
    LocalDateTime recoveryCodeExpirationDate;

    public SendPasswordRecoveryEmailCommand(String email, String recoveryCode, String recoveryLink, LocalDateTime recoveryCodeExpirationDate) {
        this.email = email;
        this.recoveryCode = recoveryCode;
        this.recoveryLink = recoveryLink;
        this.recoveryCodeExpirationDate = recoveryCodeExpirationDate;
    }
}
