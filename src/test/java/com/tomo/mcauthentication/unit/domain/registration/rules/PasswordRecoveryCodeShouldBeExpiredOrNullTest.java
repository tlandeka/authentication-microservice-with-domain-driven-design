package com.tomo.mcauthentication.unit.domain.registration.rules;

import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.rules.PasswordRecoveryCodeShouldBeExpiredOrNull;
import com.tomo.mcauthentication.unit.domain.AbstractUnitTest;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PasswordRecoveryCodeShouldBeExpiredOrNullTest extends AbstractUnitTest {

    @Test
    public void testRuleIsNotBroken() {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setRecoveryCodeExpirationDate(LocalDateTime.now().minusSeconds(60));

        assertTrue((new PasswordRecoveryCodeShouldBeExpiredOrNull(userRegistration)).isRuleComplied());
        assertTrue((new PasswordRecoveryCodeShouldBeExpiredOrNull(new UserRegistration())).isRuleComplied());
    }

    @Test
    public void testRuleIsBroken() {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setRecoveryCodeExpirationDate(LocalDateTime.now().plusSeconds(60));
        userRegistration.setRecoveryCode("123");

        assertFalse((new PasswordRecoveryCodeShouldBeExpiredOrNull(userRegistration)).isRuleComplied());
    }
}
