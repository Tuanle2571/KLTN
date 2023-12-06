package com.duan.demo01.repositories;

import com.duan.demo01.controllers.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTypeRepo extends JpaRepository<DeviceType,Integer> {
    DeviceType findByName(String name);
}
