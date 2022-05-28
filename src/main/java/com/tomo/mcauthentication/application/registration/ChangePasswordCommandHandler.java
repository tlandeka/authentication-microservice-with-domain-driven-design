package com.tomo.mcauthentication.application.registration;

import com.tomo.mcauthentication.application.configuration.AbstractVoidyCommandHandler;
import com.tomo.mcauthentication.application.registration.command.ChangePasswordCommand;
import com.tomo.mcauthentication.domain.registration.EmailAuthenticationService;
import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionAuthenticationService;

import org.springframework.stereotype.Service;

@Service
public class ChangePasswordCommandHandler extends AbstractVoidyCommandHandler<ChangePasswordCommand> {

    EmailAuthenticationService emailAuthenticationService;
    SessionAuthenticationService sessionAuthenticationService;
    UserRegistrationRepository userRegistrationRepository;

    public ChangePasswordCommandHandler(
            EmailAuthenticationService emailAuthenticationService,
            SessionAuthenticationService aSessionAuthenticationService,
            UserRegistrationRepository anUserRegistrationRepository) {
        this.emailAuthenticationService = emailAuthenticationService;
        this.sessionAuthenticationService = aSessionAuthenticationService;
        this.userRegistrationRepository = anUserRegistrationRepository;
    }

    @Override
    protected void abstractHandle(ChangePasswordCommand aCommand) {
        Session session = sessionAuthenticationService.authenticate(aCommand.getAccessToken());

        UserRegistration userRegistration = userRegistrationRepository.findByUserId(session.getUserId());

        if (userRegistration == null) {
            throw new IllegalStateException(String.format("User with id %s doesn't exist.", session.getUserId().id()));
        }

        userRegistration.changePassword(aCommand.getOldPassword(), aCommand.getNewPassword(), aCommand.getNewPasswordRepeated());
        userRegistrationRepository.save(userRegistration);
    }
}
