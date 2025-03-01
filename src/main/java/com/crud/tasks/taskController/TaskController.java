package com.crud.tasks.taskController;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @GetMapping
    public List<TaskDto> getTasks(){
        return new ArrayList<TaskDto>();
    }
    @GetMapping
    public TaskDto getTaskId(Long taskId) {
        return new TaskDto(1L, "test title", "test_content");
    }
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
}
