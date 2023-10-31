package com.duan.demo01.servies.impl;

import com.duan.demo01.models.Device;
import com.duan.demo01.models.DeviceStatus;
import com.duan.demo01.models.Warehouse;
import com.duan.demo01.repositories.DeviceRepo;
import com.duan.demo01.repositories.DeviceStatusRepo;
import com.duan.demo01.repositories.WarehouseRepo;
import com.duan.demo01.servies.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration

public class WarehouseServiceImpl implements WarehouseService {
    private WarehouseRepo warehouseRepo;
    private DeviceStatusRepo deviceStatusRepo;
    private DeviceRepo deviceRepo;
    @Autowired
    public WarehouseServiceImpl(WarehouseRepo warehouseRepo, DeviceStatusRepo deviceStatusRepo,DeviceRepo deviceRepo) {
        this.warehouseRepo = warehouseRepo;
        this.deviceStatusRepo = deviceStatusRepo;
        this.deviceRepo =  deviceRepo;
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
    public Warehouse addDevice(String id, List<Device> add) {
        DeviceStatus status = deviceStatusRepo.findByStatusValue("active");
        Warehouse warehouse = this.getWarehouse(id);
        add.forEach(device -> {
            device.setWarehouse(warehouse);
            device.setDeviceStatus(status);
        });
        warehouse.getDevices().addAll(add);
        return warehouseRepo.saveAndFlush(warehouse);
    }

    @Override
    public Warehouse removeDevice(String id, List<Device> devices) {
        DeviceStatus status = deviceStatusRepo.findByStatusValue("free");
        Warehouse warehouse = this.getWarehouse(id);
        warehouse.getDevices().removeAll(devices);
        devices.forEach(device -> {
            device.setWarehouse(null);
            device.setDeviceStatus(status);
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
