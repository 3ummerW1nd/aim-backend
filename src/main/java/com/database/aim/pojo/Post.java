package com.database.aim.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Post{
	@Id
	private int Id;
	private int poster;
	private Timestamp createdAt;
	private String title;

	public void setCreatedAt(Timestamp createdAt){
		this.createdAt = createdAt;
	}

	public Timestamp getCreatedAt(){
		return createdAt;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setPoster(int poster){
		this.poster = poster;
	}

	public int getPoster(){
		return poster;
	}
}
