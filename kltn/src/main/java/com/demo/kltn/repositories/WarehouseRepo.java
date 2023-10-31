package com.demo.kltn.repositories;

import com.demo.kltn.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepo extends JpaRepository<Warehouse,String> {
}
