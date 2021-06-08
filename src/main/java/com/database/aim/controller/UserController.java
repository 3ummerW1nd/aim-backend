package com.database.aim.controller;

import com.alibaba.fastjson.JSON;
import com.database.aim.pojo.User;
import com.database.aim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@RequestParam("userId") int userId ) {
        User user = new User();
        try {
            user =  userService.getUserById(userId);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    @ResponseBody
    public User insertOrUpdateUser(@RequestParam("user") String userString ) {
        User user = new User();
        try {
            user = JSON.parseObject(userString, User.class);
            userService.addUser(user);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return user;
    }



}
