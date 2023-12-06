package com.duan.demo01.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "user")
public class UserEntity {
    @Id
    @GenericGenerator(name = "User-UUID",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefix", value = "USR"),
                    @org.hibernate.annotations.Parameter(name = "entity", value = "user")
            },
            strategy = "com.duan.demo01.utils.CustomIdGenerator")
    @GeneratedValue(generator = "User-UUID")
    private String id;
    @Column(unique = true)
    private String username;
    private String lastname;
    private String firstname;
    private String address;
    private String birthplace;
    private String birthdate;
    private String phone;
    private String image;

    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();
}
