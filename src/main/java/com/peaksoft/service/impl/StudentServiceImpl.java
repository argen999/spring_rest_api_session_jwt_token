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
import jdk.jshell.Snippet;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

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
        return studentConverterResponse.create(studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with " + id + " not found!")));
    }

    @Override
    public StudentResponse saveStudent(Long groupId, StudentRequest studentRequest) throws IOException {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with " + groupId + " not found!"));
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
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with " + id + " not found!"));
        studentConverterRequest.update(student, studentRequest);
        return studentConverterResponse.create(studentRepository.save(student));
    }

    @Override
    public StudentResponse deleteStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with " + id + " not found!"));
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
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with " + id + " not found!"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with " + id + " not found!"));
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
