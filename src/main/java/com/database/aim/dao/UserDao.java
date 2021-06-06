package com.database.aim.dao;

import com.database.aim.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    User findUserById(int Id);
    User findUserByIdAndPassword(int Id, String password);
}
