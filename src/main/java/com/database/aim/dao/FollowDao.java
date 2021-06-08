package com.database.aim.dao;

import com.database.aim.pojo.Follow;
import com.database.aim.pojo.FollowPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowDao extends JpaRepository<Follow, FollowPK> {
    List<Follow> findFollowsByUserId(int userId);
    List<Follow> findFollowsByFollowingId(int followingId);
}
