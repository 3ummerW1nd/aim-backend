package com.database.aim.dao;

import com.database.aim.pojo.PeriodType;
import com.database.aim.pojo.PersonalTask;
import com.database.aim.pojo.TeamTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamTaskDao extends JpaRepository<TeamTask, Integer> {
    List<TeamTask> findTeamTasksByTeamId(int teamId);
    TeamTask findTeamTaskById(int id);
    List<TeamTask> findTeamTasksByTeamIdAndPeriod(int teamId, PeriodType period);
    List<TeamTask> findTeamTasksByTeamIdAndPeriodNot(int teamId, PeriodType period);
    List<TeamTask> findTeamTasksByTeamIdAndIsPrivateAndPeriod(int userId, boolean isPrivate, PeriodType periodType);
    List<TeamTask> findTeamTasksByTeamIdAndIsPrivateAndPeriodNot(int userId, boolean isPrivate, PeriodType periodType);
}
