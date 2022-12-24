package com.peaksoft.service;

import com.peaksoft.converter.company.CompanyConverterResponse;
import com.peaksoft.dto.request.CompanyRequest;
import com.peaksoft.dto.response.CompanyResponse;
import com.peaksoft.entity.Company;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CompanyService {
    List<CompanyResponse> getAllCompany();
    CompanyResponse getCompanyById(Long id);
    CompanyResponse saveCompany(CompanyRequest companyRequest);
    CompanyResponse updateCompany(Long id, CompanyRequest companyRequest);
    CompanyResponse deleteCompanyById(Long id);
    CompanyConverterResponse getAllPagination(String text, int page, int size);
    List<CompanyResponse> viewPagination(List<Company> companies);
    List<Company> search(String text, Pageable pageable);
}
