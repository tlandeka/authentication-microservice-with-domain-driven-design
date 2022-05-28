package com.tomo.mcauthentication.application.recovery;

import com.tomo.mcauthentication.application.BaseMapper;
import com.tomo.mcauthentication.application.configuration.QueryHandler;
import com.tomo.mcauthentication.application.registration.dto.UserRegistrationDto;
import com.tomo.mcauthentication.application.recovery.dto.GetUserRegistrationWithRecoveryCodeQuery;
import com.tomo.mcauthentication.domain.EncryptionService;
import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GetUserRegistrationWithRecoveryCodeQueryHandler extends BaseMapper implements QueryHandler<GetUserRegistrationWithRecoveryCodeQuery, UserRegistrationDto> {

    private UserRegistrationRepository userRegistrationRepository;
    private EncryptionService encryptionService;

    public GetUserRegistrationWithRecoveryCodeQueryHandler(
            UserRegistrationRepository aUserRegistrationRepository,
            EncryptionService anEncryptionService,
            ModelMapper modelMapper) {
        super(modelMapper);
        this.userRegistrationRepository = aUserRegistrationRepository;
        this.encryptionService = anEncryptionService;
    }

    @Override
    public UserRegistrationDto handle(GetUserRegistrationWithRecoveryCodeQuery query) {
        UserRegistration userRegistration = userRegistrationRepository
                .findByRecoveryCode(encryptionService.encryptedValue(query.getRecoveryCode()));

        if (userRegistration == null) {
            throw new IllegalStateException(String.format("User with recovery code %s doesn't exists.", query.getRecoveryCode()));
        }

        UserRegistrationDto dto = toDto(
                userRegistration,
                UserRegistrationDto.class
        );
        return dto;
    }
}
