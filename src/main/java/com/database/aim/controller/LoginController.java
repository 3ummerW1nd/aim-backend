package com.database.aim.controller;

import com.database.aim.pojo.LoginUser;
import com.database.aim.pojo.Token;
import com.database.aim.pojo.User;
import com.database.aim.service.TokenService;
import com.database.aim.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Api
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public Token login(@RequestBody LoginUser loginUser) {
        int id = loginUser.getId();
        String password = loginUser.getPassword();
        Token reToken = new Token();
        reToken.setUserId(id);
        User user = userService.login(id, password);
        if (user != null) {//登录成功生成token并保存token
            String token = tokenService.generateToken(id, user.getUsername());
            tokenService.saveToken(token, user);
            reToken.setToken(token);
            return reToken;
        } else {
            reToken.setToken("false");
            return reToken;
        }
    }
}
