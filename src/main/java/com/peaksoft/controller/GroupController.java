package com.peaksoft.controller;

import com.peaksoft.dto.request.GroupRequest;
import com.peaksoft.dto.response.GroupResponse;
import com.peaksoft.dto.response.StudentResponse;
import com.peaksoft.service.GroupService;
import com.peaksoft.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final StudentService studentService;

    @GetMapping("/getAllGroup")
    @PreAuthorize("isAuthenticated()")
    List<GroupResponse> getAllGroup() {
        return groupService.getAllGroup();
    }

    @GetMapping("/getAllGroupByCourseId/{courseId}")
    @PreAuthorize("isAuthenticated()")
    List<GroupResponse> getAllGroupByCourseId(@PathVariable Long courseId) {
        return groupService.getAllGroupByCourseId(courseId);
    }

    @GetMapping("/getGroupById/{id}")
    @PreAuthorize("isAuthenticated()")
    GroupResponse getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @PostMapping("/saveGroup/{courseId}")
    @PreAuthorize("hasAuthority('Admin')")
    GroupResponse saveGroup(@PathVariable Long courseId, @RequestBody GroupRequest groupRequest) {
        return groupService.saveGroup(courseId, groupRequest);
    }

    @PutMapping("/updateGroup/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    GroupResponse updateGroup(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return groupService.updateGroup(id, groupRequest);
    }

    @DeleteMapping("/deleteGroupById/{courseId}/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    GroupResponse deleteGroupById(@PathVariable Long courseId, @PathVariable Long id) {
        return groupService.deleteGroupById(courseId, id);
    }

    @PostMapping("/assignStudentToGroup/{id}/{groupId}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    StudentResponse assignStudentToGroup(@PathVariable Long id,
                                          @PathVariable Long groupId) throws IOException {
        return studentService.assignStudentToGroup(id, groupId);
    }

}
