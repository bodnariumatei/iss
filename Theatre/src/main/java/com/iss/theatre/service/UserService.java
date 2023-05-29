package com.iss.theatre.service;

import com.iss.theatre.model.User;
import com.iss.theatre.repository.UserRepository;

import java.io.IOException;
import java.util.Properties;

public class UserService {
    private final UserRepository usersRepo;

    public UserService(){
        this.usersRepo = new UserRepository();
    }

    public UserService(UserRepository usersRepo) {
        this.usersRepo = usersRepo;
    }

    public Iterable<User> getAdmins(){
        return usersRepo.getAll();
    }

    public User getUserByUsernameAndPassword(String username, String password){
        return usersRepo.getOneByUsernameAndPassword(username, password);
    }
}
