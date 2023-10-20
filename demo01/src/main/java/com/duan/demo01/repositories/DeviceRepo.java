package com.duan.demo01.repositories;

import com.duan.demo01.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepo extends JpaRepository<Device, String> {
    List<Device> findByName(String name);

    @Query(value = "SELECT MAX(e.id) FROM device e",nativeQuery = true)
    Integer findMaxId();

    @Query(value = "select count(id) from device",nativeQuery = true)
    Integer countDevice();
}
