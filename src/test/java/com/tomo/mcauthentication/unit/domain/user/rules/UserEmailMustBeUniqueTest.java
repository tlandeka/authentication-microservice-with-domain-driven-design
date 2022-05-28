package com.tomo.mcauthentication.unit.domain.user.rules;

import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserRepository;
import com.tomo.mcauthentication.domain.users.rules.UserEmailMustBeUnique;
import com.tomo.mcauthentication.unit.domain.AbstractUnitTest;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserEmailMustBeUniqueTest extends AbstractUnitTest {

    UserRepository repository = Mockito.mock(UserRepository.class);

    @Test
    public void testRuleIsNotBroken() {
        when(repository.findByEmail(any())).thenReturn(null);
        assertTrue((new UserEmailMustBeUnique(repository, "random@email.com")).isRuleComplied());
    }

    @Test
    public void testRuleIsBroken() {
        when(repository.findByEmail(any())).thenReturn(new User());
        assertFalse((new UserEmailMustBeUnique(repository, "random@email.com")).isRuleComplied());
    }

}
