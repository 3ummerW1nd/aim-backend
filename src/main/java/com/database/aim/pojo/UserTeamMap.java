package com.database.aim.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(UserTeamPK.class)
@Table(name = "userteammap")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class UserTeamMap{
	@Id
	private int teamId;
	private Authority authority;
	@Id
	private int userId;
	private String username;
	private String teamName;
	private Timestamp joinedAt;
	public int getTeamId(){
		return teamId;
	}

	public Authority getAuthority(){
		return authority;
	}

	public int getUserId(){
		return userId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Timestamp getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(Timestamp joinedAt) {
		this.joinedAt = joinedAt;
	}
}
