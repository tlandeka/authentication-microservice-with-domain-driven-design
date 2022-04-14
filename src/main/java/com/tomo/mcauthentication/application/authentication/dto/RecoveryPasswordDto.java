package com.tomo.mcauthentication.application.authentication.dto;

import com.tomo.mcauthentication.application.contracts.Response;

import lombok.Getter;

@Getter
public class RecoveryPasswordDto implements Response {

    String recoveryCode;

    public RecoveryPasswordDto(String recoveryCode) {
        this.recoveryCode = recoveryCode;
    }
}
