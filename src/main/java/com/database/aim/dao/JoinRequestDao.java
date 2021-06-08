package com.database.aim.dao;

import com.database.aim.pojo.JoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoinRequestDao extends JpaRepository<JoinRequest, Integer> {
    List<JoinRequest> findJoinRequestsByTeamId(int teamId);
    List<JoinRequest> findJoinRequestsByUserId(int userId);
}
