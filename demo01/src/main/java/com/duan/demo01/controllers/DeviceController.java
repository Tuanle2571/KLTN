package com.duan.demo01.controllers;

import com.duan.demo01.models.Device;
import com.duan.demo01.servies.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/device")
public class DeviceController {
    private DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("")
    public ResponseEntity<List<Device>> getAll() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDevice(@RequestBody Device device) {
        Device added = deviceService.saveDevice(device);
        return ResponseEntity.ok(added.toString());
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Device device) {
        deviceService.updateDevice(device);
        return ResponseEntity.ok("");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        deviceService.removeDevice(id);
        return ResponseEntity.ok("");
    }

    @GetMapping("/qr/{fileName:.+}")
    public ResponseEntity<byte[]> getQRImage(@PathVariable String fileName) {
        try {
            byte[] image = deviceService.getDeviceQRCode(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @GetMapping("/{fileName:.+}")
//    public ResponseEntity<byte[]> getDeviceImage(@PathVariable String fileName) {
//        try {
//            byte[] image = deviceService.getDeviceImage(fileName);
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}

