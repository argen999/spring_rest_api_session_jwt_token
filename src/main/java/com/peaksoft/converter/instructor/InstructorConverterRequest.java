package com.peaksoft.converter.instructor;


import com.peaksoft.dto.request.InstructorRequest;
import com.peaksoft.entity.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorConverterRequest {

    public Instructor create(InstructorRequest instructorRequest) {
        if (instructorRequest == null) return null;
        Instructor instructor = new Instructor();
        instructor.setFirstname(instructorRequest.getFirstname());
        instructor.setLastname(instructorRequest.getLastname());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        return instructor;
    }

    public void update(Instructor instructor, InstructorRequest instructorRequest) {
        if (instructorRequest.getFirstname() != null) {
            instructor.setFirstname(instructorRequest.getFirstname());
        }
        if (instructorRequest.getLastname() != null) {
            instructor.setLastname(instructorRequest.getLastname());
        }
        if (instructorRequest.getPhoneNumber() != null) {
            instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        }
        if (instructorRequest.getEmail() != null) {
            instructor.setEmail(instructorRequest.getEmail());
        }
        if (instructorRequest.getSpecialization() != null) {
            instructor.setSpecialization(instructorRequest.getSpecialization());
        }
    }
}
