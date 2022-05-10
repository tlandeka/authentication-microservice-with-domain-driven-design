package com.tomo.mcauthentication.domain.oauth2;

import com.tomo.mcauthentication.ddd.domain.BusinessRuleValidationException;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserRepository;
import com.tomo.mcauthentication.domain.users.rules.UserEmailMustBeUnique;

public class OAuth2Service {

    private OAuth2Authentication oAuth2Authentication;
    private UserRepository userRespository;

    public OAuth2Service(OAuth2Authentication oAuth2Authentication, UserRepository userRespository) {
        this.oAuth2Authentication = oAuth2Authentication;
        this.userRespository = userRespository;
    }

    public User registerAuthenticate(String anAccessCode) {
        OAuth2Principal principal = oAuth2Authentication.authenticate(anAccessCode);
        User user;
        try {
            user = authenticateAndRegister(principal);
        } catch (BusinessRuleValidationException exception) {
            if (UserEmailMustBeUnique.class == exception.getBrokenRule().getClass()) {
                user = userRespository.findByEmail(principal.getEmail());
                if (!User.AuthProvider.valueOf(principal.getProvider()).equals(user.getProvider())) {
                    throw exception;
                }
            } else {
                throw exception;
            }
        }

        return user;
    }

    protected User authenticateAndRegister(OAuth2Principal principal) {
       User user = new User(
                userRespository.nextIdentity(),
                principal.getFirstName(),
                principal.getLastName(),
                principal.getEmail(),
                User.AuthProvider.valueOf(principal.getProvider()));

       return userRespository.save(user);
    }
}
