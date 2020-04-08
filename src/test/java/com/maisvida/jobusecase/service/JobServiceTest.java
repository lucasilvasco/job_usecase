package com.maisvida.jobusecase.service;

import com.maisvida.jobusecase.model.Job;
import com.maisvida.jobusecase.repository.JobRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class JobServiceTest {

    JobService jobService;

    @MockBean
    JobRepository jobRepositoryMock;

    @Before
    public void setUp() {
        jobService = new JobService(jobRepositoryMock);
    }

    @Test
    public void deveBuscarTodosOsJobs() {
        List<Job> jobList = Collections.singletonList(Job.builder().build());
        when(jobRepositoryMock.findAll()).thenReturn(jobList);

        List<Job> allJobs = jobService.getAllJobs();

        verify(jobRepositoryMock, times(1)).findAll();
        assertThat(allJobs.size()).isEqualTo(1);
    }

    @Test
    public void deveBuscarJobsPelaQuery() {
        List<Job> jobList = Collections.singletonList(Job.builder().build());
        when(jobRepositoryMock.findByNameContaining("query")).thenReturn(jobList);

        List<Job> allJobsForQuery = jobService.getJobForQuery("query");

        verify(jobRepositoryMock, times(1)).findByNameContaining(anyString());
        assertThat(allJobsForQuery.size()).isEqualTo(1);
    }

    @Test
    public void deveAdicionarJob() {
        Job jobRequest = Job.builder().parentJob(Job.builder().id(123).build()).build();

        when(jobRepositoryMock.save(any())).thenReturn(Job.builder().id(1234).parentJob(Job.builder().id(123).build()).build());
        when(jobRepositoryMock.findById(anyLong())).thenReturn(Optional.of(Job.builder().build()));

        Job job = jobService.addJob(jobRequest);

        verify(jobRepositoryMock, times(1)).save(any());
        assertThat(job.getId()).isEqualTo(1234);
    }

    @Test
    public void deveAtualizarJob() {
        Job jobRequest = Job.builder().id(1234).parentJob(Job.builder().id(123).build()).build();

        when(jobRepositoryMock.save(any())).thenReturn(Job.builder().id(1234).parentJob(Job.builder().id(123).build()).build());
        when(jobRepositoryMock.findById(anyLong())).thenReturn(Optional.of(Job.builder().build()));

        Job job = jobService.updateJob(jobRequest);

        verify(jobRepositoryMock, times(1)).save(any());
        assertThat(job.getId()).isEqualTo(1234);
    }

    @Test(expected = RuntimeException.class)
    public void deveLancarExcecaoAtualizarJob() {
        Job jobRequest = Job.builder().id(1234).parentJob(Job.builder().id(1234).build()).build();
        jobService.updateJob(jobRequest);
    }
}
