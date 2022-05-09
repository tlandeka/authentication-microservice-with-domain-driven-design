package com.tomo.mcauthentication.unit.domain.registration.rules;

import com.tomo.mcauthentication.domain.registration.rules.UserRegistrationCannotBeConfirmedAfterExpiration;
import com.tomo.mcauthentication.unit.domain.AbstractUnitTest;

import org.junit.Test;

import java.time.LocalDateTime;

import static com.tomo.mcauthentication.domain.registration.rules.UserRegistrationCannotBeConfirmedAfterExpiration.CONFIRMATION_LINK_DURATION;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserRegistrationCannotBeConfirmedAfterExpirationTest extends AbstractUnitTest {

    @Test
    public void testRuleIsNotBroken() {
        LocalDateTime registerDate1 = LocalDateTime.now();
        assertTrue((new UserRegistrationCannotBeConfirmedAfterExpiration(registerDate1)).isRuleComplied());

        LocalDateTime registerDate2 = LocalDateTime.now().minusDays(CONFIRMATION_LINK_DURATION).plusSeconds(5);
        assertTrue((new UserRegistrationCannotBeConfirmedAfterExpiration(registerDate2)).isRuleComplied());
    }

    @Test
    public void testRuleIsBroken() {
        LocalDateTime registerDate1 = LocalDateTime.now().minusDays(CONFIRMATION_LINK_DURATION);
        assertFalse((new UserRegistrationCannotBeConfirmedAfterExpiration(registerDate1)).isRuleComplied());
    }

}
