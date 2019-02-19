package com.maisvida.jobusecase.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Job {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private boolean active;
	private Job parentJob;
	
	@ManyToMany
	private List<Task> tasks;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Job getParentJob() {
		return parentJob;
	}
	public void setParentJob(Job parentJob) {
		this.parentJob = parentJob;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}	
}
