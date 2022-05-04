package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;
import com.tomo.mcauthentication.domain.users.UserId;

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

    @Override public UserRegistration findByConfirmationCode(String confirmationCode) {
        return jpaRepository.findUserRegistrationByConfirmationCode(confirmationCode);
    }

    @Override
    public UserRegistration findByRecoveryCode(String aRecoveryCode) {
        return jpaRepository.findUserRegistrationByRecoveryCode(aRecoveryCode);
    }

    @Override public UserRegistration findByUserId(UserId anUserId) {
        return jpaRepository.findUserRegistrationByUserId(anUserId);
    }
}
