package com.tomo.mcauthentication.unit.domain.user.registration;

import com.tomo.mcauthentication.domain.DomainRegistry;
import com.tomo.mcauthentication.domain.EncryptionService;
import com.tomo.mcauthentication.domain.registration.PasswordService;
import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserRegistrationTest {

    DomainRegistry domainRegistry;

    @Mock
    UserRegistrationRepository repository;

    @Mock
    UserRepository userRespository;

    @Mock
    EncryptionService encryptionService;

    @Mock
    ApplicationContext applicationContext;

    @Before
    public void setUp(){
        domainRegistry = new DomainRegistry();
        domainRegistry.setApplicationContext(applicationContext);

        when(encryptionService.encryptedValue(any())).thenReturn("randomString");
        when(applicationContext.getBean("passwordService")).thenReturn(new PasswordService());
        when(applicationContext.getBean("MD5EncryptionService")).thenReturn(encryptionService);
    }

    @Test
    public void testCreateUserRegister() {
        when(repository.countByEmailAndStatus(any(), any())).thenReturn(Long.valueOf(0));
        when(userRespository.findByEmail(any())).thenReturn(null);
        UserRegistration ur = UserRegistration.registerNewUser("AA123bb##", "email", "firstName", "lastName", repository, userRespository);

        assertEquals(ur.getEmail(), "email");
        assertEquals(ur.getStatus(), UserRegistrationStatus.WaitingForConfirmation);
        assertNotEquals(ur.getEmail(), "email1");
    }

    @Test
    public void testCreateUserRegisterBroken() {
        when(repository.countByEmailAndStatus(any(), any())).thenReturn(1L);
        assertThrows(RuntimeException.class, () -> {
            UserRegistration ur = UserRegistration.registerNewUser("test", "email", "firstName", "lastName", repository, userRespository);
        });
    }

}
