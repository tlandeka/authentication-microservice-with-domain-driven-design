package com.tomo.mcauthentication.application.authentication.facebook;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FacebookLoginCommand extends BaseCommand {
    String accessCode;
}
