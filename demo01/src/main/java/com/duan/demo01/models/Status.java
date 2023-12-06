package com.duan.demo01.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Status {
    @Id
    @GeneratedValue
    private Integer id;
    private String status;
    private String statusValue;

}
