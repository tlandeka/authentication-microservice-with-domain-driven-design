package com.tomo.mcauthentication.domain.user_registrations;

import com.tomo.mcauthentication.ddd.domain.BaseRepository;
import java.util.List;

public interface UserRegistrationRepository extends BaseRepository<UserRegistration, Long> {
    List<UserRegistration> findAllByEmail(List<String> emails);
    long countByEmailAndStatus(String email, UserRegistrationStatus status);
    UserRegistration findByConfirmLink(String confirmLink);
}
