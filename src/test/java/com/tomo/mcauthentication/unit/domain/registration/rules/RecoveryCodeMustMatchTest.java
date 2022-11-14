package com.tomo.mcauthentication.unit.domain.registration.rules;

import com.tomo.mcauthentication.domain.registration.rules.RecoveryCodeMustMatch;
import com.tomo.mcauthentication.unit.domain.AbstractUnitTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RecoveryCodeMustMatchTest extends AbstractUnitTest {

    @Test
    public void testRuleIsNotBroken() {
        assertTrue((new RecoveryCodeMustMatch("abc", "abc")).isRuleComplied());
    }

    @Test
    public void testRuleIsBroken() {
        assertFalse((new RecoveryCodeMustMatch("abc1", "abc")).isRuleComplied());
    }
}
