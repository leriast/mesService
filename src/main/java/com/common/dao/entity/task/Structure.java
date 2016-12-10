package com.common.dao.entity.task;

import com.common.dao.entity.company.Company;
import com.common.dao.entity.stencil.Stencil;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by root on 11/28/16.
 */
@Entity
@Table(name = "structure")
public class Structure {
    public Structure(){}
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID_STRUCTURE")
    private int id_structure;
    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY")
    private Company company;
    @ManyToOne(targetEntity = Language.class)
    @JoinColumn(name = "ID_LANGUAGE", referencedColumnName = "ID_LANGUAGE")
    private Language language;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_STRUCTURE")
    private Set<Stencil> stencil;
    public Structure(Company company, Language language) {
        this.company = company;
        this.language = language;
    }

    public int getId() {
        return id_structure;
    }

    public void setId(int id) {
        this.id_structure = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
