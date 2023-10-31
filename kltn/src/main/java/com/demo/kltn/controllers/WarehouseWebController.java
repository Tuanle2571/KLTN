package com.demo.kltn.controllers;

import com.demo.kltn.models.User;
import com.demo.kltn.models.Warehouse;
import com.demo.kltn.servies.UserService;
import com.demo.kltn.servies.WarehouseService;
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
        List<User> users = userService.getUsers();
        model.addAttribute("warehouse",warehouse);
        model.addAttribute("user",users);
        return "warehouse-info";
    }
}
