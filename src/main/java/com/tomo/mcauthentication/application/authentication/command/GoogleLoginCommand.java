package com.tomo.mcauthentication.application.authentication.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class GoogleLoginCommand extends BaseLoginCommand {
    private String accessCode;

    public GoogleLoginCommand(String accessCode) {
        this.accessCode = accessCode;
    }
}
