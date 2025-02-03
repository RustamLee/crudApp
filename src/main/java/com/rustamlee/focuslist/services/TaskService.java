package com.rustamlee.focuslist.services;

import com.rustamlee.focuslist.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
The TaskService interface defines the essential business logic and operations
 related to tasks within the application. Just like TaskListService,
  it abstracts the core actions needed to manage tasks
 */

public interface TaskService {

    List<Task> listTasks(UUID taskListId);

    Task createTask(UUID taskListId, Task task);

    Optional<Task> getTask(UUID taskListId, UUID taskId);

    Task updateTask(UUID taskListId, UUID taskId, Task task);

    void deleteTask(UUID taskListId, UUID taskId);
}
