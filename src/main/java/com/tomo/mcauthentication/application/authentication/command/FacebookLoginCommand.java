package com.tomo.mcauthentication.application.authentication.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FacebookLoginCommand extends BaseCommand {
    private String accessCode;
    private Boolean rememberMe;
    private String userAgent;
    private String ipAddress;

    public FacebookLoginCommand(String accessCode) {
        this.accessCode = accessCode;
    }
}
