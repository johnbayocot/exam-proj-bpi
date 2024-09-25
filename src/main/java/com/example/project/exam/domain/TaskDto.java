package com.example.project.exam.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDto {

    private Long id;
    private String taskName;
    private String taskDescription;
    private int duration;
    private LocalDate startDate;
    private LocalDate endDate;

}
