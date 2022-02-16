package com.tomo.mcauthentication.domain.users;

import com.tomo.mcauthentication.ddd.domain.ConcurrencySafeEntity;
import com.tomo.mcauthentication.domain.users.rules.UserEmailMustBeUnique;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "mcuser")
@Getter
@NoArgsConstructor
public class User extends ConcurrencySafeEntity {

    public enum LoginType {
        EMAIL,
        FACEBOOK,
        GOOGLE
    }

    @EmbeddedId
    UserId userId;
    String firstName;
    String lastName;
    String email;
    LoginType loginType;

    public User(
            UserId anId,
            String aFirstName,
            String aLastName,
            String anEmail,
            LoginType aLoginType,
            UserRespository userRespository) {
        this.checkRule(new UserEmailMustBeUnique(userRespository, email));
        this.userId = anId;
        this.firstName = aFirstName;
        this.lastName = aLastName;
        this.email = anEmail;
        this.loginType = aLoginType;
    }

    public void setFirstName(String aFirstName) {
        this.firstName = aFirstName;
    }
}
