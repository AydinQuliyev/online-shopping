package com.company.onlineshopping.mapper;

import com.company.onlineshopping.entity.User;
import com.company.onlineshopping.request.UserRequest;
import com.company.onlineshopping.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface UserEntityMapper {

    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    User toEntity(UserRequest userRequest);

    List<UserResponse> toResponseList(List<User> users);
}