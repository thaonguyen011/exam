package com.example.poductmanagement.model.dao;

import java.util.List;

public interface IEntityDAO <E>{
    List<E> selectAll();
    E select(int id);
    boolean insert(E e);
    boolean update(E e);
    boolean delete(int id);

}
