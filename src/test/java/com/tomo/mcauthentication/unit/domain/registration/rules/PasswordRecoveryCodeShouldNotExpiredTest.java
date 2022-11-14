package com.tomo.mcauthentication.unit.domain.registration.rules;

import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.rules.PasswordRecoveryCodeShouldNotExpired;
import com.tomo.mcauthentication.unit.domain.AbstractUnitTest;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PasswordRecoveryCodeShouldNotExpiredTest extends AbstractUnitTest {

    @Test
    public void testRuleIsNotBroken() {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setRecoveryCodeExpirationDate(LocalDateTime.now().plusSeconds(60));
        assertTrue((new PasswordRecoveryCodeShouldNotExpired(userRegistration)).isRuleComplied());
    }

    @Test
    public void testRuleIsBroken() {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setRecoveryCodeExpirationDate(LocalDateTime.now());

        assertFalse((new PasswordRecoveryCodeShouldNotExpired(userRegistration)).isRuleComplied());
        assertFalse((new PasswordRecoveryCodeShouldNotExpired(new UserRegistration())).isRuleComplied());
    }
}
