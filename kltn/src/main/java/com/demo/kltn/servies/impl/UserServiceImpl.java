package com.demo.kltn.servies.impl;

import com.demo.kltn.models.User;
import com.demo.kltn.repositories.UserRepo;
import com.demo.kltn.servies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User findUser(String id) {
        Optional<User> user = userRepo.findById(id);
        return user.isPresent() ? user.get() : null;
    }

    @Override
    public User remove(String id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.deleteById(user.get().getId());
            return user.get();
        }
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
