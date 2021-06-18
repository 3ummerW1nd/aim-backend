package com.database.aim.controller;

import com.database.aim.pojo.BriefUser;
import com.database.aim.pojo.Team;
import com.database.aim.pojo.User;
import com.database.aim.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeamController {
    @Autowired
    TeamService teamService;

    @PostMapping("/team/getTeam")
    public Team getTeam(@RequestBody int teamId) {
        Team team = new Team();
        try {
            team = teamService.getTeamById(teamId);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return team;
    }

    @PostMapping("/team/getAllUsers")
    public List<BriefUser> getAllUsersByTeamId(@RequestBody int teamId) {
        List<BriefUser> users = new ArrayList<>();
        try {
            users = teamService.getUsersByTeamId(teamId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @PostMapping("/team/addOrUpdate")
    public void addOrUpdateTeam(@RequestBody Team team) {
        try {
            teamService.addTeam(team);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/team/joinTeam")
    public void joinTeam(@RequestBody Team team, @RequestBody User user) {
        try {
            teamService.addMember(team, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
