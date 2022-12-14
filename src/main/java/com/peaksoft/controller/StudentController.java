package com.peaksoft.controller;

import com.peaksoft.dto.request.StudentRequest;
import com.peaksoft.dto.response.StudentResponse;
import com.peaksoft.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/getAllStudent")
    @PreAuthorize("isAuthenticated()")
    List<StudentResponse> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/getAllStudentByGroupId/{groupId}")
    @PreAuthorize("isAuthenticated()")
    List<StudentResponse> getAllStudentByGroupId(@PathVariable Long groupId) {
        return studentService.getAllStudentByGroupId(groupId);
    }

    @GetMapping("/getStudentById/{id}")
    @PreAuthorize("isAuthenticated()")
    StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/saveStudent/{groupId}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    StudentResponse saveStudent(@PathVariable Long groupId, @RequestBody StudentRequest studentRequest) throws IOException {
        return studentService.saveStudent(groupId, studentRequest);
    }

    @PutMapping("/updateStudent/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    StudentResponse UpdateStudent(@PathVariable Long id,
                                    @RequestBody StudentRequest studentRequest) throws IOException {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/deleteStudentById/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    StudentResponse deleteStudentById(@PathVariable Long id) {
        return studentService.deleteStudentById(id);
    }

}
