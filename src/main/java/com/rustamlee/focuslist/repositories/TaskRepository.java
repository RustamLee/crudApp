package com.rustamlee.focuslist.repositories;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rustamlee.focuslist.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

 // Repository interface for accessing Task entities in the database.
// Provides CRUD operations as well as custom query methods.

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByTaskListId(UUID taskListId);
    Optional<Task> findByTaskListIdAndId (UUID taskListId, UUID id);
    void deleteByTaskListIdAndId(UUID taskListId, UUID id);
}
