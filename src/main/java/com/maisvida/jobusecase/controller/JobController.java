package com.maisvida.jobusecase.controller;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @CrossOrigin(origins = "*")
    public List<Job> listar(@RequestParam("q") String query) {
        if (query.equals("")) {
            return jobService.getAllJobs();
        }
        return jobService.getJobForQuery(query);
    }

    @GetMapping("/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Job> jobForId(@PathVariable Long id) {
        Job jobForId = jobService.getJobForId(id);
        return ResponseEntity.ok(jobForId);
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<Job> addJob(@RequestBody Job job) {
        Job jobResponse = jobService.addJob(job);
        return ResponseEntity.ok(jobResponse);
    }

    @DeleteMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> removeJob(@RequestBody Job job) {
        jobService.removeJob(job);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<Job> updateJob(@RequestBody Job job) {
        Job jobResponse = jobService.updateJob(job);
        return ResponseEntity.ok(jobResponse);
    }

}
