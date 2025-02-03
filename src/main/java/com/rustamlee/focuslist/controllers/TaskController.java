package com.rustamlee.focuslist.controllers;

import com.rustamlee.focuslist.domain.dto.TaskDto;
import com.rustamlee.focuslist.domain.entities.Task;
import com.rustamlee.focuslist.mappers.TaskMapper;
import com.rustamlee.focuslist.services.TaskService;
import com.rustamlee.focuslist.services.impl.TaskServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// This controller handles task-related operations within a specific task list.
// It provides endpoints for CRUD operations on tasks.

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TaskController {

    private final TaskServiceImp taskService;
    private final TaskMapper taskMapper;

    // Constructor injection to provide TaskServiceImp and TaskMapper dependencies
    public TaskController(TaskServiceImp taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    // Retrieves all tasks associated with a specific task list.
    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        return taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    // Creates a new task in a specific task list and returns the created task as a DTO.
    @PostMapping
    public TaskDto createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto) {
        Task createdTask = taskService.createTask(
                taskListId,
                taskMapper.fromDto(taskDto)
        );
        return taskMapper.toDto(createdTask);
    }

    // Retrieves a specific task by its ID within a given task list.
    @GetMapping(path = "/{task_id}")
    public Optional<TaskDto> getTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId
    ) {
        return taskService.getTask(taskListId, taskId).map(taskMapper::toDto);
    }

    // Updates an existing task in a task list with new data and returns the updated task as a DTO.
    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId,
            @RequestBody TaskDto taskDto
    ) {
        Task updatedTask = taskService.updateTask(
                taskListId,
                taskId,
                taskMapper.fromDto(taskDto));
        return taskMapper.toDto(updatedTask);
    }

    // Deletes a specific task from a task list by its ID.
    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(
        @PathVariable("task_list_id") UUID taskListId,
        @PathVariable("task_id") UUID taskId
    ){
        taskService.deleteTask(taskListId,taskId);
    }

}
