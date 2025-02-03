package com.rustamlee.focuslist.domain.dto;

import com.rustamlee.focuslist.domain.entities.Task;

import java.util.List;
import java.util.UUID;

 // A Data Transfer Object (DTO) for task lists.
//  Used to transfer task list data between the service layer and the controller layer.

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks
) {
}
