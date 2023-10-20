package com.duan.demo01.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryCheck {
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDateTime name;

    //Relation
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User performBy;

}
