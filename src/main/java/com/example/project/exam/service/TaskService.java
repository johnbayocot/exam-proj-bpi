package com.example.project.exam.service;

import com.example.project.exam.domain.Task;
import com.example.project.exam.domain.TaskDto;
import com.example.project.exam.mapping.ResponseDefault;

import java.util.List;

public interface TaskService {

    ResponseDefault createTask(Task task);

    List<TaskDto> getAllTask();

    Task getTask(Long id);
}
