package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DbServiceTests {
    @Autowired
    private DbService dbService;

    @Test
    void saveAndGetTaskTest() {
        //Given
        Task task = new Task(3, "title test", "desc test");

        //When
        dbService.saveTask(task);
        Optional<Task> readTask = dbService.getTask(task.getId());

        //Then
        try {
            assertTrue(readTask.isPresent());
        } finally {
            dbService.deleteTask(task.getId());
        }
    }

    @Test
    void getAllTasksTest() {
        //Given
        Task task1 = new Task(1, "title test1", "desc test1");
        Task task2 = new Task(2, "title test2", "desc test2");

        //When
        dbService.saveTask(task1);
        dbService.saveTask(task2);

        List<Task> taskList = dbService.getAllTasks();

        //Then
        try {
            assertEquals(2, taskList.size());
        } finally {
            dbService.deleteTask(task1.getId());
            dbService.deleteTask(task2.getId());
        }
    }
}