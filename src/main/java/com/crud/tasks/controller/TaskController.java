package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final DbService service;
    private final TaskMapper taskMapper;

    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    //@GetMapping(value = "{taskId}")
    //public TaskDto getTask(@PathVariable Long taskId) {
   //     return new TaskDto(1L, "test title", "test_content");
   // }

    @DeleteMapping
    public void deleteTask(Long taskId) {
    }

    @PutMapping
    public TaskDto updateTask(Long taskId, TaskDto taskDto) {
        return new TaskDto(1L, "edit: test title", "edit: test_content");
    }

    @PostMapping
    public void createTask(TaskDto taskDto) {

    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = service.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
