package com.rustamlee.focuslist.domain.dto;

import com.rustamlee.focuslist.domain.entities.TaskPriority;
import com.rustamlee.focuslist.domain.entities.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A Data Transfer Object (DTO) that represents task information.
 * DTOs are used to transfer data between the service layer and the controller layer.
 * They help isolate the internal data models from external API contracts,
 * improving application maintainability and security.
 */

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
