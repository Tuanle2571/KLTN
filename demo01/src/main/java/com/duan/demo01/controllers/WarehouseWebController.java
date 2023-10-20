package com.duan.demo01.controllers;

import com.duan.demo01.models.Device;
import com.duan.demo01.models.User;
import com.duan.demo01.models.Warehouse;
import com.duan.demo01.servies.UserService;
import com.duan.demo01.servies.WarehouseService;
import com.google.zxing.qrcode.decoder.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/warehouse")
public class WarehouseWebController {
    WarehouseService warehouseService;
    UserService userService;


    @Autowired
    public WarehouseWebController(WarehouseService warehouseService, UserService userService) {
        this.warehouseService = warehouseService;
        this.userService = userService;
    }

    //warehouse
    @GetMapping("")
    public String warehouse(Model model) {
        List<Warehouse> warehouseList = warehouseService.listWarehouse();
        model.addAttribute("warehouses",warehouseList);
        return "warehouse-page";
    }

    @GetMapping("/{id}")
    public String warehouseDetail(Model model, @PathVariable("id")String id){
        Warehouse warehouse = warehouseService.getWarehouse(id);
        List<Device> warehouseDevices = warehouseService.getDeviceUsedByWarehouse(id);
        List<User> users = userService.getUsers();
        model.addAttribute("warehouse",warehouse);
        model.addAttribute("user",users);
        model.addAttribute("warehouseDevices",warehouseDevices);
        return "warehouse-info";
    }
}
