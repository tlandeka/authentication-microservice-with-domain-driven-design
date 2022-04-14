package com.tomo.mcauthentication.application.registration.query;

import com.tomo.mcauthentication.application.contracts.BaseQuery;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetUserRegistrationWithRecoveryCodeQuery extends BaseQuery {

    @NotNull
    UUID recoveryCode;
}
