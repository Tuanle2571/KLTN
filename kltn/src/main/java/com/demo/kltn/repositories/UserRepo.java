package com.demo.kltn.repositories;

import com.demo.kltn.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
}
