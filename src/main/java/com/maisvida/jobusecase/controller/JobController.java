package com.maisvida.jobusecase.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maisvida.jobusecase.model.Job;
import com.maisvida.jobusecase.service.JobService;

@Controller
@RestController
@RequestMapping(path = "/jobs")
public class JobController {

	@Autowired
	private JobService jobService;
	
	@GetMapping(params = "q")
	@CrossOrigin(origins="*")
	public List<Job> listar(@RequestParam("q") String query) {
		if(query.equals("")) {
			return jobService.getAllJobs();
		}
		return jobService.getJobForQuery(query);
	}
	
	@GetMapping("/id/{id}")
	@CrossOrigin(origins="*")
	public Optional<Job> jobForId(@PathVariable Long id) {
		return jobService.getJobForId(id);
	}
	
	@PostMapping
	@CrossOrigin(origins="*")
	public HttpStatus addJob(@RequestBody Job job) {
		return jobService.addJob(job);
	}
	
	@DeleteMapping
	@CrossOrigin(origins="*")
	public HttpStatus removeJob(@RequestBody Job job) {
		return jobService.removeJob(job);
	}
	
	@PutMapping
	@CrossOrigin(origins="*")
	public HttpStatus updateJob(@RequestBody Job job) {
		return jobService.updateJob(job);
	}
	
}
