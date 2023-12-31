package com.duan.demo01.servies;

import com.duan.demo01.controllers.DeviceType;
import com.duan.demo01.models.Device;
import com.duan.demo01.models.DeviceMaintenance;
import com.duan.demo01.models.Status;

import java.util.List;

public interface DeviceService {
    List<Device> getAllDevices();
    List<Device> notInUseDevice();
    Device getDeviceByID(String id);
    Device saveDevice(Device device);
    void removeDevice(String id);
    void updateDevice( Device newDevice);
    byte[] getDeviceQRCode(String qrName);
    byte[] getDeviceImage(String deviceName);
    List<Status> getStatus();
    Status getStatusByValue(String value);

    DeviceMaintenance addMaintenance(String id,DeviceMaintenance deviceMaintenance);
    List<DeviceMaintenance> getListMaintenance(String id);

    List<DeviceType> getTypes();
    DeviceType addType(DeviceType type);
    DeviceType findType(Integer typeId);
    DeviceType findTypeByName(String type);


}
