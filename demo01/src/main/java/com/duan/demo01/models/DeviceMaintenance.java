package com.duan.demo01.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceMaintenance {

    @Id
    @GeneratedValue
    private Integer id;

    private LocalDate createDate = LocalDate.now();
    private String cost;
    private String note;


    @ManyToOne()
    @JoinColumn(name = "device_id")
    @JsonBackReference
    private Device device;

}
