package com.common.dao.entity.task;

import com.common.dao.entity.user.User;

import javax.persistence.*;

/**
 * Created by root on 11/28/16.
 */
@Entity
@Table(name = "language")
public class Language {
    public Language(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_language")
    private int id;
    @Column(name = "name")
    private String name;


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_creator", referencedColumnName = "ID_CONTACT_PERSON")
    private User creator;



//    @Column(name = "id_creator")
//    private User creator;

    public Language(String name, User creator) {
        this.name = name;
        this.creator = creator;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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

    public Language(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
