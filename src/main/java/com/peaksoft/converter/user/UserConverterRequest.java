package com.peaksoft.converter.user;

import com.peaksoft.dto.request.UserRequest;
import com.peaksoft.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverterRequest {

    public User create(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public void update(User user, UserRequest userRequest) {
        if (userRequest.getEmail() != null)
            user.setEmail(userRequest.getEmail());
        if (userRequest.getPassword() != null)
            user.setFirstName(userRequest.getFirstName());
        if (userRequest.getFirstName() != null)
            user.setPassword(userRequest.getPassword());
    }

}
