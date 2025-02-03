package com.rustamlee.focuslist.controllers;

import com.rustamlee.focuslist.domain.dto.TaskListDto;
import com.rustamlee.focuslist.domain.entities.TaskList;
import com.rustamlee.focuslist.mappers.TaskListMapper;
import com.rustamlee.focuslist.services.TaskListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// This controller manages task list operations.
// It provides endpoints to create, read, update, and delete task lists.

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    // Constructor injection for TaskListService and TaskListMapper dependencies
    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    // Retrieves all task lists and maps them to DTOs.
    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTasklists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    // Creates a new task list and returns it as a DTO.
    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);
    }

    // Retrieves a task list by its ID and maps it to a DTO if found.
    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId) {
        return taskListService.getTaskList(taskListId).map(taskListMapper::toDto);
    }

    // Updates an existing task list with new data and returns the updated task list as a DTO.
    @PutMapping(path = "/{task_list_id}")
    public TaskListDto updateTaskList(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskListDto taskListDto
    ) {
        TaskList updatedTaskList = taskListService.updateTaskList(taskListId,
                taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(updatedTaskList);
    }

    // Deletes a task list by its ID.
    @DeleteMapping(path = "/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id")UUID taskListId){
        taskListService.deleteTaskList(taskListId);
    }

}
