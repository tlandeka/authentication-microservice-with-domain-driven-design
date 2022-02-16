package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.mcauthentication.domain.user_registrations.UserRegistration;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationStatus;

import java.util.List;

public class UserRegistrationJpaRepositoryAdapter implements UserRegistrationRepository {

    UserRegistrationJpaRepository jpaRepository;

    public UserRegistrationJpaRepositoryAdapter(UserRegistrationJpaRepository userRegistrationJpaRepository) {
        this.jpaRepository = userRegistrationJpaRepository;
    }

    @Override
    public UserRegistration add(UserRegistration userRegistration) {
        return jpaRepository.save(userRegistration);
    }

    @Override
    public List<UserRegistration> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public long countByEmailAndStatus(String email, UserRegistrationStatus status) {
        return jpaRepository.countByEmail(email);
    }

    @Override
    public List<UserRegistration> findAllByEmail(List<String> emails) {
        return jpaRepository.findAllByEmailIn(emails);
    }

    @Override public UserRegistration findByConfirmLink(String confirmLink) {
        return jpaRepository.findUserRegistrationByConfirmLink(confirmLink);
    }
}
