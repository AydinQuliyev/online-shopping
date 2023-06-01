package com.company.onlineshopping.request;

import com.company.onlineshopping.entity.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    String name;
    String surname;
    String profilePhoto;
    String email;
    String password;
    String phone;
    List<Long> roles;
}
