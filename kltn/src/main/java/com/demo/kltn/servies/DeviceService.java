package com.demo.kltn.servies;


import com.demo.kltn.models.Device;
import com.demo.kltn.models.DeviceMaintenance;
import com.demo.kltn.models.DeviceStatus;

import java.util.List;

public interface DeviceService {
    List<Device> getAllDevices();
    Device getDeviceByID(String id);
    Device addDevice(Device device);
    void removeDevice(String id);
    void updateDevice( Device newDevice);
    byte[] getDeviceQRCode(String qrName);
    byte[] getDeviceImage(String deviceName);
    List<DeviceStatus> getStatus();

    DeviceMaintenance addMaintenance(String id, DeviceMaintenance deviceMaintenance);
    List<DeviceMaintenance> getListMaintenance(String id);
}
