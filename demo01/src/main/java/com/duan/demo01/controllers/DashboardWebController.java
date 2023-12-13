package com.duan.demo01.controllers;


import com.duan.demo01.models.Device;
import com.duan.demo01.models.UserEntity;
import com.duan.demo01.models.Warehouse;
import com.duan.demo01.servies.DeviceService;
import com.duan.demo01.servies.UserService;
import com.duan.demo01.servies.WarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/dashboard")
@Controller
public class DashboardWebController {
    private WarehouseService warehouseService;
    private DeviceService deviceService;
    private UserService userService;

    public DashboardWebController(WarehouseService warehouseService, DeviceService deviceService, UserService userService) {
        this.warehouseService = warehouseService;
        this.deviceService = deviceService;
        this.userService = userService;
    }

    @GetMapping("")
    public String dashboard(Model model) {
        List<UserEntity> userEntities = userService.getUsers();
        List<Warehouse> warehouses = warehouseService.listWarehouse();
        List<Device> devices = deviceService.getAllDevices();
        try{
            int freeDevices = (int) devices.stream().filter(device -> device.getWarehouse() == null).count();
            int repairWarehouse = (int) warehouses.stream().filter(warehouse -> warehouse.getStatus().getStatusValue() == "repairing").count();
            int manager = (int) userEntities.stream().filter(user -> user.getRoles().stream().filter(role -> {
                return role.getName() == "ADMIN";
            }).isParallel()).count();
            model.addAttribute("users", userEntities);
            model.addAttribute("warehouses", warehouses);
            model.addAttribute("devices", devices);
            model.addAttribute("freeDevices", freeDevices);
            model.addAttribute("repairWarehouse", repairWarehouse);
            model.addAttribute("manager", manager);
            return "dashboard";
        }
        catch (Exception e){
            return "dashboard?fail";
        }

    }
}
