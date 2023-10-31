package com.duan.demo01;

import com.duan.demo01.models.*;
import com.duan.demo01.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@SpringBootApplication
public class Demo01Application extends SpringBootServletInitializer implements ApplicationRunner {
    @Autowired
    UserRepo userRepo;
    @Autowired
    WarehouseRepo warehouseRepo;
    @Autowired
    DeviceStatusRepo deviceStatusRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(Demo01Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleRepo.findAll().isEmpty()) {
            DeviceStatus free = new DeviceStatus(null,"Rảnh","free");
            DeviceStatus active = new DeviceStatus(null,"Đang hoạt động","active");
            DeviceStatus repairing = new DeviceStatus(null,"Đang sửa chửa","repairing");
            Warehouse warehouse1 = new Warehouse(null,"Kho 1", "Số X xã Tân Xuân, huyện Hóc Môn, tp Hồ Chí Minh",null,null);
            Warehouse warehouse2 = new Warehouse(null,"Kho 2", "Đường Trường Trinh, Tân bình, ... ",null,null);
            Warehouse warehouse3 = new Warehouse(null,"Kho 3", "Khu Công nghiệp X, Xã Nhị Bình",null,null);
            Warehouse warehouse4 = new Warehouse(null,"Kho 3", "Khu Công nghiệp X, Xã Nhị Bình",null,null);
            Warehouse warehouse5 = new Warehouse(null,"Kho 3", "Khu Công nghiệp X, Xã Nhị Bình",null,null);
            Warehouse warehouse6 = new Warehouse(null,"Kho 3", "Khu Công nghiệp X, Xã Nhị Bình",null,null);
            Warehouse warehouse7 = new Warehouse(null,"Kho 3", "Khu Công nghiệp X, Xã Nhị Bình",null,null);
            Warehouse warehouse8 = new Warehouse(null,"Kho 3", "Khu Công nghiệp X, Xã Nhị Bình",null,null);
            Warehouse warehouse9 = new Warehouse(null,"Kho 3", "Khu Công nghiệp X, Xã Nhị Bình",null,null);
            Warehouse warehouse10 = new Warehouse(null,"Kho 3", "Khu Công nghiệp X, Xã Nhị Bình",null,null);
            Warehouse warehouse11 = new Warehouse(null,"Kho 3", "Khu Công nghiệp X, Xã Nhị Bình",null,null);
            deviceStatusRepo.save(free);
            deviceStatusRepo.save(active);
            deviceStatusRepo.save(repairing);
            warehouseRepo.save(warehouse1);
            warehouseRepo.save(warehouse2);
            warehouseRepo.save(warehouse3);
            warehouseRepo.save(warehouse4);
            warehouseRepo.save(warehouse5);
            warehouseRepo.save(warehouse6);
            warehouseRepo.save(warehouse7);
            warehouseRepo.save(warehouse8);
            warehouseRepo.save(warehouse9);
            warehouseRepo.save(warehouse10);
            warehouseRepo.save(warehouse11);
            Role user =new Role(null,  "ADMIN",null);
            Role admin = new Role(null,  "USER",null);
            UserEntity new_user = new UserEntity();
            new_user.setPassword(passwordEncoder.encode("admin"));
            new_user.setEmail("admin@gmail.com");
            new_user.setUsername("admin");
            new_user.setRoles(Arrays.asList(user,admin));
            userRepo.save(new_user);

        }
    }
}
