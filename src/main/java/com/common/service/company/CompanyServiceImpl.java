package com.common.service.company;

import com.common.dao.company.CompanyDAO;
import com.common.dao.entity.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by root on 11/17/16.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyDAO companyDAO;

    @Override
    public void getCompanies() {
        companyDAO.getCompanies();
    }

    @Override
    public Company getCompanyById(int id_company) {
        return companyDAO.getCompanyById(id_company);
    }

    @Override
    public void addCompany(Company company) {
        companyDAO.addCompany(company);
    }

    @Override
    public void deleteCompany(Company company) {
        companyDAO.deleteCompany(company);
    }

    @Override
    public ArrayList<Company> getAllCompanies() {
        return companyDAO.getAllCompanies();
    }
}
