package com.common.dao.entity.task;

import com.common.dao.entity.user.User;
import org.apache.commons.lang3.SerializationUtils;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by root on 11/28/16.
 */

/*@Type(type = "hstore")
@Column(name = "tags", columnDefinition = "hstore")*/


//@org.hibernate.annotations.TypeDef(name = "MyJsonType", typeClass = MyJsonType.class)
@Entity
@Table(name = "task")
public class Task /*implements Serializable*/{
    public Task(){}

@Id
@Column(name = "ID_TASK")
@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "ID_CONTACT_PERSON", referencedColumnName = "ID_CONTACT_PERSON")
    private User user;

    @ManyToOne(targetEntity = Structure.class)
    @JoinColumn(name = "ID_STRUCTURE", referencedColumnName = "ID_STRUCTURE")
    private Structure structure;

    @Column(name = "algoritm")
    @Type(type = "com.common.dao.entity.usertype.WString")
    private String[] algoritm;

    @Column(name = "variables")
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] varibles;

    @Column(name = "priority")
    private int priority;


    @ManyToOne(targetEntity = Language.class)
    @JoinColumn(name = "ID_LANGUAGE", referencedColumnName = "ID_LANGUAGE")
    private Language language;

    @Column(name = "params")
//    @Type(type = "MyJsonType")
   private String params;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public String[] getAlgoritm() {
        return algoritm;
    }

    public void setAlgoritm(String[] algoritm) {
        this.algoritm = algoritm;
    }


    /*@Type(type = "com.common.dao.entity.usertype.WByte")*/
    public  byte[] getVaribles() throws IOException, ClassNotFoundException {
        return varibles;
    }

    public void setVaribles( byte[] varibles) {
        this.varibles = varibles;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Task(User user, Structure structure, String[] algoritm, String varibles, int priority, Language language, String params) {
        this.user = user;
        this.structure = structure;
        this.algoritm = algoritm;
        this.varibles = SerializationUtils.serialize(varibles);
        this.priority = priority;
        this.language = language;
        this.params = params;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", user=" + user +
                ", structure=" + structure +
                ", algoritm=" + Arrays.toString(algoritm) +
                ", varibles='" + varibles + '\'' +
                ", priority=" + priority +
                ", language=" + language.getName() +
                ", params='" + params + '\'' +
                '}';
    }
}
