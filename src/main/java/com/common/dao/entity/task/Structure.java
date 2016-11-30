package com.common.dao.entity.task;

import com.common.dao.entity.company.Company;

import javax.persistence.*;

/**
 * Created by root on 11/28/16.
 */
@Entity
@Table(name = "structure")
public class Structure {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID_STRUCTURE")
    private int id;
    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY")
    private Company company;
    @ManyToOne(targetEntity = Language.class)
    @JoinColumn(name = "ID_LANGUAGE", referencedColumnName = "ID_LANGUAGE")
    private Language language;
}
