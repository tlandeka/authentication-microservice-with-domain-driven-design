package com.tomo.mcauthentication.application.registration.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SendRegistrationConfirmationEmailCommand extends BaseCommand {
    String email;
    String confirmLink;

    public SendRegistrationConfirmationEmailCommand(String email, String confirmLink) {
        this.email = email;
        this.confirmLink = confirmLink;
    }
}
