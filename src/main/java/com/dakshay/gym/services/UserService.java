package com.dakshay.gym.services;

import com.dakshay.gym.models.User;
import com.dakshay.gym.repos.UserRepo;

public class UserService {

    private final UserRepo userRepo;

    public UserService() {
        this.userRepo = new UserRepo();
    }

    public User save(User user) {
       return userRepo.save(user);
    }

    public User findById(String id) {
        return userRepo.getById(id);
    }
}
