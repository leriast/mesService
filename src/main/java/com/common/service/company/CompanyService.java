package com.common.service.company;

import com.common.dao.entity.company.Company;

import java.util.ArrayList;

/**
 * Created by root on 11/17/16.
 */
public interface CompanyService {
void getCompanies();
    Company getCompanyById(int id_company);

    void addCompany(Company company);

    void deleteCompany(Company company);

    ArrayList<Company> getAllCompanies();
}
