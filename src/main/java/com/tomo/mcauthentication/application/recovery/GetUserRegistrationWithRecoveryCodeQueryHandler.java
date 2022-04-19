package com.tomo.mcauthentication.application.recovery;

import com.tomo.mcauthentication.application.BaseMapper;
import com.tomo.mcauthentication.application.configuration.QueryHandler;
import com.tomo.mcauthentication.application.registration.dto.UserRegistrationDto;
import com.tomo.mcauthentication.application.recovery.dto.GetUserRegistrationWithRecoveryCodeQuery;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GetUserRegistrationWithRecoveryCodeQueryHandler extends BaseMapper implements QueryHandler<GetUserRegistrationWithRecoveryCodeQuery, UserRegistrationDto> {

    private UserRegistrationRepository userRegistrationRepository;

    public GetUserRegistrationWithRecoveryCodeQueryHandler(
            UserRegistrationRepository aUserRegistrationRepository,
            ModelMapper modelMapper) {
        super(modelMapper);
        this.userRegistrationRepository = aUserRegistrationRepository;
    }

    @Override public UserRegistrationDto handle(GetUserRegistrationWithRecoveryCodeQuery query) {
        UserRegistrationDto dto = toDto(
                userRegistrationRepository.findByRecoveryCode(query.getRecoveryCode()),
                UserRegistrationDto.class
        );
        return dto;
    }
}
