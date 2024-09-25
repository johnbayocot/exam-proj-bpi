package com.example.project.exam.mapping;

import com.example.project.exam.domain.ProjectDto;
import com.example.project.exam.domain.Task;
import com.example.project.exam.domain.TaskDto;
import com.example.project.exam.domain.Project;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperClass {

    @Autowired
    private ModelMapper modelMapper;

    public Project toEntity(ProjectDto projectDto) { return modelMapper.map(projectDto, Project.class); }
    public TaskDto taskDto(Task task){ return modelMapper.map(task, TaskDto.class); }
    public ProjectDto projectDto(Project proj){
        return modelMapper.map(proj, ProjectDto.class);
    }
}
