//package com.peaksoft.service.impl;
//
//import com.peaksoft.dto.request.RegisterRequest;
//import com.peaksoft.dto.response.RegisterResponse;
//import com.peaksoft.repository.UserRepository;
//import com.peaksoft.entity.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
///**
// * author: Ulansky
// */
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public RegisterResponse create(RegisterRequest request) {
//        User user = mapToEntity(request);
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        userRepository.save(user);
//        return mapToResponse(user);
//    }
//
//    private User mapToEntity(RegisterRequest request) {
//        User user = new User();
//        user.setEmail(request.getEmail());
//        user.setFirstName(request.getFirstName());
//        user.setPassword(request.getPassword());
//        return user;
//    }
//
//    private RegisterResponse mapToResponse(User user) {
//        if (user == null) {
//            return null;
//        }
//        RegisterResponse response = new RegisterResponse();
//        if (user.getId() != null) {
//            response.setId(String.valueOf(user.getId()));
//        }
//        response.setEmail(user.getEmail());
//        response.setFirstName(user.getFirstName());
//        return response;
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("not found email"));
//    }
//
//}
