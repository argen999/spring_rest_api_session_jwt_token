package com.peaksoft.controller;

import com.peaksoft.dto.request.CourseRequest;
import com.peaksoft.dto.response.CourseResponse;
import com.peaksoft.dto.response.GroupResponse;
import com.peaksoft.dto.response.InstructorResponse;
import com.peaksoft.service.CourseService;
import com.peaksoft.service.GroupService;
import com.peaksoft.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final GroupService groupService;
    private final InstructorService instructorService;

    @GetMapping("/getAllCourse")
    @PreAuthorize("isAuthenticated()")
    List<CourseResponse> getAllCourse() {
        return courseService.getAllCourse();
    }

    @GetMapping("/getAllCourseByCompanyId/{companyId}")
    @PreAuthorize("isAuthenticated()")
    List<CourseResponse> getAllCourseByCompanyId(@PathVariable Long companyId) {
        return courseService.getAllCourseByCompanyId(companyId);
    }

    @GetMapping("/getCourseById/{id}")
    @PreAuthorize("isAuthenticated()")
    CourseResponse getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping("/saveCourse/{companyId}")
    @PreAuthorize("hasAuthority('Admin')")
    CourseResponse saveCourse(@PathVariable Long companyId, @RequestBody CourseRequest courseRequest) throws IOException {
        return courseService.saveCourse(companyId, courseRequest);
    }

    @PutMapping("/updateCourse/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    CourseResponse updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("/deleteCourseById/{id}/{groupId}")
    @PreAuthorize("hasAuthority('Admin')")
    CourseResponse deleteCourseById(@PathVariable Long id, @PathVariable Long groupId) {
        return courseService.deleteCourseById(groupId, id);
    }

    @PostMapping("/assignGroupToCourse/{courseId}/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    GroupResponse assignGroupToCourse(@PathVariable Long courseId,
                                              @PathVariable Long id) throws IOException {
        return groupService.assignGroupToCourse(courseId, id);
    }

    @PostMapping("/assignInstructorToCourse/{id}/{courseId}")
    @PreAuthorize("hasAuthority('Admin')")
    InstructorResponse assignInstructorToCourse(@PathVariable Long id,
                                                        @PathVariable Long courseId) throws IOException {
        return instructorService.assignInstructorToCourse(id, courseId);
    }

}
