package com.company.onlineshopping.response;

import com.company.onlineshopping.entity.Role;

import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse{
    Long id;
    String name;
    String surname;
    String profilePhoto;
    String email;
    String password;
    String phone;
    List<Role> roles;
}
