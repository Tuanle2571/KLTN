package com.duan.demo01;

import com.duan.demo01.models.*;
import com.duan.demo01.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class Demo01Application extends SpringBootServletInitializer implements ApplicationRunner {
    @Autowired
    UserRepo userRepo;
    @Autowired
    WarehouseRepo warehouseRepo;
    @Autowired
    StatusRepo statusRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(Demo01Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (statusRepo.findAll().isEmpty()) {
            Status free = new Status(null,"Rảnh","free");
            Status active = new Status(null,"Đang hoạt động","active");
            Status repairing = new Status(null,"Đang bảo trì","repairing");
            Warehouse warehouse1 = new Warehouse(null,"Kho 1", "Số X xã Tân Xuân, huyện Hóc Môn, tp Hồ Chí Minh",null,null,null);
            Warehouse warehouse2 = new Warehouse(null,"Kho 2", "Đường Trường Trinh, Tân bình, ... ",null,null,null);
            Warehouse warehouse3 = new Warehouse(null,"Kho 3", "Khu Công nghiệp X, Xã Nhị Bình",null,null,null);
            statusRepo.save(free);
            statusRepo.save(active);
            statusRepo.save(repairing);
            warehouseRepo.save(warehouse1);
            warehouseRepo.save(warehouse2);
            warehouseRepo.save(warehouse3);
            Role user =new Role(null,  "ADMIN",null);
            Role admin = new Role(null,  "USER",null);
            if (userRepo.findAll().isEmpty()){
                UserEntity new_user = new UserEntity();
                new_user.setPassword(passwordEncoder.encode("admin"));
                new_user.setEmail("admin@gmail.com");
                new_user.setUsername("admin");
                new_user.setRoles(Arrays.asList(user,admin));
                userRepo.save(new_user);
            }
        }
    }
}
