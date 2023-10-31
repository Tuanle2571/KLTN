package com.demo.kltn.repositories;

import com.demo.kltn.models.DeviceMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceMaintenanceRepo extends JpaRepository<DeviceMaintenance, Integer> {
}
