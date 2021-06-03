package com.database.aim.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "finishedTask")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class FinishedTask{
	@Id
	private int taskId;
	private int teamId;
	private String name;
	private int userId;
	private Timestamp finishedAt;

	public void setTeamId(int teamId){
		this.teamId = teamId;
	}

	public int getTeamId(){
		return teamId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setTaskId(int taskId){
		this.taskId = taskId;
	}

	public int getTaskId(){
		return taskId;
	}

	public void setFinishedAt(Timestamp finishedAt){
		this.finishedAt = finishedAt;
	}

	public Timestamp getFinishedAt(){
		return finishedAt;
	}
}
