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
    List<TaskResponse> getAllTask() {
        return taskService.getAllTask();
    }

    @GetMapping("/getAllTaskByLessonId/{lessonId}")
    @PreAuthorize("isAuthenticated()")
    List<TaskResponse> getAllTaskByLessonId(@PathVariable Long lessonId) {
        return taskService.getAllTaskByLessonId(lessonId);
    }

    @GetMapping("/getTaskById/{id}")
    @PreAuthorize("isAuthenticated()")
    TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/saveTask/{lessonId}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    TaskResponse saveTask(@PathVariable Long lessonId, @RequestBody TaskRequest taskRequest) {
        return taskService.saveTask(lessonId, taskRequest);
    }

    @PutMapping("/updateTask/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    TaskResponse updateTask(@PathVariable Long id,
                                 @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest);
    }

    @DeleteMapping("/deleteTaskById/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    TaskResponse deleteTaskById(@PathVariable Long id) {
        return taskService.deleteTaskById(id);
    }

}
