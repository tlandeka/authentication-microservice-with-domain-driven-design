package com.tomo.mcauthentication.application.authentication.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FacebookLoginCommand extends BaseLoginCommand {
    private String accessCode;

    public FacebookLoginCommand(String accessCode) {
        this.accessCode = accessCode;
    }
}
