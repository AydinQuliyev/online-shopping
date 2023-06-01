package com.company.onlineshopping.service.user;

import com.company.onlineshopping.entity.User;
import com.company.onlineshopping.mapper.UserEntityMapper;
import com.company.onlineshopping.repository.UserRepository;
import com.company.onlineshopping.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserEntityMapper userEntityMapper;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(UserRequest userRequest) {
        User user = userEntityMapper.toEntity(userRequest);
        return userRepository.save(user);
    }

}
