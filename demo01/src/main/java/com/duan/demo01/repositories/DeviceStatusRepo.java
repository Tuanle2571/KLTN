package com.duan.demo01.repositories;

import com.duan.demo01.models.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceStatusRepo extends JpaRepository<DeviceStatus, Integer> {
    DeviceStatus findByStatusValue(String value);
}
