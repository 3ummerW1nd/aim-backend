package com.database.aim.dao;

import com.database.aim.pojo.PeriodType;
import com.database.aim.pojo.PersonalTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalTaskDao extends JpaRepository<PersonalTask, Integer> {
    PersonalTask findPersonalTaskById(int Id);
    List<PersonalTask> findPersonalTasksByTeamTaskId(int teamTaskId);
    List<PersonalTask> findPersonalTasksByUserIdAndPeriod(int userId, PeriodType periodType);
    List<PersonalTask> findPersonalTasksByUserIdAndPeriodNot(int userId, PeriodType periodType);
    List<PersonalTask> findPersonalTasksByUserId(int userId);
    PersonalTask findPersonalTaskByTeamTaskIdAndUserId(int teamTaskId, int userId);
}
