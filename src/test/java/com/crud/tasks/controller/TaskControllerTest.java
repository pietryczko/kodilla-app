package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void getTaskTest() throws Exception {
        //Given
        List<Task> tasks = new ArrayList<>();
        List<TaskDto> tasksDto = new ArrayList<>();
        tasks.add(new Task(1L, "Test", "Test"));
        tasksDto.add(new TaskDto(1L, "Test", "Test"));
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);
        when(service.getAllTasks()).thenReturn(tasks);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("Test")));
    }

    @Test
    void getTaskByIdTest() throws Exception {
        //Given
        Task task = new Task(1L, "Test", "Test");
        TaskDto taskDto = new TaskDto(1L, "Test", "Test");
        when(service.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/tasks/{tasksId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test")));
    }

    @Test
    void shouldCreateNewTask() throws Exception {
        // Given
        Task task = new Task(1L, "Test", "Test");
        TaskDto taskDto = new TaskDto(1L, "Test", "Test");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}