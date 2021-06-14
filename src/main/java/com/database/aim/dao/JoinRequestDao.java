package com.database.aim.dao;

import com.database.aim.pojo.FollowUser;
import com.database.aim.pojo.JoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JoinRequestDao extends JpaRepository<JoinRequest, Integer> {
    @Query(value = "select * from joinrequest where team_id = ?1 and success is null", nativeQuery = true)
    List<JoinRequest> findUnreadJoinRequestsByTeamId(int teamId);

    @Query(value = "select * from joinrequest where user_id = ?1 and success is null", nativeQuery = true)
    List<JoinRequest> findUnreadJoinRequestsByUserId(int userId);

    @Query(value = "select * from joinrequest where team_id = ?1 and success is not null", nativeQuery = true)
    List<JoinRequest> findReadJoinRequestsByTeamId(int teamId);

    @Query(value = "select * from joinrequest where user_id = ?1 and success is not null", nativeQuery = true)
    List<JoinRequest> findReadJoinRequestsByUserId(int userId);
}
