package com.database.aim.controller;

import com.database.aim.pojo.User;
import com.database.aim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public boolean login(@RequestBody User user) {
        if(userService.login(user)) {
            return true;
        }
        return false;
    }
}
