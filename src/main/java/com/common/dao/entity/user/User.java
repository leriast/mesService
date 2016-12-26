package com.common.dao.entity.user;

import com.common.dao.entity.company.Company;
import com.common.dao.entity.stencil.Duct;
import com.common.dao.entity.stencil.Stencil;
import com.common.dao.entity.task.Language;
import com.common.dao.entity.task.Structure;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "CONTACT_PERSON")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CONTACT_PERSON")
    private int idUser;
    @NotEmpty
    //@Size(min = 5, max = 255)
    @Column(name = "USERNAME")
    private String username;
    @NotEmpty
    //@Size(min=5, max=255)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ENABLED")
    private boolean enabled;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "SECONDNAME")
    private String secondname;
    @Column(name = "DEPARTMENT")
    private String department;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", insertable = false)
    private Date create_date;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFY", insertable = false)
    private Date last_modify;
    @Column(name = "CREATOR")
    private String creator;
    @Column(name = "PRIORITY")
    private int priority;
    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY")
    private Company company;
    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE")
    private Role id_role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CREATOR")
    private Set<Language> languages;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CREATOR")
    private Set<Structure> structures;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CREATOR")
    private Set<Duct> ducts;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CREATOR")
    private Set<Stencil> stencils;

    public User(){}

    public User(String username, String password, boolean enabled, String firstname, String secondname, String department, Date create_date, Date last_modify, String creator, int priority) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.firstname = firstname;
        this.secondname = secondname;
        this.department = department;
        this.create_date = create_date;
        this.last_modify = last_modify;
        this.creator = creator;
        this.priority = priority;
    }

    public User(String username, String password, boolean enabled, String firstname, String secondname, String department, String creator, int priority, Role id_role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.firstname = firstname;
        this.secondname = secondname;
        this.department = department;
        this.creator = creator;
        this.priority = priority;
        this.id_role = id_role;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getLast_modify() {
        return last_modify;
    }

    public void setLast_modify(Date last_modify) {
        this.last_modify = last_modify;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Role getId_role() {
        return id_role;
    }

    public void setId_role(Role id_role) {
        this.id_role = id_role;
    }

    public User(String username, String password, boolean enabled, String firstname, String secondname, String department, Date create_date, Date last_modify, String creator, int priority, Company company, Role id_role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.firstname = firstname;
        this.secondname = secondname;
        this.department = department;
        this.create_date = create_date;
        this.last_modify = last_modify;
        this.creator = creator;
        this.priority = priority;
        this.company = company;
        this.id_role = id_role;
    }

    public User(String username, String password, boolean enabled, String department, String firstname, String secondname, String creator, int i, Company company, Role id_role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.firstname = firstname;
        this.secondname = secondname;
        this.creator = creator;
        this.id_role = id_role;
        this.department = department;
        this.company = company;
    }

}