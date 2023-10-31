package com.duan.demo01.servies.impl;

import com.duan.demo01.models.Role;
import com.duan.demo01.models.UserEntity;
import com.duan.demo01.repositories.RoleRepo;
import com.duan.demo01.repositories.UserRepo;
import com.duan.demo01.servies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo,PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        UserEntity user = new UserEntity();
        Role role = roleRepo.findByName("USER");
        user.setEmail(userEntity.getEmail());
        user.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        user.setUsername(userEntity.getUsername());
        user.setRoles(Arrays.asList(role));
        return userRepo.save(user);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public UserEntity findUser(String id) {
        Optional<UserEntity> user = userRepo.findById(id);
        return user.isPresent() ? user.get() : null;
    }

    @Override
    public UserEntity remove(String id) {
        Optional<UserEntity> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.deleteById(user.get().getId());
            return user.get();
        }
        return null;
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
