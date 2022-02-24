package com.tomo.mcauthentication.application.authentication.facebook;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FacebookLoginCommand extends BaseCommand {
    String accessCode;

    public FacebookLoginCommand(String accessCode) {
        this.accessCode = accessCode;
    }
}
