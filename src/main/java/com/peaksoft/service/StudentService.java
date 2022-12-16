package com.peaksoft.service;

import com.peaksoft.dto.request.StudentRequest;
import com.peaksoft.dto.response.StudentResponse;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    List<StudentResponse> getAllStudent();
    List<StudentResponse> getAllStudentByGroupId(Long groupId);
    StudentResponse getStudentById(Long id);
    StudentResponse saveStudent(Long groupId, StudentRequest studentRequest) throws IOException;
    StudentResponse updateStudent(Long id, StudentRequest studentRequest) throws IOException;
    StudentResponse deleteStudentById(Long id);
    StudentResponse assignStudentToGroup(Long id, Long groupId) throws IOException;

}
