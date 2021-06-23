package com.database.aim.service;

import com.database.aim.dao.FollowDao;
import com.database.aim.pojo.Follow;
import com.database.aim.pojo.FollowUser;
import com.database.aim.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FollowService {
    @Autowired
    FollowDao followDao;
    @Autowired
    UserService userService;
    public List<FollowUser> getAllFollowers(int userId) {
        return followDao.findFollowersByUserId(userId);
    }

    public List<FollowUser> getAllUsersFollowing(int userId) {
        return followDao.findUsersByFollowingId(userId);
    }

    public void follow(int userId, int followerId) {
        Follow follow = new Follow();
        User user = userService.getUserById(userId);
        User follower = userService.getUserById(followerId);
        follow.setUserId(user.getId());
        follow.setUsername(user.getUsername());
        follow.setFollowingId(follower.getId());
        follow.setFollowingName(follower.getUsername());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        follow.setFollowedAt(timestamp);
        followDao.save(follow);
    }

    public void unfollow(int userId, int followerId) {
        Follow follow = followDao.findFollowByUserIdAndFollowingId(userId, followerId);
        followDao.delete(follow);
    }

    public boolean isFollow(int userId, int followingId) {
        return followDao.findFollowByUserIdAndFollowingId(userId, followingId) != null;
    }

}
