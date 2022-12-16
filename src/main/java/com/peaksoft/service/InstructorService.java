package com.peaksoft.service;

import com.peaksoft.dto.request.InstructorRequest;
import com.peaksoft.dto.response.InstructorResponse;

import java.io.IOException;
import java.util.List;

public interface InstructorService {
    List<InstructorResponse> getAllInstructor();
    List<InstructorResponse> getAllInstructorByCourseId(Long courseId);
    InstructorResponse getInstructorById(Long id);
    InstructorResponse saveInstructor(Long courseId, InstructorRequest instructorRequest) throws IOException;
    InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest) throws IOException;
    InstructorResponse deleteInstructorById(Long id);
    InstructorResponse assignInstructorToCourse(Long id, Long courseId) throws IOException;
}
