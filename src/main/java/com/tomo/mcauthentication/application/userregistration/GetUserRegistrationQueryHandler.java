package com.tomo.mcauthentication.application.userregistration;

import com.tomo.mcauthentication.application.configuration.QueryHandler;
import com.tomo.mcauthentication.application.userregistration.dto.UserRegistrationDto;
import com.tomo.mcauthentication.application.userregistration.query.GetUserRegistrationQuery;

public class GetUserRegistrationQueryHandler implements QueryHandler<GetUserRegistrationQuery, UserRegistrationDto> {

    @Override public UserRegistrationDto handle(GetUserRegistrationQuery request) {
        return null;
    }

}
