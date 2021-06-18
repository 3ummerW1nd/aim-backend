package com.database.aim.controller;

import com.database.aim.pojo.JoinRequest;
import com.database.aim.service.JoinRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JoinRequestController {
    @Autowired
    JoinRequestService joinRequestService;

    @PostMapping("/request/addJoinRequest")
    public void addJoinRequest(@RequestBody  JoinRequest joinRequest) {
        try {
            joinRequestService.addJoinRequest(joinRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("request/getAllReadRequestByUserId")
    public List<JoinRequest> getAllReadRequestByUserId(@RequestBody  int userId) {
        List<JoinRequest> joinRequests = new ArrayList<>();
        try {
            joinRequests = joinRequestService.getAllReadRequestByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joinRequests;
    }

    @PostMapping("request/getAllUnreadRequestByUserId")
    public List<JoinRequest> getAllUnreadRequestByUserId(@RequestBody  int userId) {
        List<JoinRequest> joinRequests = new ArrayList<>();
        try {
            joinRequests = joinRequestService.getAllUnreadRequestByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joinRequests;
    }

    @PostMapping("request/getAllReadRequestByTeamId")
    public List<JoinRequest> getAllReadRequestByTeamId(@RequestBody int teamId) {
        List<JoinRequest> joinRequests = new ArrayList<>();
        try {
            joinRequests = joinRequestService.getAllReadRequestByTeamId(teamId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joinRequests;
    }

    @PostMapping("request/getAllUnreadRequestByTeamId")
    public List<JoinRequest> getAllUnreadRequestByTeamId(@RequestBody int teamId) {
        List<JoinRequest> joinRequests = new ArrayList<>();
        try {
            joinRequests = joinRequestService.getAllReadRequestByUserId(teamId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joinRequests;
    }

}
