package com.database.aim.controller;

import com.alibaba.fastjson.JSON;
import com.database.aim.pojo.FollowRelation;
import com.database.aim.pojo.FollowUser;
import com.database.aim.service.FollowService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
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
    public boolean addFollow(@RequestBody FollowRelation followRelation) {
        int userId = followRelation.getUserId();
        int followerId = followRelation.getFollowingId();
        if(followService.isFollow(userId, followerId))
            return false;
        try {
            followService.follow(userId, followerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @CrossOrigin
    @PostMapping("/follow/unfollow")
    public void deleteFollow(@RequestBody FollowRelation followRelation) {
        int userId = followRelation.getUserId();
        int followerId = followRelation.getFollowingId();
        try {
            followService.unfollow(userId, followerId);
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
                    if(!getFollowState(iitt.getId(), userId) && iitt.getId() != userId)
                        answer.add(iitt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashSet set = new HashSet(answer);
        //使用LinkedHashSet可以保证输入的顺序
        //LinkedHashSet<String> set2 = new LinkedHashSet<String>(list);
        answer.clear();
        answer.addAll(set);
        return answer;
    }

}
