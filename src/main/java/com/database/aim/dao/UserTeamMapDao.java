package com.database.aim.dao;

import com.database.aim.pojo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTeamMapDao extends JpaRepository<UserTeamMap, UserTeamPK> {

    @Query(value = "select user_id, username, authority from UserTeamMap where team_id = ?1", nativeQuery = true)
    List<BriefUser> findUsersByTeamId(int teamId);
    //返回小组中所有人的ID与姓名

    @Query(value = "select team_id, team_name, authority from UserTeamMap where user_id = ?1", nativeQuery = true)
    List<BriefTeam> findTeamsByUserId(int userId);
    //返回某个人关注的所有小组的ID与组名

    @Query(value = "select authority from UserTeamMap where team_id = ?1 and user_id = ?2", nativeQuery = true)
    Authority findAuthority(int teamId, int userId);
    //返回某个小组某个成员的职务

    UserTeamMap findUserTeamMapByUserIdAndTeamId(int userId, int teamId);

    List<UserTeamMap> findUserTeamMapsByUserId(int userId);

    List<UserTeamMap> findUserTeamMapsByTeamId(int teamId);
}
