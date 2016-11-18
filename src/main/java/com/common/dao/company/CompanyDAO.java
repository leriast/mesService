package com.common.dao.company;

import com.common.dao.entity.company.Company;

/**
 * Created by root on 11/17/16.
 */
public interface CompanyDAO {
    void getCompanies();

    Company getCompanyById(int id_company);
}
