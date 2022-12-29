package com.peaksoft.service.impl;

import com.peaksoft.converter.lesson.LessonConverterRequest;
import com.peaksoft.converter.lesson.LessonConverterResponse;
import com.peaksoft.dto.request.LessonRequest;
import com.peaksoft.dto.response.LessonResponse;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Lesson;
import com.peaksoft.repository.CourseRepository;
import com.peaksoft.repository.LessonRepository;
import com.peaksoft.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final LessonConverterRequest lessonConverterRequest;
    private final LessonConverterResponse lessonConverterResponse;

    @Override
    public List<LessonResponse> getAllLesson() {
        return lessonConverterResponse.getAll(lessonRepository.findAll());
    }

    @Override
    public List<LessonResponse> getAllLessonByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course with " + courseId + " not found!"));
        return lessonConverterResponse.getAll(course.getLessons());
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        return lessonConverterResponse.create(lessonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson with " + id + " not found!")));
    }

    @Override
    public LessonResponse saveLesson(Long courseId, LessonRequest lessonRequest) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course with " + courseId + " not found!"));
        Lesson lesson = lessonConverterRequest.create(lessonRequest);
        course.addLesson(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        return lessonConverterResponse.create(lesson);
    }

    @Override
    public LessonResponse updateLesson(Long id, LessonRequest lessonRequest) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson with " + id + " not found!"));
        lessonConverterRequest.update(lesson, lessonRequest);
        return lessonConverterResponse.create(lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse deleteLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson with " + id + " not found!"));
        lessonRepository.delete(lesson);
        return lessonConverterResponse.create(lesson);
    }
}
