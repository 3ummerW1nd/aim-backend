package com.database.aim.pojo;

import java.io.Serializable;

public class UserTeamPK implements Serializable{
    private static final long serialVersionUID = -1158141803682305656L;
    private int userId;
    private int teamId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
