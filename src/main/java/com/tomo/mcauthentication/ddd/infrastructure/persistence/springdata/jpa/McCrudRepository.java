package com.tomo.mcauthentication.ddd.infrastructure.persistence.springdata.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoRepositoryBean
public interface McCrudRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {


    default List<T> findAllById(List<ID> anIdList) {
        List ids = StreamSupport.stream(anIdList.spliterator(), false)
                .collect(Collectors.toList());
        return findAllById(ids);
    }
}
