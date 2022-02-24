package com.tomo.mcauthentication.integration.application;

import com.tomo.mcauthentication.application.authentication.facebook.FacebookLoginCommand;
import com.tomo.mcauthentication.application.authentication.facebook.FacebookLoginCommandHandler;
import com.tomo.mcauthentication.application.authentication.google.GoogleLoginCommand;
import com.tomo.mcauthentication.application.authentication.google.GoogleLoginCommandHandler;
import com.tomo.mcauthentication.application.userregistration.confirm_user_registration.ConfirmUserRegistrationCommand;
import com.tomo.mcauthentication.application.userregistration.confirm_user_registration.ConfirmUserRegistrationCommandHandler;
import com.tomo.mcauthentication.application.userregistration.register_new_user.RegisterNewUserCommand;
import com.tomo.mcauthentication.application.userregistration.register_new_user.RegisterNewUserCommandHandler;
import com.tomo.mcauthentication.domain.oauth2.OAuth2Principal;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistration;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserRespository;
import com.tomo.mcauthentication.infrastructure.http.oauth2.FacebookOAuth2Authentication;
import com.tomo.mcauthentication.infrastructure.http.oauth2.GoogleOAuth2Authentication;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
public abstract class ApplicationServiceTest {

    private static final String ACCESS_CODE = "anAccessCode";
    private static final String USER_OAUTH_ID = "anAccessCode";
    private static final String USER_FIRST_NAME = "Tom";
    private static final String USER_LAST_NAME = "Land";
    private static final String USER_EMAIL = "random@email.com";
    private static final String PASSWORD = "AA123bb##";

    @Autowired
    FacebookLoginCommandHandler facebookLoginCommandHandler;

    @Autowired
    GoogleLoginCommandHandler googleLoginCommandHandler;

    @MockBean
    FacebookOAuth2Authentication facebookOAuth2Authentication;

    @MockBean
    GoogleOAuth2Authentication googleOAuth2Authentication;

    @Autowired
    ConfirmUserRegistrationCommandHandler confirmUserRegistrationCommandHandler;

    @Autowired
    RegisterNewUserCommandHandler registerNewUserCommandHandler;

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Autowired
    UserRespository userRespository;

    protected User createFormUser() {
        confirmUserRegistrationCommandHandler.handle(
                new ConfirmUserRegistrationCommand(createUserRegistration().getConfirmLink()));
        return userRespository.findByEmail(USER_EMAIL);
    }

    protected UserRegistration createUserRegistration() {
        registerNewUserCommandHandler.handle(new RegisterNewUserCommand(USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, PASSWORD));
        Optional<UserRegistration> userRegistration = userRegistrationRepository
                .findAllByEmail(Arrays.asList(USER_EMAIL))
                .stream()
                .findFirst();

        return userRegistration.get();
    }

    protected User createFacbookUser() {
        when(facebookOAuth2Authentication.authenticate(anyString()))
                .thenReturn(oAuth2Principal(User.AuthProvider.FACEBOOK.toString()));

        facebookLoginCommandHandler.handle(new FacebookLoginCommand(ACCESS_CODE));
        return userRespository.findByEmail(USER_EMAIL);
    }

    protected User createGoogleUser() {
        when(googleOAuth2Authentication.authenticate(anyString()))
                .thenReturn(oAuth2Principal(User.AuthProvider.GOOGLE.toString()));

       googleLoginCommandHandler.handle(new GoogleLoginCommand(ACCESS_CODE));
        return userRespository.findByEmail(USER_EMAIL);
    }

    private OAuth2Principal oAuth2Principal(String anProvider) {
        return new OAuth2Principal(
                USER_OAUTH_ID,
                USER_EMAIL,
                USER_FIRST_NAME,
                USER_LAST_NAME,
                "img",
                anProvider);
    }

}
