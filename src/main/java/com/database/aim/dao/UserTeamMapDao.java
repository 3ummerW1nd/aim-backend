package com.database.aim.dao;

import com.database.aim.pojo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserTeamMapDao extends JpaRepository<UserTeamMap, UserTeamPK> {

    @Query(value = "select new com.database.aim.pojo.BriefUser(a.userId, a.username, a.authority) from UserTeamMap a where a.teamId = ?1")
    List<BriefUser> findUsersByTeamId(int teamId);
    //返回小组中所有人的ID与姓名

    @Query(value = "select new com.database.aim.pojo.BriefTeam(a.teamId, a.teamName, a.authority) from UserTeamMap a where a.userId = ?1")
    List<BriefTeam> findTeamsByUserId(int userId);
    //返回某个人关注的所有小组的ID与组名

    @Query(value = "select a.authority from UserTeamMap a where a.teamId = ?1 and a.userId = ?2")
    Authority findAuthority(int teamId, int userId);
    //返回某个小组某个成员的职务

    UserTeamMap findUserTeamMapByUserIdAndTeamId(int userId, int teamId);

    List<UserTeamMap> findUserTeamMapsByUserId(int userId);

    List<UserTeamMap> findUserTeamMapsByTeamId(int teamId);

    @Query(value = "select team_id from UserTeamMap where user_id = ?1", nativeQuery = true)
    List<Integer> findTeamIdsByUserId(int userId);
}
