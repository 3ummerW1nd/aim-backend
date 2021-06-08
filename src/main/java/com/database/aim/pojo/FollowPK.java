package com.database.aim.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class FollowPK implements Serializable {
    private int userId;
    private int followingId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }
}
