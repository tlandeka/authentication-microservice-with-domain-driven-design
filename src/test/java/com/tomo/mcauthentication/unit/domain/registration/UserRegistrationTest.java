package com.tomo.mcauthentication.unit.domain.registration;

import com.tomo.ddd.domain.DomainEventPublisher;
import com.tomo.mcauthentication.domain.DomainRegistry;
import com.tomo.mcauthentication.domain.EncryptionService;
import com.tomo.mcauthentication.domain.registration.PasswordService;
import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;
import com.tomo.mcauthentication.domain.registration.events.*;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserId;
import com.tomo.mcauthentication.domain.users.UserRepository;
import com.tomo.mcauthentication.testdata.StaticFields;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = { "unit" })
public class UserRegistrationTest {

    private static boolean setUpIsDone = false;

    private static final String WEAK_PASS = "abc";

    DomainRegistry domainRegistry;

    @Mock
    UserRegistrationRepository userRegistrationRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    EncryptionService encryptionService;

    @Mock
    ApplicationContext applicationContext;

    @Mock
    DomainEventPublisher domainEventPublisher;

    MockedStatic<DomainEventPublisher> publisher;

    MockedConstruction<User> mockedUser;

    @BeforeEach
    public void setUp() {
        publisher = Mockito.mockStatic(DomainEventPublisher.class);
        publisher.when(DomainEventPublisher::instance).thenReturn(domainEventPublisher);
        mockedUser = Mockito.mockConstruction(User.class);

        domainRegistry = new DomainRegistry();
        domainRegistry.setApplicationContext(applicationContext);

        when(applicationContext.getBean("passwordService")).thenReturn(new PasswordService());
        when(applicationContext.getBean("MD5EncryptionService")).thenReturn(encryptionService);
        when(applicationContext.getBean("userRepository")).thenReturn(userRepository);
        when(applicationContext.getBean("userRegistrationRepository")).thenReturn(userRegistrationRepository);

        when(encryptionService.encryptedValue(anyString())).thenReturn("randomEncrypted");
        when(encryptionService.encryptedValue(WEAK_PASS)).thenReturn("weekPassEncrypted");
        when(encryptionService.encryptedValue(StaticFields.PASSWORD)).thenReturn("currentPassEncrypted");
        when(encryptionService.encryptedValue(StaticFields.NEW_PASS)).thenReturn("newPassEncrypted");

        when(userRegistrationRepository.countByEmailAndStatus(any(), any())).thenReturn(Long.valueOf(0));
        when(userRepository.findByEmail(any())).thenReturn(null);

        setUpIsDone = true;
    }

    @AfterEach
    public void after() {
        mockedUser.close();
        publisher.close();
        Mockito.reset(domainEventPublisher);
    }

    @Order(1)
    @Test
    public void testCreateUserRegistration() {
        UserRegistration ur = userRegistration();

        assertEquals(ur.getEmail(), StaticFields.USER_EMAIL);
        assertEquals(ur.getFirstName(), StaticFields.USER_FIRST_NAME);
        assertEquals(ur.getLastName(), StaticFields.USER_LAST_NAME);
        assertEquals(ur.getStatus(), UserRegistrationStatus.WaitingForConfirmation);
        assertNotEquals(ur.getPassword(), StaticFields.PASSWORD);
        assertNotNull(ur.getConfirmationCode());

        verify(domainEventPublisher, times(1)).publish(isA(UserRegistrationRequested.class));
    }

    @Test
    @Order(2)
    public void testCreateUserFromUserRegistration() {
        UserId userId = new UserId(UUID.randomUUID());
        when(userRepository.nextIdentity()).thenReturn(userId);

        UserRegistration ur = userRegistration();
        ur.createUser(userRepository);

        assertEquals(ur.getStatus(), UserRegistrationStatus.Confirmed);
        assertEquals(ur.getUserId(), userId);
        verify(domainEventPublisher, times(1)).publish(isA(UserRegistrationConfirmed.class));
    }

    @Test
    @Order(3)
    public void testCreateRecoveryCodeAndRecoverPassword() {
        UserId userId = new UserId(UUID.randomUUID());
        when(userRepository.nextIdentity()).thenReturn(userId);

        UserRegistration ur = userRegistration();
        ur.createUser(userRepository);

        String recoveryCode = ur.createRecoveryCode();

        assertNotNull(ur.getRecoveryCode());
        assertTrue(ur.getRecoveryCode().length() > 0);
        assertNotNull(ur.getRecoveryCodeExpirationDate());
        assertTrue(ur.getRecoveryCodeExpirationDate().isAfter(LocalDateTime.now()));

        verify(domainEventPublisher, times(1)).publish(isA(PasswordRecoveryCodeCreated.class));

        assertThrows(RuntimeException.class, () -> {
            ur.changePasswordWithRecoveryCode(recoveryCode, StaticFields.NEW_PASS, StaticFields.PASSWORD); //repeted pass not equal
        });

        assertThrows(RuntimeException.class, () -> {
            ur.changePasswordWithRecoveryCode(recoveryCode, StaticFields.PASSWORD, StaticFields.PASSWORD); //same pass
        });

        assertThrows(RuntimeException.class, () -> {
            ur.changePasswordWithRecoveryCode(recoveryCode, WEAK_PASS, WEAK_PASS); //weak pass
        });

        assertThrows(RuntimeException.class, () -> {
            ur.changePasswordWithRecoveryCode(recoveryCode, StaticFields.USER_EMAIL, StaticFields.USER_EMAIL); //pass not equal to email
        });

        ur.changePasswordWithRecoveryCode(recoveryCode, StaticFields.NEW_PASS, StaticFields.NEW_PASS);
        verify(domainEventPublisher, times(1)).publish(isA(PasswordRecovered.class));
    }

    @Test
    @Order(4)
    public void testChangePassword() {
        UserId userId = new UserId(UUID.randomUUID());
        when(userRepository.nextIdentity()).thenReturn(userId);

        UserRegistration ur = userRegistration();
        ur.createUser(userRepository);

        String oldPassword = ur.getPassword();

        assertThrows(RuntimeException.class, () -> {
            ur.changePassword(StaticFields.PASSWORD, StaticFields.NEW_PASS, StaticFields.PASSWORD); //repeted pass not equal
        });

        assertThrows(RuntimeException.class, () -> {
            ur.changePassword(StaticFields.PASSWORD, StaticFields.PASSWORD, StaticFields.PASSWORD); //same pass
        });

        assertThrows(RuntimeException.class, () -> {
            ur.changePassword(StaticFields.PASSWORD, WEAK_PASS, WEAK_PASS); //weak pass
        });

        assertThrows(RuntimeException.class, () -> {
            ur.changePassword(StaticFields.PASSWORD, StaticFields.USER_EMAIL, StaticFields.USER_EMAIL); //pass not equal to email
        });

        ur.changePassword(StaticFields.PASSWORD, StaticFields.NEW_PASS, StaticFields.NEW_PASS);

        assertNotEquals(oldPassword, ur.getPassword());
        assertEquals(this.encryptionService.encryptedValue(StaticFields.NEW_PASS), ur.getPassword());

        verify(domainEventPublisher, times(1)).publish(isA(PasswordChanged.class));
    }

    private UserRegistration userRegistration() {
        return UserRegistration.registerNewUser(
                StaticFields.PASSWORD,
                StaticFields.USER_EMAIL,
                StaticFields.USER_FIRST_NAME,
                StaticFields.USER_LAST_NAME);
    }
}
