package com.common.dao.entity.user;

import javax.persistence.*;

/**
 * Created by root on 11/18/16.
 */
@Entity
@Table(name = "D_CONTACT_TYPE")
public class ContactsDictonary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CONTACT_TYPE")
    private int id;

    @Column(name = "NAME")
    private String name;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CONTACT_CONTACT_PERSON")
    private Set<Contacts> contacts;*/


    public ContactsDictonary() {
    }

    public ContactsDictonary(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
