package com.rustamlee.focuslist.services.impl;

import com.rustamlee.focuslist.domain.dto.TaskListDto;
import com.rustamlee.focuslist.domain.entities.Task;
import com.rustamlee.focuslist.domain.entities.TaskList;
import com.rustamlee.focuslist.domain.entities.TaskStatus;
import com.rustamlee.focuslist.mappers.TaskListMapper;
import com.rustamlee.focuslist.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;
import java.util.Optional;
@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    // Constructor injection of TaskMapper dependency
    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }


    // Method for converting TaskListDto to TaskList (Entity)
    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    // Method for converting TaskList (Entity) to TaskListDto
    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList()).orElse(null)
        );
    }

    // Method for calculating the progress of the task list
    private Double calculateTaskListProgress(List<Task> tasks) {
        if (null == tasks) {
            return null;
        }
        long closedTaskCount = tasks.stream().filter(task -> TaskStatus.CLOSED == task.getStatus()).count();
        return (double) closedTaskCount / tasks.size();
    }
}
