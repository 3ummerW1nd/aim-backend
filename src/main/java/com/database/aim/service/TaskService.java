package com.database.aim.service;

import com.database.aim.dao.TaskDao;
import com.database.aim.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskDao taskDao;
    public boolean addTask(Task task) {
        if(task.getTeamId() == -1) {
            taskDao.save(task);
            return true;
        } else {
            TeamService teamService = new TeamService();
            if(teamService.isCreator(task.getTeamId(), task.getUserId())) {
                taskDao.save(task);
                return true;
            } else if(teamService.isManager(task.getTeamId(), task.getUserId())) {
                taskDao.save(task);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(Task task) {
        if(task.getTeamId() == -1) {
            taskDao.delete(task);
            return true;
        } else {
            TeamService teamService = new TeamService();
            if(teamService.isCreator(task.getTeamId(), task.getUserId())) {
                taskDao.delete(task);
                return true;
            } else if(teamService.isManager(task.getTeamId(), task.getUserId())) {
                taskDao.delete(task);
                return true;
            }
        }
        return false;
    }

    public Task getTaskById(int id) {
        return taskDao.findTaskById(id);
    }

    public List<Task> getTasksByUserId(int userId) {
        return taskDao.findTasksByUserId(userId);
    }

    public List<Task> getTasksByTeamId(int teamId) {
        return taskDao.findTasksByTeamId(teamId);
    }
}
