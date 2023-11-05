package com.duan.demo01.servies.impl;

import com.duan.demo01.models.Device;
import com.duan.demo01.models.DeviceMaintenance;
import com.duan.demo01.models.DeviceStatus;
import com.duan.demo01.models.QR;
import com.duan.demo01.repositories.DeviceRepo;
import com.duan.demo01.repositories.DeviceStatusRepo;
import com.duan.demo01.repositories.QRRepo;
import com.duan.demo01.servies.DeviceService;
import com.duan.demo01.utils.ImageUtil;
import com.duan.demo01.utils.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
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
    public List<Device> notInUseDevice() {
        List<Device> devices = deviceRepo.findDevicesNotInUsed();
        return devices;
    }

    @Override
    public Device getDeviceByID(String id) {
        Optional<Device> device1 = deviceRepo.findById(id);
        if (device1.isPresent()) return device1.get();
        return null;
    }

    @Value("${server.port}")
    private String serverPort;

    @Override
    public Device saveDevice(Device device) {
        Device added = deviceRepo.save(device);
        String id = added.getId();

        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String address = ip + ":" + serverPort;
            String request = "http://" + address + "/device/detail/" + id;

            BufferedImage qrCodeImage = QRCodeGenerator.getQRCodeImage(request);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "png", bytes);
            InputStream qrImage = new ByteArrayInputStream(bytes.toByteArray());
            String qrImgName = ImageUtil.uploadFile(qrImage, "qr");
            QR qr = new QR();
            qr.setName(qrImgName);
            added.setQr(qr);
        } catch (Exception e) {
            e.getMessage();
        }
        return deviceRepo.save(added);
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
        return ImageUtil.getImage(qrName);
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
        List<DeviceStatus> statusList = deviceStatusRepo.findAll();
        return statusList;
    }

    @Override
    public DeviceStatus getStatusByValue(String value) {
        return deviceStatusRepo.findByStatusValue(value);
    }

    @Override
    public DeviceMaintenance addMaintenance(String id, DeviceMaintenance deviceMaintenance) {
        Device device = deviceRepo.findById(id).get();
        if (device != null) {
            deviceMaintenance.setDevice(device);
            device.getDeviceMaintenanceList().add(deviceMaintenance);
            deviceRepo.saveAndFlush(device);
        }
        return null;
    }

    @Override
    public List<DeviceMaintenance> getListMaintenance(String id) {
        return null;
    }



}

