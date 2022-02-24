package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.mcauthentication.ddd.infrastructure.persistence.springdata.jpa.McCrudRepository;

public class BaseJpaAdapter<T extends McCrudRepository> {

    protected T jpaRepository;

    public BaseJpaAdapter(T jpaReposirotry) {
        this.jpaRepository = jpaReposirotry;
    }

    public void flush() {
        jpaRepository.flush();
    }

    public void saveAndFlush() {
        jpaRepository.flush();
    }
}
