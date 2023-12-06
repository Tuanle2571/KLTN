package com.duan.demo01.repositories;

import com.duan.demo01.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status, Integer> {
    Status findByStatusValue(String value);
}
