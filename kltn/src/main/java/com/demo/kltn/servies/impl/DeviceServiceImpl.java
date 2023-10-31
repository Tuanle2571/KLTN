package com.demo.kltn.servies.impl;

import com.demo.kltn.models.Device;
import com.demo.kltn.models.DeviceMaintenance;
import com.demo.kltn.models.DeviceStatus;
import com.demo.kltn.models.QR;
import com.demo.kltn.repositories.DeviceRepo;
import com.demo.kltn.repositories.DeviceStatusRepo;
import com.demo.kltn.repositories.QRRepo;
import com.demo.kltn.servies.DeviceService;
import com.demo.kltn.utils.ImageUtil;
import com.demo.kltn.utils.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StreamUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Configuration
public class DeviceServiceImpl implements DeviceService {
    private DeviceRepo deviceRepo;
    private QRRepo qrRepo;
    private DeviceStatusRepo deviceStatusRepo;

    @Autowired
    public DeviceServiceImpl(DeviceRepo deviceRepo, QRRepo qrRepo, DeviceStatusRepo deviceStatusRepo) {
        this.deviceRepo = deviceRepo;
        this.qrRepo = qrRepo;
        this.deviceStatusRepo = deviceStatusRepo;
    }

    @Override
    public List<Device> getAllDevices() {
        List<Device> deviceList = deviceRepo.findAll();
        return deviceList;
    }

    @Override
    public Device getDeviceByID(String id) {
        Optional<Device> device1 = deviceRepo.findById(id);
        if (device1.isPresent()) return device1.get();
        return null;
    }

    @Override
    public Device addDevice(Device device) {
        Device added = deviceRepo.save(device);
        try {
            String id = added.getId();
            BufferedImage qrCodeImage = QRCodeGenerator.getQRCodeImage(id.toString());
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "png", bytes);
            InputStream qrImage = new ByteArrayInputStream(bytes.toByteArray());
            String qrImgName = ImageUtil.uploadFile(qrImage, "qr");
            QR qr = new QR();
            qr.setName(qrImgName);

            added.setQr(qr);
            deviceRepo.save(added);
        } catch (Exception e) {
            e.getMessage();
        }
        return added;
    }

    @Override
    public void removeDevice(String id) {
        deviceRepo.deleteById(id);
    }

    @Override
    public void updateDevice(Device updateDevice) {
        Optional<Device> oldDevice = deviceRepo.findById(updateDevice.getId());
        updateDevice.setQr(oldDevice.get().getQr());
        deviceRepo.save(updateDevice);
    }

    @Override
    public byte[] getDeviceQRCode(String qrName) {
        try {
            Path storageFolder = Paths.get("qr");
            Path file = storageFolder.resolve(qrName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            } else {
                throw new RuntimeException("Could not read file: " + qrName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + qrName, e);
        }
    }

    @Override
    public byte[] getDeviceImage(String imageName) {
        try {
            Path storageFolder = Paths.get("devices");
            Path file = storageFolder.resolve(imageName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            } else {
                throw new RuntimeException("Could not read file: " + imageName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + imageName, e);
        }
    }

    @Override
    public List<DeviceStatus> getStatus() {
        List<DeviceStatus> statusList= deviceStatusRepo.findAll();
        return statusList;
    }

    @Override
    public DeviceMaintenance addMaintenance(String id, DeviceMaintenance deviceMaintenance) {
        Device device = deviceRepo.findById(id).get();
//        if (device != null){
//            deviceMaintenance.setDevice(device);
//            device.getDeviceMaintenanceList().add(deviceMaintenance);
//            deviceRepo.saveAndFlush(device);
//        }
        return null;
    }

    @Override
    public List<DeviceMaintenance> getListMaintenance(String id) {
        return null;
    }

}

