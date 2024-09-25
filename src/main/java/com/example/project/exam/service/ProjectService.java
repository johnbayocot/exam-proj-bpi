package com.example.project.exam.service;

import com.example.project.exam.domain.ProjectDto;
import com.example.project.exam.domain.Project;
import com.example.project.exam.mapping.ResponseDefault;

import java.util.List;

public interface ProjectService {

    ResponseDefault createProject(ProjectDto projectDto);

    List<ProjectDto> getAllProjects();

    Project getProject(Long id);

    ProjectDto taskSchedule(Long id);


}
