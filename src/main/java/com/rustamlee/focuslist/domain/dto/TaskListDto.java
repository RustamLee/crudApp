package com.rustamlee.focuslist.domain.dto;

import com.rustamlee.focuslist.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks
) {
}
