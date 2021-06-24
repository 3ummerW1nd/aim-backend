package com.database.aim.service;

import com.database.aim.dao.FinishedTaskDao;
import com.database.aim.dao.PersonalTaskDao;
import com.database.aim.dao.TaskRecordDao;
import com.database.aim.dao.TeamTaskDao;
import com.database.aim.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TeamTaskDao teamTaskDao;
    @Autowired
    PersonalTaskDao personalTaskDao;
    @Autowired
    TaskRecordDao taskRecordDao;
    @Autowired
    FinishedTaskDao finishedTaskDao;
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;
    //UserService userService = new UserService();

    public void addPersonalTask(PersonalTask personalTask) {
        personalTaskDao.save(personalTask);
    }

    public void deletePersonalTask(int personalTaskId) {
        personalTaskDao.deleteById(personalTaskId);
    }

    public Timestamp addDeadline(Timestamp deadline, PeriodType period) {
        long millis = deadline.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        if(period == PeriodType.daily)
            calendar.add(Calendar.DATE , 1);
        else if(period == PeriodType.weekly)
            calendar.add(Calendar.DATE, 7);
        else if(period == PeriodType.monthly)
            calendar.add(Calendar.MONTH, 1);
        else if(period == PeriodType.yearly)
            calendar.add(Calendar.YEAR, 1);
        deadline.setTime(calendar.getTimeInMillis());
        return deadline;
    }

    public void finishPersonalTask(int personalTaskId) {
        PersonalTask personalTask = personalTaskDao.findPersonalTaskById(personalTaskId);
        PeriodType period = personalTask.getPeriod();
        if(period == PeriodType.once)
            deletePersonalTask(personalTaskId);
        else {
            Timestamp deadline = personalTask.getDeadline();
            deadline = addDeadline(deadline, period);
            personalTask.setDeadline(deadline);
            personalTaskDao.save(personalTask);
        }
        int teamTaskId = personalTask.getTeamTaskId();
        if(teamTaskId != -1) {
            TeamTask teamTask = teamTaskDao.findTeamTaskById(teamTaskId);
            teamTask.setCompleteNum(teamTask.getCompleteNum() + 1);
            if(teamTask.getCompleteNum() == teamService.getMemberNumByTeamId(teamTask.getTeamId()))
                finishTeamTask(teamTask);
            else
                teamTaskDao.save(teamTask);
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        FinishedTask finishedTask = new FinishedTask();
        finishedTask.setTaskId(personalTask.getId());
        finishedTask.setUserId(personalTask.getUserId());
        finishedTask.setName(personalTask.getName());
        finishedTask.setTeamId(-1);
        finishedTask.setFinishedAt(timestamp);
        finishedTaskDao.save(finishedTask);
        long millisSeconds = System.currentTimeMillis();
        Date date = new Date(millisSeconds);
        TaskRecord taskRecord = taskRecordDao.findTaskRecordByUserIdAndFinishedAt(personalTask.getUserId(), date);
        if(taskRecord != null) {
            taskRecord.setAmount(taskRecord.getAmount() + 1);
        } else {
            taskRecord = new TaskRecord();
            taskRecord.setAmount(1);
            taskRecord.setFinishedAt(date);
            taskRecord.setTeamId(-1);
            taskRecord.setUserId(personalTask.getUserId());
        }
        taskRecordDao.save(taskRecord);
    }

    public boolean addTeamTask(TeamTask teamTask) {
        int teamId = teamTask.getTeamId();
        int userId = teamTask.getUserId();
        if(teamService.isCreator(teamId, userId) || teamService.isManager(teamId, userId)) {
            teamTaskDao.save(teamTask);
            List<BriefUser> users = teamService.getUsersByTeamId(teamId);
            for(BriefUser it : users) {
                addPersonalTaskByTeamTask(teamTask, it.getId());
            }
            return true;
        }
        return false;
    }

    public boolean deleteTeamTask(int teamTaskId) {
        TeamTask teamTask = teamTaskDao.findTeamTaskById(teamTaskId);
        int teamId = teamTask.getTeamId();
        int userId = teamTask.getUserId();
        if(teamService.isCreator(teamId, userId) || teamService.isManager(teamId, userId)) {
            teamTaskDao.delete(teamTask);
            List<PersonalTask> personalTasks = getAllPersonalTasksByteamTaskId(teamTask.getId());
            for(PersonalTask it : personalTasks) {
                personalTaskDao.delete(it);
            }
            return true;
        }
        return false;
    }

    public void finishTeamTask(TeamTask teamTask) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        teamTaskDao.delete(teamTask);
        FinishedTask finishedTask = new FinishedTask();
        finishedTask.setTeamId(teamTask.getTeamId());
        finishedTask.setName(teamTask.getName());
        finishedTask.setFinishedAt(timestamp);
        finishedTask.setUserId(-1);
        finishedTask.setTaskId(teamTask.getId());
        finishedTaskDao.save(finishedTask);
        long millisSeconds = System.currentTimeMillis();
        Date date = new Date(millisSeconds);
        TaskRecord taskRecord = taskRecordDao.findTaskRecordByTeamIdAndFinishedAt(teamTask.getTeamId(), date);
        if(taskRecord != null) {
            taskRecord.setAmount(taskRecord.getAmount() + 1);
        } else {
            taskRecord = new TaskRecord();
            taskRecord.setAmount(1);
            taskRecord.setFinishedAt(date);
            taskRecord.setTeamId(teamTask.getTeamId());
            taskRecord.setUserId(-1);
        }
        taskRecordDao.save(taskRecord);
    }

    public List<PersonalTask> initUserPageOnceTasks(int userId) {
        return personalTaskDao.findPersonalTasksByUserIdAndPeriod(userId, PeriodType.once);
    }

    public List<PersonalTask> initUserPagePeriodicTasks(int userId) {
        return personalTaskDao.findPersonalTasksByUserIdAndPeriodNot(userId, PeriodType.once);
    }

    public List<TeamTask> initTeamPageOnceTasks(int userId, int teamId) {
        List<Integer> teamIds= userService.getTeamIdsByUserId(userId);
        for(int it : teamIds) {
            if(it == teamId)
                return teamTaskDao.findTeamTasksByTeamIdAndPeriod(teamId, PeriodType.once);
        }
        return teamTaskDao.findTeamTasksByTeamIdAndIsPrivateAndPeriod(teamId, false, PeriodType.once);
    }

    public List<TeamTask> initTeamPagePeriodicTasks(int userId, int teamId) {
        List<Integer> teamIds= userService.getTeamIdsByUserId(userId);
        for(int it : teamIds) {
            if(it == teamId)
                return teamTaskDao.findTeamTasksByTeamIdAndPeriodNot(teamId, PeriodType.once);
        }
        return teamTaskDao.findTeamTasksByTeamIdAndIsPrivateAndPeriodNot(teamId, false, PeriodType.once);
    }

    public List<TeamTask> getAllTeamTasksByTeamId(int teamId) {
        return teamTaskDao.findTeamTasksByTeamId(teamId);
    }

    public List<PersonalTask> getAllPersonalTasksByUserId(int userId) {
        return personalTaskDao.findPersonalTasksByUserId(userId);
    }

    public void addPersonalTaskByTeamTask(TeamTask teamTask, int userId) {
        int teamTaskId = teamTask.getId();
        Timestamp deadline = teamTask.getDeadline();
        boolean isPrivate = teamTask.isIsPrivate();
        String name = teamTask.getName();
        PeriodType period = teamTask.getPeriod();
        PersonalTask personalTask = new PersonalTask();
        personalTask.setDeadline(deadline);
        personalTask.setIsPrivate(isPrivate);
        personalTask.setName(name);
        personalTask.setPeriod(period);
        personalTask.setTeamTaskId(teamTaskId);
        personalTask.setUserId(userId);
        personalTaskDao.save(personalTask);
    }

    public void removePersonalTaskByTeamTask(TeamTask teamTask, int userId) {
        int teamTaskId = teamTask.getId();
        PersonalTask personalTask = personalTaskDao.findPersonalTaskByTeamTaskIdAndUserId(teamTaskId, userId);
        personalTaskDao.delete(personalTask);
    }

    public List<PersonalTask> getAllPersonalTasksByteamTaskId(int teamTaskId) {
        return personalTaskDao.findPersonalTasksByTeamTaskId(teamTaskId);
    }

    public List<PersonalTask> visitOthersPageOnceTask(int userId) {
        return personalTaskDao.findPersonalTasksByUserIdAndIsPrivateAndPeriod(userId, false, PeriodType.once);
    }

    public List<PersonalTask> visitOthersPagePeriodicTask(int userId) {
        return personalTaskDao.findPersonalTasksByUserIdAndIsPrivateAndPeriodNot(userId, false, PeriodType.once);
    }

    public List<TaskRecord> getTaskRecordsById(int userId) {
        return taskRecordDao.findTaskRecordsByUserId(userId);
    }

}
