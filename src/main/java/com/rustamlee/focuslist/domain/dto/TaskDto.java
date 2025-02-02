package com.rustamlee.focuslist.domain.dto;

import com.rustamlee.focuslist.domain.entities.TaskPriority;
import com.rustamlee.focuslist.domain.entities.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
