package com.tomo.mcauthentication.application.registration.query;

import com.tomo.mcauthentication.application.contracts.BaseQuery;

import java.util.UUID;

public class GetUserRegistrationQuery extends BaseQuery {

    public GetUserRegistrationQuery(UUID id) {
        super(id);
    }
}
