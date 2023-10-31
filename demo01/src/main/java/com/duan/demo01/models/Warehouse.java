package com.duan.demo01.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Warehouse {
    @Id
    @GenericGenerator(name = "Warehouse-UUID",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefix", value = "WH"),
                    @org.hibernate.annotations.Parameter(name = "entity", value = "warehouse")
            },
            strategy = "com.duan.demo01.utils.CustomIdGenerator")
    @GeneratedValue(generator = "Warehouse-UUID")
    private String id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "warehouse",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Device> devices = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity manager;



}
