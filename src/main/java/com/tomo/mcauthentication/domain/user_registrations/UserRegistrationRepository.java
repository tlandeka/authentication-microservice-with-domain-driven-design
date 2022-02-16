package com.tomo.mcauthentication.domain.user_registrations;

import com.tomo.mcauthentication.domain.users.User;

import java.util.List;
public interface UserRegistrationRepository {
    UserRegistration add (UserRegistration userRegistration);
    List<UserRegistration> findAll();
    List<UserRegistration> findAllByEmail(List<String> emails);
    long countByEmailAndStatus(String email, UserRegistrationStatus status);
    UserRegistration findByConfirmLink(String confirmLink);
}
