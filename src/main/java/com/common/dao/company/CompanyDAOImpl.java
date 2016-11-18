package com.common.dao.company;

import com.common.dao.entity.company.Company;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by root on 11/17/16.
 */
@Repository
@Transactional
public class CompanyDAOImpl implements CompanyDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public void getCompanies() {
        Criteria criteria = sessionFactory
                .getCurrentSession()
                .createCriteria(Company.class, "arr");
        Company a = (Company) criteria.list().get(0);
        System.out.println(a.getCompany_name());
    }

    @Override
    public Company getCompanyById(int id_company) {
       Company company= (Company) sessionFactory.getCurrentSession().load(Company.class,id_company);
        return company;
    }
}
