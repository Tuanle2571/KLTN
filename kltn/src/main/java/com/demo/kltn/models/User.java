package com.demo.kltn.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GenericGenerator(name = "User-UUID",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefix", value = "USR0"),
                    @org.hibernate.annotations.Parameter(name = "entity", value = "user")
            },
            strategy = "com.demo.kltn.utils.CustomIdGenerator")
    @GeneratedValue(generator = "User-UUID")
    private String id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;

}
