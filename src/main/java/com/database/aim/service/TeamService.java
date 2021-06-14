package com.database.aim.service;

import com.database.aim.dao.TeamDao;
import com.database.aim.dao.UserTeamMapDao;
import com.database.aim.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    @Autowired
    TeamDao teamDao;
    @Autowired
    UserTeamMapDao userTeamMapDao;
    public boolean addTeam(Team team) {
        teamDao.save(team);
        return true;
    }
    //向数据库中添加组

    public Team getTeamById(int id) {
        return teamDao.findTeamById(id);
    }
    //通过ID查询组

    public List<BriefUser> getUsersByTeamId(int teamId) {
        return userTeamMapDao.findUsersByTeamId(teamId);
    }
    //获取这个小组中所有成员的简要信息

    public Authority getAuthority(int teamId, int userId) {
        return userTeamMapDao.findAuthority(teamId, userId);
    }
    //获取某个成员的角色

    public boolean isManager(int teamId, int userId) {
        Authority authority = getAuthority(teamId, userId);
        if(authority.equals("CREATOR") || authority.equals("ADMIN")) {
            return true;
        }
        return false;
    }
    //检测某人是否有权利发布规则

    public boolean isCreator(int teamId, int userId) {
        Authority authority = getAuthority(teamId, userId);
        if(authority.equals("CREATOR")) {
            return true;
        }
        return false;
    }
    //检测某人是否有做管理员的权力
    public boolean deleteTeam(Team team, int userId) {
        if(isCreator(team.getId(), userId)) {
            teamDao.delete(team);
            return true;
        }
        return false;
    }

    public void addMember(Team team, User user) {
        UserTeamMap userTeamMap = new UserTeamMap();
        userTeamMap.setTeamId(team.getId());
        userTeamMap.setTeamName(team.getName());
        userTeamMap.setUserId(user.getId());
        userTeamMap.setUsername(user.getUsername());
        userTeamMap.setAuthority(Authority.MEMBER);
        userTeamMapDao.save(userTeamMap);
    }

    public void removeMember(Team team, User user) {
        UserTeamMap userTeamMap = new UserTeamMap();
        userTeamMap.setTeamId(team.getId());
        userTeamMap.setTeamName(team.getName());
        userTeamMap.setUserId(user.getId());
        userTeamMap.setUsername(user.getUsername());
        userTeamMapDao.delete(userTeamMap);
    }

    public void setAdmin(int teamId, int userId) {
        UserTeamMap userTeamMap = userTeamMapDao.findUserTeamMapByUserIdAndTeamId(userId, teamId);
        userTeamMap.setAuthority(Authority.ADMIN);
        userTeamMapDao.save(userTeamMap);
    }

    public void changeTeamName(int teamId, String newName) {
        Team team = new Team();
        team.setName(newName);
        teamDao.save(team);
        List<UserTeamMap> userTeamMaps;
        userTeamMaps = userTeamMapDao.findUserTeamMapsByTeamId(teamId);
        for(UserTeamMap it : userTeamMaps) {
            it.setTeamName(newName);
            userTeamMapDao.save(it);
        }
    }

    public void changeDescription(int teamId, String description) {
        Team team = new Team();
        team.setDescription(description);
        teamDao.save(team);
    }
}
