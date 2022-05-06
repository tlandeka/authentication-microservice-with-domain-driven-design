package com.tomo.mcauthentication.application.recovery.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePasswordRecoveryCodeCommand extends BaseCommand {

    @NotNull
    private String email;

    public CreatePasswordRecoveryCodeCommand(String email) {
        this.email = email;
    }
}
