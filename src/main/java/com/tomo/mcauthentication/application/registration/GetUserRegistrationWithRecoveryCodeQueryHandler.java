package com.tomo.mcauthentication.application.registration;

import com.tomo.mcauthentication.application.BaseMapper;
import com.tomo.mcauthentication.application.configuration.QueryHandler;
import com.tomo.mcauthentication.application.registration.dto.UserRegistrationDto;
import com.tomo.mcauthentication.application.registration.query.GetUserRegistrationWithRecoveryCodeQuery;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GetUserRegistrationWithRecoveryCodeQueryHandler extends BaseMapper implements QueryHandler<GetUserRegistrationWithRecoveryCodeQuery, UserRegistrationDto> {

    private UserRepository userRepository;

    public GetUserRegistrationWithRecoveryCodeQueryHandler(
            UserRepository userRepository,
            ModelMapper modelMapper) {
        super(modelMapper);
        this.userRepository = userRepository;
    }

    @Override public UserRegistrationDto handle(GetUserRegistrationWithRecoveryCodeQuery query) {
        return null;
    }
}
