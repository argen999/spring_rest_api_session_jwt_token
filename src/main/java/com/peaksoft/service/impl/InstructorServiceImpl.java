package com.peaksoft.service.impl;

import com.peaksoft.converter.instructor.InstructorConverterRequest;
import com.peaksoft.converter.instructor.InstructorConverterResponse;
import com.peaksoft.dto.request.InstructorRequest;
import com.peaksoft.dto.response.InstructorResponse;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Instructor;
import com.peaksoft.repository.CourseRepository;
import com.peaksoft.repository.InstructorRepository;
import com.peaksoft.service.InstructorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InstructorServiceImpl implements InstructorService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final InstructorConverterRequest instructorConverterRequest;
    private final InstructorConverterResponse instructorConverterResponse;


    @Override
    public List<InstructorResponse> getAllInstructor() {
        return instructorConverterResponse.getAll(instructorRepository.findAll());
    }

    @Override
    public List<InstructorResponse> getAllInstructorByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        return instructorConverterResponse.getAll(course.getInstructors());
    }

    @Override
    public InstructorResponse getInstructorById(Long id) {
        return instructorConverterResponse.create(instructorRepository.findById(id).get());
    }

    @Override
    public InstructorResponse saveInstructor(Long courseId, InstructorRequest instructorRequest) throws IOException {
        Course course = courseRepository.findById(courseId).get();
        Instructor instructor = instructorConverterRequest.create(instructorRequest);
        for (Group g : course.getGroups()) {
            if (g.getStudents().size() != 0) {
                instructor.setStudent(g.getStudents().size());
            }
        }
        course.addInstructor(instructor);
        instructor.setCourse(course);
        instructorRepository.save(instructor);
        return instructorConverterResponse.create(instructor);
    }

    @Override
    public InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest) throws IOException {
        Instructor instructor = instructorRepository.findById(id).get();
        instructorConverterRequest.update(instructor, instructorRequest);
        return instructorConverterResponse.create(instructorRepository.save(instructor));
    }

    @Override
    public InstructorResponse deleteInstructorById(Long id) {
        Instructor instructor = instructorRepository.findById(id).get();
        instructorRepository.delete(instructor);
        return instructorConverterResponse.create(instructor);
    }

    @Override
    public InstructorResponse assignInstructorToCourse(Long id, Long courseId) throws IOException {
        Instructor instructor = instructorRepository.findById(id).get();
        Course course = courseRepository.findById(courseId).get();
        if (course.getInstructors() != null) {
            for (Instructor i : course.getInstructors()) {
                if (i.getId() == id) {
                    throw new IOException("This instructor already exists!");
                }
            }
        }
        instructor.getCourse().getInstructors().remove(instructor);
        instructor.setCourse(course);
        course.addInstructor(instructor);
        courseRepository.save(course);
        instructorRepository.save(instructor);
        return instructorConverterResponse.create(instructor);
    }


}
