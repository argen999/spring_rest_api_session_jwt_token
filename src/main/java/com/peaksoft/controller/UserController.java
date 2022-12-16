package com.peaksoft.controller;

import com.peaksoft.converter.LoginConverter;
import com.peaksoft.dto.request.UserRequest;
import com.peaksoft.dto.response.LoginResponse;
import com.peaksoft.dto.response.UserResponse;
import com.peaksoft.entity.User;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.security.ValidationExceptionType;
import com.peaksoft.security.jwt.JwtTokenUtil;
import com.peaksoft.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class UserController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final LoginConverter loginConverter;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody UserRequest userRequest) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userRequest.getEmail(),
                            userRequest.getPassword());
            User user = userRepository.findByEmail(token.getName()).get();
            return ResponseEntity.ok().body(loginConverter.
                    loginView(jwtTokenUtil.generateToken(user),
                            ValidationExceptionType.SUCCESSFUL, user));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(loginConverter.
                            loginView("", ValidationExceptionType
                                    .LOGIN_FAILED, null));
        }
    }

    @PostMapping("/registration")
    public UserResponse registration(@RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }

    @GetMapping("/getAllUser")
    @PreAuthorize("isAuthenticated()")
    public List<UserResponse> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/getUserById/{id}")
    @PreAuthorize("isAuthenticated()")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public UserResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }



}
