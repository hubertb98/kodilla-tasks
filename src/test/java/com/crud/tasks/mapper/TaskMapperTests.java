package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class TaskMapperTests {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1, "title test", "content test");

        //When
        Task expectedTask = taskMapper.mapToTask(taskDto);

        //Then
        assertThat(expectedTask.getId()).isEqualTo(taskDto.getId());
        assertThat(expectedTask.getTitle()).isEqualTo(taskDto.getTitle());
        assertThat(expectedTask.getContent()).isEqualTo(taskDto.getContent());
    }

    @Test
    void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1, "title test", "content test");

        //When
        TaskDto expectedTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertThat(expectedTaskDto.getId()).isEqualTo(task.getId());
        assertThat(expectedTaskDto.getTitle()).isEqualTo(task.getTitle());
        assertThat(expectedTaskDto.getContent()).isEqualTo(task.getContent());
    }

    @Test
    void mapToTaskDtoListTest() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1, "title test", "content test");
        taskList.add(task);

        //When
        List<TaskDto> expectedTaskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertThat(expectedTaskDtoList.size()).isEqualTo(taskList.size());
        assertThat(expectedTaskDtoList.get(0).getId()).isEqualTo(taskList.get(0).getId());
        assertThat(expectedTaskDtoList.get(0).getTitle()).isEqualTo(taskList.get(0).getTitle());
        assertThat(expectedTaskDtoList.get(0).getContent()).isEqualTo(taskList.get(0).getContent());

    }

}