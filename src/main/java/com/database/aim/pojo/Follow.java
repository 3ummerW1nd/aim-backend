package com.database.aim.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "follow")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Follow{
	@Id
	private int userId;
	private Timestamp followedAt;
	private int followingId;

	public void setFollowedAt(Timestamp followedAt){
		this.followedAt = followedAt;
	}

	public Timestamp getFollowedAt(){
		return followedAt;
	}

	public void setFollowingId(int followingId){
		this.followingId = followingId;
	}

	public int getFollowingId(){
		return followingId;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}
}
