package com.dakshay.gym.models;

import java.util.UUID;

public class User extends Base{

    private final String name;
    private final int age;
    private final String city;

    public User(String name, int age, String city) {
        super(UUID.randomUUID().toString());
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }
}
