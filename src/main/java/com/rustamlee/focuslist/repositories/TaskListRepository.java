package com.rustamlee.focuslist.repositories;


import com.rustamlee.focuslist.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

 // Repository interface for accessing TaskList entities in the database.
// Extending JpaRepository provides CRUD operations for TaskList

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> {

}

// No need to write methods for basic CRUD operations (save, find, delete, etc.).
// JpaRepository provides all these out-of-the-box.