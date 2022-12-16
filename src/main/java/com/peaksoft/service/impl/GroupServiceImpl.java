package com.peaksoft.service.impl;

import com.peaksoft.converter.group.GroupConverterRequest;
import com.peaksoft.converter.group.GroupConverterResponse;
import com.peaksoft.dto.request.GroupRequest;
import com.peaksoft.dto.response.GroupResponse;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Instructor;
import com.peaksoft.repository.CourseRepository;
import com.peaksoft.repository.GroupRepository;
import com.peaksoft.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;
    private final GroupConverterRequest groupConverterRequest;
    private final GroupConverterResponse groupConverterResponse;

    @Override
    public List<GroupResponse> getAllGroup() {
        return groupConverterResponse.getAll(groupRepository.findAll());
    }

    @Override
    public List<GroupResponse> getAllGroupByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        return groupConverterResponse.getAll(course.getGroups());
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        return groupConverterResponse.create(groupRepository.findById(id).get());
    }

    @Override
    public GroupResponse saveGroup(Long courseId, GroupRequest groupRequest) {
        Course course = courseRepository.findById(courseId).get();
        Group group = groupConverterRequest.create(groupRequest);
        course.addGroup(group);
        group.addCourse(course);
        groupRepository.save(group);
        return groupConverterResponse.create(group);
    }

    @Override
    public GroupResponse updateGroup(Long id, GroupRequest groupRequest) {
        Group group = groupRepository.findById(id).get();
        groupConverterRequest.update(group, groupRequest);
        return groupConverterResponse.create(groupRepository.save(group));
    }

    @Override
    public GroupResponse deleteGroupById(Long courseId, Long id) {
        Course course = courseRepository.findById(courseId).get();
        Group group = groupRepository.findById(id).get();
        for (Course c : group.getCourses()) {
            c.getCompany().minus();
        }
        for (Course c : group.getCourses()) {
            for (Instructor i : c.getInstructors()) {
                i.minus();
            }
        }
        group.remove(course);
        groupRepository.delete(group);
        return groupConverterResponse.create(group);
    }

    @Override
    public GroupResponse assignGroupToCourse(Long courseId, Long id) throws IOException {
        Group group = groupRepository.findById(id).get();
        Course course = courseRepository.findById(courseId).get();
        if (course.getGroups() != null) {
            for (Group g : course.getGroups()) {
                if (g.getId() == id) {
                    throw new IOException("This group already exists!");
                }
            }
        }
        group.addCourse(course);
        course.addGroup(group);
        courseRepository.save(course);
        groupRepository.save(group);
        return groupConverterResponse.create(group);
    }
}
