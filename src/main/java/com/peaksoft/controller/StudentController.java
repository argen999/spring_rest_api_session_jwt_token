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
    public List<StudentResponse> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/getAllStudentByGroupId/{groupId}")
    @PreAuthorize("isAuthenticated()")
    public List<StudentResponse> getAllStudentByCourseId(@PathVariable Long groupId) {
        return studentService.getAllStudent(groupId);
    }

    @GetMapping("/getStudentById/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/saveStudent/{groupId}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public StudentResponse saveStudent(@PathVariable Long groupId, @RequestBody StudentRequest studentRequest) throws IOException {
        return studentService.saveStudent(groupId, studentRequest);
    }

    @PutMapping("/updateStudent/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Student')")
    public StudentResponse UpdateStudent(@PathVariable Long id,
                                    @RequestBody StudentRequest studentRequest) throws IOException {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/deleteStudentById/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public StudentResponse deleteStudentById(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

}
