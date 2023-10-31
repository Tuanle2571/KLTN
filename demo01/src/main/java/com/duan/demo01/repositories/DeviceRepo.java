package com.duan.demo01.repositories;

import com.duan.demo01.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepo extends JpaRepository<Device, String> {
    @Query(value = "select * from device D WHERE D.warehouse_id IS NULL",nativeQuery = true)
    List<Device> findDevicesNotInUsed();
}
