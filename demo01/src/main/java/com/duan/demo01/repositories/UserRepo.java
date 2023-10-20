package com.duan.demo01.repositories;

import com.duan.demo01.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,String> {
}
