package com.database.aim.dao;

import com.database.aim.pojo.TaskRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRecordDao extends JpaRepository<TaskRecord, Integer> {
    List<TaskRecord> findTaskRecordsByUserId(int userId);
    List<TaskRecord> findTaskRecordsByTeamId(int teamId);
}
