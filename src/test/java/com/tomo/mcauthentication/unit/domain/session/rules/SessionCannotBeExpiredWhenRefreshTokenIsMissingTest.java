package com.tomo.mcauthentication.unit.domain.session.rules;

import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.rules.PasswordRecoveryCodeShouldNotExpired;
import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.rule.SessionCannotBeExpiredWhenRefreshTokenIsMissing;
import com.tomo.mcauthentication.unit.domain.AbstractUnitTest;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SessionCannotBeExpiredWhenRefreshTokenIsMissingTest extends AbstractUnitTest {

    @Test
    public void testRuleIsNotBroken() {
        Session session1 = new Session();
        session1.setExpirationDate(LocalDateTime.now().plusDays(2));


        Session session2 = new Session();
        session2.setExpirationDate(LocalDateTime.now().minusDays(2));
        session2.setRefreshToken("randomToken");

        assertTrue((new SessionCannotBeExpiredWhenRefreshTokenIsMissing(session1)).isRuleComplied());
        assertTrue((new SessionCannotBeExpiredWhenRefreshTokenIsMissing(session2)).isRuleComplied());
    }

    @Test
    public void testRuleIsBroken() {
        Session session1 = new Session();
        session1.setExpirationDate(LocalDateTime.now().minusDays(2));

        assertFalse((new SessionCannotBeExpiredWhenRefreshTokenIsMissing(session1)).isRuleComplied());
    }

}
