package com.company.onlineshopping.controller;

import com.company.onlineshopping.entity.User;
import com.company.onlineshopping.request.UserRequest;
import com.company.onlineshopping.response.BaseResponse;
import com.company.onlineshopping.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }


    @PostMapping
    public BaseResponse<Void> saveUser(@Valid @RequestBody UserRequest userRequest) {
        userService.saveUser(userRequest);
        return BaseResponse.success();
    }

//    @GetMapping("/users")
//    public List<User> getAllUsers(User user) {
//
//        return userService.getAllUsers();
//
//    }


}
