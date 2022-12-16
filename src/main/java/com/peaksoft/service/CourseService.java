package com.peaksoft.service;

import com.peaksoft.dto.request.CourseRequest;
import com.peaksoft.dto.response.CourseResponse;

import java.io.IOException;
import java.util.List;

public interface CourseService {
    List<CourseResponse> getAllCourse();
    List<CourseResponse> getAllCourseByCompanyId(Long companyId);
    CourseResponse getCourseById(Long id);
    CourseResponse saveCourse(Long companyId, CourseRequest course) throws IOException;
    CourseResponse updateCourse(Long id, CourseRequest course);
    CourseResponse deleteCourseById(Long groupId, Long id);

}
