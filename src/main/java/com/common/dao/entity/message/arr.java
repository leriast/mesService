//package com.common.dao.entity.message;
//
//import org.hibernate.annotations.Type;
//
//import javax.persistence.*;
//
//
//@Entity
//@Table(name = "arr1")
//public class arr {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name="name")
//    @Type(type = "com.common.dao.entity.usertype.WString")
//    private String[] values;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String[] getArr() {
//        return values;
//    }
//
//    public void setArr(String[] values) {
//        this.values = values;
//    }
//}