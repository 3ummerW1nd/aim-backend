package com.database.aim.controller;

import com.database.aim.pojo.BriefTeam;
import com.database.aim.pojo.User;
import com.database.aim.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/getUser")
    public User getUser(@RequestBody int userId) {
        User user = new User();
        try {
            user =  userService.getUserById(userId);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @PostMapping("/user/addOrUpdate")
    public void insertOrUpdateUser(@RequestBody User user) {
        try {
            userService.addUser(user);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/user/getAllTeams")
    public List<BriefTeam> getAllTeams(int userId){
        List<BriefTeam> briefTeams = new ArrayList<>();
        try {
            briefTeams = userService.getTeamsByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return briefTeams;
    }

}
