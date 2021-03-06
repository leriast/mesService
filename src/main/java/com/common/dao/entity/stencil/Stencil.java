package com.common.dao.entity.stencil;

import com.common.dao.entity.task.Structure;
import com.common.dao.entity.user.User;

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
    @Column(name = "name")
    private String name;
    @Column(name = "STENCIL_ENTITY")
    private String stencil_entity;

    @ManyToOne(targetEntity = Structure.class)
    @JoinColumn(name = "ID_STRUCTURE", referencedColumnName = "ID_STRUCTURE")
    private Structure structure;

    @ManyToOne(targetEntity = Duct.class)
    @JoinColumn(name = "ID_D_DUCT", referencedColumnName = "ID_D_DUCT")
    private Duct duct;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_creator", referencedColumnName = "ID_CONTACT_PERSON")
    private User creator;

    public Stencil(String name, String stencil_entity, Structure structure, Duct duct, User creator) {
        this.name = name;
        this.stencil_entity = stencil_entity;
        this.structure = structure;
        this.duct = duct;
        this.creator = creator;
    }

    public Stencil(String stencil_entity, Structure structure, Duct duct) {
        this.stencil_entity = stencil_entity;
        this.structure = structure;
        this.duct = duct;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
