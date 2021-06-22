package com.database.aim.controller;

import com.database.aim.pojo.FollowUser;
import com.database.aim.service.FollowService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@Api
public class FollowController {
    @Autowired
    FollowService followService;

    @PostMapping("/follow/getFollers")
    public List<FollowUser> getFollowers(int userID) {
        List<FollowUser> followUsers = new ArrayList<>();
        try {
            followUsers = followService.getAllFollowers(userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return followUsers;
    }

    @PostMapping("/follow/getUsersFollowing")
    public List<FollowUser> getUsersFollowing(int userID) {
        List<FollowUser> followUsers = new ArrayList<>();
        try {
            followUsers = followService.getAllUsersFollowing(userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return followUsers;
    }

    @PostMapping("/follow/addFollow")
    public void addFollow(int userId, int followerId) {
        try {
            followService.follow(userId, followerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/follow/unfollow")
    public void deleteFollow(int userId, int followerId) {
        try {
            followService.follow(userId, followerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
