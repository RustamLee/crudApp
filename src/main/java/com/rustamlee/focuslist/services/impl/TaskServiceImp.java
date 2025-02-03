package com.rustamlee.focuslist.services.impl;

import com.rustamlee.focuslist.domain.entities.Task;
import com.rustamlee.focuslist.domain.entities.TaskList;
import com.rustamlee.focuslist.domain.entities.TaskPriority;
import com.rustamlee.focuslist.domain.entities.TaskStatus;
import com.rustamlee.focuslist.repositories.TaskListRepository;
import com.rustamlee.focuslist.repositories.TaskRepository;
import com.rustamlee.focuslist.services.TaskListService;
import com.rustamlee.focuslist.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;


    // Constructor to inject dependencies
    public TaskServiceImp(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    // Lists all tasks associated with a specific Task List ID
    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    // Creates a new task in the specified task list
    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task already has Id! ");
        }
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task must have a title!");
        }
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;
        LocalDateTime now = LocalDateTime.now();
        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("Invlalid task list id! "));
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );
        return taskRepository.save(taskToSave);
    }

    // Retrieves a task by its Task List ID and Task ID
    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    // Updates an existing task in the task list
    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("Task must have an id! ");
        }
        if (!Objects.equals(taskId, task.getId())) {
            throw new IllegalArgumentException("Task Ids don't match!");
        }
        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Task must have priority!");
        }
        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Task must have a status! ");
        }
        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found! "));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());
        return taskRepository.save(existingTask);
    }

    // Service for managing task lists
    @Service
    public static class TaskListServiceImpl implements TaskListService {

        private final TaskListRepository taskListRepository;

        public TaskListServiceImpl(TaskListRepository taskListRepository) {
            this.taskListRepository = taskListRepository;
        }

        @Override
        public List<TaskList> listTasklists() {
            return taskListRepository.findAll();
        }

        @Override
        public TaskList createTaskList(TaskList taskList) {
            if(taskList.getId() != null){
                throw new IllegalArgumentException("Task list already has an ID! ");
            }
            if(taskList.getTitle() == null || taskList.getTitle().isBlank()){
                throw new IllegalArgumentException("Task list title must be present! ");
            }
            LocalDateTime now = LocalDateTime.now();
            return taskListRepository.save(new TaskList(
                    null,
                    taskList.getTitle(),
                    taskList.getDescription(),
                    null,
                    now,
                    now
            ));
        }

        @Override
        public Optional<TaskList> getTaskList(UUID id) {
            return taskListRepository.findById(id);
        }

        @Transactional
        @Override
        public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
            if(taskList.getId() == null){
                throw new IllegalArgumentException("Task list must have an Id");
            }
            if(!Objects.equals(taskList.getId(), taskListId)){
                throw new IllegalArgumentException("You can't change task list with this Id");
            }
            TaskList existenTaskList =  taskListRepository.findById(taskListId).orElseThrow(() ->
                    new IllegalArgumentException("Task List not found"));
            existenTaskList.setTitle(taskList.getTitle());
            existenTaskList.setDescription(taskList.getDescription());
            existenTaskList.setUpdated(LocalDateTime.now());
            return taskListRepository.save(existenTaskList);
        }

        @Override
        public void deleteTaskList(UUID taskListId) {
            taskListRepository.deleteById(taskListId);
        }
    }

    // Deletes a task from the task list

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }
}








