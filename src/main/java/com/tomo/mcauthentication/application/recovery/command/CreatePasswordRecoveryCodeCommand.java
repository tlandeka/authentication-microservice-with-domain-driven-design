package com.tomo.mcauthentication.application.recovery.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePasswordRecoveryCodeCommand extends BaseCommand {
    private String email;

    public CreatePasswordRecoveryCodeCommand(String email) {
        this.email = email;
    }
}
