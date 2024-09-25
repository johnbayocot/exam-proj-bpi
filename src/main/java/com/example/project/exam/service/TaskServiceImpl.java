package com.example.project.exam.service;

import com.example.project.exam.domain.Task;
import com.example.project.exam.domain.TaskDto;
import com.example.project.exam.exception.TaskException;
import com.example.project.exam.mapping.MapperClass;
import com.example.project.exam.mapping.ResponseDefault;
import com.example.project.exam.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private MapperClass mapperClass;
    @Autowired
    private ResponseDefault responseDefault;

    @SneakyThrows
    @Transactional
    public ResponseDefault createTask(Task task) {
        log.info("inside createTask");

        List<Task> dependencies = task.getDependencies();

        if (dependencies != null && !dependencies.isEmpty()) {
            for (Task dependency : dependencies) {
                Optional<Task> taskItem = taskRepository.findById(dependency.getId());
                if (!taskItem.isPresent()) {
                    throw new TaskException("Dependent task not found");
                }
            }
            taskRepository.save(task);

        }
        responseDefault.setResponseCode("00");
        responseDefault.setDescription("Successful");
        return responseDefault;
    }

    public List<TaskDto> getAllTask(){
        log.info("inside getAllTask");

        List<Task> allTask = taskRepository.findAll();
        return allTask.stream().map(mapperClass::taskDto).collect(Collectors.toList());
    }

    @SneakyThrows
    public Task getTask(Long id){
        log.info("inside createTask");

        Optional<Task> task = taskRepository.findById(id);
        if(task.isEmpty()) {
            throw new TaskException("No task found");
        }

        return task.get();
    }
}
