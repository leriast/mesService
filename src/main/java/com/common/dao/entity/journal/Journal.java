package com.common.dao.entity.journal;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by root on 12/23/16.
 */
@Entity
@Table(name = "JOURNAL")
public class Journal {
    public Journal(){}
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "EVENT_TIME")
    private Date time;
    @Column(name = "STATUS")
    private int status;
    @Column(name = "ID_TASK")
    private long taskId;
    @Column(name = "priority")
    private int priority;

    public Journal(long id, Date time, int status, long taskId) {
        this.id = id;
        this.time = time;
        this.status = status;
        this.taskId = taskId;
    }

    public Journal(Date time, int status,int priority, long taskId) {
        this.time = time;
        this.status = status;
        this.taskId = taskId;
        this.priority=priority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
