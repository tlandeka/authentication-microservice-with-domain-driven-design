package com.tomo.mcauthentication.domain.users;

import com.tomo.mcauthentication.ddd.domain.ConcurrencySafeEntity;
import com.tomo.mcauthentication.ddd.domain.DomainEvent;
import com.tomo.mcauthentication.ddd.domain.DomainEventPublisher;
import com.tomo.mcauthentication.domain.DomainRegistry;
import com.tomo.mcauthentication.domain.users.events.UserCreated;
import com.tomo.mcauthentication.domain.users.events.UserNameChanged;
import com.tomo.mcauthentication.domain.users.rules.UserEmailMustBeUnique;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "mcuser")
@Getter
@NoArgsConstructor
public class User extends ConcurrencySafeEntity {

    public enum AuthProvider {
        EMAIL,
        FACEBOOK,
        GOOGLE
    }

    @EmbeddedId
    UserId userId;
    String firstName;
    String lastName;
    String email;

    @Enumerated(EnumType.STRING)
    AuthProvider provider;

    public User(
            UserId anId,
            String aFirstName,
            String aLastName,
            String anEmail,
            AuthProvider aProvider) {
        this.checkRule(DomainRegistry.userEmailMustBeUnique(anEmail));
        this.userId = anId;
        this.firstName = aFirstName;
        this.lastName = aLastName;
        this.email = anEmail;
        this.provider = aProvider;

        this.publish(new UserCreated(
                this.getUserId(),
                this.getFirstName(),
                this.getLastName(),
                this.getEmail(),
                this.getProvider()
        ));
    }

    public void updateDetails(String aFirstName, String aLastName) {
        this.setFirstName(aFirstName);
        this.setLastName(aLastName);

        publish(new UserNameChanged(
                this.getEmail(),
                this.getFirstName(),
                this.getLastName()));
    }

    public void setLastName(String aLastName) {
        assertArgumentNotEmpty(lastName, "Last name cannot be empty.");
        this.lastName = aLastName;
    }

    public void setFirstName(String aFirstName) {
        assertArgumentNotEmpty(aFirstName, "First name cannot be empty.");
        this.firstName = aFirstName;
    }

    private void publish(DomainEvent event) {
        DomainEventPublisher.instance().publish(event);
    }
}
