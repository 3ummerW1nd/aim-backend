package com.database.aim.controller;

import com.alibaba.fastjson.JSON;
import com.database.aim.pojo.Authority;
import com.database.aim.pojo.BriefTeam;
import com.database.aim.pojo.User;
import com.database.aim.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@Api
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/getUser")
    public User getUser(@RequestParam("userId") int userId) {
        System.out.println(userId);
        User user = new User();
        try {
            user = userService.getUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @PostMapping("/user/addOrUpdate")
    public void insertOrUpdateUser(@RequestBody User user) {
        System.out.println(JSON.toJSONString(user));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/user/getCreateTeams")
    public List<BriefTeam> getCreateTeams(@RequestParam("userId") int userId) {
        List<BriefTeam> answerTeams = new ArrayList<>();
        List<BriefTeam> briefTeams = new ArrayList<>();
        try {
            briefTeams = userService.getTeamsByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (BriefTeam briefTeam : briefTeams) {
            if (briefTeam.getAuthority() == Authority.creator) {
                answerTeams.add(briefTeam);
            }
        }
        return answerTeams;
    }

    @GetMapping("/user/getJoinTeams")
    public List<BriefTeam> getJoinTeams(@RequestParam("userId") int userId) {
        List<BriefTeam> briefTeams = new ArrayList<>();
        List<BriefTeam> answerTeams = new ArrayList<>();
        try {
            briefTeams = userService.getTeamsByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (BriefTeam briefTeam : briefTeams) {
            if (briefTeam.getAuthority() != Authority.creator) {
                answerTeams.add(briefTeam);
            }
        }
        return answerTeams;
    }

}
