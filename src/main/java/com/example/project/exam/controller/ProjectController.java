package com.example.project.exam.controller;

import com.example.project.exam.domain.ProjectDto;
import com.example.project.exam.mapping.ResponseDefault;
import com.example.project.exam.service.ProjectService;
import com.example.project.exam.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(path = "api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDefault> createProject(@RequestBody ProjectDto projectDto){
        ResponseDefault resp = projectService.createProject(projectDto);
        if(!resp.getResponseCode().equalsIgnoreCase("00")){
            resp.setDescription(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
        return ResponseEntity.ok(resp);
    }

    @GetMapping(path = "/get-all-projects")
    public ResponseEntity<List<ProjectDto>> getAllProjects(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id){
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @GetMapping(path = "/schedule/{id}")
    public ResponseEntity<ProjectDto> taskSchedule(@PathVariable Long id){
        return ResponseEntity.ok(projectService.taskSchedule(id));
    }
}
