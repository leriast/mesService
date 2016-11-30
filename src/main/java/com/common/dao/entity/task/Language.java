package com.common.dao.entity.task;

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
