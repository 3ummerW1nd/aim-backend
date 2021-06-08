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
    public void addTask(Task task) {
        taskDao.save(task);
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
