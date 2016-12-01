package com.common.dao.entity.stencil;

import javax.persistence.*;

/**
 * Created by root on 11/18/16.
 */
//@Entity
//@Table(name = "STENCIL")
public class Stencil {
    public Stencil (){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_STENCIL")
    private int id;
    @Column(name = "STENCIL_ENTITY")
    private String stencil_entity;
//    @OneToMany(targetEntity = Duct.class)
//    @JoinColumn(name = "ID_D_DUCT", referencedColumnName = "ID_D_DUCT")
//    private Duct duct;
//    @OneToMany(targetEntity = Structure.class)
//    @JoinColumn(name = "ID_STRUCTURE", referencedColumnName = "IDSTRUCTURE")
//    private Structure structure;
//
//
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "ID_USER")
//    private Set<User> user;
}
