package com.maisvida.jobusecase.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maisvida.jobusecase.model.Job;
import com.maisvida.jobusecase.service.JobService;

@Controller
@RestController
@RequestMapping(path = "/jobs")
public class JobController {

	@Autowired
	private JobService jobService;
	
	@GetMapping
	public List<Job> listar() {
		return jobService.getAllJobs();
	}
	
	@GetMapping("/{id}")
	public Optional<Job> jobForId(@PathVariable Long id) {
		return jobService.getJobForId(id);
	}
	
	@PostMapping
	public HttpStatus addJob(@RequestBody Job job) {
		return jobService.addJob(job);
	}
	
	@DeleteMapping
	public HttpStatus removeJob(@RequestBody Job job) {
		return jobService.removeJob(job);
	}
	
	@PutMapping
	public HttpStatus updateJob(@RequestBody Job job) {
		return jobService.updateJob(job);
	}
	
}
