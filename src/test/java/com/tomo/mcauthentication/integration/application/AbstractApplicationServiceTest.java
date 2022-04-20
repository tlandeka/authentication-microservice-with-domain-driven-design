package com.tomo.mcauthentication.integration.application;

import com.tomo.mcauthentication.application.authentication.EmailLoginCommandHandler;
import com.tomo.mcauthentication.application.authentication.FacebookLoginCommandHandler;
import com.tomo.mcauthentication.application.authentication.GoogleLoginCommandHandler;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.registration.ConfirmUserRegistrationCommandHandler;
import com.tomo.mcauthentication.application.registration.RegisterNewUserCommandHandler;
import com.tomo.mcauthentication.application.registration.command.ConfirmUserRegistrationCommand;
import com.tomo.mcauthentication.domain.oauth2.OAuth2Principal;
import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserRepository;
import com.tomo.mcauthentication.infrastructure.http.oauth2.FacebookOAuth2Authentication;
import com.tomo.mcauthentication.infrastructure.http.oauth2.GoogleOAuth2Authentication;
import com.tomo.mcauthentication.integration.BaseIntegrationTest;
import com.tomo.mcauthentication.testdata.CommandObjectMother;
import com.tomo.mcauthentication.testdata.StaticFields;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public abstract class AbstractApplicationServiceTest extends BaseIntegrationTest {

    @Autowired
    protected FacebookLoginCommandHandler facebookLoginCommandHandler;

    @Autowired
    protected GoogleLoginCommandHandler googleLoginCommandHandler;

    @Autowired
    protected EmailLoginCommandHandler emailLoginCommandHandler;

    @MockBean
    protected FacebookOAuth2Authentication facebookOAuth2Authentication;

    @MockBean
    protected GoogleOAuth2Authentication googleOAuth2Authentication;

    @Autowired
    protected ConfirmUserRegistrationCommandHandler confirmUserRegistrationCommandHandler;

    @Autowired
    protected RegisterNewUserCommandHandler registerNewUserCommandHandler;

    @Autowired
    protected UserRegistrationRepository userRegistrationRepository;

    @Autowired
    protected UserRepository userRepository;

    protected User createFormUser() {
        confirmUserRegistrationCommandHandler.handle(
                new ConfirmUserRegistrationCommand(createUserRegistration().getConfirmLink()));
        return userRepository.findByEmail(StaticFields.USER_EMAIL);
    }

    protected UserRegistration createUserRegistration() {
        registerNewUserCommandHandler.handle(CommandObjectMother.registerNewUserCommand());
        Optional<UserRegistration> userRegistration = userRegistrationRepository
                .findAllByEmail(Arrays.asList(StaticFields.USER_EMAIL))
                .stream()
                .findFirst();

        return userRegistration.get();
    }

    protected SessionDto formLogin(){
        createFormUser();
        return emailLoginCommandHandler.handle(CommandObjectMother.emailLoginCommand());
    }

    protected User createFacbookUser() {
        when(facebookOAuth2Authentication.authenticate(anyString()))
                .thenReturn(oAuth2Principal(User.AuthProvider.FACEBOOK.toString()));

        facebookLoginCommandHandler.handle(CommandObjectMother.facebookLoginCommand());
        return userRepository.findByEmail(StaticFields.USER_EMAIL);
    }

    protected User createGoogleUser() {
        when(googleOAuth2Authentication.authenticate(anyString()))
                .thenReturn(oAuth2Principal(User.AuthProvider.GOOGLE.toString()));

       googleLoginCommandHandler.handle(CommandObjectMother.googleLoginCommand());
        return userRepository.findByEmail(StaticFields.USER_EMAIL);
    }

    private OAuth2Principal oAuth2Principal(String anProvider) {
        return new OAuth2Principal(
                StaticFields.USER_OAUTH_ID,
                StaticFields.USER_EMAIL,
                StaticFields.USER_FIRST_NAME,
                StaticFields.USER_LAST_NAME,
                "img",
                anProvider);
    }
}
