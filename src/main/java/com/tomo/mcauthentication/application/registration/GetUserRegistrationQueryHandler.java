package com.tomo.mcauthentication.application.registration;

import com.tomo.mcauthentication.application.configuration.QueryHandler;
import com.tomo.mcauthentication.application.registration.dto.UserRegistrationDto;
import com.tomo.mcauthentication.application.registration.query.GetUserRegistrationQuery;

public class GetUserRegistrationQueryHandler implements QueryHandler<GetUserRegistrationQuery, UserRegistrationDto> {

    @Override public UserRegistrationDto handle(GetUserRegistrationQuery request) {
        return null;
    }

}
