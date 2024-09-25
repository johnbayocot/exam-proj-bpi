package com.example.project.exam.service;

import com.example.project.exam.domain.Project;
import com.example.project.exam.domain.Task;
import com.example.project.exam.exception.TaskException;
import com.example.project.exam.repository.TaskRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private TaskRepository taskRepository;

    @SneakyThrows
    public void calculateTaskSchedule(Project project) {
        log.info("inside calculateTaskSchedule for project {}", project.getProjectName());

        if(project.getTasks().isEmpty()) {
            throw new TaskException("Task must not be empty");
        }

        Map<Task, LocalDate> taskStartDate = new HashMap<>();

        for (Task task : project.getTasks()) {

            if(taskStartDate.containsKey(task)){
                updateTaskDate(task, taskStartDate.get(task));
                return;
            }

            LocalDate startDate = LocalDate.now();

            startDate = (task.getDependencies() == null || task.getDependencies().isEmpty())
                    ? startDate
                    : endDateLatest(task, taskStartDate);
            updateTaskDate(task, startDate);

            taskStartDate.put(task, startDate);
            taskRepository.save(task);
        }
    }

    private void updateTaskDate(Task task, LocalDate startDate){
        log.info("inside updateTaskDate for task {}", task.getTaskName());

        task.setStartDate(startDate);
        task.setEndDate(startDate.plusDays(task.getDuration()));
    }

    private LocalDate endDateLatest(Task task, Map<Task, LocalDate> taskStartDate) {
        log.info("inside endDateLatest for task {}", task.getTaskName());

        LocalDate latestEndDate = LocalDate.now();
        for (Task dependency : task.getDependencies()) {
            if (dependency.getEndDate() == null) {

                if(taskStartDate.containsKey(task)){
                    updateTaskDate(task, taskStartDate.get(task));
                }

                LocalDate startDate = LocalDate.now();

                startDate = (task.getDependencies() == null || task.getDependencies().isEmpty())
                        ? startDate
                        : endDateLatest(task, taskStartDate);
                updateTaskDate(task, startDate);

                taskStartDate.put(task, startDate);
                taskRepository.save(task);
            }
            latestEndDate = dependency.getEndDate().isAfter(latestEndDate) ? dependency.getEndDate() : latestEndDate;
        }
        return latestEndDate;
    }

}
