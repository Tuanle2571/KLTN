package com.duan.demo01.repositories;

import com.duan.demo01.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,String> {
    UserEntity findByEmail(String email);
    UserEntity findFirstByUsername(String username);
    UserEntity findByUsername(String username);

}
