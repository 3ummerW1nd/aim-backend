package com.database.aim.dao;

import com.database.aim.pojo.FinishedTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinishedTaskDao extends JpaRepository<FinishedTask, Integer> {
    List<FinishedTask> findFinishedTasksByTeamId(int teamId);
    List<FinishedTask> findFinishedTasksByUserId(int userId);

}
