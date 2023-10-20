package com.duan.demo01.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Device {

    @Id
    @GenericGenerator(name = "Device-UUID",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefix", value = "DVC0"),
                    @org.hibernate.annotations.Parameter(name = "entity", value = "device")
            },
            strategy = "com.duan.demo01.utils.CustomIdGenerator")
    @GeneratedValue(generator = "Device-UUID")
    private String id;

    private String name;
    private String type;
    private String price;
    private String description;

    @JsonFormat(pattern = "dd/MM/yy")
    private LocalDate dateBuy = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yy")
    private LocalDate warrantyEnd;


    private String supplier;
    private String note;

    // relationship
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qr_id")
    private QR qr;

    @ManyToOne()
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private DeviceStatus deviceStatus;
}
