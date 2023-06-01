package com.company.onlineshopping.repository;
import com.company.onlineshopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Long> {
    User findByEmail(String email);

}
