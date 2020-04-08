package com.maisvida.jobusecase.service;

import com.maisvida.jobusecase.model.Job;
import com.maisvida.jobusecase.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobService {

    private JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getJobForQuery(String query) {
        return jobRepository.findByNameContaining(query);
    }

    public Job getJobForId(Long id) {
    	return jobRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Job addJob(Job job) {
		Job newJob = Job.builder()
                .name(job.getName())
                .parentJob(getParentJob(job))
                .tasks(job.getTasks())
                .active(job.isActive())
                .build();

        return jobRepository.save(newJob);
    }


	public HttpStatus removeJob(Job job) {
        try {
            jobRepository.delete(job);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public Job updateJob(Job job) {
        Job jobToUpdate = getJobForId(job.getId());

		verificarAutoDependencia(job);

		jobToUpdate.setName(job.getName());
        jobToUpdate.setTasks(job.getTasks());
        jobToUpdate.setActive(job.isActive());
        jobToUpdate.setParentJob(getParentJob(job));

        return jobRepository.save(jobToUpdate);
    }

	private void verificarAutoDependencia(Job job) {
		if (job.getParentJob() != null && job.getId() == job.getParentJob().getId()) {
			throw new RuntimeException("Auto dependencia!");
		}
	}

	private Job getParentJob(Job job) {
		return jobRepository.findById(job.getParentJob().getId()).orElse(null);
	}
}
