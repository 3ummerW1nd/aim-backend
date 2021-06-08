package com.database.aim.service;

import com.database.aim.dao.TeamDao;
import com.database.aim.dao.UserTeamMapDao;
import com.database.aim.pojo.BriefTeam;
import com.database.aim.pojo.BriefUser;
import com.database.aim.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    TeamDao teamDao;
    @Autowired
    UserTeamMapDao userTeamMapDao;
    public void addTeam(Team team) {
        teamDao.save(team);
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
}
