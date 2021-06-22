package com.database.aim.service;

import com.alibaba.fastjson.JSON;
import com.database.aim.dao.TeamDao;
import com.database.aim.dao.UserTeamMapDao;
import com.database.aim.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.database.aim.pojo.Authority.ADMIN;
import static com.database.aim.pojo.Authority.CREATOR;

@Service
public class TeamService {
    @Autowired
    TeamDao teamDao;
    @Autowired
    UserTeamMapDao userTeamMapDao;
    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;

    public boolean addTeam(CreateTeam createTeam) {
        System.out.println(JSON.toJSONString(createTeam));
        Team team = new Team();
        team.setMemberNum(1);
        team.setDescription(createTeam.getDescription());
        team.setName(createTeam.getName());
        team = teamDao.save(team);
        User user = userService.getUserById(createTeam.getUserId());
        UserTeamMap userTeamMap = new UserTeamMap();
        userTeamMap.setTeamId(team.getId());
        userTeamMap.setTeamName(team.getName());
        userTeamMap.setUserId(user.getId());
        userTeamMap.setUsername(user.getUsername());
        userTeamMap.setAuthority(CREATOR);
        userTeamMapDao.save(userTeamMap);
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
        if(authority.equals(CREATOR) || authority.equals(ADMIN)) {
            return true;
        }
        return false;
    }
    //检测某人是否有权利发布规则

    public boolean isCreator(int teamId, int userId) {
        Authority authority = getAuthority(teamId, userId);
        if(authority.equals(CREATOR)) {
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
        int num = team.getMemberNum() + 1;
        team.setMemberNum(num);
        teamDao.save(team);
        UserTeamMap userTeamMap = new UserTeamMap();
        userTeamMap.setTeamId(team.getId());
        userTeamMap.setTeamName(team.getName());
        userTeamMap.setUserId(user.getId());
        userTeamMap.setUsername(user.getUsername());
        userTeamMap.setAuthority(Authority.MEMBER);
        userTeamMapDao.save(userTeamMap);
        List<TeamTask> teamTasks = taskService.getAllTeamTasksByTeamId(team.getId());
        for(TeamTask it : teamTasks) {
            taskService.addPersonalTaskByTeamTask(it, user.getId());
        }
    }

    public void removeMember(Team team, User user) {
        if(isCreator(team.getId(), user.getId())) {
            teamDao.delete(team);
            List<TeamTask> teamTasks = taskService.getAllTeamTasksByTeamId(team.getId());
            for(TeamTask it : teamTasks) {
                taskService.deleteTeamTask(it);
                List<PersonalTask> personalTasks = taskService.getAllPersonalTasksByUserId(it.getId());
                for(PersonalTask personalTask : personalTasks) {
                    taskService.deletePersonalTask(personalTask);
                }
            }
        }
        int num = team.getMemberNum() - 1;
        team.setMemberNum(num);
        teamDao.save(team);
        UserTeamMap userTeamMap = new UserTeamMap();
        userTeamMap.setTeamId(team.getId());
        userTeamMap.setTeamName(team.getName());
        userTeamMap.setUserId(user.getId());
        userTeamMap.setUsername(user.getUsername());
        userTeamMapDao.delete(userTeamMap);
        List<TeamTask> teamTasks = taskService.getAllTeamTasksByTeamId(team.getId());
        for(TeamTask it : teamTasks) {
            taskService.removePersonalTaskByTeamTask(it, user.getId());
        }
    }

    public void setAdmin(int teamId, int userId) {
        UserTeamMap userTeamMap = userTeamMapDao.findUserTeamMapByUserIdAndTeamId(userId, teamId);
        userTeamMap.setAuthority(ADMIN);
        userTeamMapDao.save(userTeamMap);
    }

    public void unsetAdmin(int teamId, int userId) {
        UserTeamMap userTeamMap = userTeamMapDao.findUserTeamMapByUserIdAndTeamId(userId, teamId);
        userTeamMap.setAuthority(Authority.MEMBER);
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

    public int getMemberNumByTeamId(int teamId) {
        return teamDao.findTeamById(teamId).getMemberNum();
    }
}
