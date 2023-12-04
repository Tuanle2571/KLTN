package com.duan.demo01.servies;

import com.duan.demo01.models.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<UserEntity> getUsers();
    UserEntity saveUser(UserEntity userEntity);
    UserEntity findByUsername(String username);
    UserEntity findUser(String id);
    UserEntity remove(String id);
    UserEntity update(UserEntity userEntity);
    UserEntity findByEmail(String email);

    byte[] getUserThumbnail(String id);
    String saveUserThumbnail(String id, MultipartFile file);


}
