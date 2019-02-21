package com.maisvida.jobusecase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maisvida.jobusecase.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	public List<Job> findByNameContaining(String name);
}
