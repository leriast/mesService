package com.common.dao.entity.message;

import com.common.dao.entity.task.Task;
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
    private Long idMessage;
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
    @ManyToOne(targetEntity = Task.class)
    @JoinColumn(name = "ID_TASK", referencedColumnName = "ID_TASK")
    private Task id_task;
    @Column(name = "status")
    private int status;

    public Message(int priority, Date frequence, Date departureTime, Date relevantTime, Date delay, String[] duct, String message, String address,Task task,int status) {
        this.priority = priority;
        this.frequence = frequence;
        this.departureTime = departureTime;
        this.relevantTime = relevantTime;
        this.delay = delay;
        this.duct = duct;
        this.message = message;
        this.address = address;
        this.id_task=task;
        this.status=status;
    }

    public Message(Date frequence, Date departureTime, Date relevantTime, Date delay, String[] duct, String message, String address,Task task,int status) {
        this.frequence = frequence;
        this.departureTime = departureTime;
        this.relevantTime = relevantTime;
        this.delay = delay;
        this.duct = duct;
        this.message = message;
        this.address = address;
        this.id_task=task;
        this.status=status;
    }
    public Message(){}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Task getId_task() {
        return id_task;
    }

    public void setId_task(Task id_task) {
        this.id_task = id_task;
    }

    public Long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Long idMessage) {
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