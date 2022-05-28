package com.tomo.mcauthentication.unit.application.registration;

import com.tomo.mcauthentication.domain.DomainRegistry;
import com.tomo.mcauthentication.domain.EncryptionService;
import com.tomo.mcauthentication.domain.registration.PasswordService;
import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;
import com.tomo.mcauthentication.domain.users.UserRepository;
import com.tomo.mcauthentication.unit.domain.AbstractUnitTest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoSession;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.context.ApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRegistrationCommandHandlerTest {

    @Spy
    DomainRegistry domainRegistry;

    @Mock
    UserRegistrationRepository userRegistrationRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    EncryptionService encryptionService;

    @Mock
    ApplicationContext applicationContext;

    protected MockitoSession mockito;

//    @BeforeAll
//    public void setup() {
//        mockito = Mockito.mockitoSession()
//                .initMocks(this)
//                          .strictness(Strictness.STRICT_STUBS)
//                .startMocking();
//    }
//
//    @AfterAll
//    public void tearDown() {
//        mockito.finishMocking();
//    }

    @BeforeEach
    public void setUp(){
        domainRegistry.setApplicationContext(applicationContext);

//        when(encryptionService.encryptedValue(any())).thenReturn("randomString");
        when(applicationContext.getBean("passwordService")).thenReturn(new PasswordService());
        when(applicationContext.getBean("MD5EncryptionService")).thenReturn(encryptionService);
        when(applicationContext.getBean("userRepository")).thenReturn(userRepository);
        when(applicationContext.getBean("userRegistrationRepository")).thenReturn(userRegistrationRepository);
        DomainRegistry.encryptionService();
        int a = 5;
    }

    @Test
    public void testCreateUserRegister() {
        when(userRegistrationRepository.countByEmailAndStatus(any(), any())).thenReturn(Long.valueOf(0));
        when(userRepository.findByEmail(any())).thenReturn(null);
        UserRegistration ur = UserRegistration.registerNewUser("AA123bb##", "email", "firstName", "lastName");

        assertEquals(ur.getEmail(), "email");
        assertEquals(ur.getStatus(), UserRegistrationStatus.WaitingForConfirmation);
        assertNotEquals(ur.getEmail(), "email1");
    }
}
