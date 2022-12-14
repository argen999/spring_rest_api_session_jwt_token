package com.peaksoft.service;

import com.peaksoft.dto.request.UserRequest;
import com.peaksoft.dto.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserResponse> getAllUser();
    UserResponse getUserById(Long id);
    UserResponse saveUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest taskRequest);
    UserResponse deleteUser(Long id);
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
