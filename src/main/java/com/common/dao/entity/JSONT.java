package com.common.dao.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by root on 11/23/16.
 */
//@TypeDefs( {@TypeDef( name= "StringJsonObject", typeClass = StringJsonUserType.class)})
@Entity
@Table(name = "jsonT")
public class JSONT implements Serializable,Comparable<JSONT>{
    public JSONT(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;
 //   @Type(type = "hstore")
    @Column(name="info"/*, columnDefinition="hstore"*/)
    private String json;

    public JSONT(int id,String json){
        this.id=id;
        this.json=json;
    }
    public JSONT(String json) {
        this.json=json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//    @Type(type = "StringJsonObject")
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public int compareTo(JSONT o) {
        return 0;
    }
}
