package com.peaksoft.service.impl;

import com.peaksoft.converter.task.TaskConverterRequest;
import com.peaksoft.converter.task.TaskConverterResponse;
import com.peaksoft.dto.request.TaskRequest;
import com.peaksoft.dto.response.TaskResponse;
import com.peaksoft.entity.Lesson;
import com.peaksoft.entity.Task;
import com.peaksoft.repository.LessonRepository;
import com.peaksoft.repository.TaskRepository;
import com.peaksoft.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final LessonRepository lessonRepository;
    private final TaskRepository taskRepository;
    private final TaskConverterRequest taskConverterRequest;
    private final TaskConverterResponse taskConverterResponse;


    @Override
    public List<TaskResponse> getAllTask() {
        return taskConverterResponse.getAll(taskRepository.findAll());
    }

    @Override
    public List<TaskResponse> getAllTaskByLessonId(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        return taskConverterResponse.getAll(lesson.getTasks());
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        return taskConverterResponse.create(taskRepository.findById(id).get());
    }

    @Override
    public TaskResponse saveTask(Long lessonId, TaskRequest taskRequest) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        Task task = taskConverterRequest.create(taskRequest);
        lesson.addTask(task);
        task.setLesson(lesson);
        taskRepository.save(task);
        return taskConverterResponse.create(task);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id).get();
        taskConverterRequest.update(task, taskRequest);
        return taskConverterResponse.create(task);
    }

    @Override
    public TaskResponse deleteTaskById(Long id) {
        Task task = taskRepository.findById(id).get();
        taskRepository.delete(task);
        return taskConverterResponse.create(task);
    }
}
