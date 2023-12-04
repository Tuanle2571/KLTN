package com.duan.demo01.servies.impl;

import com.duan.demo01.models.Role;
import com.duan.demo01.models.UserEntity;
import com.duan.demo01.repositories.RoleRepo;
import com.duan.demo01.repositories.UserRepo;
import com.duan.demo01.servies.UserService;
import com.duan.demo01.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
    private String storage = "users";

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
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
        return userRepo.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public byte[] getUserThumbnail(String id) {
        UserEntity user = this.findUser(id);
        String imageName = null;
        if (user != null & user.getImage() != null) {
            try {
                imageName = user.getImage();
                Path storageFolder = Paths.get(storage);
                Path file = storageFolder.resolve(imageName);
                Resource resource = new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) {
                    byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                    return bytes;
                } else {
                    throw new RuntimeException("Could not read file: " + imageName);
                }
            } catch (IOException e) {
                throw new RuntimeException("Could not read file: " + imageName, e);
            }
        }

        return new byte[0];
    }

    @Override
    public String saveUserThumbnail(String id, MultipartFile file) {
        UserEntity user = this.findUser(id);
        if (user != null) {
            if (user.getImage() != null) {
                ImageUtil.deleteFile(user.getImage(), storage);
            }
            try {
                String save = ImageUtil.uploadFile(file.getInputStream(), storage);
                user.setImage(save);
                return save;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
