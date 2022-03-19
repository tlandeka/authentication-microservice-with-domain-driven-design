package com.tomo.mcauthentication.application.registration.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ConfirmUserRegistrationCommand extends BaseCommand {

    private String confirmationUrl;

    public ConfirmUserRegistrationCommand(String confirmationUrl) {
        super(UUID.randomUUID());
        this.confirmationUrl = confirmationUrl;
    }
}
