package com.database.aim.controller;

import com.alibaba.fastjson.JSON;
import com.database.aim.pojo.FollowRelation;
import com.database.aim.pojo.FollowUser;
import com.database.aim.service.FollowService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@Api
public class FollowController {
    @Autowired
    FollowService followService;

    @GetMapping("/follow/getFollers")
    public List<FollowUser> getFollowers(@RequestParam("userId") int userID) {
        List<FollowUser> followUsers = new ArrayList<>();
        try {
            followUsers = followService.getAllFollowers(userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return followUsers;
    }

    @GetMapping("/follow/getUsersFollowing")
    public List<FollowUser> getUsersFollowing(@RequestParam("userId") int userID) {
        List<FollowUser> followUsers = new ArrayList<>();
        try {
            followUsers = followService.getAllUsersFollowing(userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return followUsers;
    }

    @PostMapping("/follow/addFollow")
    public void addFollow(@RequestBody FollowRelation followRelation) {
        System.out.println(JSON.toJSONString(followRelation));
        int userId = followRelation.getUserId();
        int followerId = followRelation.getFollowingId();
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

    @GetMapping("/follow/getFollowState")
    public boolean getFollowState(@RequestParam("userId") int userId, @RequestParam("followingId") int followingId) {
        try {
            return followService.isFollow(userId, followingId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @GetMapping("/follow/wouldLikeToFollow")
    public List<FollowUser> getUsersWouldLikeToFollow(@RequestParam("userId") int userId) {
        List <FollowUser> answer = new ArrayList<>();
        List<FollowUser> followUsers = new ArrayList<>();
        try {
            followUsers = followService.getAllUsersFollowing(userId);
            for(FollowUser it : followUsers) {
                List<FollowUser> temp = followService.getAllUsersFollowing(it.getId());
                for(FollowUser iitt : temp) {
                    if(!getFollowState(iitt.getId(), userId))
                        answer.add(iitt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

}
