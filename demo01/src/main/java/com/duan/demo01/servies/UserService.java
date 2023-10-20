package com.duan.demo01.servies;

import com.duan.demo01.models.User;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User findUser(String id);
    User remove(String id);
    User update(User user);

}
