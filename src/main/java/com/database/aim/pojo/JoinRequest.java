package com.database.aim.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "joinRequest")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class JoinRequest{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String requestTime;
	private int teamId;
	private boolean succeed;
	private RequestSource requestSource;
	private int userId;

	public void setRequestTime(String requestTime){
		this.requestTime = requestTime;
	}

	public String getRequestTime(){
		return requestTime;
	}

	public void setTeamId(int teamId){
		this.teamId = teamId;
	}

	public int getTeamId(){
		return teamId;
	}

	public void setSucceed(boolean succeed){
		this.succeed = succeed;
	}

	public boolean isSucceed(){
		return succeed;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setRequestSource(RequestSource requestSource){
		this.requestSource = requestSource;
	}

	public RequestSource getRequestSource(){
		return requestSource;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}
}
