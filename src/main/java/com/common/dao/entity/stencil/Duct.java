package com.common.dao.entity.stencil;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by root on 11/18/16.
 */
@Entity
@Table(name = "D_DUCT")
public class Duct {
    public Duct(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_D_DUCT")
    private int id;
    @Column(name = "name_duct")
    private String name;
    @Column(name = "DUCT_PRIORITY")
    private int duct_priority;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_D_DUCT")
    private Set<Stencil> stencil;

    public Duct(String name, int duct_priority, Set<Stencil> stencil) {
        this.name = name;
        this.duct_priority = duct_priority;
        this.stencil = stencil;
    }

    public Duct(String name, int duct_priority) {
        this.name = name;
        this.duct_priority = duct_priority;
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

    public int getDuct_priority() {
        return duct_priority;
    }

    public void setDuct_priority(int duct_priority) {
        this.duct_priority = duct_priority;
    }

    public Set<Stencil> getStencil() {
        return stencil;
    }

    public void setStencil(Set<Stencil> stencil) {
        this.stencil = stencil;
    }
}
