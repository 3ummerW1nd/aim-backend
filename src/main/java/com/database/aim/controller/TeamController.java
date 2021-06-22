package com.database.aim.controller;

import com.alibaba.fastjson.JSON;
import com.database.aim.pojo.*;
import com.database.aim.service.TeamService;
import com.database.aim.service.UserService;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@Api
public class TeamController {
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;

    @GetMapping("/team/getTeam")
    public Team getTeam(@RequestParam("teamId") int teamId) {
        Team team = new Team();
        try {
            team = teamService.getTeamById(teamId);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return team;
    }

    @GetMapping("/team/getAllUsers")
    public List<BriefUser> getAllUsersByTeamId(@RequestParam("teamId") int teamId) {
        List<BriefUser> users = new ArrayList<>();
        try {
            users = teamService.getUsersByTeamId(teamId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @CrossOrigin
    @PostMapping("/team/addOrUpdate")
    public void addOrUpdateTeam(@RequestBody CreateTeam createTeam) {
        try {
            teamService.addTeam(createTeam);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/team/joinTeam")
    public boolean joinTeam(@RequestBody UserTeamRelation userTeamRelation) {
        try {
            int teamId = userTeamRelation.getTeamId();
            int userId = userTeamRelation.getUserId();
            Team team = teamService.getTeamById(teamId);
            User user = userService.getUserById(userId);
            return teamService.addMember(team, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @PostMapping("/team/exitTeam")
    public void exitTeam(@RequestBody Team team, @RequestBody User user) {
        try {
            teamService.removeMember(team, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/team/setAdmin")
    public void setAdmin(@RequestBody int teamId, @RequestBody int userId) {
        try {
            teamService.setAdmin(teamId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/team/unsetAdmin")
    public void unsetAdmin(@RequestBody int teamId, @RequestBody int userId) {
        try {
            teamService.unsetAdmin(teamId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
