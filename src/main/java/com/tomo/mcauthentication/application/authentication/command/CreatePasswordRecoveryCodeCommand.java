package com.tomo.mcauthentication.application.authentication.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePasswordRecoveryCodeCommand extends BaseCommand {
    private String email;
}
