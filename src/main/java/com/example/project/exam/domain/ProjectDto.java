package com.example.project.exam.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectDto {
    private Long id;
    private String projectName;
    private String projectDesc;
    private List<Task> tasks;
}
