package com.database.aim.dao;

import com.database.aim.pojo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowDao extends JpaRepository<Follow, FollowPK> {
    @Query(value = "select new com.database.aim.pojo.FollowUser(a.userId, a.username) from Follow a where a.followingId = ?1")
    List<FollowUser> findUsersByFollowingId(int followingId);
    //返回某用户所有关注的人

    @Query(value = "select new com.database.aim.pojo.FollowUser(a.userId, a.username) from Follow a where a.userId = ?1")
    List<FollowUser> findFollowersByUserId(int userId);
    //返回某用户的所有粉丝

    Follow findFollowByUserIdAndFollowingId(int userId, int followingId);
}
