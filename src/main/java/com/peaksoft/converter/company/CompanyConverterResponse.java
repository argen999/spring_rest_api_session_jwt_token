package com.peaksoft.converter.company;

import com.peaksoft.dto.response.CompanyResponse;
import com.peaksoft.entity.Company;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Setter
@Getter
public class CompanyConverterResponse {

    private List<CompanyResponse> companyResponses;

    public CompanyResponse create(Company company) {
        if (company == null) return null;
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(company.getId());
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setLocatedCountry(company.getLocatedCountry());
        companyResponse.setStudent(company.getStudent());
        return companyResponse;
    }

    public List<CompanyResponse> getAll(List<Company> companies) {
        List<CompanyResponse> companyResponses = new ArrayList<>();
        for (Company c : companies) {
            companyResponses.add(create(c));
        }
        return companyResponses;
    }

}
