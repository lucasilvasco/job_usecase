package com.maisvida.jobusecase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private boolean active;

    @ManyToOne(cascade = CascadeType.ALL)
    private Job parentJob;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

}
