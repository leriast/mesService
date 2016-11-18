package com.common.dao.entity.company;

import com.common.dao.entity.security.User;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by root on 11/17/16.
 */
@Entity
@Table(name = "COMPANY")
public class Company {

    @Column(name = "ID_COMPANY", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_company;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USER")
    private Set<User> user;
    @Column(name = "COMPANY_NAME")
    private String company_name;
    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "ADDRESS")
    private String address;

    public int getId_company() {
        return id_company;
    }

    public void setId_company(int id_company) {
        this.id_company = id_company;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Company() {
    }

    public Company(int id_company, String company_name, String telephone, String address) {
        this.id_company = id_company;
        this.company_name = company_name;
        this.telephone = telephone;
        this.address = address;
    }
}
