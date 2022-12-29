package com.peaksoft.service.impl;

import com.peaksoft.converter.company.CompanyConverterRequest;
import com.peaksoft.converter.company.CompanyConverterResponse;
import com.peaksoft.dto.request.CompanyRequest;
import com.peaksoft.dto.response.CompanyResponse;
import com.peaksoft.entity.Company;
import com.peaksoft.repository.CompanyRepository;
import com.peaksoft.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyConverterRequest companyConverterRequest;
    private final CompanyConverterResponse companyConverterResponse;

    @Override
    public List<CompanyResponse> getAllCompany() {
        return companyConverterResponse.getAll(companyRepository.findAll());
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with " + id + " not found!"));
        return companyConverterResponse.create(company);
    }

    @Override
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        Company company = companyConverterRequest.create(companyRequest);
        return companyConverterResponse.create(companyRepository.save(company));
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {
        Company company = companyConverterRequest.update(companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with " + id + " not found!")), companyRequest);
        return companyConverterResponse.create(companyRepository.save(company));
    }

    @Override
    public CompanyResponse deleteCompanyById(Long id) {
        companyRepository.delete(companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with " + id + " not found!")));
        return companyConverterResponse.create(companyRepository.findById(id).get());
    }

    @Override
    public CompanyConverterResponse getAllPagination(String text, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        companyConverterResponse.setCompanyResponses(viewPagination(search(text, pageable)));
        return companyConverterResponse;
    }

    @Override
    public List<CompanyResponse> viewPagination(List<Company> companies) {
        List<CompanyResponse> companyResponses = new ArrayList<>();
        for (Company c : companies) {
            companyResponses.add(companyConverterResponse.create(c));
        }
        return companyResponses;
    }

    @Override
    public List<Company> search(String text, Pageable pageable) {
        String companyName = text == null ? "" : text;
        return companyRepository.searchPagination(companyName.toUpperCase(), pageable);
    }

}
