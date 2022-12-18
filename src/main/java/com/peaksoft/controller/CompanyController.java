package com.peaksoft.controller;

import com.peaksoft.dto.request.CompanyRequest;
import com.peaksoft.dto.response.CompanyResponse;
import com.peaksoft.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/getAllCompany")
    @PreAuthorize("isAuthenticated()")
    List<CompanyResponse> getAllCompany() {
        return companyService.getAllCompany();
    }

    @GetMapping("/getCompanyById/{id}")
    @PreAuthorize("isAuthenticated()")
    CompanyResponse getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping("/saveCompany")
    @PreAuthorize("hasAuthority('Admin')")
    CompanyResponse saveCompany(@RequestBody CompanyRequest companyRequest) {
        return companyService.saveCompany(companyRequest);
    }

    @PutMapping("/updateCompany/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    CompanyResponse updateCompany(@PathVariable Long id, @RequestBody CompanyRequest companyRequest) {
        return companyService.updateCompany(id, companyRequest);
    }

    @DeleteMapping("/deleteCompany/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    CompanyResponse deleteCompany(@PathVariable Long id) {
        return companyService.deleteCompanyById(id);
    }

}
