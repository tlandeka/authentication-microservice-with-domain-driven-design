package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.mcauthentication.ddd.domain.BaseRepository;
import com.tomo.mcauthentication.ddd.infrastructure.persistence.springdata.jpa.McCrudRepository;

import java.util.List;
import java.util.Optional;

//https://github.com/benthurley82/generic-type-resolver-test/blob/main/src/main/java/com/example/test/AbstractFoo.java
public class BaseJpaAdapter<T, ID, E extends McCrudRepository> implements BaseRepository<T, ID> {

    protected E jpaRepository;

    public BaseJpaAdapter(E jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public T save(T entity) {
        return (T) jpaRepository.save(entity);
    }

    @Override
    public T findById(ID id) {
        return ((Optional<T>) jpaRepository.findById(id)).get();
    }

    @Override
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void saveAll(List<T> entities) {
        jpaRepository.saveAll(entities);
    }
}
