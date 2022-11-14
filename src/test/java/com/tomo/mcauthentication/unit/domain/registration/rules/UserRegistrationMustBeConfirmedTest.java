package com.tomo.mcauthentication.unit.domain.registration.rules;

import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;
import com.tomo.mcauthentication.domain.registration.rules.UserRegistrationMustBeConfirmed;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserRegistrationMustBeConfirmedTest {

    @Test
    public void testRuleIsNotBroken() {
        assertTrue((new UserRegistrationMustBeConfirmed(UserRegistrationStatus.Confirmed)).isRuleComplied());
    }

    @Test
    public void testRuleIsBroken() {
        assertFalse((new UserRegistrationMustBeConfirmed(UserRegistrationStatus.WaitingForConfirmation)).isRuleComplied());
        assertFalse((new UserRegistrationMustBeConfirmed(UserRegistrationStatus.Expired)).isRuleComplied());
    }

}
