package com.database.aim.service;

import com.database.aim.dao.FollowDao;
import com.database.aim.pojo.FollowUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    @Autowired
    FollowDao followDao;
    List<FollowUser> getAllFollowers(int userId) {
        return followDao.findFollowersByUserId(userId);
    }

    List<FollowUser> getAllUsersFollowing(int userId) {
        return followDao.findUsersByFollowingId(userId);
    }
}
