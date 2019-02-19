package com.maisvida.jobusecase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maisvida.jobusecase.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
