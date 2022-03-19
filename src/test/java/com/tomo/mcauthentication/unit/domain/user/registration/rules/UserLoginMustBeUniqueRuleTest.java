package com.tomo.mcauthentication.unit.domain.user.registration.rules;

import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.registration.rules.UserRegistrationMustBeUniqueRule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginMustBeUniqueRuleTest {

    UserRegistrationRepository repository = Mockito.mock(UserRegistrationRepository.class);

    @Test
    public void testRuleIsNotBroken() {
        when(repository.countByEmailAndStatus(any(), any())).thenReturn(0L);
        assertFalse((new UserRegistrationMustBeUniqueRule(repository, "")).isBroken());
    }

    @Test
    public void testRuleIsBroken() {
        when(repository.countByEmailAndStatus(any(), any())).thenReturn(1L);
        assertTrue((new UserRegistrationMustBeUniqueRule(repository, "")).isBroken());
    }
}
