package com.tomo.mcauthentication.domain.registration;

import com.tomo.mcauthentication.ddd.domain.ConcurrencySafeEntity;
import com.tomo.mcauthentication.ddd.domain.DomainEvent;
import com.tomo.mcauthentication.ddd.domain.DomainEventPublisher;
import com.tomo.mcauthentication.domain.DomainRegistry;
import com.tomo.mcauthentication.domain.registration.events.PasswordChanged;
import com.tomo.mcauthentication.domain.registration.events.PasswordRecovered;
import com.tomo.mcauthentication.domain.registration.events.UserRegistrationConfirmed;
import com.tomo.mcauthentication.domain.registration.events.UserRegistrationRequested;
import com.tomo.mcauthentication.domain.registration.events.PasswordRecoveryCodeCreated;
import com.tomo.mcauthentication.domain.registration.rules.PasswordRecoveryCodeShouldBeExpiredOrNull;
import com.tomo.mcauthentication.domain.registration.rules.PasswordRecoveryCodeShouldNotExpired;
import com.tomo.mcauthentication.domain.registration.rules.PasswordsMustMatch;
import com.tomo.mcauthentication.domain.registration.rules.RecoveryCodeMustMatch;
import com.tomo.mcauthentication.domain.registration.rules.UserRegistrationCannotBeConfirmedAfterExpiration;
import com.tomo.mcauthentication.domain.registration.rules.UserRegistrationCannotBeConfirmedMoreThanOnce;
import com.tomo.mcauthentication.domain.registration.rules.UserRegistrationMustBeConfirmed;
import com.tomo.mcauthentication.domain.registration.rules.UserRegistrationMustBeUnique;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserId;
import com.tomo.mcauthentication.domain.users.UserRepository;
import com.tomo.mcauthentication.domain.users.rules.UserEmailMustBeUnique;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserRegistration extends ConcurrencySafeEntity {

    public static Long RECOVERY_CODE_EXPIREATION_MSEC = 172800000L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String confirmationCode;
    private LocalDateTime registerDate;
    @Enumerated(EnumType.STRING)
    private UserRegistrationStatus status;
    private String recoveryCode;
    private LocalDateTime recoveryCodeExpirationDate;

    @Embedded
    @AttributeOverride(name="id", column = @Column(name="user_id"))
    private UserId userId;

    public static UserRegistration registerNewUser(
            String password,
            String email,
            String firstName,
            String lastName,
            UserRegistrationRepository registrationRepository,
            UserRepository userRespository)
    {
        return new UserRegistration(password, email, firstName, lastName, registrationRepository, userRespository);
    }

    private UserRegistration(
            String aPassword,
            String anEmail,
            String aFirstName,
            String aLastName,
            UserRegistrationRepository aRepository,
            UserRepository userRespository) {
        this.checkRule(new UserRegistrationMustBeUnique(aRepository, anEmail));
        this.checkRule(new UserEmailMustBeUnique(userRespository, anEmail));
        this.email = anEmail;
        this.firstName = aFirstName;
        this.lastName = aLastName;
        this.confirmationCode = UUID.randomUUID().toString();
        this.registerDate = LocalDateTime.now();
        this.status = UserRegistrationStatus.WaitingForConfirmation;

        this.protectPassword("", aPassword);

        this.publishEvent(new UserRegistrationRequested(
                this.email,
                this.confirmationCode,
                this.firstName,
                this.lastName,
                this.registerDate,
                this.status
        ));
    }

    public User createUser(UserRepository userRespository) {
        this.checkRule(new UserRegistrationCannotBeConfirmedMoreThanOnce(this.status));
        this.checkRule(new UserRegistrationCannotBeConfirmedAfterExpiration(this.registerDate));

        this.setStatus(UserRegistrationStatus.Confirmed);
        this.setUserId(userId);

        UserId userId = userRespository.nextIdentity();

        this.publishEvent(new UserRegistrationConfirmed(this.id, this.getStatus(), this.getUserId()));

        return new User(userId, getFirstName(), getLastName(), getEmail(), User.AuthProvider.EMAIL, userRespository);
    }

    public String createRecoveryCode() {
        this.checkRule(new UserRegistrationMustBeConfirmed(this.getStatus()));
        this.checkRule(new PasswordRecoveryCodeShouldBeExpiredOrNull(this));

        String recoveryCode = UUID.randomUUID().toString();
        this.recoveryCodeExpirationDate = LocalDateTime.now().plus(RECOVERY_CODE_EXPIREATION_MSEC, ChronoField.MILLI_OF_DAY.getBaseUnit());

        this.setRecoveryCodeExpirationDate(recoveryCodeExpirationDate);
        this.setRecoveryCode(this.asEncryptedValue(recoveryCode));

        this.publishEvent(new PasswordRecoveryCodeCreated(
                this.id,
                this.email,
                recoveryCode,
                this.recoveryCodeExpirationDate));

        return recoveryCode;
    }

    public boolean isRecoveryCodeUnexpired() {
        return recoveryCodeExpirationDate != null && recoveryCodeExpirationDate.isAfter(LocalDateTime.now());
    }

    public boolean isRecoveryCodeExpired() {
        return recoveryCodeExpirationDate != null && recoveryCodeExpirationDate.isBefore(LocalDateTime.now());
    }

    public void changePassword(String anOldPassword, String aNewPassword, String aNewPasswordRepeated) {
        this.assertArgumentNotNull(anOldPassword, "Old password is missing.");
        this.assertNewPassword(aNewPassword, aNewPasswordRepeated);

        this.checkRule(new PasswordsMustMatch(this.getPassword(), this.asEncryptedValue(anOldPassword)));

        this.protectPassword(this.getPassword(), aNewPassword);

        this.publishEvent(new PasswordChanged(this.id, this.password));
    }

    public void changePasswordWithRecoveryCode(String aRecoveryCode, String aNewPassword, String aNewPasswordRepeated) {
        this.assertArgumentNotNull(aNewPassword, "New password is missing.");
        this.assertNewPassword(aNewPassword, aNewPasswordRepeated);

        this.checkRule(new RecoveryCodeMustMatch(this.asEncryptedValue(aRecoveryCode), this.getRecoveryCode()));
        this.checkRule(new PasswordRecoveryCodeShouldNotExpired(this));

        this.setRecoveryCodeExpirationDate(LocalDateTime.now());
        this.protectPassword(this.getPassword(), aNewPassword);

        this.publishEvent(new PasswordRecovered(
                this.id,
                this.getPassword(),
                this.getRecoveryCode()
        ));
    }

    protected void assertNewPassword(String aNewPassword, String aNewPasswordRepeated) {
        this.assertArgumentNotNull(aNewPassword, "New password is missing.");
        this.assertArgumentNotNull(aNewPasswordRepeated, "Repeated password is missing.");
        this.assertArgumentEquals(aNewPassword, aNewPasswordRepeated, "Provided passwords must be equal.");
    }

    protected void protectPassword(String aCurrentPassword, String aChangedPassword) {
        this.assertPasswordsNotSame(aCurrentPassword, aChangedPassword);
        this.assertPasswordNotWeak(aChangedPassword);
        this.assertUsernamePasswordNotSame(aChangedPassword);
        this.setPassword(this.asEncryptedValue(aChangedPassword));
    }

    protected void assertPasswordsNotSame(String aCurrentPassword, String aChangedPassword) {
        this.assertArgumentNotEquals(
                aCurrentPassword,
                aChangedPassword,
                "The password is unchanged.");
    }

    protected void assertPasswordNotWeak(String aPlainTextPassword) {
        this.assertArgumentFalse(
                DomainRegistry.passwordService().isWeak(aPlainTextPassword),
                "The password must be stronger.");
    }

    protected void assertUsernamePasswordNotSame(String aPlainTextPassword) {
        this.assertArgumentNotEquals(
                this.getEmail(),
                aPlainTextPassword,
                "The username and password must not be the same.");
    }

    protected String asEncryptedValue(String aPlainTextPassword) {
        String encryptedValue =
                DomainRegistry
                        .encryptionService()
                        .encryptedValue(aPlainTextPassword);

        return encryptedValue;
    }

    protected void setPassword(String aPassword) {
        this.password = aPassword;
    }

    private void publishEvent(DomainEvent domainEvent) {
        DomainEventPublisher.instance().publish(domainEvent);
    }
}
