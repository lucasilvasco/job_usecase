package com.maisvida.jobusecase.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.maisvida.jobusecase.model.Job;
import com.maisvida.jobusecase.repository.JobRepository;

import net.bytebuddy.build.Plugin.Engine.Source.Empty;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;
	
	public List<Job> getAllJobs(){
		return jobRepository.findAll();
	}
	
	public List<Job> getJobForQuery(String query) {
		return jobRepository.findByNameContaining(query);
	}
	
	public Optional<Job> getJobForId(Long id) {
		return jobRepository.findById(id);
	}
	
	public HttpStatus addJob(Job job) {
		try {
			Job newJob = new Job();			
			
			if(jobRepository.existsById(job.getParentJob().getId())) {
				Job parentJob = jobRepository.getOne(job.getParentJob().getId());
				newJob.setParentJob(parentJob);
			} else {
				newJob.setParentJob(null);
			}
			
			newJob.setName(job.getName());
			newJob.setActive(job.isActive());
			newJob.setTasks(job.getTasks());
			
			jobRepository.save(newJob);
			return HttpStatus.OK;
		} catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	public HttpStatus removeJob(Job job) {
		try {
			jobRepository.delete(job);
			return HttpStatus.OK;
		} catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	public HttpStatus updateJob(Job job) {
		try {
			Job jobToUpdate = jobRepository.getOne(job.getId());

			if(job.getParentJob() != null && job.getId() == job.getParentJob().getId()) {
				throw new Exception("Auto dependencia!");
			}
			
			if(job.getParentJob() != null && jobRepository.existsById(job.getParentJob().getId())) {
				Job parentJob = jobRepository.getOne(job.getParentJob().getId());
				jobToUpdate.setParentJob(parentJob);
			} else {
				jobToUpdate.setParentJob(null);
			}
				
			jobToUpdate.setName(job.getName());
			jobToUpdate.setTasks(job.getTasks());
			jobToUpdate.setActive(job.isActive());
			
			jobRepository.save(jobToUpdate);
			
			return HttpStatus.OK;			
		} catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
}
