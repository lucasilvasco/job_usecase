package com.maisvida.jobusecase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
