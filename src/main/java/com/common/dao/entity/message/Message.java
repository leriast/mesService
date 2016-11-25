package com.common.dao.entity.message;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Message")
public class Message implements Serializable,Comparable<Message>{



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMessage")
    private int idMessage;
    @Column(name = "priority")
    private int priority;
    @Column(name = "frequence")
    private Date frequence;
    @Column(name = "departureTime")
    private Date departureTime;
    @Column(name = "relevantTime")
    private Date relevantTime;
    @Column(name = "delay")
    private Date delay;
    @Column(name = "duct")
    @Type(type = "com.common.dao.entity.usertype.WString")
    private String[] duct;
    @Column(name = "message")
    private String message;
    @Column(name = "address")
    private String address;

    public Message(int priority, Date frequence, Date departureTime, Date relevantTime, Date delay, String[] duct, String message, String address) {
        this.priority = priority;
        this.frequence = frequence;
        this.departureTime = departureTime;
        this.relevantTime = relevantTime;
        this.delay = delay;
        this.duct = duct;
        this.message = message;
        this.address = address;
    }

    public Message(Date frequence, Date departureTime, Date relevantTime, Date delay, String[] duct, String message, String address) {
        this.frequence = frequence;
        this.departureTime = departureTime;
        this.relevantTime = relevantTime;
        this.delay = delay;
        this.duct = duct;
        this.message = message;
        this.address = address;
    }
    public Message(){}

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getFrequence() {
        return frequence;
    }

    public void setFrequence(Date frequence) {
        this.frequence = frequence;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getRelevantTime() {
        return relevantTime;
    }

    public void setRelevantTime(Date relevantTime) {
        this.relevantTime = relevantTime;
    }

    public Date getDelay() {
        return delay;
    }

    public void setDelay(Date delay) {
        this.delay = delay;
    }

    public String[] getDuct() {
        return duct;
    }

    public void setDuct(String[] duct) {
        this.duct = duct;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int compareTo(Message o) {
        if(o.priority>priority) return -1;
        if(priority>o.priority) return 1;
        return 0;
    }
}