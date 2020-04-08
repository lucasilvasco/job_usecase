package com.maisvida.jobusecase.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import javax.persistence.*;


@Entity
@Builder
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int weight;
    private boolean completed;
    private LocalDate createdAt;

}
