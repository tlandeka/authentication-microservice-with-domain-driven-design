package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.mcauthentication.ddd.infrastructure.persistence.springdata.jpa.McCrudRepository;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("UserRegistrationJpaRepository")
public interface UserRegistrationJpaRepository extends McCrudRepository <UserRegistration, Long> {

    long countByEmail(String email);

    List<UserRegistration> findAllByEmailIn(List<String> email);

    UserRegistration findUserRegistrationByConfirmLink(String confirmLink);

    UserRegistration findUserRegistrationByEmail(String email);

}
