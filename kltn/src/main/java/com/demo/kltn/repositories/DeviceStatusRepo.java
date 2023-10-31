package com.demo.kltn.repositories;

import com.demo.kltn.models.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceStatusRepo extends JpaRepository<DeviceStatus, Integer> {
}
