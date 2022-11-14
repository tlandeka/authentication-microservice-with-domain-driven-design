package com.tomo.mcauthentication.unit.domain.registration.rules;

import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;
import com.tomo.mcauthentication.domain.registration.rules.UserRegistrationCannotBeConfirmedMoreThanOnce;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserRegistrationCannotBeConfirmedMoreThanOnceTest {
    @Test
    public void testRuleIsNotBroken() {
        assertTrue((new UserRegistrationCannotBeConfirmedMoreThanOnce(UserRegistrationStatus.WaitingForConfirmation)).isRuleComplied());
        assertTrue((new UserRegistrationCannotBeConfirmedMoreThanOnce(UserRegistrationStatus.Expired)).isRuleComplied());
    }

    @Test
    public void testRuleIsBroken() {
        assertFalse((new UserRegistrationCannotBeConfirmedMoreThanOnce(UserRegistrationStatus.Confirmed)).isRuleComplied());
    }
}
