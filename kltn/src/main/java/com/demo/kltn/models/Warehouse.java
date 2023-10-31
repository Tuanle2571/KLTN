package com.demo.kltn.models;

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
                    @org.hibernate.annotations.Parameter(name = "prefix", value = "WH0"),
                    @org.hibernate.annotations.Parameter(name = "entity", value = "warehouse")
            },
            strategy = "com.demo.kltn.utils.CustomIdGenerator")
    @GeneratedValue(generator = "Warehouse-UUID")
    private String id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "warehouse")
    private List<Device> devices = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User manager;

}
