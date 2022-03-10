package com.tomo.mcauthentication.application.userregistration.query;

import com.tomo.mcauthentication.application.contracts.BaseQuery;
import com.tomo.mcauthentication.application.contracts.Query;

import java.util.UUID;

public class GetUserRegistrationQuery extends BaseQuery {

    public GetUserRegistrationQuery(UUID id) {
        super(id);
    }
}
