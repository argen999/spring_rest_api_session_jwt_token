package com.peaksoft.service.impl;

import com.peaksoft.converter.student.StudentConverterRequest;
import com.peaksoft.converter.student.StudentConverterResponse;
import com.peaksoft.dto.request.StudentRequest;
import com.peaksoft.dto.response.StudentResponse;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Instructor;
import com.peaksoft.entity.Student;
import com.peaksoft.repository.GroupRepository;
import com.peaksoft.repository.StudentRepository;
import com.peaksoft.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final StudentConverterRequest studentConverterRequest;
    private final StudentConverterResponse studentConverterResponse;

    @Override
    public List<StudentResponse> getAllStudent() {
        return studentConverterResponse.getAll(studentRepository.findAll());
    }

    @Override
    public List<StudentResponse> getAllStudentByGroupId(Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        return studentConverterResponse.getAll(group.getStudents());
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return studentConverterResponse.create(studentRepository.findById(id).get());
    }

    @Override
    public StudentResponse saveStudent(Long groupId, StudentRequest studentRequest) throws IOException {
        Group group = groupRepository.findById(groupId).get();
        for (Course c : group.getCourses()) {
            c.getCompany().plus();
        }
        for (Course c : group.getCourses()) {
            for (Instructor i : c.getInstructors()) {
                i.plus();
            }
        }
        Student student = studentConverterRequest.create(studentRequest);
        group.addStudent(student);
        student.setGroup(group);
        studentRepository.save(student);
        return studentConverterResponse.create(student);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) throws IOException {
        Student student = studentRepository.findById(id).get();
        studentConverterRequest.update(student, studentRequest);
        return studentConverterResponse.create(studentRepository.save(student));
    }

    @Override
    public StudentResponse deleteStudentById(Long id) {
        Student student = studentRepository.findById(id).get();
        for (Course c : student.getGroup().getCourses()) {
            c.getCompany().minus();
        }
        for (Course c : student.getGroup().getCourses()) {
            for (Instructor i : c.getInstructors()) {
                i.minus();
            }
        }
        studentRepository.delete(student);
        return studentConverterResponse.create(student);
    }

    @Override
    public StudentResponse assignStudentToGroup(Long id, Long groupId) throws IOException {
        Student student = studentRepository.findById(id).get();
        Group group = groupRepository.findById(groupId).get();
        if (group.getStudents() != null) {
            for (Student s : group.getStudents()) {
                if (s.getId() == id) {
                    throw new IOException("This student already exists!");
                }
            }
        }
        student.setGroup(group);
        group.addStudent(student);
        studentRepository.save(student);
        groupRepository.save(group);
        return studentConverterResponse.create(student);
    }

}
