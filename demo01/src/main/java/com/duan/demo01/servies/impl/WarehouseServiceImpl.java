package com.duan.demo01.servies.impl;

import com.duan.demo01.models.Device;
import com.duan.demo01.models.Status;
import com.duan.demo01.models.Warehouse;
import com.duan.demo01.repositories.DeviceRepo;
import com.duan.demo01.repositories.StatusRepo;
import com.duan.demo01.repositories.WarehouseRepo;
import com.duan.demo01.servies.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration

public class WarehouseServiceImpl implements WarehouseService {
    private WarehouseRepo warehouseRepo;
    private StatusRepo statusRepo;
    private DeviceRepo deviceRepo;
    @Autowired
    public WarehouseServiceImpl(WarehouseRepo warehouseRepo, StatusRepo statusRepo, DeviceRepo deviceRepo) {
        this.warehouseRepo = warehouseRepo;
        this.statusRepo = statusRepo;
        this.deviceRepo =  deviceRepo;
    }


    @Override
    public List<Warehouse> listWarehouse() {
        return warehouseRepo.findAll();
    }

    @Override
    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepo.save(warehouse);
    }

    @Override
    public Warehouse getWarehouse(String id) {
        Optional<Warehouse> warehouse =warehouseRepo.findById(id);
        return warehouse.isPresent() ? warehouse.get() : null;
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse) {
        if (warehouse.getStatus() == null){
            Status status = statusRepo.findByStatusValue("free");
            warehouse.setStatus(status);
        }
        return warehouseRepo.save(warehouse);
    }



    @Override
    public Warehouse removeWarehouse(String id) {
        Optional<Warehouse> warehouse =warehouseRepo.findById(id);
        warehouseRepo.deleteById(id);
        return warehouse.isPresent() ? warehouse.get() : null;
    }

    @Override
    public Warehouse addDevice(String id, List<Device> add) {
        Status status = statusRepo.findByStatusValue("active");
        Warehouse warehouse = this.getWarehouse(id);
        add.forEach(device -> {
            device.setWarehouse(warehouse);
            device.setStatus(status);
        });
        warehouse.getDevices().addAll(add);
        return warehouseRepo.saveAndFlush(warehouse);
    }

    @Override
    public Warehouse removeDevice(String id, List<Device> devices) {
        Status status = statusRepo.findByStatusValue("free");
        Warehouse warehouse = this.getWarehouse(id);
        warehouse.getDevices().removeAll(devices);
        devices.forEach(device -> {
            device.setWarehouse(null);
            device.setStatus(status);
        });
        deviceRepo.saveAll(devices);
        return warehouseRepo.save(warehouse);
    }

    @Override
    public List<Device> getDeviceUsedByWarehouse(String id) {
        Optional<Warehouse> warehouse = warehouseRepo.findById(id);
        return warehouse.isPresent() ? warehouse.get().getDevices() : null;
    }
}
