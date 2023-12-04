package com.duan.demo01.controllers;

import com.duan.demo01.models.Device;
import com.duan.demo01.models.Status;
import com.duan.demo01.models.UserEntity;
import com.duan.demo01.models.Warehouse;
import com.duan.demo01.servies.DeviceService;
import com.duan.demo01.servies.UserService;
import com.duan.demo01.servies.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/warehouse")
public class WarehouseWebController {
    WarehouseService warehouseService;
    DeviceService deviceService;
    UserService userService;


    @Autowired
    public WarehouseWebController(WarehouseService warehouseService, UserService userService, DeviceService deviceService) {
        this.deviceService = deviceService;
        this.warehouseService = warehouseService;
        this.userService = userService;
    }

    //warehouse
    @GetMapping("")
    public String warehouse(Model model) {
        List<Warehouse> warehouseList = warehouseService.listWarehouse();
        model.addAttribute("warehouses", warehouseList);
        return "warehouse-page";
    }

    @GetMapping("/detail/{id}")
    public String warehouseDetail(Model model, @PathVariable("id") String id) {
        Warehouse warehouse = warehouseService.getWarehouse(id);
        List<UserEntity> userEntities = userService.getUsers();
        List<Status> statuses = deviceService.getStatus();
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("users", userEntities);
        model.addAttribute("statuses", statuses);
        return "warehouse-detail";
    }

    @GetMapping("/detail/{id}/addDevice")
    public String addDeviceToWarehouse(Model model, @PathVariable String id) {
        List<Device> devices = deviceService.notInUseDevice();
        List<Device> selectedDevices = new ArrayList<>();
        model.addAttribute("devices", devices);
        model.addAttribute("selectedDevices", selectedDevices);
        model.addAttribute("warehouseId", id);
        return "warehouse-add-device";
    }

    @GetMapping("/add")
    public String addWarehouse(Model model) {
        Warehouse warehouse = new Warehouse();
        List<UserEntity> userEntities = userService.getUsers();
        List<Status> statuses = deviceService.getStatus();
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("users", userEntities);
        model.addAttribute("statuses", statuses);
        return "warehouse-add";
    }

    @PostMapping("/add")
    public String addWarehousePost(@ModelAttribute("warehouse") Warehouse warehouse, @RequestParam("userId") String userId) {
        if (!(userId.equalsIgnoreCase("") | userId == null)) {
            UserEntity user = userService.findUser(userId);
            warehouse.setUser(user);
        } else {
            warehouse.setUser(null);
        }
        Warehouse savedWarehouse = warehouseService.addWarehouse(warehouse);
        return "redirect:/warehouse/detail/" + savedWarehouse.getId();
    }
    // action

    @PostMapping("/update")
    public String saveUpdateWarehouse(@ModelAttribute("device") Warehouse warehouse, @RequestParam("action") String action) {
        if (action.equalsIgnoreCase("delete")) {
            warehouseService.removeWarehouse(warehouse.getId());
            return "redirect:/warehouse";
        }
        if (action.equalsIgnoreCase("update")) {
            warehouseService.updateWarehouse(warehouse);
            return "redirect:/warehouse/detail/" + warehouse.getId();
        } else {
            return "redirect:/warehouse/detail/" + warehouse.getId();

        }

    }

    @PostMapping("/detail/{id}/addDevice")
    public String saveAddDeviceToWarehouse(@RequestBody List<Device> devices, @PathVariable String id) {
        List<Device> selectedDevice = new ArrayList<>();
        devices.forEach(device -> {
            Device getDevice = deviceService.getDeviceByID(device.getId());
            selectedDevice.add(getDevice);
        });
        warehouseService.addDevice(id, selectedDevice);
        return "redirect:/warehouse";
    }

    @PostMapping("/detail/{id}/removedevices")
    public String saveRemoveDeviceToWarehouse(@RequestBody List<Device> devices, @PathVariable String id) {
        List<Device> selectedDevice = new ArrayList<>();
        devices.forEach(device -> {
            Device getDevice = deviceService.getDeviceByID(device.getId());
            selectedDevice.add(getDevice);
        });
        warehouseService.removeDevice(id, selectedDevice);
        return "redirect:/warehouse/detail/" + id;
    }
}
