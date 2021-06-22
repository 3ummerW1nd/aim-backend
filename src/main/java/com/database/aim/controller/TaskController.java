package com.database.aim.controller;

import com.database.aim.pojo.PersonalTask;
import com.database.aim.pojo.TeamTask;
import com.database.aim.service.TaskService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping("/task/personalOnceTasks")
    public List<PersonalTask> getPersonalOnceTasks(@RequestBody int userId) {
        List<PersonalTask> personalTasks = new ArrayList<>();
        try {
            personalTasks = taskService.initUserPageOnceTasks(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personalTasks;
    }

    @PostMapping("/task/personalPeriodicTasks")
    public List<PersonalTask> getPersonalPeriodTasks(@RequestBody int userId) {
        List<PersonalTask> personalTasks = new ArrayList<>();
        try {
            personalTasks = taskService.initUserPagePeriodicTasks(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personalTasks;
    }

    @PostMapping("/task/teamOnceTasks")
    public List<TeamTask> getTeamOnceTasks(@RequestBody int userId, @RequestBody int teamId) {
        List<TeamTask> teamTasks = new ArrayList<>();
        try {
            teamTasks = taskService.initTeamPageOnceTasks(userId, teamId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamTasks;
    }

    @PostMapping("/task/teamPeriodicTasks")
    public List<TeamTask> getTeamPeriodicTasks(@RequestBody int userId, @RequestBody int teamId) {
        List<TeamTask> teamTasks = new ArrayList<>();
        try {
            teamTasks = taskService.initTeamPagePeriodicTasks(userId, teamId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamTasks;
    }

    @PostMapping("/task/addPersonalTask")
    public void addPersonalTask(@RequestBody PersonalTask personalTask) {
        try {
            taskService.addPersonalTask(personalTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/task/addTeamTask")
    public void addTeamTask(@RequestBody TeamTask teamTask) {
        try {
            taskService.addTeamTask(teamTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/task/deletePersonalTask")
    public void deletePersonalTask(@RequestBody PersonalTask personalTask) {
        try {
            taskService.deletePersonalTask(personalTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/task/deleteTeamTask")
    public void deleteTeamTask(@RequestBody TeamTask teamTask) {
        try {
            taskService.deleteTeamTask(teamTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/task/finishPersonalTask")
    public void finishPersonalTask(@RequestBody PersonalTask personalTask) {
        try {
            taskService.finishPersonalTask(personalTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/task/finishTeamTask")
    public void finishTeamTask(@RequestBody TeamTask teamTask) {
        try {
            taskService.finishTeamTask(teamTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
