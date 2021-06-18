package com.database.aim.dao;

import com.database.aim.pojo.FinishedTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface FinishedTaskDao extends JpaRepository<FinishedTask, Integer> {
    List<FinishedTask> findFinishedTasksByUserId(int userId);
    List<FinishedTask> findFinishedTasksByTaskId(int taskId);
    List<FinishedTask> findFinishedTasksByTaskIdAndFinishedAtAfter(int taskId, Timestamp finishedAt);
}
