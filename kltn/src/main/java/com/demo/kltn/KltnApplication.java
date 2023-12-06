package com.demo.kltn;

import com.demo.kltn.models.DeviceStatus;
import com.demo.kltn.models.Role;
import com.demo.kltn.models.User;
import com.demo.kltn.models.Warehouse;
import com.demo.kltn.repositories.DeviceStatusRepo;
import com.demo.kltn.repositories.UserRepo;
import com.demo.kltn.repositories.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KltnApplication implements ApplicationRunner {
	@Autowired
	UserRepo userRepo;
	@Autowired
	DeviceStatusRepo deviceStatusRepo;
	@Autowired
	WarehouseRepo warehouseRepo;

	public static void main(String[] args) {
		SpringApplication.run(KltnApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		if (userRepo.findAll().isEmpty()) {
//			Role staff = new Role(null, "Nhân viên", "staff");
//			Role manager = new Role(null, "Quản lý", "manager");
//			Role admin = new Role(null, "Quản trị viên", "admin");
//			User admin_user = new User(null, "admin", "admin@gmail.com", "admin", admin);
//			User null_user = new User(null, "Không có người dùng", "null", "null", null);
//			User staff_user = new User(null, "staff", "staff@gmail.com", "staff", staff);
//			User manager_user = new User(null, "manager", "manager@gmail.com", "manager", manager);
//			DeviceStatus deviceStatus1 = new DeviceStatus(null, "Rảnh", "free");
//			DeviceStatus deviceStatus2 = new DeviceStatus(null, "Đang sử dụng", "using");
//			DeviceStatus deviceStatus3 = new DeviceStatus(null, "Cần sửa chửa", "repair");
//			DeviceStatus deviceStatus4 = new DeviceStatus(null, "Đang sửa chửa", "repairing");
//			Warehouse warehouse1 = new Warehouse(null,"Kho 1", "1/5 ap 2",null,null);
//			Warehouse warehouse2 = new Warehouse(null,"Kho 2", "1/5 ap 2",null,null);
//			Warehouse warehouse3 = new Warehouse(null,"Kho 3", "1/5 ap 2",null,null);
//			Warehouse warehouse4 = new Warehouse(null,"Kho 4", "1/5 ap 2",null,null);
//			userRepo.save(null_user);
//			userRepo.save(admin_user);
//			userRepo.save(manager_user);
//			userRepo.save(staff_user);
//			deviceStatusRepo.save(deviceStatus1);
//			deviceStatusRepo.save(deviceStatus2);
//			deviceStatusRepo.save(deviceStatus3);
//			deviceStatusRepo.save(deviceStatus4);
//			warehouseRepo.save(warehouse1);
//			warehouseRepo.save(warehouse2);
//			warehouseRepo.save(warehouse3);
//			warehouseRepo.save(warehouse4);
//		}
	}
}
