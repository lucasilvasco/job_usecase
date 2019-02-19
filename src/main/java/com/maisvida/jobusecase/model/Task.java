package com.maisvida.jobusecase.model;

import java.time.LocalDate;

import javax.persistence.*;


@Entity
@Table
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private int weight;
	private boolean completed;
	private LocalDate createdAt;
	
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
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}	
}
