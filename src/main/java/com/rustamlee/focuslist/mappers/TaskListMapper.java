package com.rustamlee.focuslist.mappers;

import com.rustamlee.focuslist.domain.dto.TaskListDto;
import com.rustamlee.focuslist.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
