package com.peaksoft.controller;

import com.peaksoft.dto.request.TaskRequest;
import com.peaksoft.dto.response.TaskResponse;
import com.peaksoft.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/getAllTask")
    @PreAuthorize("isAuthenticated()")
    public List<TaskResponse> getAllTask() {
        return taskService.getAllTask();
    }

    @GetMapping("/getAllTaskByLessonId/{lessonId}")
    @PreAuthorize("isAuthenticated()")
    public List<TaskResponse> getAllTaskByLessonId(@PathVariable Long lessonId) {
        return taskService.getAllTaskByLessonId(lessonId);
    }

    @GetMapping("/getTaskById/{id}")
    @PreAuthorize("isAuthenticated()")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/saveTask/{lessonId}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public TaskResponse saveTask(@PathVariable Long lessonId, @RequestBody TaskRequest taskRequest) {
        return taskService.saveTask(lessonId, taskRequest);
    }

    @PutMapping("/updateTask/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public TaskResponse updateTask(@PathVariable Long id,
                                 @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest);
    }

    @DeleteMapping("/deleteTaskById/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public TaskResponse deleteTaskById(@PathVariable Long id) {
        return taskService.deleteTaskById(id);
    }

}
