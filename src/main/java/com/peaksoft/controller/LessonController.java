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
    List<LessonResponse> getAllLesson() {
        return lessonService.getAllLesson();
    }

    @GetMapping("/getAllLessonByCourseId/{courseId}")
    @PreAuthorize("isAuthenticated()")
    List<LessonResponse> getAllInstructorByCourseId(@PathVariable Long courseId) {
        return lessonService.getAllLessonByCourseId(courseId);
    }

    @GetMapping("/getLessonById/{id}")
    @PreAuthorize("isAuthenticated()")
    LessonResponse getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping("/saveLesson/{courseId}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    LessonResponse saveLesson(@PathVariable Long courseId, @RequestBody LessonRequest lessonRequest) {
        return lessonService.saveLesson(courseId, lessonRequest);
    }

    @PutMapping("/updateLesson/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    LessonResponse updateLesson(@PathVariable Long id, @RequestBody LessonRequest lessonRequest) {
        return lessonService.updateLesson(id, lessonRequest);
    }

    @DeleteMapping("/deleteLessonById/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    LessonResponse deleteLessonById(@PathVariable Long id) {
        return lessonService.deleteLessonById(id);
    }
}
