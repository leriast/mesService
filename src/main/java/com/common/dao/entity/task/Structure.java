package com.common.dao.entity.task;

import com.common.dao.entity.company.Company;
import com.common.dao.entity.stencil.Stencil;
import com.common.dao.entity.user.User;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;
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
    @Column(name = "name")
    private String name;
    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY")
    private Company company;
    @ManyToOne(targetEntity = Language.class)
    @JoinColumn(name = "ID_LANGUAGE", referencedColumnName = "ID_LANGUAGE")
    private Language language;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_STRUCTURE")
    private Set<Stencil> stencil;
    @Column(name = "algoritm")
    @Type(type = "com.common.dao.entity.usertype.WString")
    private String[] algoritm;
    @Column(name = "priority")
    private int priority;
    @Column(name = "params")
    private String params;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_creator", referencedColumnName = "ID_CONTACT_PERSON")
    private User creator;

    public Structure(Company company, Language language) {
        this.company = company;
        this.language = language;
    }

    public Structure(String name, Company company, Language language, Set<Stencil> stencil, String[] algoritm, int priority, String params, User creator) {
        this.name = name;
        this.company = company;
        this.language = language;
        this.stencil = stencil;
        this.algoritm = algoritm;
        this.priority = priority;
        this.params = params;
        this.creator = creator;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_structure() {
        return id_structure;
    }

    public void setId_structure(int id_structure) {
        this.id_structure = id_structure;
    }

    public Set<Stencil> getStencil() {
        return stencil;
    }

    public void setStencil(Set<Stencil> stencil) {
        this.stencil = stencil;
    }

    public String[] getAlgoritm() {
        return algoritm;
    }

    public void setAlgoritm(String[] algoritm) {
        this.algoritm = algoritm;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }


    @Override
    public String toString() {
        return "Structure{" +
                "id_structure=" + id_structure +
                ", name='" + name + '\'' +
                ", company=" + company +
                ", language=" + language +
                ", algoritm=" + Arrays.toString(algoritm) +
                ", priority=" + priority +
                ", params='" + params + '\'' +
                ", creator=" + creator +
                '}';
    }
}
