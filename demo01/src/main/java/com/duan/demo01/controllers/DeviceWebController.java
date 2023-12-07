package com.duan.demo01.controllers;

import com.duan.demo01.models.Device;
import com.duan.demo01.models.DeviceMaintenance;
import com.duan.demo01.models.Status;
import com.duan.demo01.models.UserEntity;
import com.duan.demo01.repositories.DeviceTypeRepo;
import com.duan.demo01.servies.DeviceService;
import com.duan.demo01.servies.UserService;
import com.duan.demo01.utils.QRCodeGenerator;
import com.duan.demo01.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/device")
public class DeviceWebController {
    DeviceService deviceService;
    UserService userService;

    @Autowired
    public DeviceWebController(DeviceService deviceService, UserService userService) {
        this.deviceService = deviceService;
        this.userService = userService;
    }

    @GetMapping("")
    public String home(Model model) {
        try {
            List<Device> devices = deviceService.getAllDevices();
            model.addAttribute("devices", devices);
            model.addAttribute("editDevice", new Device());
            model.addAttribute("addDevice", new Device());

            return "device-page";
        } catch (Exception e) {
            return "404";
        }
    }

    @GetMapping("/add")
    public String addDevice(Model model) {
        try {
            Device device = new Device();
            List<UserEntity> userEntities = userService.getUsers();
            List<Status> statuses = deviceService.getStatus();
            List<DeviceType> types = deviceService.getTypes();
            model.addAttribute("types", types);
            model.addAttribute("device", device);
            model.addAttribute("users", userEntities);
            model.addAttribute("statuses", statuses);
            return "device-add";
        } catch (Exception e) {
            return "404";
        }
    }

    // ACTION
    @PostMapping("/add")
    public String saveDevice(@ModelAttribute("device") Device device, @Nullable @RequestParam("userId") String userId, @Nullable @RequestParam("type") String typeId) {
        if (!(userId.equalsIgnoreCase("") | userId == null)) {
            UserEntity user = userService.findUser(userId);
            device.setUser(user);
        } else {
            device.setUser(null);
        }

        if (!(typeId.equalsIgnoreCase("") | typeId == null)) {
            DeviceType type = deviceService.findType(Integer.valueOf(typeId));
            device.setType(type);
        } else {
            device.setUser(null);
        }
        Device added = deviceService.saveDevice(device);
        return "redirect:/device/detail/" + added.getId();
    }

    @GetMapping("/detail/{id}")
    public String getDevice(Model model, @PathVariable String id) {
        Device device = deviceService.getDeviceByID(id);
        DeviceMaintenance deviceMaintenance = new DeviceMaintenance();
        List<UserEntity> userEntities = userService.getUsers();
        List<Status> statuses = deviceService.getStatus();
        List<DeviceType> types = deviceService.getTypes();
        model.addAttribute("types", types);
        model.addAttribute("device", device);
        model.addAttribute("maintenance", deviceMaintenance);
        model.addAttribute("users", userEntities);
        model.addAttribute("statuses", statuses);
        return "device-detail";
    }

    @PostMapping("/update")
    public String updateDevice(@ModelAttribute("device") Device device, @RequestParam("action") String action, @RequestParam("userId") String userId, @RequestParam("dateBuy") String dateBuy) {
        if (action.equalsIgnoreCase("delete")) {
            deviceService.removeDevice(device.getId());
            return "redirect:/device";
        }
        if (action.equalsIgnoreCase("update")) {
            if (!(userId.equalsIgnoreCase("") | userId == null)) {
                UserEntity user = userService.findUser(userId);
                device.setUser(user);
            } else {
                device.setUser(null);
            }
            deviceService.updateDevice(device);
            return "redirect:/device/detail/" + device.getId();
        } else {
            return "redirect:/device/detail/" + device.getId();
        }

    }

    @PostMapping("/maintenance/add")
    public String addMaintenance(@ModelAttribute("maintenance") DeviceMaintenance deviceMaintenance, @RequestParam("deviceId") String id, HttpServletRequest request) {
        deviceService.addMaintenance(id, deviceMaintenance);
        return "redirect:/device/detail/" + id;
    }


    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/detail/{id}/qr")
    public ResponseEntity<byte[]> getDeviceQR(HttpServletRequest request) {
        byte[] QR = null;
        try {
            String requestURL = RequestUtil.getUrl(request);
            requestURL = requestURL.substring(0, requestURL.lastIndexOf('/'));
            String ip = InetAddress.getLocalHost().getHostAddress();
            String address ="http://" + ip + ":" + serverPort;
            String url = address + requestURL;


            BufferedImage qrCodeImage = QRCodeGenerator.getQRCodeImage(url);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "png", bytes);
            QR = bytes.toByteArray();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(QR);
        } catch (UnknownHostException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}

