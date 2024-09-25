package com.example.project.exam.service;

import com.example.project.exam.domain.Project;
import com.example.project.exam.domain.ProjectDto;
import com.example.project.exam.domain.Task;
import com.example.project.exam.exception.TaskException;
import com.example.project.exam.mapping.MapperClass;
import com.example.project.exam.mapping.ResponseDefault;
import com.example.project.exam.repository.ProjectRepository;
import com.example.project.exam.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService{
     @Autowired
     private ProjectRepository projectRepository;
     @Autowired
     private TaskRepository taskRepository;
     @Autowired
     private ScheduleServiceImpl scheduleService;
     @Autowired
     private MapperClass mapperClass;
     @Autowired
     private ResponseDefault responseDefault;

     @Transactional
     public ResponseDefault createProject(ProjectDto projectDto){
        log.info("inside createProject");

        Project proj = mapperClass.toEntity(projectDto);

        projectRepository.save(proj);

        if(projectDto.getTasks() != null){
            for (Task task : projectDto.getTasks()) {
                task.setProject(proj);
            }

            proj.setTasks(projectDto.getTasks());
        }

         responseDefault.setResponseCode("00");
         responseDefault.setDescription("Successful");
         return responseDefault;
    }

    public List<ProjectDto> getAllProjects(){
        log.info("inside getAllProjects");

        List<Project> projectList = projectRepository.findAll();
        return projectList.stream()
                .map(mapperClass::projectDto)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public Project getProject(Long id){
        log.info("inside getProject");

        Optional<Project> proj = projectRepository.findById(id);
        if(proj.isEmpty()){
            throw new TaskException("Project not found");
        }

        log.info("Project exists {}", proj.get());

        return proj.get();
    }

    @SneakyThrows
    public ProjectDto taskSchedule(Long id) {
        log.info("inside taskSchedule for id {}", id);

        Optional<Project> proj = projectRepository.findById(id);
        if(proj.isEmpty()){
            throw new TaskException("Project not found");
        }

        scheduleService.calculateTaskSchedule(proj.get());

        return mapperClass.projectDto(proj.get());
    }
}
