package com.peaksoft.controller;

import com.peaksoft.dto.request.InstructorRequest;
import com.peaksoft.dto.response.InstructorResponse;
import com.peaksoft.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/getAllInstructor")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public List<InstructorResponse> getAllInstructor() {
        return instructorService.getAllInstructor();
    }

    @GetMapping("/getAllInstructorByCourseId/{courseId}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public List<InstructorResponse> getAllInstructorByCourseId(@PathVariable Long courseId) {
        return instructorService.getAllInstructor(courseId);
    }

    @GetMapping("/getInstructorById/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public InstructorResponse getInstructorById(@PathVariable Long id) {
        return instructorService.getInstructorById(id);
    }

    @PostMapping("/saveInstructor/{courseId}")
    @PreAuthorize("hasAuthority('Admin')")
    public InstructorResponse saveInstructor(@PathVariable Long courseId,
                                             @RequestBody InstructorRequest instructorRequest) throws IOException {
        return instructorService.saveInstructor(courseId, instructorRequest);
    }

    @PutMapping("/updateInstructor/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public InstructorResponse saveUpdateInstructor(@PathVariable Long id, @RequestBody InstructorRequest instructorRequest) throws IOException {
        return instructorService.updateInstructor(id, instructorRequest);
    }

    @DeleteMapping("/deleteInstructorById/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public InstructorResponse deleteInstructorById(@PathVariable Long id) {
        return instructorService.deleteInstructor(id);
    }
}
