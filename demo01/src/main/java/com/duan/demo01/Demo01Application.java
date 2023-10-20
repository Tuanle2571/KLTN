package com.duan.demo01;

import com.duan.demo01.models.Device;
import com.duan.demo01.models.DeviceStatus;
import com.duan.demo01.models.Role;
import com.duan.demo01.models.User;
import com.duan.demo01.repositories.DeviceRepo;
import com.duan.demo01.repositories.DeviceStatusRepo;
import com.duan.demo01.repositories.RoleRepo;
import com.duan.demo01.repositories.UserRepo;
import com.duan.demo01.servies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Demo01Application extends SpringBootServletInitializer implements ApplicationRunner {
    @Autowired
    UserRepo userRepo;
    @Autowired
    DeviceStatusRepo deviceStatusRepo;

    public static void main(String[] args) {
        SpringApplication.run(Demo01Application.class, args);
    }

    private final Path storageFolder = Paths.get("uploads");

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepo.findAll().isEmpty()) {
            Role staff = new Role(null, "Nhân viên", "staff");
            Role manager = new Role(null, "Quản lý", "manager");
            Role admin = new Role(null, "Quản trị viên", "admin");
            User admin_user = new User(null, "admin", "admin@gmail.com", "admin", admin);
            User null_user = new User(null, "Không có người dùng", "null", "null", null);
            User staff_user = new User(null, "staff", "staff@gmail.com", "staff", staff);
            User manager_user = new User(null, "manager", "manager@gmail.com", "manager", manager);
            DeviceStatus deviceStatus1 = new DeviceStatus(null, "Rảnh", "free");
            DeviceStatus deviceStatus2 = new DeviceStatus(null, "Đang sử dụng", "using");
            DeviceStatus deviceStatus3 = new DeviceStatus(null, "Cần sửa chửa", "repair");
            DeviceStatus deviceStatus4 = new DeviceStatus(null, "Đang sửa chửa", "repairing");
            userRepo.save(null_user);
            userRepo.save(admin_user);
            userRepo.save(manager_user);
            userRepo.save(staff_user);
            deviceStatusRepo.save(deviceStatus1);
            deviceStatusRepo.save(deviceStatus2);
            deviceStatusRepo.save(deviceStatus3);
            deviceStatusRepo.save(deviceStatus4);
        }
    }
}
