package com.tomo.mcauthentication.unit.domain.registration.rules;

import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.registration.rules.UserRegistrationMustBeUnique;
import com.tomo.mcauthentication.unit.domain.AbstractUnitTest;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.junit.Assert.assertFalse;

public class UserRegistrationMustBeUniqueTest extends AbstractUnitTest {

    UserRegistrationRepository repository = Mockito.mock(UserRegistrationRepository.class);

    @Test
    public void testRuleIsNotBroken() {
        when(repository.countByEmailAndStatus(any(), any())).thenReturn(0L);
        assertTrue((new UserRegistrationMustBeUnique(repository, "")).isRuleComplied());
    }

    @Test
    public void testRuleIsBroken() {
        when(repository.countByEmailAndStatus(any(), any())).thenReturn(1L);
        assertFalse((new UserRegistrationMustBeUnique(repository, "")).isRuleComplied());
    }
}
