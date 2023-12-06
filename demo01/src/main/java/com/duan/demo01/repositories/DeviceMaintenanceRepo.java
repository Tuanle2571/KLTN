package com.duan.demo01.repositories;

import com.duan.demo01.models.DeviceMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceMaintenanceRepo extends JpaRepository<DeviceMaintenance, Integer> {
}
