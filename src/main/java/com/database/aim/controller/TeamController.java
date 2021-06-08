package com.database.aim.controller;

import com.database.aim.pojo.Team;
import com.database.aim.pojo.User;
import com.database.aim.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamController {
    @Autowired
    TeamService teamService;

    @RequestMapping(value = "/getTeam", method = RequestMethod.GET)
    @ResponseBody
    public Team getTeam(@RequestParam("teamId") int teamId ) {
        Team team = new Team();
        try {
            team =  teamService.getTeamById(teamId);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return team;
    }
}
