package com.dakshay.gym.repos;

import java.util.ArrayList;
import java.util.List;

public interface FlipBaseRepo<T, I> {

    public T save(T t);
    public T getById(I id) ;
    public List<T> findAll();
}
