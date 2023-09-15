package com.dakshay.gym.repos;

import com.dakshay.gym.models.Base;

import java.util.*;

public abstract class RepoAbstraction<T extends Base,I>  implements FlipBaseRepo <T,I>{

    public RepoAbstraction() {
        this.data = new HashMap<>();
    }

    public Map<String, T> getData() {
        return data;
    }

    private final Map<String, T> data;

    public T save(T t){
        if(t!=null) data.put(t.getId(), t);
        return t;
    }
    public T getById(I id) {
        return data.get(id);
    }
   public List<T> findAll(){
       return new ArrayList<>(data.values());
   }


}
