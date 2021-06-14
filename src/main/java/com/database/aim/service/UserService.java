package com.database.aim.service;

import com.database.aim.dao.UserDao;
import com.database.aim.dao.UserTeamMapDao;
import com.database.aim.pojo.BriefTeam;
import com.database.aim.pojo.User;
import com.database.aim.pojo.UserTeamMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDAO;
    @Autowired
    UserTeamMapDao userTeamMapDao;

    public void addUser(User user) {
        userDAO.save(user);
    }
    //向数据库中添加用户

    public User getUserById(int id) {
        return userDAO.findUserById(id);
    }
    //通过ID查询用户
    public User getUserByIDAndPassword(int id, String Password) {
        return userDAO.findUserByIdAndPassword(id, Password);
    }

    //获取这个用户加入的所有小组的组名和组号
    public List<BriefTeam> getTeamsByUserId(int userId) {
        return userTeamMapDao.findTeamsByUserId(userId);
    }

    public boolean login(User user) {
        if(getUserByIDAndPassword(user.getId(), user.getPassword()) != null) {
            return true;
        }
        return false;
    }

    public void changeUsername(int userId, String newName) {
        User user = userDAO.findUserById(userId);
        user.setUsername(newName);
        userDAO.save(user);
        List<UserTeamMap> userTeamMaps;
        userTeamMaps = userTeamMapDao.findUserTeamMapsByUserId(userId);
        for(UserTeamMap it : userTeamMaps) {
            it.setUsername(newName);
            userTeamMapDao.save(it);
        }
    }
}
