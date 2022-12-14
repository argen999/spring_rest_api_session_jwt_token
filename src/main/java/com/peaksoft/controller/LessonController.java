package com.peaksoft.controller;

import com.peaksoft.dto.request.LessonRequest;
import com.peaksoft.dto.response.LessonResponse;
import com.peaksoft.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/getAllLesson")
    @PreAuthorize("isAuthenticated()")
    public List<LessonResponse> getAllLesson() {
        return lessonService.getAllLesson();
    }

    @GetMapping("/getAllLessonByCourseId/{courseId}")
    @PreAuthorize("isAuthenticated()")
    public List<LessonResponse> getAllInstructorByCourseId(@PathVariable Long courseId) {
        return lessonService.getAllLesson(courseId);
    }

    @GetMapping("/getLessonById/{id}")
    @PreAuthorize("isAuthenticated()")
    public LessonResponse getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping("/saveLesson/{courseId}")
    @PreAuthorize("hasAnyAuthority('Instructor')")
    public LessonResponse saveLesson(@PathVariable Long courseId, @RequestBody LessonRequest lessonRequest) {
        return lessonService.saveLesson(courseId, lessonRequest);
    }

    @PutMapping("/updateLesson/{id}")
    @PreAuthorize("hasAnyAuthority('Instructor')")
    public LessonResponse updateLesson(@PathVariable Long id, @RequestBody LessonRequest lessonRequest) {
        return lessonService.updateLesson(id, lessonRequest);
    }

    @DeleteMapping("/deleteLessonById/{id}")
    @PreAuthorize("hasAnyAuthority('Instructor')")
    public LessonResponse deleteLessonById(@PathVariable Long id) {
        return lessonService.deleteLesson(id);
    }
}
