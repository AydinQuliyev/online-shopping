package com.company.onlineshopping.service.user;

import com.company.onlineshopping.entity.User;
import com.company.onlineshopping.request.UserRequest;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User saveUser(UserRequest userRequest);
}
