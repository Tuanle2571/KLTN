package com.demo.kltn.controllers;

import com.demo.kltn.models.Device;
import com.demo.kltn.models.DeviceMaintenance;
import com.demo.kltn.models.DeviceStatus;
import com.demo.kltn.models.User;
import com.demo.kltn.servies.DeviceService;
import com.demo.kltn.servies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
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
            List<User> users = userService.getUsers();
            List<DeviceStatus> deviceStatuses = deviceService.getStatus();
            model.addAttribute("device", device);
            model.addAttribute("users", users);
            model.addAttribute("deviceStatuses", deviceStatuses);
            return "device-add";
        } catch (Exception e) {
            return "404";
        }
    }

    // ACTION
    @PostMapping("/add")
    public String saveDevice(@ModelAttribute("device") Device device,HttpServletRequest request) {
        final String baseUrl =
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        Device added = deviceService.addDevice(device);
        return "redirect:/device/detail/" + added.getId();
    }

    @GetMapping("/detail/{id}")
    public String getDevice(Model model, @PathVariable String id) {
        Device device = deviceService.getDeviceByID(id);
        DeviceMaintenance deviceMaintenance = new DeviceMaintenance();
        List<User> users = userService.getUsers();
        List<DeviceStatus> deviceStatuses = deviceService.getStatus();
        model.addAttribute("device", device);
        model.addAttribute("maintenance", deviceMaintenance);
        model.addAttribute("users", users);
        model.addAttribute("deviceStatuses", deviceStatuses);
        return "device-detail";
    }

    @PostMapping("/update")
    public String updateDevice(@ModelAttribute("device") Device device, @RequestParam("action") String action) {
        if (action.equalsIgnoreCase("delete")) {
            deviceService.removeDevice(device.getId());
        }
        if (action.equalsIgnoreCase("update")) {
            deviceService.updateDevice(device);
        }
        return "redirect:/device/detail" + device.getId();
    }

    @PostMapping("/maintenance/add")
    public String addMaintenance(@ModelAttribute("maintenance")DeviceMaintenance deviceMaintenance,@RequestParam("deviceId")String id){
        deviceService.addMaintenance(id,deviceMaintenance);
        return "redirect:/device/detail/" + id;
    }

}

