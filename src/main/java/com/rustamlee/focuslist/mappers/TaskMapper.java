package com.rustamlee.focuslist.mappers;

import com.rustamlee.focuslist.domain.dto.TaskDto;
import com.rustamlee.focuslist.domain.entities.Task;

//  Mapper interface for converting between Task entities and TaskDto.
public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}
