package com.peaksoft.service;

import com.peaksoft.dto.request.TaskRequest;
import com.peaksoft.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> getAllTask();
    List<TaskResponse> getAllTask(Long lessonId);
    TaskResponse getTaskById(Long id);
    TaskResponse saveTask(Long lessonId, TaskRequest taskRequest);
    TaskResponse updateTask(Long id, TaskRequest taskRequest);
    TaskResponse deleteTask(Long id);
}
