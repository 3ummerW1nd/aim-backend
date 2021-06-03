package com.database.aim.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "team")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Team{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private String name;
	private int memberNum;

	public void setDescription(String discription){
		this.description = discription;
	}

	public String getDescription(){
		return description;
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

	public void setMemberNum(int memberNum){
		this.memberNum = memberNum;
	}

	public int getMemberNum(){
		return memberNum;
	}
}
