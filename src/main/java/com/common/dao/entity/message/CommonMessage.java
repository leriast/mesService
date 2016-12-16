package com.common.dao.entity.message;

import com.common.dao.entity.task.Task;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by root on 12/12/16.
 */
@Table(name = "common_message")
@Entity
public class CommonMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMessage")
    private Long idMessage;
    @Column(name = "priority")
    private int priority;
    @Column(name = "departureTime")
    private Date departureTime;
    @Column(name = "relevantTime")
    private Date relevantTime;
    @Column(name = "duct")
    @Type(type = "com.common.dao.entity.usertype.WString")
    private String[] duct;
    @Column(name = "message")
    private String message;
    @Column(name = "stencil")
    private String stencil;
    @Column(name = "address")
    private String address;
    @ManyToOne(targetEntity = Task.class)
    @JoinColumn(name = "ID_TASK", referencedColumnName = "ID_TASK")
    private Task id_task;
    @Column(name = "status")
    private int status;
    @Column(name = "params")
    private String params;
    @Column(name = "statistic")
    private String statistic;


    public CommonMessage(int priority, Date departureTime, Date relevantTime,  String[] duct,String stencil, String message, String address,Task task,int status,String params,String statistic) {
        this.priority = priority;
        this.departureTime = departureTime;
        this.relevantTime = relevantTime;

        this.duct = duct;
        this.message = message;
        this.address = address;
        this.id_task=task;
        this.status=status;
        this.stencil=stencil;
        this.params=params;
        this.statistic=statistic;
    }

    public CommonMessage( Date departureTime, Date relevantTime,  String[] duct,String stencil, String message, String address,Task task,int status,String params,String statistic) {

        this.departureTime = departureTime;
        this.relevantTime = relevantTime;
        this.duct = duct;
        this.message = message;
        this.address = address;
        this.id_task=task;
        this.status=status;
        this.stencil=stencil;
        this.params=params;
        this.statistic=statistic;
    }
    public CommonMessage(){}



    public String getStatistic() {
        return statistic;
    }

    public void setStatistic(String statistic) {
        this.statistic = statistic;
    }

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

    public String getStencil() {
        return stencil;
    }

    public void setStencil(String stencil) {
        this.stencil = stencil;
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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

}
