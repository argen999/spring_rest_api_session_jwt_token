package com.peaksoft.service;


import com.peaksoft.dto.request.LessonRequest;
import com.peaksoft.dto.response.LessonResponse;

import java.util.List;

public interface LessonService {
    List<LessonResponse> getAllLesson();
    List<LessonResponse> getAllLessonByCourseId(Long courseId);
    LessonResponse getLessonById(Long id);
    LessonResponse saveLesson(Long courseId, LessonRequest lessonRequest);
    LessonResponse updateLesson(Long id, LessonRequest lessonRequest);
    LessonResponse deleteLessonById(Long id);
}
