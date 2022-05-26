package com.tomo.mcauthentication.domain.registration;

import com.tomo.mcauthentication.ddd.domain.BaseRepository;
import com.tomo.mcauthentication.domain.users.UserId;

import java.util.List;

public interface UserRegistrationRepository extends BaseRepository<UserRegistration, Long> {
    List<UserRegistration> findAllByEmail(List<String> emails);
    UserRegistration findByEmail(String anEmail);
    long countByEmailAndStatus(String email, UserRegistrationStatus status);
    UserRegistration findByConfirmationCode(String confirmationCode);
    UserRegistration findByRecoveryCode(String aRecoveryCode);
    UserRegistration findByUserId(UserId anUserId);
}
