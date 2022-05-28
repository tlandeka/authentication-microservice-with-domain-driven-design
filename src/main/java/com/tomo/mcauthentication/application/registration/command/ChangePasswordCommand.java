package com.tomo.mcauthentication.application.registration.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordCommand extends BaseCommand {

    String accessToken;

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;

    @NotNull
    private String newPasswordRepeated;

    public ChangePasswordCommand(String accessToken, String oldPassword, String newPassword, String newPasswordRepeated) {
        this.accessToken = accessToken;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordRepeated = newPasswordRepeated;
    }
}
