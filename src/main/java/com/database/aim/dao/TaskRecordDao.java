package com.database.aim.dao;

import com.database.aim.pojo.TaskRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface TaskRecordDao extends JpaRepository<TaskRecord, Integer> {
    List<TaskRecord> findTaskRecordsByUserId(int userId);
    List<TaskRecord> findTaskRecordsByTeamId(int teamId);
    TaskRecord findTaskRecordByUserIdAndFinishedAt(int userId, Date date);
    TaskRecord findTaskRecordByTeamIdAndFinishedAt(int teamId, Date date);
}
