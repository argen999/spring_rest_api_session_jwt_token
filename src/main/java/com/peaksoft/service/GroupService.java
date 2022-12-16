package com.peaksoft.service;

import com.peaksoft.dto.request.GroupRequest;
import com.peaksoft.dto.response.GroupResponse;

import java.io.IOException;
import java.util.List;

public interface GroupService {
    List<GroupResponse> getAllGroup();
    List<GroupResponse> getAllGroupByCourseId(Long courseId);
    GroupResponse getGroupById(Long id);
    GroupResponse saveGroup(Long courseId, GroupRequest groupRequest);
    GroupResponse updateGroup(Long id, GroupRequest groupRequest);
    GroupResponse deleteGroupById(Long courseId, Long id);
    GroupResponse assignGroupToCourse(Long courseId, Long id) throws IOException;
}
