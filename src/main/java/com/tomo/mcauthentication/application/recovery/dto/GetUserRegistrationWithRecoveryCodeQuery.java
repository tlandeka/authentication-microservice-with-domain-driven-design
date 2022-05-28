package com.tomo.mcauthentication.application.recovery.dto;

import com.tomo.mcauthentication.application.contracts.BaseQuery;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetUserRegistrationWithRecoveryCodeQuery extends BaseQuery {

    @NotNull
    String recoveryCode;

    public GetUserRegistrationWithRecoveryCodeQuery(String recoveryCode) {
        this.recoveryCode = recoveryCode;
    }
}
