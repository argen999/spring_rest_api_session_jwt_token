package com.peaksoft.converter.user;

import com.peaksoft.dto.response.UserResponse;
import com.peaksoft.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverterResponse {

    public UserResponse create(User user) {
        if (user == null) return null;
        UserResponse userResponse = new UserResponse();
        if (user.getId() != null) {
            userResponse.setId(String.valueOf(user.getId()));
        }
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        return userResponse;
    }

    public List<UserResponse> getAll(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User u : users) {
            userResponses.add(create(u));
        }
        return userResponses;
    }

}
