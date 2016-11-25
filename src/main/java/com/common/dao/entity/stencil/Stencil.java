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

    private Duct duct;
}
