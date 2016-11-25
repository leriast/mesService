package com.common.dao.entity.user;

import com.common.dao.entity.company.Company;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by root on 11/18/16.
 */
@Entity
@Table(name = "CONTACTS_CONTACT_PERSON")
public class Contacts {
    public Contacts(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CONTACT_CONTACT_PERSON")
    private int id;
    @NotEmpty
    @Column(name = "PRIORITY_CALLBACK")
    private int priority;
    //@Column(name = "ID_CONTACT_PERSON")
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "ID_CONTACT_PERSON", referencedColumnName = "ID_CONTACT_PERSON")
    private User user;
   // private int id_person;
   @ManyToOne(targetEntity = Company.class)
   @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY")
   private Company company;

    @ManyToOne(targetEntity = ContactsDictonary.class)
    @JoinColumn(name = "ID_CONTACT_TYPE", referencedColumnName = "ID_CONTACT_TYPE")
    private ContactsDictonary contactDictonary;

    public Contacts(int priority, User user, Company company, ContactsDictonary contactDictonary) {
        this.priority = priority;
        this.user = user;
        this.company = company;
        this.contactDictonary = contactDictonary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ContactsDictonary getContactDictonary() {
        return contactDictonary;
    }

    public void setContactDictonary(ContactsDictonary contactDictonary) {
        this.contactDictonary = contactDictonary;
    }
}
