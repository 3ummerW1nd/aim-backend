package com.database.aim.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "personalTask")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class PersonalTask {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private PeriodType period;
	private int teamTaskId;
	private String name;
	private boolean isPrivate;
	private Timestamp deadline;
	private int userId;

	public void setPeriod(PeriodType period){
		this.period = period;
	}

	public PeriodType getPeriod(){
		return period;
	}

	public int getTeamTaskId() {
		return teamTaskId;
	}

	public void setTeamTaskId(int teamTaskId) {
		this.teamTaskId = teamTaskId;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean aPrivate) {
		isPrivate = aPrivate;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setIsPrivate(boolean isPrivate){
		this.isPrivate = isPrivate;
	}

	public boolean isIsPrivate(){
		return isPrivate;
	}

	public void setDeadline(Timestamp deadline){
		this.deadline = deadline;
	}

	public Timestamp getDeadline(){
		return deadline;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}
}
