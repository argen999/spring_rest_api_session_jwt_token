package com.peaksoft.converter.group;

import com.peaksoft.dto.response.GroupResponse;
import com.peaksoft.entity.Group;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupConverterResponse {

    public GroupResponse create(Group group) {
        if (group == null) return null;
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(group.getId());
        groupResponse.setGroupName(group.getGroupName());
        groupResponse.setDataOfStart(group.getDataOfStart());
        groupResponse.setImage(group.getImage());
        return groupResponse;
    }

    public List<GroupResponse> getAll(List<Group> groups) {
        List<GroupResponse> groupResponses = new ArrayList<>();
        for (Group g : groups) {
            groupResponses.add(create(g));
        }
        return groupResponses;
    }

}
