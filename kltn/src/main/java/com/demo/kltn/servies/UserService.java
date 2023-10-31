package com.demo.kltn.servies;


import com.demo.kltn.models.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User findUser(String id);
    User remove(String id);
    User update(User user);

}
