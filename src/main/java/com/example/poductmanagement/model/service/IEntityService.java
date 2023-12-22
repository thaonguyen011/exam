package com.example.poductmanagement.model.service;

import java.util.List;

public interface IEntityService<E>{
    List<E> selectAll();
    E select(int id);
    boolean insert(E e);
    boolean update(E e);
    boolean delete(int id);
}
