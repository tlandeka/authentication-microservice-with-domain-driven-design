package com.tomo.mcauthentication.domain.users;

import com.tomo.mcauthentication.ddd.domain.BaseRepository;

public interface UserRepository extends BaseRepository<User, UserId> {
    UserId nextIdentity();
    User findByEmail(String anEmail);
}
