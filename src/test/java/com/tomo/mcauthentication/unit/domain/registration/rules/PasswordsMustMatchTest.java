package com.tomo.mcauthentication.unit.domain.registration.rules;

import com.tomo.mcauthentication.domain.registration.rules.PasswordsMustMatch;
import com.tomo.mcauthentication.unit.domain.AbstractUnitTest;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PasswordsMustMatchTest extends AbstractUnitTest {

    @Test
    public void testRuleIsNotBroken() {
        assertTrue((new PasswordsMustMatch("abc", "abc")).isRuleComplied());
    }

    @Test
    public void testRuleIsBroken() {
        assertFalse((new PasswordsMustMatch("abc1", "abc")).isRuleComplied());
    }
}
