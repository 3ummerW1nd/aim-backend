package com.database.aim.service;

import com.alibaba.fastjson.JSONObject;
import com.database.aim.pojo.User;
import com.database.aim.util.RedisUtil;
import nl.bitwalker.useragentutils.UserAgent;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class TokenService {
    @Autowired
    RedisUtil redisUtil;

    //生成token(格式为token:设备-加密的用户名-时间-六位随机数)
    public String generateToken(int userId, String username){
        StringBuilder token = new StringBuilder();
        token.append("token:");
        token.append(DigestUtils.md5Hex(userId +"-" + username) + "-");
        token.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "-");
        token.append(new Random().nextInt((999999 - 111111 + 1)) + 111111);
        System.out.println("token=>" + token.toString());
        return token.toString();
    }

    //保存token
    public void saveToken(String token, User user){
        redisUtil.setex(String.valueOf(user.getId()),2*60*60, token);
    }
}
