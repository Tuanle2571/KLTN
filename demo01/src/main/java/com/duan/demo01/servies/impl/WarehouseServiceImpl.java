package com.duan.demo01.servies.impl;

import com.duan.demo01.models.Device;
import com.duan.demo01.models.Warehouse;
import com.duan.demo01.repositories.WarehouseRepo;
import com.duan.demo01.servies.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration

public class WarehouseServiceImpl implements WarehouseService {
    private WarehouseRepo warehouseRepo;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepo warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }


    @Override
    public List<Warehouse> listWarehouse() {
        return warehouseRepo.findAll();

    }

    @Override
    public Warehouse getWarehouse(String id) {
        Optional<Warehouse> warehouse =warehouseRepo.findById(id);
        return warehouse.isPresent() ? warehouse.get() : null;
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse) {
        return warehouseRepo.save(warehouse);
    }

    @Override
    public Warehouse removeWarehouse(String id) {
        Optional<Warehouse> warehouse =warehouseRepo.findById(id);
        warehouseRepo.deleteById(id);
        return warehouse.isPresent() ? warehouse.get() : null;
    }

    @Override
    public List<Device> getDeviceUsedByWarehouse(String id) {
        Optional<Warehouse> warehouse = warehouseRepo.findById(id);
        return warehouse.isPresent() ? warehouse.get().getDevices() : null;
    }
}
