package com.tomo.mcauthentication.ddd.domain;

import java.util.List;

public interface BaseRepository<T, ID> {
    T save (T entity);
    T findById(ID id);
    List<T> findAll();
    void saveAll(List<T> entities);
}
