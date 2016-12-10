package com.common.dao.entity.stencil;

import com.common.dao.entity.task.Structure;

import javax.persistence.*;

/**
 * Created by root on 11/18/16.
 */
@Entity
@Table(name = "STENCIL")
public class Stencil {
    public Stencil (){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_STENCIL")
    private int id;
    @Column(name = "STENCIL_ENTITY")
    private String stencil_entity;

    @ManyToOne(targetEntity = Structure.class)
    @JoinColumn(name = "ID_STRUCTURE", referencedColumnName = "ID_STRUCTURE")
    private Structure structure;

    @ManyToOne(targetEntity = Duct.class)
    @JoinColumn(name = "ID_D_DUCT", referencedColumnName = "ID_D_DUCT")
    private Duct duct;

    public Stencil(String stencil_entity, Structure structure, Duct duct) {
        this.stencil_entity = stencil_entity;
        this.structure = structure;
        this.duct = duct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStencil_entity() {
        return stencil_entity;
    }

    public void setStencil_entity(String stencil_entity) {
        this.stencil_entity = stencil_entity;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Duct getDuct() {
        return duct;
    }

    public void setDuct(Duct duct) {
        this.duct = duct;
    }
}
