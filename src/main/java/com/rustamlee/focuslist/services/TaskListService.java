package com.rustamlee.focuslist.services;

import com.rustamlee.focuslist.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/*
The TaskListService interface defines the core business logic for
managing task lists in the application. It serves as a contract
 for the implementation class, which will contain the actual
  logic for interacting with the database or other storage
  systems.
 */

public interface TaskListService {

    List<TaskList> listTasklists();
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskList(UUID id);
    TaskList updateTaskList(UUID taskListId, TaskList taskList);
    void deleteTaskList(UUID taskListId);

}
