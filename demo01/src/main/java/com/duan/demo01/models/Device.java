package com.duan.demo01.models;

import com.duan.demo01.controllers.DeviceType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Device {

    @Id
    @GenericGenerator(name = "Device-UUID",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefix", value = "DVC"),
                    @org.hibernate.annotations.Parameter(name = "entity", value = "device")
            },
            strategy = "com.duan.demo01.utils.CustomIdGenerator")
    @GeneratedValue(generator = "Device-UUID")
    private String id;
    private String name;
    private String price;
    private String note;
    private String supplier;

    private LocalDate dateBuy = LocalDate.now();
    private LocalDate warrantyEnd;

    // relationship
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qr_id")
    private QR qr;

    @ManyToOne()
    @JoinColumn(name = "warehouse_id")
    @JsonBackReference
    private Warehouse warehouse;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne()
    @JoinColumn(name = "type_id")
    private DeviceType type;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    List<DeviceMaintenance> deviceMaintenanceList = new ArrayList<>();
}
