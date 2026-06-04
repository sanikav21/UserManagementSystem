package com.example.UserManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private long id;
	
	private String title;
	
	private String description;
	
	@ManyToOne
	@JsonIgnore
	
	private UserEntity assignedUser;
	
	public Task()
	{}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserEntity getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(UserEntity assignedUser) {
		this.assignedUser = assignedUser;
	}
	
	
}
