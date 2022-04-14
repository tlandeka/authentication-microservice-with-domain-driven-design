package com.tomo.mcauthentication.domain.registration;

import com.tomo.mcauthentication.ddd.domain.BusinessRuleValidator;
import com.tomo.mcauthentication.domain.EncryptionService;
import com.tomo.mcauthentication.domain.registration.rules.PasswordsMustMatch;
import com.tomo.mcauthentication.domain.registration.rules.UserRegistrationMustBeConfirmed;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmailAuthenticationService extends BusinessRuleValidator {

    UserRegistrationRepository userRegistrationRepository;
    UserRepository userRepository;
    EncryptionService encryptionService;

    public EmailAuthenticationService(
            UserRegistrationRepository userRegistrationRepository,
            UserRepository userRepository,
            EncryptionService encryptionService) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
    }

    public User authenticate(
            String anEmail,
            String aPassword) {
        this.assertArgumentNotEmpty(anEmail, "Email must be provided.");
        this.assertArgumentNotEmpty(aPassword, "Password must be provided.");

        UserRegistration userRegistration = userRegistrationRepository.findByEmail(anEmail);

        if (userRegistration == null) {
            throw new IllegalStateException(String.format("User with email %s doesn't exists.", anEmail));
        }

        this.checkRule(new PasswordsMustMatch(userRegistration.getPassword(), encryptionService.encryptedValue(aPassword)));

        return userRepository.findByEmail(anEmail);
    }

    public String createPasswordRecoveryCode(String anEmail) {
        this.assertArgumentNotEmpty(anEmail, "Email must be provided.");

        UserRegistration userRegistration = userRegistrationRepository.findByEmail(anEmail);

        if (userRegistration == null) {
            throw new IllegalStateException(String.format("User with email %s doesn't exists.", anEmail));
        }

        return userRegistration.createRecoveryCode();
    }

    public void recoverPasswordWithRecoveryCode(String aRecoveryCode, String aNewPassword, String aNewPasswordRepeated) {
        this.assertArgumentNotNull(aRecoveryCode, "Recovery code is missing.");

        UserRegistration userRegistration = userRegistrationRepository
                .findByRecoveryCode(encryptionService.encryptedValue(aRecoveryCode));

        if (userRegistration == null) {
            throw new IllegalStateException(String.format("User with recovery code %s doesn't exists.", aRecoveryCode));
        }

        userRegistration.updatePasswordWithRecoveryCode(aRecoveryCode, aNewPassword, aNewPasswordRepeated);
    }
}
