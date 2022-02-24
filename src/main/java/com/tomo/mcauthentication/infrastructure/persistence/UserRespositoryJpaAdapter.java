package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserId;
import com.tomo.mcauthentication.domain.users.UserRespository;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserRespositoryJpaAdapter extends BaseJpaAdapter<UserJpaRepository> implements UserRespository {

    public UserRespositoryJpaAdapter(UserJpaRepository userJpaRepository) {
        super(userJpaRepository);
    }

    @Override
    public User save(User anUser) {
        return jpaRepository.save(anUser);
    }

    @Override
    public UserId nextIdentity() {
        return new UserId(UUID.randomUUID());
    }

    @Override
    public User findByEmail(String anEmail) {
        return jpaRepository.findUserByEmail(anEmail);
    }
}
