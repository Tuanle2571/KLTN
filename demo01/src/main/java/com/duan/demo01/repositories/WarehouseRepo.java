package com.duan.demo01.repositories;

import com.duan.demo01.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepo extends JpaRepository<Warehouse,String> {
}
