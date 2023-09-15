package com.dakshay.gym.repos;

import com.dakshay.gym.models.Centre;

import java.util.*;

public class CentreStorage extends RepoAbstraction<Centre, String> {

    @Override
    public Centre save(Centre centre) {
        return getData().put(centre.getCentreName(), centre);
    }

    @Override
    public Centre getById(String id) {
        return null;
    }

    public Centre getByName(String name) {
        return getData().get(name);
    }

    @Override
    public List<Centre> findAll() {
        return new ArrayList<>(getData().values());
    }
}
