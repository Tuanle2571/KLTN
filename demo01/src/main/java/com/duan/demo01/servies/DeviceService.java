package com.duan.demo01.servies;

import com.duan.demo01.models.Device;
import com.duan.demo01.models.DeviceStatus;
import org.springframework.web.multipart.MultipartFile;

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
}
