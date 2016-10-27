package com.common.dao.entity.incoming;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by root on 10/13/16.
 */
public class IncomingTask   implements Serializable, Comparable<IncomingTask>{
    private int id;
    private Autor autor;
    private ArrayList<String> duct;
    private String messageStencil;
    private Date DepartureTime;
    private Date relevant;
    private String language;
    private int event;
    private ArrayList<Recipient> recipientList;
    private int priority;
    private int loop;


//    public IncomingTask(IncomingTask task){
//
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public ArrayList<String> getChanel() {
        return duct;
    }

    public void setChanel(ArrayList<String> duct) {
        this.duct = duct;
    }

    public String getMessageStencil() {
        return messageStencil;
    }

    public void setMessageStencil(String messageStencil) {
        this.messageStencil = messageStencil;
    }

    public Date getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(Date departureTime) {
        DepartureTime = departureTime;
    }

    public Date getRelevant() {
        return relevant;
    }

    public void setRelevant(Date relevant) {
        this.relevant = relevant;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public ArrayList<Recipient> getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(ArrayList<Recipient> recipientList) {
        this.recipientList = recipientList;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getLoop() {
        return loop;
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }

    public IncomingTask() {

    }

    public IncomingTask(Autor autor, ArrayList<String> duct, String messageStencil, Date departureTime, Date relevant, String language, int event, ArrayList<Recipient> recipientList, int priority, int loop) {
        this.autor = autor;
        this.duct = duct;
        this.messageStencil = messageStencil;
        DepartureTime = departureTime;
        this.relevant = relevant;
        this.language = language;
        this.event = event;
        this.recipientList = recipientList;
        this.priority = priority;
        this.loop = loop;
    }

    @Override
    public String toString() {
        return "IncomingTask{" +
                "id=" + id +
                ", autor=" + autor +
                ", duct=" + duct +
                ", messageStencil='" + messageStencil + '\'' +
                ", DepartureTime=" + DepartureTime +
                ", relevant=" + relevant +
                ", language='" + language + '\'' +
                ", event=" + event +
                ", recipientList=" + recipientList +
                ", priority=" + priority +
                ", loop=" + loop +
                '}';
    }
    @Override
    public int compareTo(IncomingTask o) {
        if(o.priority>priority) return -1;
        if(priority>o.priority) return 1;
        return 0;
    }
}