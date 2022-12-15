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
    public List<GroupResponse> getAllGroup() {
        return groupService.getAllGroup();
    }

    @GetMapping("/getAllGroupByCourseId/{courseId}")
    @PreAuthorize("isAuthenticated()")
    public List<GroupResponse> getAllGroupByCourseId(@PathVariable Long courseId) {
        return groupService.getAllGroup(courseId);
    }

    @GetMapping("/getGroupById/{id}")
    @PreAuthorize("isAuthenticated()")
    public GroupResponse getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @PostMapping("saveGroup/{courseId}")
    @PreAuthorize("hasAuthority('Admin')")
    public GroupResponse saveGroup(@PathVariable Long courseId, @RequestBody GroupRequest groupRequest) {
        return groupService.saveGroup(courseId, groupRequest);
    }

    @PutMapping("/updateGroupByCourseId/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public GroupResponse updateGroupByCourseId(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return groupService.updateGroup(id, groupRequest);
    }

    @DeleteMapping("/deleteGroupById/{courseId}/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public GroupResponse deleteGroupById(@PathVariable Long courseId, @PathVariable Long id) {
        return groupService.deleteGroup(courseId, id);
    }

    @PostMapping("/assignStudent/{id}/{groupId}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    private StudentResponse assignStudent(@PathVariable Long id,
                                          @PathVariable Long groupId) throws IOException {
        return studentService.assignStudent(id, groupId);
    }

}
