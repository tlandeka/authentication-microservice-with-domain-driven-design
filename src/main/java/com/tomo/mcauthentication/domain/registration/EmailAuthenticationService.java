package com.tomo.mcauthentication.domain.registration;

import com.tomo.mcauthentication.ddd.domain.BusinessRuleValidator;
import com.tomo.mcauthentication.domain.EncryptionService;
import com.tomo.mcauthentication.domain.registration.rules.PasswordsMustMatch;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class EmailAuthenticationService extends BusinessRuleValidator {

    UserRegistrationRepository userRegistrationRepository;
    UserRepository userRepository;
    EncryptionService encryptionService;

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
}
