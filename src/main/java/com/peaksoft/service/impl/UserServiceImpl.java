package com.peaksoft.service.impl;

import com.peaksoft.converter.user.UserConverterRequest;
import com.peaksoft.converter.user.UserConverterResponse;
import com.peaksoft.dto.request.UserRequest;
import com.peaksoft.dto.response.UserResponse;
import com.peaksoft.entity.User;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverterRequest userConverterRequest;
    private final UserConverterResponse userConverterResponse;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserResponse> getAllUser() {
        return userConverterResponse.getAll(userRepository.findAll());
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userConverterResponse.create(userRepository.findById(id).get());
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        User user = userConverterRequest.create(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        return userConverterResponse.create(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).get();
        userConverterRequest.update(user, userRequest);
        return userConverterResponse.create(userRepository.save(user));
    }

    @Override
    public UserResponse deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return userConverterResponse.create(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("not found email"));
    }

}