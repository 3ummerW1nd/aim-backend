package com.database.aim.dao;

import com.database.aim.pojo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowDao extends JpaRepository<Follow, FollowPK> {
    @Query(value = "select user_id, username from follow where following_id = ?1", nativeQuery = true)
    List<FollowUser> findUsersByFollowingId(int followingId);
    //返回某用户所有关注的人

    @Query(value = "select following_id, following_name from follow where user_id = ?1", nativeQuery = true)
    List<FollowUser> findFollowersByUserId(int userId);
    //返回某用户的所有粉丝

    Follow findFollowByUserIdAndFollowingId(int userId, int followingId);

}
