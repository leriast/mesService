package com.common.service.company;

import com.common.dao.entity.company.Company;

/**
 * Created by root on 11/17/16.
 */
public interface CompanyService {
void getCompanies();
    Company getCompanyById(int id_company);
}
