package com.duan.demo01.servies;

import com.duan.demo01.models.Device;
import com.duan.demo01.models.Warehouse;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> listWarehouse();
    Warehouse getWarehouse(String id);
    Warehouse updateWarehouse(Warehouse warehouse);
    Warehouse addWarehouse(Warehouse warehouse);
    Warehouse removeWarehouse(String id);
    Warehouse addDevice(String id,List<Device> devices);
    Warehouse removeDevice(String id, List<Device> devices);
    List<Device> getDeviceUsedByWarehouse(String id);


}
