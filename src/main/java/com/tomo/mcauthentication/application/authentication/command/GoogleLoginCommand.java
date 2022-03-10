package com.tomo.mcauthentication.application.authentication.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class GoogleLoginCommand extends BaseCommand {
    private String accessCode;
    private Boolean rememberMe;
    private String userAgent;
    private String ipAddress;

    public GoogleLoginCommand(String accessCode) {
        this.accessCode = accessCode;
    }
}
