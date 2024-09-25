package com.example.project.exam.controller;

import com.example.project.exam.domain.Task;
import com.example.project.exam.domain.TaskDto;
import com.example.project.exam.mapping.ResponseDefault;
import com.example.project.exam.service.TaskService;
import com.example.project.exam.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDefault> createTask(@RequestBody Task task){
        ResponseDefault resp = taskService.createTask(task);
        if(!resp.getResponseCode().equalsIgnoreCase("00")){
            resp.setDescription(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
        return ResponseEntity.ok(resp);
    }

    @GetMapping(path = "/get-all-tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTask(id));
    }

}
