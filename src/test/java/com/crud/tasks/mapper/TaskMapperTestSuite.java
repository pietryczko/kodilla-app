package com.crud.tasks.mapper;


import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "exampleTask", "exampleContent");

        //When
        Task testTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(testTask.getId(), taskDto.getId());
        assertEquals(testTask.getTitle(), taskDto.getTitle());
        assertEquals(testTask.getContent(), taskDto.getContent());
    }

    @Test
    void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "exampleTask", "exampleContent");

        //When
        TaskDto testTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(task.getId(), testTaskDto.getId());
        assertEquals(task.getTitle(), testTaskDto.getTitle());
        assertEquals(task.getContent(), testTaskDto.getContent());
    }

    @Test
    void testMapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "exampleTask", "exampleContent");
        Task task2 = new Task(2L, "exampleTask2", "exampleContent2");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        //When
        List<TaskDto> testList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(2, testList.size());
    }

}