package com.peaksoft.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * author: Ulansky
 */
@Getter
@Setter
public class LoginResponse {

    private String jwtToken;
    private String message;
    private Set<String> authorities;
}
