package com.database.aim.service;

import com.database.aim.dao.UserDao;
import com.database.aim.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDAO;

    public void addUser(User user) {
        userDAO.save(user);
    }
    //向数据库中添加用户

    public User getUserByID(int Id) {
        return userDAO.findUserById(Id);
    }
    //通过ID查询用户
    public User getUserByIDAndPassword(int Id, String Password) {
        return userDAO.findUserByIdAndPassword(Id, Password);
    }
}
