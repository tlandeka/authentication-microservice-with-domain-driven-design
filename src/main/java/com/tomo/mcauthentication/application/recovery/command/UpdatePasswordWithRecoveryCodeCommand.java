package com.tomo.mcauthentication.application.recovery.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePasswordWithRecoveryCodeCommand extends BaseCommand {
    @NotNull
    private String newPassword;

    @NotNull
    private String newPasswordRepeated;

    @NotNull
    private String recoveryCode;

}
