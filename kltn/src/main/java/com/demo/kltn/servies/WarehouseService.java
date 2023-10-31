package com.demo.kltn.servies;


import com.demo.kltn.models.Device;
import com.demo.kltn.models.Warehouse;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> listWarehouse();
    Warehouse getWarehouse(String id);
    Warehouse updateWarehouse(Warehouse warehouse);
    Warehouse removeWarehouse(String id);

    List<Device> getDeviceUsedByWarehouse(String id);

}
