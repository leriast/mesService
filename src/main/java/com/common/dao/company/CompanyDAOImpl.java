package com.common.dao.company;

import com.common.dao.entity.company.Company;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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

    @Override
    public void addCompany(Company company) {
        sessionFactory.getCurrentSession().save(company);
    }

    @Override
    public void deleteCompany(Company company) {
        sessionFactory.getCurrentSession().delete(company);
    }

    @Override
    public ArrayList<Company> getAllCompanies() {
        ArrayList<Company>list=new ArrayList<>();
        list.addAll(sessionFactory.getCurrentSession().createQuery("from Company").list());
        for(Company co:list){
            System.out.println(co.getCompany_name());
        }
        return list;
    }
}
