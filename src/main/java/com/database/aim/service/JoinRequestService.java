package com.database.aim.service;

import com.database.aim.dao.JoinRequestDao;
import com.database.aim.pojo.JoinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinRequestService {
    @Autowired
    JoinRequestDao joinRequestDao;
    public boolean addJoinRequest(JoinRequest joinRequest) {
        TeamService teamService = new TeamService();
        if(teamService.getTeamById(joinRequest.getTeamId()) == null) {
            return false;
        }
        joinRequestDao.save(joinRequest);
        return true;
    }

    List<JoinRequest> getAllReadRequestByTeamId(int teamId) {
        return joinRequestDao.findReadJoinRequestsByTeamId(teamId);
    }

    List<JoinRequest> getAllReadRequestByUserId(int userId) {
        return joinRequestDao.findReadJoinRequestsByUserId(userId);
    }

    List<JoinRequest> getAllUnreadRequestByTeamId(int teamId) {
        return joinRequestDao.findUnreadJoinRequestsByTeamId(teamId);
    }

    List<JoinRequest> getAllUnreadRequestByUserId(int userId) {
        return joinRequestDao.findUnreadJoinRequestsByUserId(userId);
    }
}
