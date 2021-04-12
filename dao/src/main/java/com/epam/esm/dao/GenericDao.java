package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

     Optional<T> findOne(long id);

     List<T> findAll() ;

     T insert(T obj);

     T update(T obj);

     void delete(long id);
}
