package com.peaksoft.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorRequest {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private String specialization;
}
