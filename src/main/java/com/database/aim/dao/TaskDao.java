package com.database.aim.dao;

import com.database.aim.pojo.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDao extends JpaRepository<Task, Integer> {
    Task findTaskById(int Id);
    List<Task> findTasksByUserId(int userId);
    List<Task> findTasksByTeamId(int teamId);
}
