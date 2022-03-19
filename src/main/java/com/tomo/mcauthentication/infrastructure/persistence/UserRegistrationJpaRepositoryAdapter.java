package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.mcauthentication.domain.user_registrations.UserRegistration;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationStatus;

import java.util.List;

public class UserRegistrationJpaRepositoryAdapter
        extends BaseJpaAdapter<UserRegistration, Long, UserRegistrationJpaRepository>
        implements UserRegistrationRepository {

    public UserRegistrationJpaRepositoryAdapter(UserRegistrationJpaRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public long countByEmailAndStatus(String email, UserRegistrationStatus status) {
        return jpaRepository.countByEmail(email);
    }

    @Override
    public List<UserRegistration> findAllByEmail(List<String> emails) {
        return jpaRepository.findAllByEmailIn(emails);
    }

    @Override
    public UserRegistration findByEmail(String anEmail) {
        return jpaRepository.findUserRegistrationByEmail(anEmail);
    }

    @Override public UserRegistration findByConfirmLink(String confirmLink) {
        return jpaRepository.findUserRegistrationByConfirmLink(confirmLink);
    }
}
