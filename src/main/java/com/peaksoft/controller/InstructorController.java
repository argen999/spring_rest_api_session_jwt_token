package com.peaksoft.controller;

import com.peaksoft.dto.request.InstructorRequest;
import com.peaksoft.dto.response.InstructorResponse;
import com.peaksoft.service.InstructorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructors")
@Slf4j
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/getAllInstructor")
    @PreAuthorize("isAuthenticated()")
    List<InstructorResponse> getAllInstructor() {
        return instructorService.getAllInstructor();
    }

    @GetMapping("/getAllInstructorByCourseId/{courseId}")
    @PreAuthorize("isAuthenticated()")
    List<InstructorResponse> getAllInstructorByCourseId(@PathVariable Long courseId) {
        return instructorService.getAllInstructorByCourseId(courseId);
    }

    @GetMapping("/getInstructorById/{id}")
    @PreAuthorize("isAuthenticated()")
    InstructorResponse getInstructorById(@PathVariable Long id) {
        return instructorService.getInstructorById(id);
    }

    @PostMapping("/saveInstructor/{courseId}")
    @PreAuthorize("hasAuthority('Admin')")
    InstructorResponse saveInstructor(@PathVariable Long courseId,
                                             @RequestBody InstructorRequest instructorRequest) throws IOException {
        return instructorService.saveInstructor(courseId, instructorRequest);
    }

    @PutMapping("/updateInstructor/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    InstructorResponse updateInstructor(@PathVariable Long id, @RequestBody InstructorRequest instructorRequest) throws IOException {
        return instructorService.updateInstructor(id, instructorRequest);
    }

    @DeleteMapping("/deleteInstructorById/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    InstructorResponse deleteInstructorById(@PathVariable Long id) {
        return instructorService.deleteInstructorById(id);
    }

}
