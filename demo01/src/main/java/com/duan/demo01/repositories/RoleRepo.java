package com.duan.demo01.repositories;

import com.duan.demo01.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, String> {
    Role findByName(String user);
}
